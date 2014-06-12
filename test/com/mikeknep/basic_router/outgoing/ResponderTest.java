package com.mikeknep.basic_router.outgoing;

import com.mikeknep.basic_router.builders.MockResponseBuilder;
import com.mikeknep.basic_router.builders.ResponseBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponderTest {
    @Test
    public void itSendsDataToOutgoingStream() throws Exception {
        ResponseBuilder mockResponseBuilder = new MockResponseBuilder();
        MockOutgoingStream mockOutgoingStream = new MockOutgoingStream();
        Responder.sendData(mockOutgoingStream, mockResponseBuilder);

        assertTrue(mockOutgoingStream.getIsClosed());
        assertArrayEquals("Mock body".getBytes(), (byte[]) mockOutgoingStream.getWrittenObject());
    }
}