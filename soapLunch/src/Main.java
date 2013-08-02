import java.io.IOException;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fajardo
 * Date: 7/30/13
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[]args) throws Exception {
        HttpURLConnection connection = (HttpURLConnection)(new URL("http://localhost:8080/getMenu")).openConnection();
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("SoapAction", "");
        connection.setDoOutput(true);
    }
}
