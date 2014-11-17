
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
 *         &lt;element name="extDebit2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "extDebit2Result"
})
@XmlRootElement(name = "extDebit2Response")
public class ExtDebit2Response {

    protected String extDebit2Result;

    /**
     * Gets the value of the extDebit2Result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtDebit2Result() {
        return extDebit2Result;
    }

    /**
     * Sets the value of the extDebit2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtDebit2Result(String value) {
        this.extDebit2Result = value;
    }

}
