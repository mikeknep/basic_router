package com.mikeknep.basic_router.builders;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PostRequestResponseBuilderTest {
    PostRequestResponseBuilder builder;
    @Before
    public void instantiateBuilder() {
        builder = new PostRequestResponseBuilder("public/", "/form", "foo=bar");
    }

    @Test
    public void itReturns303Status() {
        assertEquals("200 OK", builder.getStatus());
    }

    @Test
    public void itReturnsHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Location", "http://localhost:5000/public/form");

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsBlankBody() {
        assertArrayEquals("".getBytes(), builder.getBody());
    }

    @Test
    public void itWritesBodyToAFile() throws Exception {
        byte[] body = Files.readAllBytes(Paths.get("public/form"));

        assertArrayEquals("foo = bar".getBytes(), body);
    }
}