package com.mikeknep.basic_router.utils;

import java.util.HashMap;

/**
 * Created by mrk on 6/9/14.
 */
public class RequestDataCollector {
    private String method;
    private String resource;
    private HashMap<String, String> headers;
    private String body;

    public RequestDataCollector(ExchangeStream incoming) throws Exception {
        this.method = incoming.getMethod();
        this.resource = incoming.getResource();
        this.headers = incoming.getHeaders();
        this.body = incoming.getBody();
    }

    public String getMethod() {
        return this.method;
    }

    public String getRequestedResource() {
        return this.resource;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }
}
