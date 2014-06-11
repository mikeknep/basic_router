package com.mikeknep.basic_router.builders;

import com.mikeknep.basic_router.utils.Logger;

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
    private HashMap<String, String> headers;

    public ProtectedResourceResponseBuilder(String rootDirectory, String requestedResource, HashMap<String, String> headers) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        this.headers = headers;
    }

    public String getStatus() {
        if (validCredentials()) {
            return "200 OK";
        } else {
            return "401 Unauthorized";
        }
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        if (!validCredentials()) {
            headers.put("WWW-Authenticate", "Basic realm=Dahomey");
        }
        return headers;
    }

    public byte[] getBody() {
        if (validCredentials()) {
            try {
                return Files.readAllBytes(Paths.get(rootDirectory + requestedResource));
            } catch (IOException e) {
                return "".getBytes();
            }
        } else {
            return "Authentication required".getBytes();
        }
    }

    private boolean validCredentials() {
        try {
            String rawCredsValue = headers.get("Authorization");
            String encoded = rawCredsValue.replace("Basic ", "");
            byte[] decoded = parseBase64Binary(encoded);

            return (Arrays.equals("admin:hunter2".getBytes(), decoded));
        } catch (NullPointerException e) {
            return false;
        }
    }
}
