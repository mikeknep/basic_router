package com.mikeknep.basic_router.builders;

import java.util.HashMap;

/**
 * Created by mrk on 6/12/14.
 */
public class MockResponseBuilder implements ResponseBuilder {
    public String getStatus() {
        return "999 Mock Status";
    }

    public HashMap<String, String> getHeaders() {
        return new HashMap<String, String>();
    }

    public byte[] getBody() {
        return "Mock body".getBytes();
    }
}
