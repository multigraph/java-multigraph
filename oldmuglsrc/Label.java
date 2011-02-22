package org.multigraph.mugl.graph.axis.labels;

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

public class org.multigraph.mugl.graph.axis.labels.Label extends MuglBean {

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
    private String spacing;
    private boolean spacingIsSet     = false;
    private boolean spacingIsDefault = false;
    private DataValue start;
    private boolean startIsSet     = false;
    private boolean startIsDefault = false;
    private String format;
    private boolean formatIsSet     = false;
    private boolean formatIsDefault = false;
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
	public String getSpacing() {
		return spacing;
	}
	public void setSpacing(String spacing) {
		this.spacing = spacing;
	}
	public void setSpacing(String spacing, boolean isDefault) {
        setSpacing(spacing);
        this.spacingIsSet = true;
        this.spacingIsDefault = isDefault;
    }
	public boolean haveSpacing() {
        return spacingIsSet;
    }
	public DataValue getStart() {
		return start;
	}
	public void setStart(DataValue start) {
		this.start = start;
	}
	public void setStart(DataValue start, boolean isDefault) {
        setStart(start);
        this.startIsSet = true;
        this.startIsDefault = isDefault;
    }
	public boolean haveStart() {
        return startIsSet;
    }
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public void setFormat(String format, boolean isDefault) {
        setFormat(format);
        this.formatIsSet = true;
        this.formatIsDefault = isDefault;
    }
	public boolean haveFormat() {
        return formatIsSet;
    }
    public static org.multigraph.mugl.graph.axis.labels.Label parse( Element node, Element defaultsNode, Labels parent ) throws Exception {
        final org.multigraph.mugl.graph.axis.labels.Label label = new org.multigraph.mugl.graph.axis.labels.Label();
        
        setProperty("fontname", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setFontname( value, isDefault );
                    }});
        if (!label.haveFontname()) { label.setFontname(parent.getFontname(),true); }
        setProperty("fontsize", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setFontsize( Integer.parseInt(value), isDefault );
                    }});
        if (!label.haveFontsize()) { label.setFontsize(parent.getFontsize(),true); }
        setProperty("fontcolor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setFontcolor( new RGBColor(value), isDefault );
                    }});
        if (!label.haveFontcolor()) { label.setFontcolor(parent.getFontcolor(),true); }
        setProperty("angle", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setAngle( Double.parseDouble(value), isDefault );
                    }});
        if (!label.haveAngle()) { label.setAngle(parent.getAngle(),true); }
        setProperty("position", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setPosition( new DPoint(value), isDefault );
                    }});
        if (!label.havePosition()) { label.setPosition(parent.getPosition(),true); }
        setProperty("anchor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setAnchor( new DPoint(value), isDefault );
                    }});
        if (!label.haveAnchor()) { label.setAnchor(parent.getAnchor(),true); }
        setProperty("spacing", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setSpacing( value, isDefault );
                    }});
        if (!label.haveSpacing()) { label.setSpacing(parent.getSpacing(),true); }
        setProperty("start", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setStart( new DoubleDataValue(value), isDefault );
                    }});
        if (!label.haveStart()) { label.setStart(parent.getStart(),true); }
        setProperty("format", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        label.setFormat( value, isDefault );
                    }});
        if (!label.haveFormat()) { label.setFormat(parent.getFormat(),true); }
        return label;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("label");
        if (fontnameIsSet && (includeAll || !fontnameIsDefault)) { element.setAttribute("fontname",  fontname ); }
        if (fontsizeIsSet && (includeAll || !fontsizeIsDefault)) { element.setAttribute("fontsize",  Integer.toString(fontsize) ); }
        if (fontcolorIsSet && (includeAll || !fontcolorIsDefault)) { element.setAttribute("fontcolor",  fontcolor.toString() ); }
        if (angleIsSet && (includeAll || !angleIsDefault)) { element.setAttribute("angle",  Double.toString(angle) ); }
        if (positionIsSet && (includeAll || !positionIsDefault)) { element.setAttribute("position",  position.toString() ); }
        if (anchorIsSet && (includeAll || !anchorIsDefault)) { element.setAttribute("anchor",  anchor.toString() ); }
        if (spacingIsSet && (includeAll || !spacingIsDefault)) { element.setAttribute("spacing",  spacing ); }
        if (startIsSet && (includeAll || !startIsDefault)) { element.setAttribute("start",  start.getStringValue() ); }
        if (formatIsSet && (includeAll || !formatIsDefault)) { element.setAttribute("format",  format ); }
        return element;
    }
}
