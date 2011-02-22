package org.multigraph.mugl.graph;

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

public class Debug extends MuglBean {

    private RGBColor xcolor;
    private boolean xcolorIsSet     = false;
    private boolean xcolorIsDefault = false;
    private double xwidth;
    private boolean xwidthIsSet     = false;
    private boolean xwidthIsDefault = false;
	public RGBColor getXcolor() {
		return xcolor;
	}
	public void setXcolor(RGBColor xcolor) {
		this.xcolor = xcolor;
	}
	public void setXcolor(RGBColor xcolor, boolean isDefault) {
        setXcolor(xcolor);
        this.xcolorIsSet = true;
        this.xcolorIsDefault = isDefault;
    }
	public boolean haveXcolor() {
        return xcolorIsSet;
    }
	public double getXwidth() {
		return xwidth;
	}
	public void setXwidth(double xwidth) {
		this.xwidth = xwidth;
	}
	public void setXwidth(double xwidth, boolean isDefault) {
        setXwidth(xwidth);
        this.xwidthIsSet = true;
        this.xwidthIsDefault = isDefault;
    }
	public boolean haveXwidth() {
        return xwidthIsSet;
    }
    public static Debug parse( Element node, Element defaultsNode, org.multigraph.mugl.Graph parent ) throws Exception {
        final Debug debug = new Debug();
        
        setProperty("xcolor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        debug.setXcolor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("xwidth", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        debug.setXwidth( Double.parseDouble(value), isDefault );
                    }});
        
        return debug;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("debug");
        if (xcolorIsSet && (includeAll || !xcolorIsDefault)) { element.setAttribute("xcolor",  xcolor.toString() ); }
        if (xwidthIsSet && (includeAll || !xwidthIsDefault)) { element.setAttribute("xwidth",  Double.toString(xwidth) ); }
        return element;
    }
}
