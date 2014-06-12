package com.mikeknep.basic_router.utils;

import com.mikeknep.basic_router.builders.ResponseBuilder;

import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class Responder {
    public static void sendData(OutgoingStream outStream, ResponseBuilder builder) throws Exception {
        outStream.write(builder.getStatus());
        outStream.write(builder.getHeaders());
        outStream.write(builder.getBody());
        outStream.close();
    }
}
