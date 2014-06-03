import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class BadRequestResponseBuilder implements ResponseBuilder {
    public String getStatus() {
        return "400 Bad Request";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/plain");
        return headers;
    }

    public byte[] getBody() {
        return "".getBytes();
    }
}
