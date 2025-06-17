package com.crawler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String LOG_FORMAT = "[%s] [%s] %s";

    public enum Level {
        INFO, WARN, ERROR
    }

    public static void info(String message) {
        log(Level.INFO, message);
    }

    public static void warn(String message) {
        log(Level.WARN, message);
    }

    public static void error(String message) {
        log(Level.ERROR, message);
    }

    public static void error(String message, Throwable throwable) {
        log(Level.ERROR, message + ": " + throwable.getMessage());
    }

    private static void log(Level level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format(LOG_FORMAT, timestamp, level, message));
    }
} 