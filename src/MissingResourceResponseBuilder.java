import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class MissingResourceResponseBuilder implements ResponseBuilder {
    private Path filepath;

    public MissingResourceResponseBuilder(String rootDirectory) {
        this.filepath = Paths.get(rootDirectory + "/404.html");
    }

    public String getStatus() {
        return "404 Not Found";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", determineContentType());
        return headers;
    }

    public byte[] getBody() {
        return generateErrorBody();
    }



    private byte[] generateErrorBody() {
        try {
            return Files.readAllBytes(filepath);
        } catch (IOException e) {
            return "404".getBytes();
        }
    }

    private String determineContentType() {
        if (Files.exists(filepath)) {
            return "text/html";
        } else {
            return "text/plain";
        }
    }
}
