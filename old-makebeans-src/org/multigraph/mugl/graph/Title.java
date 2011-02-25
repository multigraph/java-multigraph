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

public class Title extends MuglBean {

    private String text;
    private String frame;
    private boolean frameIsSet     = false;
    private boolean frameIsDefault = false;
    private DPoint base;
    private boolean baseIsSet     = false;
    private boolean baseIsDefault = false;
    private DPoint anchor;
    private boolean anchorIsSet     = false;
    private boolean anchorIsDefault = false;
    private DPoint position;
    private boolean positionIsSet     = false;
    private boolean positionIsDefault = false;
    private int border;
    private boolean borderIsSet     = false;
    private boolean borderIsDefault = false;
    private RGBColor color;
    private boolean colorIsSet     = false;
    private boolean colorIsDefault = false;
    private RGBColor bordercolor;
    private boolean bordercolorIsSet     = false;
    private boolean bordercolorIsDefault = false;
    private double opacity;
    private boolean opacityIsSet     = false;
    private boolean opacityIsDefault = false;
    private boolean round;
    private boolean roundIsSet     = false;
    private boolean roundIsDefault = false;
    private int radius;
    private boolean radiusIsSet     = false;
    private boolean radiusIsDefault = false;
    private int padding;
    private boolean paddingIsSet     = false;
    private boolean paddingIsDefault = false;
    private int fontsize;
    private boolean fontsizeIsSet     = false;
    private boolean fontsizeIsDefault = false;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
	public String getFrame() {
		return frame;
	}
	public void setFrame(String frame) {
		this.frame = frame;
	}
	public void setFrame(String frame, boolean isDefault) {
        setFrame(frame);
        this.frameIsSet = true;
        this.frameIsDefault = isDefault;
    }
	public boolean haveFrame() {
        return frameIsSet;
    }
	public DPoint getBase() {
		return base;
	}
	public void setBase(DPoint base) {
		this.base = base;
	}
	public void setBase(DPoint base, boolean isDefault) {
        setBase(base);
        this.baseIsSet = true;
        this.baseIsDefault = isDefault;
    }
	public boolean haveBase() {
        return baseIsSet;
    }
	public DPoint getAnchor() {
		return anchor;
	}
	public void setAnchor(DPoint anchor) {
		this.anchor = anchor;
	}
	public void setAnchor(DPoint anchor, boolean isDefault) {
        setAnchor(anchor);
        this.anchorIsSet = true;
        this.anchorIsDefault = isDefault;
    }
	public boolean haveAnchor() {
        return anchorIsSet;
    }
	public DPoint getPosition() {
		return position;
	}
	public void setPosition(DPoint position) {
		this.position = position;
	}
	public void setPosition(DPoint position, boolean isDefault) {
        setPosition(position);
        this.positionIsSet = true;
        this.positionIsDefault = isDefault;
    }
	public boolean havePosition() {
        return positionIsSet;
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
	public RGBColor getColor() {
		return color;
	}
	public void setColor(RGBColor color) {
		this.color = color;
	}
	public void setColor(RGBColor color, boolean isDefault) {
        setColor(color);
        this.colorIsSet = true;
        this.colorIsDefault = isDefault;
    }
	public boolean haveColor() {
        return colorIsSet;
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
	public double getOpacity() {
		return opacity;
	}
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}
	public void setOpacity(double opacity, boolean isDefault) {
        setOpacity(opacity);
        this.opacityIsSet = true;
        this.opacityIsDefault = isDefault;
    }
	public boolean haveOpacity() {
        return opacityIsSet;
    }
	public boolean getRound() {
		return round;
	}
	public void setRound(boolean round) {
		this.round = round;
	}
	public void setRound(boolean round, boolean isDefault) {
        setRound(round);
        this.roundIsSet = true;
        this.roundIsDefault = isDefault;
    }
	public boolean haveRound() {
        return roundIsSet;
    }
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public void setRadius(int radius, boolean isDefault) {
        setRadius(radius);
        this.radiusIsSet = true;
        this.radiusIsDefault = isDefault;
    }
	public boolean haveRadius() {
        return radiusIsSet;
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
	public int getFontsize() {
		return fontsize;
	}
	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
	}
	public void setFontsize(int fontsize, boolean isDefault) {
        setFontsize(fontsize);
        this.fontsizeIsSet = true;
        this.fontsizeIsDefault = isDefault;
    }
	public boolean haveFontsize() {
        return fontsizeIsSet;
    }
    public static Title parse( Element node, Element defaultsNode, org.multigraph.mugl.Graph parent ) throws Exception {
        final Title title = new Title();
        
        setProperty("frame", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setFrame( value, isDefault );
                    }});
        
        setProperty("base", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setBase( new DPoint(value), isDefault );
                    }});
        
        setProperty("anchor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setAnchor( new DPoint(value), isDefault );
                    }});
        
        setProperty("position", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setPosition( new DPoint(value), isDefault );
                    }});
        
        setProperty("border", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setBorder( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("color", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setColor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("bordercolor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setBordercolor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("opacity", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setOpacity( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("round", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setRound( (value.equals("true") || value.equals("yes")), isDefault );
                    }});
        
        setProperty("radius", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setRadius( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("padding", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setPadding( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("fontsize", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setFontsize( Integer.parseInt(value), isDefault );
                    }});
        
        title.setText( node == null ? null : node.getText() );
        return title;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("title");
        if (frameIsSet && (includeAll || !frameIsDefault)) { element.setAttribute("frame",  frame ); }
        if (baseIsSet && (includeAll || !baseIsDefault)) { element.setAttribute("base",  base.toString() ); }
        if (anchorIsSet && (includeAll || !anchorIsDefault)) { element.setAttribute("anchor",  anchor.toString() ); }
        if (positionIsSet && (includeAll || !positionIsDefault)) { element.setAttribute("position",  position.toString() ); }
        if (borderIsSet && (includeAll || !borderIsDefault)) { element.setAttribute("border",  Integer.toString(border) ); }
        if (colorIsSet && (includeAll || !colorIsDefault)) { element.setAttribute("color",  color.toString() ); }
        if (bordercolorIsSet && (includeAll || !bordercolorIsDefault)) { element.setAttribute("bordercolor",  bordercolor.toString() ); }
        if (opacityIsSet && (includeAll || !opacityIsDefault)) { element.setAttribute("opacity",  Double.toString(opacity) ); }
        if (roundIsSet && (includeAll || !roundIsDefault)) { element.setAttribute("round",  Boolean.toString(round) ); }
        if (radiusIsSet && (includeAll || !radiusIsDefault)) { element.setAttribute("radius",  Integer.toString(radius) ); }
        if (paddingIsSet && (includeAll || !paddingIsDefault)) { element.setAttribute("padding",  Integer.toString(padding) ); }
        if (fontsizeIsSet && (includeAll || !fontsizeIsDefault)) { element.setAttribute("fontsize",  Integer.toString(fontsize) ); }
        element.setText( text );
        return element;
    }
}
