package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Fajardo
 * Date: 8/1/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[]args) {
        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL("localhost:8080/getRestaurant")).openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new FileReader("/resources.restaurants-soap.request.xml"));
            String line = null;
            while ((line = reader.readLine()) != null) {

            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
