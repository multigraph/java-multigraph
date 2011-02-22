package org.multigraph.mugl.graph.legend;

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

public class Icon extends MuglBean {

    private int border;
    private boolean borderIsSet     = false;
    private boolean borderIsDefault = false;
    private int width;
    private boolean widthIsSet     = false;
    private boolean widthIsDefault = false;
    private int height;
    private boolean heightIsSet     = false;
    private boolean heightIsDefault = false;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setWidth(int width, boolean isDefault) {
        setWidth(width);
        this.widthIsSet = true;
        this.widthIsDefault = isDefault;
    }
	public boolean haveWidth() {
        return widthIsSet;
    }
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setHeight(int height, boolean isDefault) {
        setHeight(height);
        this.heightIsSet = true;
        this.heightIsDefault = isDefault;
    }
	public boolean haveHeight() {
        return heightIsSet;
    }
    public static Icon parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Legend parent ) throws Exception {
        final Icon icon = new Icon();
        
        setProperty("border", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        icon.setBorder( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("width", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        icon.setWidth( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("height", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        icon.setHeight( Integer.parseInt(value), isDefault );
                    }});
        
        return icon;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("icon");
        if (borderIsSet && (includeAll || !borderIsDefault)) { element.setAttribute("border",  Integer.toString(border) ); }
        if (widthIsSet && (includeAll || !widthIsDefault)) { element.setAttribute("width",  Integer.toString(width) ); }
        if (heightIsSet && (includeAll || !heightIsDefault)) { element.setAttribute("height",  Integer.toString(height) ); }
        return element;
    }
}
