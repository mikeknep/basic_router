package com.mikeknep.basic_router.utils;

import com.mikeknep.basic_router.builders.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class DispatcherTest {
    @Test
    public void itRoutesBadRequestToBadRequestResponseBuilder() {
        assertEquals(BadRequestResponseBuilder.class, Dispatcher.setResponseBuilder("", "", "", "").getClass());
    }

    @Test
    public void itRoutesOptionsRequestToOptionsResponseBuilder() {
        assertEquals(OptionsResponseBuilder.class, Dispatcher.setResponseBuilder("public/", "OPTIONS", "/", "").getClass());
    }

    @Test
    public void itRoutesDirectoryRequestToDirectoryResponseBuilder() {
        assertEquals(DirectoryResponseBuilder.class, Dispatcher.setResponseBuilder("public/", "GET", "/", "").getClass());
    }

    @Test
    public void itRoutesFileRequestToFileResponseBuilder() {
        assertEquals(FileResponseBuilder.class, Dispatcher.setResponseBuilder("public/", "GET", "/mock.html", "").getClass());
    }

    @Test
    public void itRoutesMissingResourceRequestToMissingResourceResponseBuilder() {
        assertEquals(MissingResourceResponseBuilder.class, Dispatcher.setResponseBuilder("public/", "GET", "/not_here.html", "").getClass());
    }

    @Test
    public void itRoutesRedirectRequestToRedirectResponseBuilder() {
        assertEquals(RedirectResponseBuilder.class, Dispatcher.setResponseBuilder("public/", "GET", "/redirect", "").getClass());
    }

    @Test
    public void itRoutesDisallowedMethodToMethodNotAllowedResponseBuilder() {
        assertEquals(MethodNotAllowedResponseBuilder.class, Dispatcher.setResponseBuilder("public/", "PUT", "/file1", "").getClass());
    }

    @Test
    public void itRoutesPostRequestsToPostResponseBuilder() {
        assertEquals(PostRequestResponseBuilder.class, Dispatcher.setResponseBuilder("public/", "POST", "/form", "My=Data").getClass());
    }

    @Test
    public void itRoutesRequestWithParamsToParameterDecoderResponseBuilder() {
        assertEquals(ParameterDecodeResponseBuilder.class, Dispatcher.setResponseBuilder("public/", "GET", "/parameters?foo=bar", "").getClass());
    }
}