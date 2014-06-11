package com.mikeknep.basic_router.utils;

/**
 * Created by mrk on 6/11/14.
 */
public class Logger {
    private static String log = "";

    public static String getLog() {
        return log;
    }

    public static void addToLog(String string) {
        StringBuilder builder = new StringBuilder(getLog());
        builder.append(string);
        log = builder.toString();
    }
}
