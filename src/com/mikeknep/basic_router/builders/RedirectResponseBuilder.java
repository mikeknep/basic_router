package com.mikeknep.basic_router.builders;

import java.util.HashMap;

/**
 * Created by mrk on 6/5/14.
 */
public class RedirectResponseBuilder implements ResponseBuilder {
    public String getStatus() {
        return "301 Moved Permanently";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Location", "/");
        return headers;
    }

    public byte[] getBody() {
        return "This page has moved.".getBytes();
    }
}
