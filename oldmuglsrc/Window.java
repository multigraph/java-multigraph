package org.multigraph.mugl.graph;

import java.util.List;
import java.util.ArrayList;
import org.jdom.DataConversionException;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.xpath.XPath;

import org.multigraph.DataValue;
import org.multigraph.DoubleDataValue;
import org.multigraph.DPoint;
import org.multigraph.RGBColor;

public class org.multigraph.mugl.graph.Window extends MuglBean {

    private int margin;
    private boolean marginIsSet     = false;
    private boolean marginIsDefault = false;
    private int border;
    private boolean borderIsSet     = false;
    private boolean borderIsDefault = false;
    private int padding;
    private boolean paddingIsSet     = false;
    private boolean paddingIsDefault = false;
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public void setMargin(int margin, boolean isDefault) {
        setMargin(margin);
        this.marginIsSet = true;
        this.marginIsDefault = isDefault;
    }
	public boolean haveMargin() {
        return marginIsSet;
    }
	public int getBorder() {
		return border;
	}
	public void setBorder(int border) {
		this.border = border;
	}
	public void setBorder(int border, boolean isDefault) {
        setBorder(border);
        this.borderIsSet = true;
        this.borderIsDefault = isDefault;
    }
	public boolean haveBorder() {
        return borderIsSet;
    }
	public int getPadding() {
		return padding;
	}
	public void setPadding(int padding) {
		this.padding = padding;
	}
	public void setPadding(int padding, boolean isDefault) {
        setPadding(padding);
        this.paddingIsSet = true;
        this.paddingIsDefault = isDefault;
    }
	public boolean havePadding() {
        return paddingIsSet;
    }
    public static org.multigraph.mugl.graph.Window parse( Element node, Element defaultsNode, Graph parent ) throws Exception {
        final org.multigraph.mugl.graph.Window window = new org.multigraph.mugl.graph.Window();
        
        setProperty("margin", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setMargin( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("border", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setBorder( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("padding", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setPadding( Integer.parseInt(value), isDefault );
                    }});
        
        return window;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("window");
        if (marginIsSet && (includeAll || !marginIsDefault)) { element.setAttribute("margin",  Integer.toString(margin) ); }
        if (borderIsSet && (includeAll || !borderIsDefault)) { element.setAttribute("border",  Integer.toString(border) ); }
        if (paddingIsSet && (includeAll || !paddingIsDefault)) { element.setAttribute("padding",  Integer.toString(padding) ); }
        return element;
    }
}
