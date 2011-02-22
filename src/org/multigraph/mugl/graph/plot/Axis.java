package org.multigraph.mugl.graph.plot;

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

public class Axis extends MuglBean {
    private org.multigraph.mugl.graph.Axis.Orientation orientation;
    public org.multigraph.mugl.graph.Axis.Orientation getOrientation() {
        return orientation;
    }
    public void setOrientation(org.multigraph.mugl.graph.Axis.Orientation orientation) {
        this.orientation = orientation;
    }

    private String ref;
    private boolean refIsSet     = false;
    private boolean refIsDefault = false;
    private ArrayList<org.multigraph.mugl.graph.plot.axis.Variable> variables = new ArrayList<org.multigraph.mugl.graph.plot.axis.Variable>();
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
	public ArrayList<org.multigraph.mugl.graph.plot.axis.Variable> getVariables() {
		return variables;
	}
    public static Axis parse(org.multigraph.mugl.graph.Axis.Orientation orientation, Element node, Element defaultsNode, org.multigraph.mugl.graph.Plot parent ) throws Exception {
        final Axis axis = new Axis();
        axis.setOrientation(orientation);
        setProperty("ref", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setRef( value, isDefault );
                    }});
        
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "variable"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "variable");
            for (Element element : elements) {
                if (element != null) {
                    axis.getVariables().add( org.multigraph.mugl.graph.plot.axis.Variable.parse( element, defaultsElement, axis) );
                } else {
                	org.multigraph.mugl.graph.plot.axis.Variable variable = org.multigraph.mugl.graph.plot.axis.Variable.parse( null, defaultsElement, axis);
                    
                    axis.getVariables().add( variable );
                }
            }
        }
        return axis;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element(org.multigraph.mugl.graph.Axis.OrientationToString(orientation));
        if (refIsSet && (includeAll || !refIsDefault)) { element.setAttribute("ref",  ref ); }
        for (org.multigraph.mugl.graph.plot.axis.Variable variable : variables) {
            element.addContent( variable.build(includeAll)  );
        }
        return element;
    }
}
