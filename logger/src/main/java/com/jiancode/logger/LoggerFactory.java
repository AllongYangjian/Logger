package com.jiancode.logger;

/**
 * author：yangjian
 * email：yj@allong.net
 * data：2019-06-0615:59
 * desc：
 * version：1.0
 */
public class LoggerFactory {

    public static Logger getLogger(Class clazz) {
        return new Logger(clazz);
    }



}
