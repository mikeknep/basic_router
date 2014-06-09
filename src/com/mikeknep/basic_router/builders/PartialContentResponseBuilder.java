package com.mikeknep.basic_router.builders;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

/**
 * Created by mrk on 6/9/14.
 */
public class PartialContentResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private HashMap<String, String> requestHeaders;
    private String requestedResource;

    public PartialContentResponseBuilder(String rootDirectory, HashMap<String, String> requestHeaders, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.requestHeaders = requestHeaders;
        this.requestedResource = requestedResource;
    }

    public String getStatus() {
        return "206 Partial Content";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Range", rawByteRange());
        return headers;
    }

    public byte[] getBody() {
        try {
            int length = endingByte() - startingByte();
            byte[] body = new byte[length];
            File resource = new File(rootDirectory + requestedResource);
            RandomAccessFile raf = new RandomAccessFile(resource, "r");
            raf.read(body, 0, endingByte() - startingByte());
            return body;
        } catch (IOException e) {
            return "".getBytes();
        }
    }

    private String rawByteRange() {
        return requestHeaders.get("Range").replace("bytes=", "");
    }

    private int startingByte() {
        return Integer.valueOf(rawByteRange().split("-")[0]);
    }

    private int endingByte() {
        return Integer.valueOf(rawByteRange().split("-")[1]);
    }
}
