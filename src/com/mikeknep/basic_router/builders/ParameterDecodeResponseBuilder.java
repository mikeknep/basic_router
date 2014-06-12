package com.mikeknep.basic_router.builders;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * Created by mrk on 6/9/14.
 */
public class ParameterDecodeResponseBuilder implements ResponseBuilder {
    private String rawParams;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public ParameterDecodeResponseBuilder(String requestedResource) {
        setRawParams(requestedResource);
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
    }

    private void setBody() {
        this.body = parseParams(rawParams).getBytes();
    }


    private void setRawParams(String requestedResource) {
        this.rawParams = requestedResource.split("\\?", 2)[1];
    }

    private String parseParams(String rawParams) {
        StringBuilder builder = new StringBuilder();

        String[] splitParams = rawParams.split("&");
        for (String param : splitParams) {
            try {
                String key = param.split("=", 2)[0];
                String value = decode(param.split("=", 2)[1]);
                builder.append(key + " = " + value + "\n");
            } catch (UnsupportedEncodingException e) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private String decode(String rawParamText) throws UnsupportedEncodingException {
        return URLDecoder.decode(rawParamText, "UTF-8");
    }
}
