package com.mikeknep.basic_router.builders;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PartialContentResponseBuilderTest {
    PartialContentResponseBuilder builder;
    @Before
    public void instantiateBuilder() {
        builder = new PartialContentResponseBuilder("public/", "Key: Value\r\nRange: bytes=0-3\r\nOtherKey: OtherValue", "mock.html");
    }

    @Test
    public void itReturns206Status() {
        assertEquals("206 Partial Content", builder.getStatus());
    }

    @Test
    public void itReturnsHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Range", "0-3");

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsPartialContentBody() {
        assertArrayEquals("foo".getBytes(), builder.getBody());
    }
}