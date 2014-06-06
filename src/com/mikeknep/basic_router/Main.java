package com.mikeknep.basic_router;

import com.mikeknep.basic_router.builders.ResponseBuilder;
import com.mikeknep.basic_router.utils.Dispatcher;
import com.mikeknep.basic_router.utils.Parser;
import com.mikeknep.basic_router.utils.Responder;

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
