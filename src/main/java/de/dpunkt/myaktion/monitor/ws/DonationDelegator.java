
package de.dpunkt.myaktion.monitor.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import de.dpunkt.myaktion.model.Donation;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DonationDelegator", targetNamespace = "http://ws.monitor.myaktion.dpunkt.de/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DonationDelegator {


    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "sendDonation", targetNamespace = "http://ws.monitor.myaktion.dpunkt.de/", className = "de.dpunkt.myaktion.monitor.ws.SendDonation")
    @ResponseWrapper(localName = "sendDonationResponse", targetNamespace = "http://ws.monitor.myaktion.dpunkt.de/", className = "de.dpunkt.myaktion.monitor.ws.SendDonationResponse")
    @Action(input = "http://ws.monitor.myaktion.dpunkt.de/DonationDelegator/sendDonationRequest", output = "http://ws.monitor.myaktion.dpunkt.de/DonationDelegator/sendDonationResponse")
    public void sendDonation(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Donation arg1);

}
