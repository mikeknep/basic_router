package com.mikeknep.basic_router;

/**
 * Created by mrk on 6/2/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser(args);
        ResponseBuilder builder = Dispatcher.setResponseBuilder(parser.getRootDirectory(), parser.getMethod(), parser.getRequestedResource());
        Responder.sendData(builder.getStatus(), builder.getHeaders(), builder.getBody());
    }
}
