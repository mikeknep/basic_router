import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by mrk on 6/2/14.
 */
public class Responder {
    public static void sendData(String status, HashMap<String, String> headers, byte[] body) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(System.out);
        oos.writeObject(status);
        oos.writeObject(headers);
        oos.writeObject(body);
        oos.close();
    }
}
