package com.mikeknep.basic_router.builders;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by mrk on 6/10/14.
 */
public class PutRequestResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private String requestedResource;
    private String body;

    public PutRequestResponseBuilder(String rootDirectory, String requestedResource, String body) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        this.body = body;
        updateFile();
    }
    public String getStatus() {
        return "200 OK";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Location", requestedResource);
        return headers;
    }

    public byte[] getBody() {
        return "".getBytes();
    }

    private void updateFile() {
        try {
            FileWriter fileWriter = new FileWriter(rootDirectory + requestedResource, true);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.write(formatBody(body));
            writer.close();
        } catch (IOException e) {}
    }

    private String formatBody(String body) {
        String[] splits = body.split("=");
        return (splits[0] + " = " + splits[1]);
    }
}
