//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.01 at 03:16:16 PM EST 
//


package org.multigraph.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.multigraph.DPoint;

public class Adapter5
    extends XmlAdapter<String, DPoint>
{


    public DPoint unmarshal(String value) {
        return (org.multigraph.DPoint.parse(value));
    }

    public String marshal(DPoint value) {
        return (org.multigraph.DPoint.toString(value));
    }

}
