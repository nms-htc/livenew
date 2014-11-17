
package com.nms.ws.checksubscriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GETMSISDNResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getmsisdnResult"
})
@XmlRootElement(name = "GETMSISDNResponse")
public class GETMSISDNResponse {

    @XmlElement(name = "GETMSISDNResult")
    protected String getmsisdnResult;

    /**
     * Gets the value of the getmsisdnResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGETMSISDNResult() {
        return getmsisdnResult;
    }

    /**
     * Sets the value of the getmsisdnResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGETMSISDNResult(String value) {
        this.getmsisdnResult = value;
    }

}
