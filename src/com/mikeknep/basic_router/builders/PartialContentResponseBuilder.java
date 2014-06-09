package com.mikeknep.basic_router.builders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mrk on 6/9/14.
 */
public class PartialContentResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private String rawHeaders;
    private String requestedResource;

    public PartialContentResponseBuilder(String rootDirectory, String rawHeaders, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.rawHeaders = rawHeaders;
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
        Pattern pattern = Pattern.compile("(Range: bytes=[0-9]*-[0-9]*)");
        Matcher matcher = pattern.matcher(rawHeaders);
        matcher.find();
        String fullRangeHeader = matcher.group(0);
        return fullRangeHeader.replace("Range: bytes=", "");
    }

    private int startingByte() {
        return Integer.valueOf(rawByteRange().split("-")[0]);
    }

    private int endingByte() {
        return Integer.valueOf(rawByteRange().split("-")[1]);
    }
}
