import com.github.veronicazzzz.storageserverclient.Client;
import com.github.veronicazzzz.storageserverclient.Exception.NonSuccessResponseException;
import okhttp3.Response;

import java.io.IOException;

public class ClientTest {
    public static void main(String[] args) {
        String serverUrl = "http://134.209.97.150:8080";

        Client client = new Client(serverUrl);

        try {
            //client.postFile("/home/nika/Pictures/cat.jpg");

            String responseJSON = client.getFilesInfo();
            System.out.println(responseJSON);

            client.downloadFile("cat.jpg","/home/nika/Downloads/cat.jpg");

            client.deleteFile("cat.jpg");
        } catch (IOException | NonSuccessResponseException e) {
            e.printStackTrace();
        }
    }
}
