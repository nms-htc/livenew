
package com.nms.ws.charging;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.nms.ws.charging package. 
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

    private final static QName _ChargeItemsResponse_QNAME = new QName("http://chargingwebservice.nms.com/", "ChargeItemsResponse");
    private final static QName _ChargeItems_QNAME = new QName("http://chargingwebservice.nms.com/", "ChargeItems");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.nms.ws.charging
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChargeItemsResponse }
     * 
     */
    public ChargeItemsResponse createChargeItemsResponse() {
        return new ChargeItemsResponse();
    }

    /**
     * Create an instance of {@link ChargeItems }
     * 
     */
    public ChargeItems createChargeItems() {
        return new ChargeItems();
    }

    /**
     * Create an instance of {@link ContentPurcharseRes }
     * 
     */
    public ContentPurcharseRes createContentPurcharseRes() {
        return new ContentPurcharseRes();
    }

    /**
     * Create an instance of {@link RequestBase }
     * 
     */
    public RequestBase createRequestBase() {
        return new RequestBase();
    }

    /**
     * Create an instance of {@link ContentPurcharseReq }
     * 
     */
    public ContentPurcharseReq createContentPurcharseReq() {
        return new ContentPurcharseReq();
    }

    /**
     * Create an instance of {@link ResponseBase }
     * 
     */
    public ResponseBase createResponseBase() {
        return new ResponseBase();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeItemsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chargingwebservice.nms.com/", name = "ChargeItemsResponse")
    public JAXBElement<ChargeItemsResponse> createChargeItemsResponse(ChargeItemsResponse value) {
        return new JAXBElement<ChargeItemsResponse>(_ChargeItemsResponse_QNAME, ChargeItemsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chargingwebservice.nms.com/", name = "ChargeItems")
    public JAXBElement<ChargeItems> createChargeItems(ChargeItems value) {
        return new JAXBElement<ChargeItems>(_ChargeItems_QNAME, ChargeItems.class, null, value);
    }

}
