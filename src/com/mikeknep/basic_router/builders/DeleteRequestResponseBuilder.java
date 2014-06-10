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

    public DeleteRequestResponseBuilder(String rootDirectory, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        deleteFile();
    }

    public String getStatus() {
        return "200 OK";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        return headers;
    }

    public byte[] getBody() {
        return "".getBytes();
    }

    private void deleteFile() {
        try {
            Files.deleteIfExists(Paths.get(rootDirectory + requestedResource));
        } catch (IOException e) {}
    }
}
