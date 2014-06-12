package com.mikeknep.basic_router.builders;

import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class FileResponseBuilder implements ResponseBuilder {
    private Path resourcePath;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public FileResponseBuilder(String rootDirectory, String requestedResource) {
        this.resourcePath = Paths.get(rootDirectory + requestedResource);
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
        headers.put("Content-Type", URLConnection.guessContentTypeFromName(String.valueOf(resourcePath)));
    }

    private void setBody() {
        try {
            this.body = Files.readAllBytes(resourcePath);
        } catch (Exception e) {
            this.body = "".getBytes();
        }
    }
}
