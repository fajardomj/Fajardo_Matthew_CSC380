package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fajardo
 * Date: 8/1/13
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        JAXBContext reqContext = JAXBContext.newInstance("CreateOrderRequest");
        Unmarshaller reqUm = reqContext.createUnmarshaller();
        orderRequest.Envelope envelope = (orderRequest.Envelope)reqUm.unmarshal(request.getInputStream());
           String restaurant = envelope.getBody().getPlaceOrder().getRestaurant().getName();
           String menuItem =  envelope.getBody().getPlaceOrder().getMenuItem().getName();
            response.getWriter().write("You ordered from " + restaurant + " You ordered a " + menuItem);

        } catch(Exception e) {

        }
    }
}
