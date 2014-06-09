package com.mikeknep.basic_router;

import com.mikeknep.basic_router.builders.ResponseBuilder;
import com.mikeknep.basic_router.utils.*;

/**
 * Created by mrk on 6/2/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ArgsParser argsParser = new ArgsParser(args);
        RequestDataCollector collector = new RequestDataCollector(new ObjectExchangeStream(System.in));
        ResponseBuilder builder = Dispatcher.setResponseBuilder(argsParser.getRootDirectory(), collector);
        Responder.sendData(builder.getStatus(), builder.getHeaders(), builder.getBody());
    }
}
