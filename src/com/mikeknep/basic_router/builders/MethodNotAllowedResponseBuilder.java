package com.mikeknep.basic_router.builders;

import java.util.HashMap;

/**
 * Created by mrk on 6/5/14.
 */
public class MethodNotAllowedResponseBuilder implements ResponseBuilder {
    public String getStatus() {
        return "405 Method Not Allowed";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Allow", "GET,HEAD,OPTIONS");
        return headers;
    }

    public byte[] getBody() {
        return "The requested method is not allowed.".getBytes();
    }
}
