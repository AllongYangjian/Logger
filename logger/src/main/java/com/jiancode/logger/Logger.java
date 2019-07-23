package com.jiancode.logger;

import android.util.Log;

/**
 * author：yangjian
 * email：yj@allong.net
 * data：2019-06-0615:58
 * desc：对外工厂类
 * version：1.0
 */
public class Logger {

    private String tag;

    Logger(Class clazz) {
        tag = clazz.getSimpleName();
    }

    public void info(String msg) {
        log(LogType.INFO, "", msg);
    }

    public void debug(String msg) {
        log(LogType.DEBUG, "", msg);
    }

    public void warn(String msg) {
        log(LogType.WARN, "", msg);
    }

    public void error(String msg) {
        log(LogType.ERROR, "", msg);
    }

    public void hj(String bedno, String msg) {
        log(LogType.HJ, bedno, msg);
    }

    public void shuye(String bedno, String msg) {
        log(LogType.SY, bedno, msg);
    }

    public void guagou(String bedno, String msg) {
        log(LogType.GUAGOU, bedno, msg);
    }

    public void serial(String msg) {
        log(LogType.SERIAL, "", msg);
    }

    public void other(String msg) {
        log(LogType.OTHER, "", msg);
    }

    public void unknown(String msg) {
        log(LogType.UNKNOWN, "", msg);
    }

    public void alarm(String msg){
        log(LogType.EMERGENCY, "emergency", msg);
    }

    private void log(LogType type, String bed, String msg) {
        LogInfo info = new LogInfo();
        info.setLevel(type.getType());
        info.setMsg(msg);
        info.setTag(tag);
        info.setBed(bed);
        info.setTime(LoggerManager.getInstance().getCurrentDateStr());
        LoggerManager.getInstance().put(info);

        if (LoggerManager.getInstance().isDebug()) {
            Log.e(tag, msg);
        }
    }
}
