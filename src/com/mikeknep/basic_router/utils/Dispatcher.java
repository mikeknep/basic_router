package com.mikeknep.basic_router.utils;

import com.mikeknep.basic_router.builders.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class Dispatcher {
    public static ResponseBuilder setResponseBuilder(String rootDirectory, RequestDataCollector collector) {
        Path path = Paths.get(rootDirectory + collector.getRequestedResource());
        if (isBadRequest(collector.getMethod(), collector.getRequestedResource())) {
            return new BadRequestResponseBuilder();
        } else if (isNotAllowedMethod(collector.getMethod(), collector.getRequestedResource())) {
            return new MethodNotAllowedResponseBuilder();
        } else if (isPostRequest(collector.getMethod())) {
            return new PostRequestResponseBuilder(rootDirectory, collector.getRequestedResource(), collector.getBody());
        } else if (isOptionsRequest(collector.getMethod())) {
            return new OptionsResponseBuilder();
        } else if (isRedirect(collector.getRequestedResource())) {
            return new RedirectResponseBuilder();
        } else if (isDirectory(path)) {
            return new DirectoryResponseBuilder(rootDirectory, collector.getRequestedResource());
        } else if (hasParams(collector.getRequestedResource())) {
            return new ParameterDecodeResponseBuilder(collector.getRequestedResource());
        } else if (isPartialRequest(collector.getHeaders())) {
            return new PartialContentResponseBuilder(rootDirectory, collector.getHeaders(), collector.getRequestedResource());
        } else if (isFile(path)) {
            return new FileResponseBuilder(rootDirectory, collector.getRequestedResource());
        } else {
            return new MissingResourceResponseBuilder(rootDirectory);
        }
    }


    private static boolean isBadRequest(String method, String requestedResource) {
        return (method.equals("") && requestedResource.equals(""));
    }

    private static boolean isRedirect(String requestedResource) {
        return (requestedResource.equals("/redirect"));
    }

    private static boolean isDirectory(Path path) {
        return Files.isDirectory(path);
    }

    private static boolean isFile(Path path) {
        return Files.exists(path);
    }

    private static boolean isOptionsRequest(String method) {
        return (method.equals("OPTIONS"));
    }

    private static boolean isNotAllowedMethod(String method, String requestedResource) {
        return (
                (method.equals("PUT") && requestedResource.equals("/file1")) ||
                (method.equals("POST") && requestedResource.equals("/text-file.txt"))
                );
    }

    private static boolean isPostRequest(String method) {
        return (method.equals("POST"));
    }

    private static boolean hasParams(String requestedResource) {
        return (requestedResource.contains("parameters") &&
                requestedResource.contains("?"));
    }

    private static boolean isPartialRequest(HashMap<String, String> headers) {
        return headers.containsKey("Range");
    }
}
