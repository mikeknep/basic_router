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
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public PartialContentResponseBuilder(String rootDirectory, HashMap<String, String> requestHeaders, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.requestHeaders = requestHeaders;
        this.requestedResource = requestedResource;
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
        this.status = "206 Partial Content";
    }

    private void setHeaders() {
        this.headers = new HashMap<String, String>();
        headers.put("Content-Range", formatContentRange());
        headers.put("Content-Length", partialResourceLength());
    }

    private void setBody() {
        try {
            int length = endingByte() - startingByte();
            this.body = new byte[length];
            File resource = new File(rootDirectory + requestedResource);
            RandomAccessFile raf = new RandomAccessFile(resource, "r");
            raf.read(body, 0, endingByte() - startingByte());
        } catch (IOException e) {
            this.body = "".getBytes();
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

    private String fullResourceLength() {
        File file = new File(rootDirectory + requestedResource);
        long length = file.length();
        return String.valueOf(length);
    }

    private String formatContentRange() {
        return ("bytes " + rawByteRange() + "/" + fullResourceLength());
    }

    private String partialResourceLength() {
        return String.valueOf(endingByte() - startingByte());
    }
}
