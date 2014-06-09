package com.mikeknep.basic_router.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArgsParserTest {
    ArgsParser parser;
    @Before
    public void instantiateParser() {
        String[] args = {"test/sample_files", "GET", "/mock.html", "body"};
        parser = new ArgsParser(args);
    }

    @Test
    public void itGetsRootDirectory() {
        assertEquals("test/sample_files", parser.getRootDirectory());
    }
}