package com.mikeknep.basic_router.builders;

import com.mikeknep.basic_router.builders.BadRequestResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BadRequestResponseBuilderTest {
    BadRequestResponseBuilder builder;
    @Before
    public void instantiateBadRequestResponseBuilder() {
        builder = new BadRequestResponseBuilder();
    }

    @Test
    public void itReturns400BadRequestStatus() {
        assertEquals("400 Bad Request", builder.getStatus());
    }

    @Test
    public void itReturnsPlainTextContentType() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "text/plain");

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsBlankBody() {
        assertArrayEquals("".getBytes(), builder.getBody());
    }
}
