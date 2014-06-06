package com.mikeknep.basic_router.builders;

import com.mikeknep.basic_router.builders.OptionsResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class OptionsResponseBuilderTest {
    OptionsResponseBuilder builder;
    @Before
    public void instantiateBuilder() {
        builder = new OptionsResponseBuilder();
    }

    @Test
    public void itReturns200OKStatus() {
        assertEquals("200 OK", builder.getStatus());
    }

    @Test
    public void itReturnsAllowedMethodsHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsBlankBody() {
        assertArrayEquals("".getBytes(), builder.getBody());
    }
}