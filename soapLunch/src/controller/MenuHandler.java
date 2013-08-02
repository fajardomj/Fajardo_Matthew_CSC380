package controller;

import menuResponse.GetMenuItemsResponse;
import restaurantResponse.Envelope;
import restaurantResponse.GetAllRestaurantsResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fajardo
 * Date: 8/1/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/MenuHandler")
public class MenuHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JAXBContext reqContext = JAXBContext.newInstance("menuRequest");
            Unmarshaller reqUm = reqContext.createUnmarshaller();
            menuRequest.Envelope envelope = (menuRequest.Envelope)reqUm.unmarshal(request.getInputStream());
            menuResponse.MenuItem item = new menuResponse.MenuItem();
            item.setName("Pizza");
            menuResponse.GetMenuItemsResponse menuResponse = new GetMenuItemsResponse();
            menuResponse.setMenuItem(item);
            menuResponse.Body body = new menuResponse.Body();
            body.setGetMenuItemsResponse(menuResponse);
            menuResponse.Envelope responseEnv = new menuResponse.Envelope();
            responseEnv.setBody(body);
            responseEnv.setEncodingStyle("application/soap+xml");
            JAXBContext resContext = JAXBContext.newInstance("menuResponse");
            Marshaller marshaller = resContext.createMarshaller();
            marshaller.marshal(envelope,response.getOutputStream());



        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
