import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mrk on 6/2/14.
 */
public class Dispatcher {
    public static ResponseBuilder setResponseBuilder(String rootDirectory, String method, String requestedResource) {
        Path path = Paths.get(rootDirectory + requestedResource);
        if (isBadRequest(method, requestedResource)) {
            return new BadRequestResponseBuilder();
        } else if (isDirectory(path)) {
            return new DirectoryResponseBuilder(rootDirectory, requestedResource);
        } else if (isFile(path)) {
            return new FileResponseBuilder(rootDirectory, requestedResource);
        } else {
            return new MissingResourceResponseBuilder(rootDirectory);
        }
    }


    private static boolean isBadRequest(String method, String requestedResource) {
        return (method.equals("") && requestedResource.equals(""));
    }

    private static boolean isDirectory(Path path) {
        return Files.isDirectory(path);
    }

    private static boolean isFile(Path path) {
        return Files.exists(path);
    }
}
