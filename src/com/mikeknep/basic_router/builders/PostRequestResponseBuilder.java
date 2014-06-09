package com.mikeknep.basic_router.builders;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by mrk on 6/6/14.
 */
public class PostRequestResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private String requestedResource;
    private String body;

    public PostRequestResponseBuilder(String rootDirectory, String requestedResource, String body) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        this.body = body;
        createFile();
    }

    public String getStatus() {
        return "303 See Other";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Location", requestedResource);
        return headers;
    }

    public byte[] getBody() {
        return "".getBytes();
    }


    private void createFile() {
        try {
            File file = new File(rootDirectory + requestedResource);
            PrintWriter writer = new PrintWriter(file);
            writer.write(body);
            writer.close();
        } catch (IOException e) {}
    }
}
