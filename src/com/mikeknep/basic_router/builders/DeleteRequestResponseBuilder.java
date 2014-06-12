package com.mikeknep.basic_router.builders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 6/10/14.
 */
public class DeleteRequestResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private String requestedResource;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public DeleteRequestResponseBuilder(String rootDirectory, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        deleteFile();
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


    private void deleteFile() {
        try {
            Files.deleteIfExists(Paths.get(rootDirectory + requestedResource));
        } catch (IOException e) {}
    }

    private void setStatus() {
        this.status = "200 OK";
    }

    private void setHeaders() {
        this.headers = new HashMap<String, String>();
    }

    private void setBody() {
        this.body = "".getBytes();
    }
}
