package com.mikeknep.basic_router.builders;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PutRequestResponseBuilderTest {
    PutRequestResponseBuilder builder;
    @Before
    public void instantiateBuilder() {
        builder = new PutRequestResponseBuilder("public/", "/form", "My=CherieAmour");
    }

    @Test
    public void itReturns200Status() {
        assertEquals("200 OK", builder.getStatus());
    }

    @Test
    public void itReturnsAppropriateHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Location", "/form");

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsBlankBody() {
        assertArrayEquals("".getBytes(), builder.getBody());
    }

    @Test
    public void itAppendsToAFile() throws Exception {
        byte[] fileBytes = Files.readAllBytes(Paths.get("public/form"));
        String content = new String(fileBytes, "UTF-8");
        assertTrue(content.contains("My = CherieAmour"));
    }
}