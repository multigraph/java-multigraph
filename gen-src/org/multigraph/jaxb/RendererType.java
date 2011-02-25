//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.25 at 12:35:34 AM EST 
//


package org.multigraph.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RendererType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RendererType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="pointline"/>
 *     &lt;enumeration value="line"/>
 *     &lt;enumeration value="bar"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RendererType")
@XmlEnum
public enum RendererType {

    @XmlEnumValue("pointline")
    POINTLINE("pointline"),
    @XmlEnumValue("line")
    LINE("line"),
    @XmlEnumValue("bar")
    BAR("bar");
    private final String value;

    RendererType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RendererType fromValue(String v) {
        for (RendererType c: RendererType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
