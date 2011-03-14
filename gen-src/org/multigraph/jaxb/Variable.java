//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.14 at 06:10:12 PM EDT 
//


package org.multigraph.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.multigraph.datatypes.DataType;


/**
 * <p>Java class for Variable complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Variable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="column" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="type" type="{}DataType" default="number" />
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
@XmlType(name = "Variable")
public class Variable {

    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "column")
    protected Integer column;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(Adapter3 .class)
    protected DataType type;
    @XmlAttribute(name = "missingvalue")
    protected String missingvalue;
    @XmlAttribute(name = "missingop")
    protected String missingop;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
    }

    /**
     * Gets the value of the column property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the value of the column property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setColumn(int value) {
        this.column = value;
    }

    public boolean isSetColumn() {
        return (this.column!= null);
    }

    public void unsetColumn() {
        this.column = null;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public DataType getType() {
        if (type == null) {
            return new Adapter3().unmarshal("number");
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(DataType value) {
        this.type = value;
    }

    public boolean isSetType() {
        return (this.type!= null);
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
