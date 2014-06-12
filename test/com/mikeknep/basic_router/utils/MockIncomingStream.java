package com.mikeknep.basic_router.utils;

import java.util.HashMap;

/**
 * Created by mrk on 6/9/14.
 */
public class MockIncomingStream implements IncomingStream {
    private String method;
    private String resource;
    private HashMap<String, String> headers;
    private String body;

    public MockIncomingStream(String method, String resource, HashMap<String, String> headers, String body) {
        this.method = method;
        this.resource = resource;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return this.method;
    }

    public String getResource() {
        return this.resource;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }
}
