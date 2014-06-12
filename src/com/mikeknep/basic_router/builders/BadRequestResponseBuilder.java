package com.mikeknep.basic_router.builders;

import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class BadRequestResponseBuilder implements ResponseBuilder {
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public BadRequestResponseBuilder() {
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
        this.status = "400 Bad Request";
    }

    private void setHeaders() {
        this.headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/plain");
    }

    private void setBody() {
        this.body = "".getBytes();
    }
}
