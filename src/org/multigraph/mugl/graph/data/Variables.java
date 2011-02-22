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

public class Variables extends MuglBean {

    private String missingvalue;
    private boolean missingvalueIsSet     = false;
    private boolean missingvalueIsDefault = false;
    private String missingop;
    private boolean missingopIsSet     = false;
    private boolean missingopIsDefault = false;
    private ArrayList<org.multigraph.mugl.graph.data.variables.Variable> variables = new ArrayList<org.multigraph.mugl.graph.data.variables.Variable>();
	public String getMissingvalue() {
		return missingvalue;
	}
	public void setMissingvalue(String missingvalue) {
		this.missingvalue = missingvalue;
	}
	public void setMissingvalue(String missingvalue, boolean isDefault) {
        setMissingvalue(missingvalue);
        this.missingvalueIsSet = true;
        this.missingvalueIsDefault = isDefault;
    }
	public boolean haveMissingvalue() {
        return missingvalueIsSet;
    }
	public String getMissingop() {
		return missingop;
	}
	public void setMissingop(String missingop) {
		this.missingop = missingop;
	}
	public void setMissingop(String missingop, boolean isDefault) {
        setMissingop(missingop);
        this.missingopIsSet = true;
        this.missingopIsDefault = isDefault;
    }
	public boolean haveMissingop() {
        return missingopIsSet;
    }
	public ArrayList<org.multigraph.mugl.graph.data.variables.Variable> getVariables() {
		return variables;
	}
    public static Variables parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Data parent ) throws Exception {
        final Variables variables = new Variables();
        
        setProperty("missingvalue", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        variables.setMissingvalue( value, isDefault );
                    }});
        
        setProperty("missingop", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        variables.setMissingop( value, isDefault );
                    }});
        
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "variable"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "variable");
            for (Element element : elements) {
                if (element != null) {
                    variables.getVariables().add( org.multigraph.mugl.graph.data.variables.Variable.parse( element, defaultsElement, variables) );
                } else {
                	org.multigraph.mugl.graph.data.variables.Variable variable = org.multigraph.mugl.graph.data.variables.Variable.parse( null, defaultsElement, variables);
                    
                    variables.getVariables().add( variable );
                }
            }
        }
        return variables;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("variables");
        if (missingvalueIsSet && (includeAll || !missingvalueIsDefault)) { element.setAttribute("missingvalue",  missingvalue ); }
        if (missingopIsSet && (includeAll || !missingopIsDefault)) { element.setAttribute("missingop",  missingop ); }
        for (org.multigraph.mugl.graph.data.variables.Variable variable : variables) {
            element.addContent( variable.build(includeAll)  );
        }
        return element;
    }
}
