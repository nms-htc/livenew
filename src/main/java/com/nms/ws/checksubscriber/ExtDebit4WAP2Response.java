
package com.nms.ws.checksubscriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="extDebit4WAP2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "extDebit4WAP2Result"
})
@XmlRootElement(name = "extDebit4WAP2Response")
public class ExtDebit4WAP2Response {

    protected String extDebit4WAP2Result;

    /**
     * Gets the value of the extDebit4WAP2Result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtDebit4WAP2Result() {
        return extDebit4WAP2Result;
    }

    /**
     * Sets the value of the extDebit4WAP2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtDebit4WAP2Result(String value) {
        this.extDebit4WAP2Result = value;
    }

}
