//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.14 at 06:10:12 PM EDT 
//


package org.multigraph.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.multigraph.datatypes.string.StringValue;

public class Adapter4
    extends XmlAdapter<String, StringValue>
{


    public StringValue unmarshal(String value) {
        return (org.multigraph.datatypes.string.StringValue.parse(value));
    }

    public String marshal(StringValue value) {
        return (org.multigraph.datatypes.string.StringValue.toString(value));
    }

}
