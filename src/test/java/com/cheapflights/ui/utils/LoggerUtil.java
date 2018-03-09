package com.cheapflights.ui.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class LoggerUtil {
    private static Logger logger = LogManager.getRootLogger();

    public static void error(String msg) {
        logger.error(msg);
    }

    public static void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void warn(String msg) {
        logger.warn(msg);
    }

    public static void trace(String msg) {
        logger.trace(msg);
    }
}
