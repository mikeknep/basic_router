package com.mikeknep.basic_router.outgoing;

import com.mikeknep.basic_router.outgoing.OutgoingStream;

/**
 * Created by mrk on 6/12/14.
 */
public class MockOutgoingStream implements OutgoingStream {
    private boolean isClosed = false;
    private Object writtenObject;

    public void write(Object object) throws Exception {
        this.writtenObject = object;
    }

    public void close() throws Exception {
        this.isClosed = true;
    }

    public boolean getIsClosed() {
        return this.isClosed;
    }

    public Object getWrittenObject() {
        return this.writtenObject;
    }
}
