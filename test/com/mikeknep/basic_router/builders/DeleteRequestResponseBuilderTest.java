package com.mikeknep.basic_router.builders;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DeleteRequestResponseBuilderTest {
    DeleteRequestResponseBuilder builder;
    File tempFile;
    @Before
    public void instantiateBuilder() {
        tempFile = new File("public/temp");
        builder = new DeleteRequestResponseBuilder("public/", "/form");
    }

    @Test
    public void itReturns200Status() {
        assertEquals("200 OK", builder.getStatus());
    }

    @Test
    public void itReturnsBlankHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsEmptyBody() {
        assertArrayEquals("".getBytes(), builder.getBody());
    }

    @Test
    public void itDeletesAFile() {
        assertFalse(Files.exists(Paths.get("public/temp")));
    }
}