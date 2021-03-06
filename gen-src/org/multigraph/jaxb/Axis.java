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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.multigraph.AxisOrientation;
import org.multigraph.RGBColor;
import org.multigraph.datatypes.DataType;
import org.multigraph.datatypes.string.StringValue;


/**
 * <p>Java class for Axis complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Axis">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="labels" type="{}Labels" minOccurs="0"/>
 *         &lt;element name="grid" type="{}Grid" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" type="{}DataType" default="number" />
 *       &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}double" default="1.0" />
 *       &lt;attribute name="base" type="{http://www.w3.org/2001/XMLSchema}string" default="-1 1" />
 *       &lt;attribute name="min" type="{}DataValueString" default="auto" />
 *       &lt;attribute name="max" type="{}DataValueString" default="auto" />
 *       &lt;attribute name="tickmin" type="{http://www.w3.org/2001/XMLSchema}int" default="-3" />
 *       &lt;attribute name="tickmax" type="{http://www.w3.org/2001/XMLSchema}int" default="3" />
 *       &lt;attribute name="linewidth" type="{http://www.w3.org/2001/XMLSchema}int" default="1" />
 *       &lt;attribute name="color" type="{}RGBColor" default="0x000000" />
 *       &lt;attribute name="orientation" type="{}AxisOrientation" default="unknown" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Axis", propOrder = {
    "labels",
    "grid"
})
@XmlSeeAlso({
    VerticalAxis.class,
    HorizontalAxis.class
})
public class Axis {

    protected Labels labels;
    protected Grid grid;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(Adapter3 .class)
    protected DataType type;
    @XmlAttribute(name = "position")
    protected Integer position;
    @XmlAttribute(name = "length")
    protected Double length;
    @XmlAttribute(name = "base")
    protected String base;
    @XmlAttribute(name = "min")
    @XmlJavaTypeAdapter(Adapter4 .class)
    protected StringValue min;
    @XmlAttribute(name = "max")
    @XmlJavaTypeAdapter(Adapter4 .class)
    protected StringValue max;
    @XmlAttribute(name = "tickmin")
    protected Integer tickmin;
    @XmlAttribute(name = "tickmax")
    protected Integer tickmax;
    @XmlAttribute(name = "linewidth")
    protected Integer linewidth;
    @XmlAttribute(name = "color")
    @XmlJavaTypeAdapter(Adapter2 .class)
    protected RGBColor color;
    @XmlAttribute(name = "orientation")
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected AxisOrientation orientation;

    /**
     * Gets the value of the labels property.
     * 
     * @return
     *     possible object is
     *     {@link Labels }
     *     
     */
    public Labels getLabels() {
        return labels;
    }

    /**
     * Sets the value of the labels property.
     * 
     * @param value
     *     allowed object is
     *     {@link Labels }
     *     
     */
    public void setLabels(Labels value) {
        this.labels = value;
    }

    public boolean isSetLabels() {
        return (this.labels!= null);
    }

    /**
     * Gets the value of the grid property.
     * 
     * @return
     *     possible object is
     *     {@link Grid }
     *     
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Sets the value of the grid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Grid }
     *     
     */
    public void setGrid(Grid value) {
        this.grid = value;
    }

    public boolean isSetGrid() {
        return (this.grid!= null);
    }

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
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPosition() {
        if (position == null) {
            return  0;
        } else {
            return position;
        }
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPosition(int value) {
        this.position = value;
    }

    public boolean isSetPosition() {
        return (this.position!= null);
    }

    public void unsetPosition() {
        this.position = null;
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getLength() {
        if (length == null) {
            return  1.0D;
        } else {
            return length;
        }
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLength(double value) {
        this.length = value;
    }

    public boolean isSetLength() {
        return (this.length!= null);
    }

    public void unsetLength() {
        this.length = null;
    }

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase() {
        if (base == null) {
            return "-1 1";
        } else {
            return base;
        }
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase(String value) {
        this.base = value;
    }

    public boolean isSetBase() {
        return (this.base!= null);
    }

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public StringValue getMin() {
        if (min == null) {
            return new Adapter4().unmarshal("auto");
        } else {
            return min;
        }
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMin(StringValue value) {
        this.min = value;
    }

    public boolean isSetMin() {
        return (this.min!= null);
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public StringValue getMax() {
        if (max == null) {
            return new Adapter4().unmarshal("auto");
        } else {
            return max;
        }
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMax(StringValue value) {
        this.max = value;
    }

    public boolean isSetMax() {
        return (this.max!= null);
    }

    /**
     * Gets the value of the tickmin property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getTickmin() {
        if (tickmin == null) {
            return -3;
        } else {
            return tickmin;
        }
    }

    /**
     * Sets the value of the tickmin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTickmin(int value) {
        this.tickmin = value;
    }

    public boolean isSetTickmin() {
        return (this.tickmin!= null);
    }

    public void unsetTickmin() {
        this.tickmin = null;
    }

    /**
     * Gets the value of the tickmax property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getTickmax() {
        if (tickmax == null) {
            return  3;
        } else {
            return tickmax;
        }
    }

    /**
     * Sets the value of the tickmax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTickmax(int value) {
        this.tickmax = value;
    }

    public boolean isSetTickmax() {
        return (this.tickmax!= null);
    }

    public void unsetTickmax() {
        this.tickmax = null;
    }

    /**
     * Gets the value of the linewidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getLinewidth() {
        if (linewidth == null) {
            return  1;
        } else {
            return linewidth;
        }
    }

    /**
     * Sets the value of the linewidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLinewidth(int value) {
        this.linewidth = value;
    }

    public boolean isSetLinewidth() {
        return (this.linewidth!= null);
    }

    public void unsetLinewidth() {
        this.linewidth = null;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public RGBColor getColor() {
        if (color == null) {
            return new Adapter2().unmarshal("0x000000");
        } else {
            return color;
        }
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColor(RGBColor value) {
        this.color = value;
    }

    public boolean isSetColor() {
        return (this.color!= null);
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public AxisOrientation getOrientation() {
        if (orientation == null) {
            return new Adapter1().unmarshal("unknown");
        } else {
            return orientation;
        }
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrientation(AxisOrientation value) {
        this.orientation = value;
    }

    public boolean isSetOrientation() {
        return (this.orientation!= null);
    }

}
