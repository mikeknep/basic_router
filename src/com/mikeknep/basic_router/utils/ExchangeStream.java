package com.mikeknep.basic_router.utils;

import java.util.HashMap;

/**
 * Created by mrk on 6/9/14.
 */
public interface ExchangeStream {
    public String getMethod();
    public String getResource();
    public HashMap<String, String> getHeaders();
    public String getBody();
}
