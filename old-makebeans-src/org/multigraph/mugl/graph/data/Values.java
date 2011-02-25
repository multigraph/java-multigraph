package org.multigraph.mugl.graph.data;

import java.util.List;
import java.util.ArrayList;
import org.jdom.DataConversionException;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.xpath.XPath;

import org.multigraph.MuglBean;
import org.multigraph.DataValue;
import org.multigraph.DoubleDataValue;
import org.multigraph.DPoint;
import org.multigraph.RGBColor;

public class Values extends MuglBean {

    private String text;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public static Values parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Data parent ) throws Exception {
        final Values values = new Values();
        
        values.setText( node == null ? null : node.getText() );
        return values;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("values");
        element.setText( text );
        return element;
    }
}
