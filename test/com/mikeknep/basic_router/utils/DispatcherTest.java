package com.mikeknep.basic_router.utils;

import com.mikeknep.basic_router.builders.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DispatcherTest {
    @Test
    public void itRoutesBadRequestToBadRequestResponseBuilder() throws Exception {
        String directory = "";
        String method = "";
        String resource = "";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(BadRequestResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesOptionsRequestToOptionsResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "OPTIONS";
        String resource = "/";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(OptionsResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesDirectoryRequestToDirectoryResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "GET";
        String resource = "/";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(DirectoryResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesFileRequestToFileResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "GET";
        String resource = "/mock.html";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(FileResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesMissingResourceRequestToMissingResourceResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "GET";
        String resource = "/not_here.html";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(MissingResourceResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesRedirectRequestToRedirectResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "GET";
        String resource = "/redirect";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(RedirectResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesDisallowedMethodToMethodNotAllowedResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "PUT";
        String resource = "/file1";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(MethodNotAllowedResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesPostRequestsToPostResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "POST";
        String resource = "/form";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "My=Data";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(PostRequestResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesRequestWithParamsToParameterDecoderResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "GET";
        String resource = "/parameters?foo=bar";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(ParameterDecodeResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesPartialContentRequestToPartialContentResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "GET";
        String resource = "/mock.html";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Range", "bytes=0-3");
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(PartialContentResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesPUTRequestToPutRequestResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "PUT";
        String resource = "/mock.html";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "Some=Data";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(PutRequestResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesDELETERequestToDeleteRequestResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "DELETE";
        String resource = "/form";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(DeleteRequestResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }

    @Test
    public void itRoutesUnauthorizedRequestToProtectedResourceResponseBuilder() throws Exception {
        String directory = "public/";
        String method = "GET";
        String resource = "/logs";
        HashMap<String, String> headers = new HashMap<String, String>();
        String body = "";

        RequestDataCollector collector = new RequestDataCollector(new MockExchangeStream(method, resource, headers, body));

        assertEquals(ProtectedResourceResponseBuilder.class, Dispatcher.setResponseBuilder(directory, collector).getClass());
    }
}