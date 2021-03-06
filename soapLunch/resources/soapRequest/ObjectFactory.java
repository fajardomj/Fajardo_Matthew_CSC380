
package soapRequest;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the restaurantRequest package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: restaurantRequest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link soapRequest.Body }
     *
     */
    public Body createBody() {
        return new Body();
    }

    /**
     * Create an instance of {@link Envelope }
     *
     */
    public Envelope createEnvelope() {
        return new Envelope();
    }

    /**
     * Create an instance of {@link soapRequest.GetAllRestaurants }
     * 
     */
    public GetAllRestaurants createGetAllRestaurants() {
        return new GetAllRestaurants();
    }

}
