package com.mikeknep.basic_router.builders;

import com.mikeknep.basic_router.builders.RedirectResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RedirectResponseBuilderTest {
    RedirectResponseBuilder builder;
    @Before
    public void instantiateBuilder() {
        builder = new RedirectResponseBuilder();
    }

    @Test
    public void itReturns301RedirectStatus() {
        assertEquals("301 Moved Permanently", builder.getStatus());
    }

    @Test
    public void itSetsLocationInHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Location", "/");
    }

    @Test
    public void itProvidesABasicBody() {
        assertArrayEquals("This page has moved.".getBytes(), builder.getBody());
    }
}