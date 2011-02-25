package org.multigraph.mugl.graph.plot.axis;

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

public class Variable extends MuglBean {

    private String ref;
    private boolean refIsSet     = false;
    private boolean refIsDefault = false;
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public void setRef(String ref, boolean isDefault) {
        setRef(ref);
        this.refIsSet = true;
        this.refIsDefault = isDefault;
    }
	public boolean haveRef() {
        return refIsSet;
    }
    public static Variable parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.plot.Axis parent ) throws Exception {
        final Variable variable = new Variable();
        
        setProperty("ref", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        variable.setRef( value, isDefault );
                    }});
        
        return variable;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("variable");
        if (refIsSet && (includeAll || !refIsDefault)) { element.setAttribute("ref",  ref ); }
        return element;
    }
}
