//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.24 at 11:56:19 PM EST 
//


package org.multigraph.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.multigraph.DPoint;


/**
 * <p>Java class for Labels complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Labels">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="label" type="{}Label" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="format" type="{http://www.w3.org/2001/XMLSchema}string" default="%1d" />
 *       &lt;attribute name="start" type="{http://www.w3.org/2001/XMLSchema}string" default="0" />
 *       &lt;attribute name="angle" type="{http://www.w3.org/2001/XMLSchema}double" default="0.0" />
 *       &lt;attribute name="position" type="{}DPoint" default="0 0" />
 *       &lt;attribute name="anchor" type="{}DPoint" default="0 0" />
 *       &lt;attribute name="spacing" type="{http://www.w3.org/2001/XMLSchema}string" default="10000 5000 2000 1000 500 200 100 50 20 10 5 2 1 0.1 0.01 0.001" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Labels", propOrder = {
    "label"
})
public class Labels {

    protected List<Label> label;
    @XmlAttribute
    protected String format;
    @XmlAttribute
    protected String start;
    @XmlAttribute
    protected Double angle;
    @XmlAttribute
    @XmlJavaTypeAdapter(Adapter3 .class)
    protected DPoint position;
    @XmlAttribute
    @XmlJavaTypeAdapter(Adapter3 .class)
    protected DPoint anchor;
    @XmlAttribute
    protected String spacing;

    /**
     * Gets the value of the label property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the label property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Label }
     * 
     * 
     */
    public List<Label> getLabel() {
        if (label == null) {
            label = new ArrayList<Label>();
        }
        return this.label;
    }

    public boolean isSetLabel() {
        return ((this.label!= null)&&(!this.label.isEmpty()));
    }

    public void unsetLabel() {
        this.label = null;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        if (format == null) {
            return "%1d";
        } else {
            return format;
        }
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    public boolean isSetFormat() {
        return (this.format!= null);
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStart() {
        if (start == null) {
            return "0";
        } else {
            return start;
        }
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStart(String value) {
        this.start = value;
    }

    public boolean isSetStart() {
        return (this.start!= null);
    }

    /**
     * Gets the value of the angle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getAngle() {
        if (angle == null) {
            return  0.0D;
        } else {
            return angle;
        }
    }

    /**
     * Sets the value of the angle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAngle(double value) {
        this.angle = value;
    }

    public boolean isSetAngle() {
        return (this.angle!= null);
    }

    public void unsetAngle() {
        this.angle = null;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public DPoint getPosition() {
        if (position == null) {
            return new Adapter3().unmarshal("0 0");
        } else {
            return position;
        }
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosition(DPoint value) {
        this.position = value;
    }

    public boolean isSetPosition() {
        return (this.position!= null);
    }

    /**
     * Gets the value of the anchor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public DPoint getAnchor() {
        if (anchor == null) {
            return new Adapter3().unmarshal("0 0");
        } else {
            return anchor;
        }
    }

    /**
     * Sets the value of the anchor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchor(DPoint value) {
        this.anchor = value;
    }

    public boolean isSetAnchor() {
        return (this.anchor!= null);
    }

    /**
     * Gets the value of the spacing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpacing() {
        if (spacing == null) {
            return "10000 5000 2000 1000 500 200 100 50 20 10 5 2 1 0.1 0.01 0.001";
        } else {
            return spacing;
        }
    }

    /**
     * Sets the value of the spacing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpacing(String value) {
        this.spacing = value;
    }

    public boolean isSetSpacing() {
        return (this.spacing!= null);
    }

}