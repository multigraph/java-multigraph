//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.28 at 08:07:00 PM EST 
//


package org.multigraph.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.multigraph.DataType;

public class Adapter3
    extends XmlAdapter<String, DataType>
{


    public DataType unmarshal(String value) {
        return (org.multigraph.DataType.parse(value));
    }

    public String marshal(DataType value) {
        return (org.multigraph.DataType.toString(value));
    }

}
