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
    private String requestBody;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public PutRequestResponseBuilder(String rootDirectory, String requestedResource, String requestBody) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        this.requestBody = requestBody;
        updateFile();
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
        headers.put("Location", requestedResource);
    }

    private void setBody() {
        this.body = "".getBytes();
    }

    private void updateFile() {
        try {
            FileWriter fileWriter = new FileWriter(rootDirectory + requestedResource, true);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.write(formatBody(requestBody));
            writer.close();
        } catch (IOException e) {}
    }

    private String formatBody(String body) {
        String[] splits = body.split("=");
        return (splits[0] + " = " + splits[1]);
    }
}
