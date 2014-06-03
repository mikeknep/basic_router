import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class FileResponseBuilder implements ResponseBuilder {
    private Path resourcePath;

    public FileResponseBuilder(String rootDirectory, String requestedResource) {
        this.resourcePath = Paths.get(rootDirectory + requestedResource);
    }

    public String getStatus() {
        return "200 OK";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", URLConnection.guessContentTypeFromName(String.valueOf(resourcePath)));
        return headers;
    }

    public byte[] getBody() {
        try {
            return Files.readAllBytes(resourcePath);
        } catch (Exception e) {
            return "".getBytes();
        }
    }
}
