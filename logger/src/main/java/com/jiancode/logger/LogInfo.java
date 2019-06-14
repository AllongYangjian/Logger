package com.jiancode.logger;

/**
 * author：yangjian
 * email：yj@allong.net
 * data：2019-06-0615:06
 * desc：日志信息实体类，统一对日志进行管理
 * version：1.0
 */
public final class LogInfo {
    /**
     * 日志产生时间
     */
    private String time;
    /**
     * 日志级别
     */
    private int level;
    /**
     * 日志tag
     */
    private String tag;
    /**
     * 日志内容
     */
    private String msg;

    /**
     * 日志对应床号
     */
    private String bed;
    /**
     * 日志路径
     */
    private String path;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "time='" + time + '\'' +
                ", level=" + level +
                ", tag='" + tag + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
