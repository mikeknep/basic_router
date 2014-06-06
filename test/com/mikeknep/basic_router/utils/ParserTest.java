package com.mikeknep.basic_router.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    Parser parser;
    @Before
    public void instantiateParser() {
        String[] args = {"test/sample_files", "GET", "/mock.html"};
        parser = new Parser(args);
    }

    @Test
    public void itGetsRootDirectory() {
        assertEquals("test/sample_files", parser.getRootDirectory());
    }

    @Test
    public void itGetsMethod() {
        assertEquals("GET", parser.getMethod());
    }

    @Test
    public void itGetsRequestedResource() {
        assertEquals("/mock.html", parser.getRequestedResource());
    }
}