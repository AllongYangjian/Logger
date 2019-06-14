package com.jiancode.logger;

/**
 * author：yangjian
 * email：yj@allong.net
 * data：2019-06-0615:11
 * desc：日志类型
 * version：1.0
 */
public enum LogType {
    INFO(1, "info.txt"),
    DEBUG(2, "debug.txt"),
    WARN(3, "warn.txt"),
    ERROR(4, "error.txt"),
    GUAGOU(5, "guagou.txt"),
    SERIAL(6, "serial.txt"),
    HJ(7, "hj.txt"),
    SY(8, "shuye.txt"),
    OTHER(9, "other.txt"),
    UNKNOWN(10,"unknown.txt");

    private int type;

    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByType(int type) {
        if(type == INFO.type){
            return INFO.name;
        }else if(type == DEBUG.type){
            return DEBUG.name;
        }else if(type == WARN.type){
            return WARN.name;
        }else if(type == ERROR.type){
            return ERROR.name;
        }else if(type == GUAGOU.type){
            return GUAGOU.name;
        }else if(type == SERIAL.type){
            return SERIAL.name;
        }else if(type == HJ.type){
            return HJ.name;
        }else if(type == SY.type){
            return SY.name;
        }else if (type == OTHER.type){
            return OTHER.name;
        }else {
            return UNKNOWN.name;
        }
    }

    LogType(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
