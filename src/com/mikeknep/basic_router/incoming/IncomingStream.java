package com.mikeknep.basic_router.incoming;

import java.util.HashMap;

/**
 * Created by mrk on 6/12/14.
 */
public interface IncomingStream {
    public String getMethod();
    public String getResource();
    public HashMap<String, String> getHeaders();
    public String getBody();
}
