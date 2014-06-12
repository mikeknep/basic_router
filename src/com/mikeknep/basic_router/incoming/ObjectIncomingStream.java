package com.mikeknep.basic_router.incoming;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * Created by mrk on 6/9/14.
 */
public class ObjectIncomingStream implements IncomingStream {
    private ObjectInputStream objectInputStream;
    private String method;
    private String resource;
    private HashMap<String, String> headers;
    private String body;

    public ObjectIncomingStream(InputStream incoming) throws Exception {
        this.objectInputStream = new ObjectInputStream(incoming);
        this.method = (String) objectInputStream.readObject();
        this.resource = (String) objectInputStream.readObject();
        this.headers = (HashMap<String, String>) objectInputStream.readObject();
        this.body = (String) objectInputStream.readObject();
    }

    public String getMethod() {
        return this.method;
    }

    public String getResource() {
        return this.resource;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }
}
