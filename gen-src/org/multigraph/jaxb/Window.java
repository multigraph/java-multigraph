//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.25 at 09:07:43 AM EST 
//


package org.multigraph.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.multigraph.RGBColor;


/**
 * <p>Java class for Window complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Window">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="border" type="{http://www.w3.org/2001/XMLSchema}int" default="2" />
 *       &lt;attribute name="margin" type="{http://www.w3.org/2001/XMLSchema}int" default="2" />
 *       &lt;attribute name="padding" type="{http://www.w3.org/2001/XMLSchema}int" default="5" />
 *       &lt;attribute name="bordercolor" type="{}RGBColor" default="0x000000" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Window")
public class Window {

    @XmlAttribute(name = "border")
    protected Integer border;
    @XmlAttribute(name = "margin")
    protected Integer margin;
    @XmlAttribute(name = "padding")
    protected Integer padding;
    @XmlAttribute(name = "bordercolor")
    @XmlJavaTypeAdapter(Adapter2 .class)
    protected RGBColor bordercolor;

    /**
     * Gets the value of the border property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getBorder() {
        if (border == null) {
            return  2;
        } else {
            return border;
        }
    }

    /**
     * Sets the value of the border property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBorder(int value) {
        this.border = value;
    }

    public boolean isSetBorder() {
        return (this.border!= null);
    }

    public void unsetBorder() {
        this.border = null;
    }

    /**
     * Gets the value of the margin property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getMargin() {
        if (margin == null) {
            return  2;
        } else {
            return margin;
        }
    }

    /**
     * Sets the value of the margin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMargin(int value) {
        this.margin = value;
    }

    public boolean isSetMargin() {
        return (this.margin!= null);
    }

    public void unsetMargin() {
        this.margin = null;
    }

    /**
     * Gets the value of the padding property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPadding() {
        if (padding == null) {
            return  5;
        } else {
            return padding;
        }
    }

    /**
     * Sets the value of the padding property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPadding(int value) {
        this.padding = value;
    }

    public boolean isSetPadding() {
        return (this.padding!= null);
    }

    public void unsetPadding() {
        this.padding = null;
    }

    /**
     * Gets the value of the bordercolor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public RGBColor getBordercolor() {
        if (bordercolor == null) {
            return new Adapter2().unmarshal("0x000000");
        } else {
            return bordercolor;
        }
    }

    /**
     * Sets the value of the bordercolor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBordercolor(RGBColor value) {
        this.bordercolor = value;
    }

    public boolean isSetBordercolor() {
        return (this.bordercolor!= null);
    }

}
