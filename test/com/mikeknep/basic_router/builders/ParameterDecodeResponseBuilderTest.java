package com.mikeknep.basic_router.builders;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ParameterDecodeResponseBuilderTest {
    ParameterDecodeResponseBuilder builder;
    @Before
    public void instantiateBuilder() {
        builder = new ParameterDecodeResponseBuilder("/parameters?foo=bar&foofoo=%22bar%20bar%22");
    }

    @Test
    public void itReturnsRawParameters() {
        assertEquals("foo=bar&foofoo=%22bar%20bar%22", builder.getRawParams());
    }

    @Test
    public void itReturns200Status() {
        assertEquals("200 OK", builder.getStatus());
    }

    @Test
    public void itReturnsBlankHeaders() {
        assertEquals(new HashMap<String, String>(), builder.getHeaders());
    }

    @Test
    public void itReturnsBodyWithDecodedParams() {
        assertArrayEquals("foo = bar\nfoofoo = \"bar bar\"\n".getBytes(), builder.getBody());
    }
}