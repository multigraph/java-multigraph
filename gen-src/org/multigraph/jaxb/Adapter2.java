//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.12 at 06:12:52 PM EST 
//


package org.multigraph.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.multigraph.RGBColor;

public class Adapter2
    extends XmlAdapter<String, RGBColor>
{


    public RGBColor unmarshal(String value) {
        return (org.multigraph.RGBColor.parse(value));
    }

    public String marshal(RGBColor value) {
        return (org.multigraph.RGBColor.toString(value));
    }

}
