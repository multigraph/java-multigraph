package org.multigraph.mugl.graph.axis;

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

public class Zoom extends MuglBean {

    private boolean allowed;
    private boolean allowedIsSet     = false;
    private boolean allowedIsDefault = false;
    private double min;
    private boolean minIsSet     = false;
    private boolean minIsDefault = false;
    private double max;
    private boolean maxIsSet     = false;
    private boolean maxIsDefault = false;
    private DataValue anchor;
    private boolean anchorIsSet     = false;
    private boolean anchorIsDefault = false;
	public boolean getAllowed() {
		return allowed;
	}
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	public void setAllowed(boolean allowed, boolean isDefault) {
        setAllowed(allowed);
        this.allowedIsSet = true;
        this.allowedIsDefault = isDefault;
    }
	public boolean haveAllowed() {
        return allowedIsSet;
    }
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public void setMin(double min, boolean isDefault) {
        setMin(min);
        this.minIsSet = true;
        this.minIsDefault = isDefault;
    }
	public boolean haveMin() {
        return minIsSet;
    }
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public void setMax(double max, boolean isDefault) {
        setMax(max);
        this.maxIsSet = true;
        this.maxIsDefault = isDefault;
    }
	public boolean haveMax() {
        return maxIsSet;
    }
	public DataValue getAnchor() {
		return anchor;
	}
	public void setAnchor(DataValue anchor) {
		this.anchor = anchor;
	}
	public void setAnchor(DataValue anchor, boolean isDefault) {
        setAnchor(anchor);
        this.anchorIsSet = true;
        this.anchorIsDefault = isDefault;
    }
	public boolean haveAnchor() {
        return anchorIsSet;
    }
    public static Zoom parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Axis parent ) throws Exception {
        final Zoom zoom = new Zoom();
        
        setProperty("allowed", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        zoom.setAllowed( (value.equals("true") || value.equals("yes")), isDefault );
                    }});
        
        setProperty("min", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        zoom.setMin( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("max", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        zoom.setMax( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("anchor", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        zoom.setAnchor( new DoubleDataValue(value), isDefault );
                    }});
        
        return zoom;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("zoom");
        if (allowedIsSet && (includeAll || !allowedIsDefault)) { element.setAttribute("allowed",  Boolean.toString(allowed) ); }
        if (minIsSet && (includeAll || !minIsDefault)) { element.setAttribute("min",  Double.toString(min) ); }
        if (maxIsSet && (includeAll || !maxIsDefault)) { element.setAttribute("max",  Double.toString(max) ); }
        if (anchorIsSet && (includeAll || !anchorIsDefault)) { element.setAttribute("anchor",  anchor.getStringValue() ); }
        return element;
    }
}
