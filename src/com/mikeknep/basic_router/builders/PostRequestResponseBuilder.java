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
    private String requestBody;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;


    public PostRequestResponseBuilder(String rootDirectory, String requestedResource, String requestBody) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        this.requestBody = requestBody;
        createFile();
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
        headers.put("Location", "http://localhost:5000/" + (rootDirectory + requestedResource).replace("//", "/"));
    }

    private void setBody() {
        this.body = "".getBytes();
    }

    private void createFile() {
        try {
            File file = new File(rootDirectory + requestedResource);
            PrintWriter writer = new PrintWriter(file);
            writer.write(formatBody(requestBody));
            writer.close();
        } catch (IOException e) {}
    }

    private String formatBody(String body) {
        StringBuilder builder = new StringBuilder();
        if (body.contains("=")) {
            String[] splits = body.split("=", 2);
            builder.append(splits[0] + " = " + splits[1]);
        } else {
            builder.append(body);
        }
        return builder.toString();
    }
}
