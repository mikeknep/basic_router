package com.mikeknep.basic_router.builders;

import java.util.HashMap;

/**
 * Created by mrk on 6/5/14.
 */
public class RedirectResponseBuilder implements ResponseBuilder {
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public RedirectResponseBuilder() {
        setStatus();
        setHeaders();
        setBody();
    }

    public String getStatus() {
        return this.status;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public byte[] getBody() {
        return this.body;
    }


    private void setStatus() {
        this.status = "301 Moved Permanently";
    }

    private void setHeaders() {
        this.headers = new HashMap<String, String>();
        headers.put("Location", "http://localhost:5000/");
    }

    private void setBody() {
        this.body = "This page has moved.".getBytes();
    }
}
