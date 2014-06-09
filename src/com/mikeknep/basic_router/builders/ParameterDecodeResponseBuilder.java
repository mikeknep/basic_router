package com.mikeknep.basic_router.builders;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * Created by mrk on 6/9/14.
 */
public class ParameterDecodeResponseBuilder implements ResponseBuilder {
    private String rawParams;

    public ParameterDecodeResponseBuilder(String requestedResource) {
        this.rawParams = requestedResource.split("\\?", 2)[1];
    }

    public String getRawParams() {
        return this.rawParams;
    }

    public String getStatus() {
        return "200 OK";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        return headers;
    }
    
    public byte[] getBody() {
        return (parseParams(rawParams)).getBytes();
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
