import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class DirectoryResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private Path resourcePath;

    public DirectoryResponseBuilder(String rootDirectory, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.resourcePath = Paths.get(rootDirectory + requestedResource);
    }

    public String getStatus() {
        return "200 OK";
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");
        return headers;
    }

    public byte[] getBody() {
        return generateBody();
    }



    private byte[] generateBody() {
        StringBuilder builder = new StringBuilder();
        File[] files = resourcePath.toFile().listFiles();

        builder.append("<ul>");
        for (File file : files) {
            builder.append("<li>" + "<a href=\"/" + getRelativePath(file) + "\">" + file.getName() + "</a>" + "</li>");
        }
        builder.append("</ul>");

        return builder.toString().getBytes();
    }

    private String getRelativePath(File file) {
        return file.getPath().split(rootDirectory)[1];
    }
}
