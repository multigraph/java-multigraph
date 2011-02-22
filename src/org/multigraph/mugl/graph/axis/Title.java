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

public class Title extends MuglBean {

    private String text;
    private String fontname;
    private boolean fontnameIsSet     = false;
    private boolean fontnameIsDefault = false;
    private int fontsize;
    private boolean fontsizeIsSet     = false;
    private boolean fontsizeIsDefault = false;
    private RGBColor fontcolor;
    private boolean fontcolorIsSet     = false;
    private boolean fontcolorIsDefault = false;
    private double angle;
    private boolean angleIsSet     = false;
    private boolean angleIsDefault = false;
    private DPoint position;
    private boolean positionIsSet     = false;
    private boolean positionIsDefault = false;
    private DPoint anchor;
    private boolean anchorIsSet     = false;
    private boolean anchorIsDefault = false;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
	public String getFontname() {
		return fontname;
	}
	public void setFontname(String fontname) {
		this.fontname = fontname;
	}
	public void setFontname(String fontname, boolean isDefault) {
        setFontname(fontname);
        this.fontnameIsSet = true;
        this.fontnameIsDefault = isDefault;
    }
	public boolean haveFontname() {
        return fontnameIsSet;
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
	public RGBColor getFontcolor() {
		return fontcolor;
	}
	public void setFontcolor(RGBColor fontcolor) {
		this.fontcolor = fontcolor;
	}
	public void setFontcolor(RGBColor fontcolor, boolean isDefault) {
        setFontcolor(fontcolor);
        this.fontcolorIsSet = true;
        this.fontcolorIsDefault = isDefault;
    }
	public boolean haveFontcolor() {
        return fontcolorIsSet;
    }
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void setAngle(double angle, boolean isDefault) {
        setAngle(angle);
        this.angleIsSet = true;
        this.angleIsDefault = isDefault;
    }
	public boolean haveAngle() {
        return angleIsSet;
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
    public static Title parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Axis parent ) throws Exception {
        final Title title = new Title();
        
        setProperty("fontname", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setFontname( value, isDefault );
                    }});
        
        setProperty("fontsize", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setFontsize( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("fontcolor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setFontcolor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("angle", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setAngle( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("position", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setPosition( new DPoint(value), isDefault );
                    }});
        
        setProperty("anchor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        title.setAnchor( new DPoint(value), isDefault );
                    }});
        
        title.setText( node == null ? null : node.getText() );
        return title;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("title");
        if (fontnameIsSet && (includeAll || !fontnameIsDefault)) { element.setAttribute("fontname",  fontname ); }
        if (fontsizeIsSet && (includeAll || !fontsizeIsDefault)) { element.setAttribute("fontsize",  Integer.toString(fontsize) ); }
        if (fontcolorIsSet && (includeAll || !fontcolorIsDefault)) { element.setAttribute("fontcolor",  fontcolor.toString() ); }
        if (angleIsSet && (includeAll || !angleIsDefault)) { element.setAttribute("angle",  Double.toString(angle) ); }
        if (positionIsSet && (includeAll || !positionIsDefault)) { element.setAttribute("position",  position.toString() ); }
        if (anchorIsSet && (includeAll || !anchorIsDefault)) { element.setAttribute("anchor",  anchor.toString() ); }
        element.setText( text );
        return element;
    }
}
