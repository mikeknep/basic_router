package com.mikeknep.basic_router.builders;

import java.util.HashMap;

/**
 * Created by mrk on 6/5/14.
 */
public class OptionsResponseBuilder implements ResponseBuilder {
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public OptionsResponseBuilder() {
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
        this.status = "200 OK";
    }

    private void setHeaders() {
        this.headers = new HashMap<String, String>();
        headers.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
    }

    private void setBody() {
        this.body = "".getBytes();
    }
}
