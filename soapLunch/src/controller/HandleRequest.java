package controller;

import restaurantResponse.Envelope;
import restaurantResponse.GetAllRestaurantsResponse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getRestaurant")
public class HandleRequest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        try {
            restaurantResponse.Restaurant restaurant = new restaurantResponse.Restaurant();
            restaurant.setName("Pizza Hut");
            GetAllRestaurantsResponse restaurantsResponse = new GetAllRestaurantsResponse();
            restaurantsResponse.getRestaurant().add(restaurant);
            restaurantResponse.Body body = new restaurantResponse.Body();
            body.setGetAllRestaurantsResponse(restaurantsResponse);
            restaurantResponse.Envelope responseEnv = new Envelope();
            responseEnv.setBody(body);
            responseEnv.setEncodingStyle("application/soap+xml");
            JAXBContext resContext = JAXBContext.newInstance("restaurantResponse");
            Marshaller marshaller = resContext.createMarshaller();
            marshaller.marshal(responseEnv,response.getOutputStream());



        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }



}