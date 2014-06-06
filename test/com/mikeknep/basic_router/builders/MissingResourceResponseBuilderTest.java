package com.mikeknep.basic_router.builders;

import com.mikeknep.basic_router.builders.MissingResourceResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class MissingResourceResponseBuilderTest {
    MissingResourceResponseBuilder yes404Builder;
    MissingResourceResponseBuilder no404Builder;
    @Before
    public void instantiateMissingResourceResponseBuilder() {
        yes404Builder = new MissingResourceResponseBuilder("test/sample_files/");
        no404Builder = new MissingResourceResponseBuilder("test/");
    }


    @Test
    public void itReturns404Status() {
        assertEquals("404 Not Found", yes404Builder.getStatus());
    }

    @Test
    public void itReturnsHTMLContentTypeWhen404Provided() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "text/html");

        assertEquals(expectedHeaders, yes404Builder.getHeaders());
    }

    @Test
    public void itReturnsPlainTextContentTypeWhen404NotProvided() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "text/plain");

        assertEquals(expectedHeaders, no404Builder.getHeaders());
    }

    @Test
    public void itReturns404FileBody() {
        assertArrayEquals("404 html file".getBytes(), yes404Builder.getBody());
    }

    @Test
    public void itReturns404PlainTextBody() {
        assertArrayEquals("404".getBytes(), no404Builder.getBody());
    }
}