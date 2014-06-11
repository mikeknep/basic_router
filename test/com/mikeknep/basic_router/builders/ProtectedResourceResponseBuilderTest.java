package com.mikeknep.basic_router.builders;

import static javax.xml.bind.DatatypeConverter.printBase64Binary;

import com.mikeknep.basic_router.utils.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ProtectedResourceResponseBuilderTest {
    ProtectedResourceResponseBuilder noCredsBuilder;
    ProtectedResourceResponseBuilder badCredsBuilder;
    ProtectedResourceResponseBuilder goodCredsBuilder;
    @Before
    public void instantiateBuilder() {
        String rootDirectory = "public/";
        String requestedResource = "/logs";
        HashMap<String, String> noCreds = new HashMap<String, String>();
        HashMap<String, String> badCreds = new HashMap<String, String>();
        HashMap<String, String> goodCreds = new HashMap<String, String>();

        badCreds.put("Authorization", "foo:bar");

        String auth = printBase64Binary("admin:hunter2".getBytes());
        goodCreds.put("Authorization", "Basic " + auth);

        noCredsBuilder = new ProtectedResourceResponseBuilder(rootDirectory, requestedResource, noCreds);
        badCredsBuilder = new ProtectedResourceResponseBuilder(rootDirectory, requestedResource, badCreds);
        goodCredsBuilder = new ProtectedResourceResponseBuilder(rootDirectory, requestedResource, goodCreds);
    }

    @Test
    public void itGenerates401CodeWhenNoCredentials() {
        assertEquals("401 Unauthorized", noCredsBuilder.getStatus());
    }

    @Test
    public void itGenerates401WhenBadCredentials() {
        assertEquals("401 Unauthorized", badCredsBuilder.getStatus());
    }

    @Test
    public void itGenerates200WhenGoodCredentials() {
        assertEquals("200 OK", goodCredsBuilder.getStatus());
    }


    @Test
    public void itReturnsAuthorizationHeaderWhenNoCredentials() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("WWW-Authenticate", "Basic realm=Dahomey");

        assertEquals(expectedHeaders, noCredsBuilder.getHeaders());
    }

    @Test
    public void itReturnsAuthorizationHeaderWhenBadCredentials() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("WWW-Authenticate", "Basic realm=Dahomey");

        assertEquals(expectedHeaders, badCredsBuilder.getHeaders());
    }

    @Test
    public void itReturnsEmptyHeadersWhenGoodCredentials() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();

        assertEquals(expectedHeaders, goodCredsBuilder.getHeaders());
    }


    @Test
    public void itReturnsAuthRequiredBodyWhenNoCredentials() {
        assertArrayEquals("Authentication required".getBytes(), noCredsBuilder.getBody());
    }

    @Test
    public void itReturnsAuthRequiredBodyWhenBadCredentials() {
        assertArrayEquals("Authentication required".getBytes(), badCredsBuilder.getBody());
    }

    @Test
    public void itReturnsFileBodyWhenGoodCredentials() {
        assertArrayEquals("These are secret logs.".getBytes(), goodCredsBuilder.getBody());
    }
}