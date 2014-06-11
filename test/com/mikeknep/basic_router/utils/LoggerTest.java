package com.mikeknep.basic_router.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerTest {
    @Test
    public void itAddsToLogField() {
        Logger.addToLog("Yolo");
        assertTrue(Logger.getLog().contains("Yolo"));
        Logger.addToLog("Bogo");
        assertTrue(Logger.getLog().contains("YoloBogo"));
    }
}