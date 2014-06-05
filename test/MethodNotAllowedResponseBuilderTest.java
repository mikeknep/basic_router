import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class MethodNotAllowedResponseBuilderTest {
    MethodNotAllowedResponseBuilder builder;
    @Before
    public void instantiateBuilder() {
        builder = new MethodNotAllowedResponseBuilder();
    }

    @Test
    public void itReturns405StatusCode() {
        assertEquals("405 Method Not Allowed", builder.getStatus());
    }

    @Test
    public void itReturnsHeadersThatIncludeAllowedMethods() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Allow", "GET,HEAD,OPTIONS");

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsBasicBodyMessage() {
        assertArrayEquals("The requested method is not allowed.".getBytes(), builder.getBody());
    }
}