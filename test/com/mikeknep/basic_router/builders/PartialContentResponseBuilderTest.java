package com.mikeknep.basic_router.builders;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PartialContentResponseBuilderTest {
    PartialContentResponseBuilder builder;
    @Before
    public void instantiateBuilder() {
        String directory = "public/";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Key", "Value");
        headers.put("Range", "bytes=0-3");
        String resource = "mock.html";
        builder = new PartialContentResponseBuilder(directory, headers, resource);
    }

    @Test
    public void itReturns206Status() {
        assertEquals("206 Partial Content", builder.getStatus());
    }

    @Test
    public void itReturnsHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Range", "bytes 0-3/" + String.valueOf(new File("public/mock.html").length()));
        expectedHeaders.put("Content-Length", "3");

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsPartialContentBody() {
        assertArrayEquals("foo".getBytes(), builder.getBody());
    }
}