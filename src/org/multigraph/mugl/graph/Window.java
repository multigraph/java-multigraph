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

public class Window extends MuglBean {

    private int margin;
    private boolean marginIsSet     = false;
    private boolean marginIsDefault = false;
    private int border;
    private boolean borderIsSet     = false;
    private boolean borderIsDefault = false;
    private RGBColor bordercolor;
    private boolean bordercolorIsSet     = false;
    private boolean bordercolorIsDefault = false;
    private int padding;
    private boolean paddingIsSet     = false;
    private boolean paddingIsDefault = false;
    private int width;
    private boolean widthIsSet     = false;
    private boolean widthIsDefault = false;
    private int height;
    private boolean heightIsSet     = false;
    private boolean heightIsDefault = false;
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
	public RGBColor getBordercolor() {
		return bordercolor;
	}
	public void setBordercolor(RGBColor bordercolor) {
		this.bordercolor = bordercolor;
	}
	public void setBordercolor(RGBColor bordercolor, boolean isDefault) {
        setBordercolor(bordercolor);
        this.bordercolorIsSet = true;
        this.bordercolorIsDefault = isDefault;
    }
	public boolean haveBordercolor() {
        return bordercolorIsSet;
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
    public static Window parse( Element node, Element defaultsNode, org.multigraph.mugl.Graph parent ) throws Exception {
        final Window window = new Window();
        
        setProperty("margin", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setMargin( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("border", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setBorder( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("bordercolor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setBordercolor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("padding", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setPadding( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("width", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setWidth( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("height", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        window.setHeight( Integer.parseInt(value), isDefault );
                    }});
        
        return window;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("window");
        if (marginIsSet && (includeAll || !marginIsDefault)) { element.setAttribute("margin",  Integer.toString(margin) ); }
        if (borderIsSet && (includeAll || !borderIsDefault)) { element.setAttribute("border",  Integer.toString(border) ); }
        if (bordercolorIsSet && (includeAll || !bordercolorIsDefault)) { element.setAttribute("bordercolor",  bordercolor.toString() ); }
        if (paddingIsSet && (includeAll || !paddingIsDefault)) { element.setAttribute("padding",  Integer.toString(padding) ); }
        if (widthIsSet && (includeAll || !widthIsDefault)) { element.setAttribute("width",  Integer.toString(width) ); }
        if (heightIsSet && (includeAll || !heightIsDefault)) { element.setAttribute("height",  Integer.toString(height) ); }
        return element;
    }
}
