import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public interface ResponseBuilder {
    public String getStatus();
    public HashMap<String, String> getHeaders();
    public byte[] getBody();
}
