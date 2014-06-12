package com.mikeknep.basic_router.utils;

/**
 * Created by mrk on 6/12/14.
 */
public interface OutgoingStream {
    public void write(Object object) throws Exception;
    public void close() throws Exception;
}
