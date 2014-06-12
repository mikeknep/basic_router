package com.mikeknep.basic_router.builders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class MissingResourceResponseBuilder implements ResponseBuilder {
    private Path filepath;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public MissingResourceResponseBuilder(String rootDirectory) {
        this.filepath = Paths.get(rootDirectory + "/404.html");
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
        this.status = "404 Not Found";
    }

    private void setHeaders() {
        this.headers = new HashMap<String, String>();
        headers.put("Content-Type", determineContentType());
    }

    private void setBody() {
        this.body = generateErrorBody();
    }

    private byte[] generateErrorBody() {
        try {
            return Files.readAllBytes(filepath);
        } catch (IOException e) {
            return "404".getBytes();
        }
    }

    private String determineContentType() {
        if (Files.exists(filepath)) {
            return "text/html";
        } else {
            return "text/plain";
        }
    }
}
