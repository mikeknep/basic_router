package com.mikeknep.basic_router.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestDataCollectorTest {
    RequestDataCollector collector;
    @Before
    public void instantiateCollector() throws Exception {
        String mockMethod = "GET";
        String mockResource = "/";
        HashMap<String, String> mockHeaders = new HashMap<String, String>();
        String mockBody = "body";
        IncomingStream mockIncomingStream = new MockIncomingStream(mockMethod, mockResource, mockHeaders, mockBody);
        collector = new RequestDataCollector(mockIncomingStream);
    }

    @Test
    public void itGetsMethod() {
        assertEquals("GET", collector.getMethod());
    }

    @Test
    public void itGetsResource() {
        assertEquals("/", collector.getRequestedResource());
    }

    @Test
    public void itGetsHeaders() {
        assertEquals(new HashMap<String, String>(), collector.getHeaders());
    }

    @Test
    public void itGetsBody() {
        assertEquals("body", collector.getBody());
    }
}