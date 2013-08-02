package client;

import menuRequest.GetMenuItems;
import menuResponse.MenuItem;
import restaurantResponse.Restaurant;
import soapRequest.GetAllRestaurants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Fajardo
 * Date: 8/1/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    private static String restaurantConn = "http://localhost:8080/getRestaurant";
    private static String menuConn = "http://localhost:8080/MenuHandler";
    private static String orderConn = "http://localhost:8080/CreateOrder";
    public static void main(String[]args) {
        try{
        getRestaurants();
        getMenus();
        createOrder();
        } catch(Exception e) {

        }
    }
    public static void getRestaurants()throws Exception {
        HttpURLConnection connection = connectToUrl(restaurantConn);
        JAXBContext reqContext = JAXBContext.newInstance("soapRequest");
        Marshaller marshaller = reqContext.createMarshaller();
        marshaller.marshal(new GetAllRestaurants(), connection.getOutputStream());
        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        System.out.println(connection.getResponseCode());

        JAXBContext resCon = JAXBContext.newInstance("restaurantResponse");
        restaurantResponse.Envelope envelope = (restaurantResponse.Envelope) resCon.createUnmarshaller().unmarshal(connection.getInputStream());
        restaurantResponse.Body body = envelope.getBody();
        List<Restaurant> restaurant =(List<Restaurant>) body.getGetAllRestaurantsResponse().getRestaurant();

        System.out.println("Restaurant is " + restaurant.get(0).getName());
        connection.disconnect();
    }
    public static void getMenus() throws Exception {

        HttpURLConnection connection = connectToUrl(menuConn);
        JAXBContext reqContext = JAXBContext.newInstance("menuRequest");
        Marshaller marshaller = reqContext.createMarshaller();
        marshaller.marshal(new GetMenuItems(), connection.getOutputStream());
        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        System.out.println(connection.getResponseCode());

        JAXBContext resCon = JAXBContext.newInstance("menuResponse");
        menuResponse.Envelope envelope = (menuResponse.Envelope) resCon.createUnmarshaller().unmarshal(connection.getInputStream());
        menuResponse.Body body = envelope.getBody();
        String menuItem = body.getGetMenuItemsResponse().getMenuItem().getName();
        System.out.println("Menu items are  " + menuItem);
        connection.disconnect();

    }

  public static void createOrder() throws Exception {
      HttpURLConnection connection = connectToUrl(menuConn);
      JAXBContext reqContext = JAXBContext.newInstance("orderRequest");
      Marshaller marshaller = reqContext.createMarshaller();
      marshaller.marshal(new orderRequest.MenuItem(), connection.getOutputStream());
      connection.getOutputStream().flush();
      connection.getOutputStream().close();

      System.out.println(connection.getResponseCode());

  }

    public static HttpURLConnection connectToUrl(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection)(new URL(url )).openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Connection","Keep-Alive");
        connection.setRequestProperty("Content-Type","text/xml");
        return connection;

    }
}

