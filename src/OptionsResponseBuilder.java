import java.util.HashMap;

/**
 * Created by mrk on 6/5/14.
 */
public class OptionsResponseBuilder implements ResponseBuilder {
    public String getStatus() {
        return "200 OK";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        return headers;
    }

    public byte[] getBody() {
        return "".getBytes();
    }
}
