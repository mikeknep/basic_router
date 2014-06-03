import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DirectoryResponseBuilderTest {
    DirectoryResponseBuilder builder;
    @Before
    public void instantiateDirectoryResponseBuilder() {
        builder = new DirectoryResponseBuilder("test/", "sample_files");
    }

    @Test
    public void itReturns200OKStatus() {
        assertEquals("200 OK", builder.getStatus());
    }

    @Test
    public void itReturnsHTMLContentType() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "text/html");

        assertEquals(expectedHeaders, builder.getHeaders());
    }

    @Test
    public void itReturnsListOfDirectoryContents() {
        String expectedString = "<ul><li><a href=\"/sample_files/404.html\">404.html</a></li><li><a href=\"/sample_files/mock.gif\">mock.gif</a></li><li><a href=\"/sample_files/mock.html\">mock.html</a></li></ul>";
        byte[] expectedBytes = expectedString.getBytes();
        assertArrayEquals(expectedBytes, builder.getBody());
    }
}