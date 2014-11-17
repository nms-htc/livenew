
package com.nms.ws.charging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contentPurcharseReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contentPurcharseReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://chargingwebservice.nms.com/}requestBase">
 *       &lt;sequence>
 *         &lt;element name="contentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="osCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="shortCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contentPurcharseReq", propOrder = {
    "contentCode",
    "cpName",
    "isdn",
    "osCode",
    "price",
    "shortCode"
})
public class ContentPurcharseReq
    extends RequestBase
{

    protected String contentCode;
    protected String cpName;
    protected String isdn;
    protected String osCode;
    protected double price;
    protected String shortCode;

    /**
     * Gets the value of the contentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentCode() {
        return contentCode;
    }

    /**
     * Sets the value of the contentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentCode(String value) {
        this.contentCode = value;
    }

    /**
     * Gets the value of the cpName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpName() {
        return cpName;
    }

    /**
     * Sets the value of the cpName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpName(String value) {
        this.cpName = value;
    }

    /**
     * Gets the value of the isdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsdn() {
        return isdn;
    }

    /**
     * Sets the value of the isdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsdn(String value) {
        this.isdn = value;
    }

    /**
     * Gets the value of the osCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsCode() {
        return osCode;
    }

    /**
     * Sets the value of the osCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsCode(String value) {
        this.osCode = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the shortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortCode() {
        return shortCode;
    }

    /**
     * Sets the value of the shortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortCode(String value) {
        this.shortCode = value;
    }

}
