package com.flab.bbt.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

}