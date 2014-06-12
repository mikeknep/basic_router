package com.mikeknep.basic_router;

import com.mikeknep.basic_router.builders.ResponseBuilder;
import com.mikeknep.basic_router.incoming.ObjectIncomingStream;
import com.mikeknep.basic_router.incoming.RequestDataCollector;
import com.mikeknep.basic_router.outgoing.ObjectOutgoingStream;
import com.mikeknep.basic_router.outgoing.Responder;
import com.mikeknep.basic_router.utils.*;

/**
 * Created by mrk on 6/2/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ArgsParser argsParser = new ArgsParser(args);
        RequestDataCollector collector = new RequestDataCollector(new ObjectIncomingStream());
        ResponseBuilder builder = Dispatcher.setResponseBuilder(argsParser.getRootDirectory(), collector);
        Responder.sendData(new ObjectOutgoingStream(), builder);
    }
}
