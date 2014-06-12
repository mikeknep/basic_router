package com.mikeknep.basic_router.utils;

import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by mrk on 6/12/14.
 */
public class ObjectOutgoingStream implements OutgoingStream {
    private ObjectOutputStream oos;
    public ObjectOutgoingStream() throws Exception {
        oos = new ObjectOutputStream(System.out);
    }

    public void write(Object object) throws Exception {
        oos.writeObject(object);
    }

    public void close() throws Exception {
        oos.close();
    }
}
