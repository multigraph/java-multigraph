//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.14 at 06:10:12 PM EDT 
//


package org.multigraph.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Variables complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Variables">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="variable" type="{}Variable" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="missingvalue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="missingop" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Variables", propOrder = {
    "variable"
})
public class Variables {

    protected List<Variable> variable;
    @XmlAttribute(name = "missingvalue")
    protected String missingvalue;
    @XmlAttribute(name = "missingop")
    protected String missingop;

    /**
     * Gets the value of the variable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Variable }
     * 
     * 
     */
    public List<Variable> getVariable() {
        if (variable == null) {
            variable = new ArrayList<Variable>();
        }
        return this.variable;
    }

    public boolean isSetVariable() {
        return ((this.variable!= null)&&(!this.variable.isEmpty()));
    }

    public void unsetVariable() {
        this.variable = null;
    }

    /**
     * Gets the value of the missingvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMissingvalue() {
        return missingvalue;
    }

    /**
     * Sets the value of the missingvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMissingvalue(String value) {
        this.missingvalue = value;
    }

    public boolean isSetMissingvalue() {
        return (this.missingvalue!= null);
    }

    /**
     * Gets the value of the missingop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMissingop() {
        return missingop;
    }

    /**
     * Sets the value of the missingop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMissingop(String value) {
        this.missingop = value;
    }

    public boolean isSetMissingop() {
        return (this.missingop!= null);
    }

}
