import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FileResponseBuilderTest {
    FileResponseBuilder htmlBuilder;
    FileResponseBuilder gifBuilder;
    @Before
    public void instantiateFileResponseBuilders() {
        htmlBuilder = new FileResponseBuilder("test/sample_files", "/mock.html");
        gifBuilder = new FileResponseBuilder("test/sample_files", "/mock.gif");
    }


    @Test
    public void itReturns200OKStatus() {
        assertEquals("200 OK", htmlBuilder.getStatus());
    }

    @Test
    public void itReturnsHTMLContentTypeHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "text/html");

        assertEquals(expectedHeaders, htmlBuilder.getHeaders());
    }

    @Test
    public void itReturnsGIFContentType() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "image/gif");

        assertEquals(expectedHeaders, gifBuilder.getHeaders());
    }

    @Test
    public void itReturnsByteArrayBody() {
        assertArrayEquals("foobar".getBytes(), htmlBuilder.getBody());
    }
}