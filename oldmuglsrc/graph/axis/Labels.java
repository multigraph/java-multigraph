package org.multigraph.mugl.graph.axis;

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

public class Labels extends MuglBean {

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
    private ArrayList<Label> labels = new ArrayList<Label>();
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
	public ArrayList<Label> getLabels() {
		return labels;
	}
    public static Labels parse( Element node, Element defaultsNode, Axis parent ) throws Exception {
        final Labels labels = new Labels();
        
        setProperty("fontname", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setFontname( value, isDefault );
                    }});
        
        setProperty("fontsize", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setFontsize( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("fontcolor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setFontcolor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("angle", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setAngle( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("position", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setPosition( new DPoint(value), isDefault );
                    }});
        
        setProperty("anchor", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setAnchor( new DPoint(value), isDefault );
                    }});
        
        setProperty("spacing", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setSpacing( value, isDefault );
                    }});
        
        setProperty("start", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setStart( new DoubleDataValue(value), isDefault );
                    }});
        
        setProperty("format", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        labels.setFormat( value, isDefault );
                    }});
        
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "label"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "label");
            for (Element element : elements) {
                if (element != null) {
                    labels.getLabels().add( Label.parse( element, defaultsElement, labels) );
                } else {
                	Label label = Label.parse( null, defaultsElement, labels);
                    
                    labels.getLabels().add( label );
                }
            }
        }
        return labels;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("labels");
        if (fontnameIsSet && (includeAll || !fontnameIsDefault)) { element.setAttribute("fontname",  fontname ); }
        if (fontsizeIsSet && (includeAll || !fontsizeIsDefault)) { element.setAttribute("fontsize",  Integer.toString(fontsize) ); }
        if (fontcolorIsSet && (includeAll || !fontcolorIsDefault)) { element.setAttribute("fontcolor",  fontcolor.toString() ); }
        if (angleIsSet && (includeAll || !angleIsDefault)) { element.setAttribute("angle",  Double.toString(angle) ); }
        if (positionIsSet && (includeAll || !positionIsDefault)) { element.setAttribute("position",  position.toString() ); }
        if (anchorIsSet && (includeAll || !anchorIsDefault)) { element.setAttribute("anchor",  anchor.toString() ); }
        if (spacingIsSet && (includeAll || !spacingIsDefault)) { element.setAttribute("spacing",  spacing ); }
        if (startIsSet && (includeAll || !startIsDefault)) { element.setAttribute("start",  start.getStringValue() ); }
        if (formatIsSet && (includeAll || !formatIsDefault)) { element.setAttribute("format",  format ); }
        for (Label label : labels) {
            element.addContent( label.build(includeAll)  );
        }
        return element;
    }
}
