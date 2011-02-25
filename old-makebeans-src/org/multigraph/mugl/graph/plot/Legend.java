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

public class Legend extends MuglBean {

    private String label;
    private boolean labelIsSet     = false;
    private boolean labelIsDefault = false;
    private boolean visible;
    private boolean visibleIsSet     = false;
    private boolean visibleIsDefault = false;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setLabel(String label, boolean isDefault) {
        setLabel(label);
        this.labelIsSet = true;
        this.labelIsDefault = isDefault;
    }
	public boolean haveLabel() {
        return labelIsSet;
    }
	public boolean getVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void setVisible(boolean visible, boolean isDefault) {
        setVisible(visible);
        this.visibleIsSet = true;
        this.visibleIsDefault = isDefault;
    }
	public boolean haveVisible() {
        return visibleIsSet;
    }
    public static Legend parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Plot parent ) throws Exception {
        final Legend legend = new Legend();
        
        setProperty("label", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setLabel( value, isDefault );
                    }});
        
        setProperty("visible", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setVisible( (value.equals("true") || value.equals("yes")), isDefault );
                    }});
        
        return legend;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("legend");
        if (labelIsSet && (includeAll || !labelIsDefault)) { element.setAttribute("label",  label ); }
        if (visibleIsSet && (includeAll || !visibleIsDefault)) { element.setAttribute("visible",  Boolean.toString(visible) ); }
        return element;
    }
}
