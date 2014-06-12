package com.mikeknep.basic_router.builders;

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by mrk on 6/10/14.
 */
public class ProtectedResourceResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private String requestedResource;
    private HashMap<String, String> requestHeaders;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public ProtectedResourceResponseBuilder(String rootDirectory, String requestedResource, HashMap<String, String> requestHeaders) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        this.requestHeaders = requestHeaders;
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
        if (validCredentials()) {
            this.status = "200 OK";
        } else {
            this.status = "401 Unauthorized";
        }
    }

    private void setHeaders() {
        this.headers = new HashMap<String, String>();
        if (!validCredentials()) {
            headers.put("WWW-Authenticate", "Basic realm=Dahomey");
        }
    }

    private void setBody() {
        if (validCredentials()) {
            try {
                this.body = Files.readAllBytes(Paths.get(rootDirectory + requestedResource));
            } catch (IOException e) {
                this.body = "".getBytes();
            }
        } else {
            this.body = "Authentication required".getBytes();
        }
    }

    private boolean validCredentials() {
        try {
            String rawCredsValue = requestHeaders.get("Authorization");
            String encoded = rawCredsValue.replace("Basic ", "");
            byte[] decoded = parseBase64Binary(encoded);

            return (Arrays.equals("admin:hunter2".getBytes(), decoded));
        } catch (NullPointerException e) {
            return false;
        }
    }
}
