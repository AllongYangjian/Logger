package com.jiancode.logger;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * author：yangjian
 * email：yj@allong.net
 * data：2019-06-0616:12
 * desc：日志管理类
 * version：1.0
 */
public class LoggerManager {

    private Logger logger = LoggerFactory.getLogger(LoggerManager.class);

    private ThreadLocal<ExecutorService> threadLocal;

    private static LoggerManager instance;

    private LinkedBlockingQueue<LogInfo> blockingQueue;

    private LogRunnable mRunnable;

    private boolean isRun = true;

    private SimpleDateFormat sdf1;

    private SimpleDateFormat sdf2;

    private Context mContext;

    private boolean debug;

    public boolean isDebug() {
        return debug;
    }

    public LoggerManager setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    private LoggerManager() {
        blockingQueue = new LinkedBlockingQueue<>();
        mRunnable = new LogRunnable();
        threadLocal = new ThreadLocal<>();
        sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sdf2 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    }

    /**
     * 日志管理类的初始化方法，只能调用一次
     */
    public LoggerManager init(Context mContext) {
        this.mContext = mContext;
        if (threadLocal.get() != null) {
            logger.warn("this method can only by called one time");
        } else {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(mRunnable);
            threadLocal.set(executorService);
        }
        return this;
    }

    public static LoggerManager getInstance() {
        if (instance == null) {
            synchronized (LoggerManager.class) {
                if (instance == null) {
                    instance = new LoggerManager();
                }
            }
        }
        return instance;
    }


    private class LogRunnable implements Runnable {
        @Override
        public void run() {
            while (isRun) {
                LogInfo info = null;
                try {
                    info = blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (info != null) {
                    printLog(info);
                } else {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 打印日志
     *
     * @param info info
     */
    private void printLog(LogInfo info) {
        isFileExist(info);
        writeFileToSdcard(info);
    }

    private void isFileExist(LogInfo info) {
        String path = getLogPath();
        if (!TextUtils.isEmpty(info.getBed())) {
            path += File.separator + info.getBed();
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        File logFile = new File(file, LogType.getNameByType(info.getLevel()));
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        info.setPath(logFile.getPath());
    }

    private void writeFileToSdcard(LogInfo info) {
        File file = new File(info.getPath());
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");

            long fileLength = randomAccessFile.length();
            randomAccessFile.seek(fileLength);

            String content = String.format(Locale.getDefault(), "【%s】%s ：%s", info.getTime(), info.getTag(), info.getMsg()) + System.getProperty("line.separator");
            randomAccessFile.write(content.getBytes("UTF-8"));
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取今日日志文件路径
     *
     * @return 日志文件根路径 /mnt/sdcard/companyName/packageName/log/yyyymmdd
     */
    public String getLogPath() {
        return getRootLogPath() + File.separator + getCurrentYMDStr();
    }

    /**
     * 获取日志文件根路径
     *
     * @return 日志文件根路径 /mnt/sdcard/companyName/packageName/log/
     */
    public String getRootLogPath() {
        String rootPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "Allong" + File.separator
                + mContext.getPackageName();
        return rootPath + File.separator + "log";
    }

    /**
     * 获取日志文件所在文件夹
     * @return {@link File}
     */
    public File getRootLogDirtory() {
        return new File(getRootLogPath());
    }

    /**
     * 获取时间
     *
     * @return current time yyyy-MM-dd HH:mm:ss
     */
    String getCurrentDateStr() {
        return sdf1.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取日期
     *
     * @return yyyyMMdd
     */
    private String getCurrentYMDStr() {
        return sdf2.format(new Date());
    }

    /**
     * 退出日志线程
     */
    public void exit() {
        isRun = false;
        if (threadLocal.get() != null) {
            threadLocal.get().shutdown();
        }
    }

    /**
     * 存放消息队列
     *
     * @param info
     */
    synchronized void put(LogInfo info) {
        try {
            blockingQueue.put(info);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
