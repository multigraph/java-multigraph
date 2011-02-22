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

public class Plotarea extends MuglBean {

    private int border;
    private boolean borderIsSet     = false;
    private boolean borderIsDefault = false;
    private RGBColor bordercolor;
    private boolean bordercolorIsSet     = false;
    private boolean bordercolorIsDefault = false;
    private int marginbottom;
    private boolean marginbottomIsSet     = false;
    private boolean marginbottomIsDefault = false;
    private int marginleft;
    private boolean marginleftIsSet     = false;
    private boolean marginleftIsDefault = false;
    private int margintop;
    private boolean margintopIsSet     = false;
    private boolean margintopIsDefault = false;
    private int marginright;
    private boolean marginrightIsSet     = false;
    private boolean marginrightIsDefault = false;
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
	public int getMarginbottom() {
		return marginbottom;
	}
	public void setMarginbottom(int marginbottom) {
		this.marginbottom = marginbottom;
	}
	public void setMarginbottom(int marginbottom, boolean isDefault) {
        setMarginbottom(marginbottom);
        this.marginbottomIsSet = true;
        this.marginbottomIsDefault = isDefault;
    }
	public boolean haveMarginbottom() {
        return marginbottomIsSet;
    }
	public int getMarginleft() {
		return marginleft;
	}
	public void setMarginleft(int marginleft) {
		this.marginleft = marginleft;
	}
	public void setMarginleft(int marginleft, boolean isDefault) {
        setMarginleft(marginleft);
        this.marginleftIsSet = true;
        this.marginleftIsDefault = isDefault;
    }
	public boolean haveMarginleft() {
        return marginleftIsSet;
    }
	public int getMargintop() {
		return margintop;
	}
	public void setMargintop(int margintop) {
		this.margintop = margintop;
	}
	public void setMargintop(int margintop, boolean isDefault) {
        setMargintop(margintop);
        this.margintopIsSet = true;
        this.margintopIsDefault = isDefault;
    }
	public boolean haveMargintop() {
        return margintopIsSet;
    }
	public int getMarginright() {
		return marginright;
	}
	public void setMarginright(int marginright) {
		this.marginright = marginright;
	}
	public void setMarginright(int marginright, boolean isDefault) {
        setMarginright(marginright);
        this.marginrightIsSet = true;
        this.marginrightIsDefault = isDefault;
    }
	public boolean haveMarginright() {
        return marginrightIsSet;
    }
    public static Plotarea parse( Element node, Element defaultsNode, org.multigraph.mugl.Graph parent ) throws Exception {
        final Plotarea plotarea = new Plotarea();
        
        setProperty("border", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        plotarea.setBorder( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("bordercolor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        plotarea.setBordercolor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("marginbottom", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        plotarea.setMarginbottom( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("marginleft", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        plotarea.setMarginleft( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("margintop", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        plotarea.setMargintop( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("marginright", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        plotarea.setMarginright( Integer.parseInt(value), isDefault );
                    }});
        
        return plotarea;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("plotarea");
        if (borderIsSet && (includeAll || !borderIsDefault)) { element.setAttribute("border",  Integer.toString(border) ); }
        if (bordercolorIsSet && (includeAll || !bordercolorIsDefault)) { element.setAttribute("bordercolor",  bordercolor.toString() ); }
        if (marginbottomIsSet && (includeAll || !marginbottomIsDefault)) { element.setAttribute("marginbottom",  Integer.toString(marginbottom) ); }
        if (marginleftIsSet && (includeAll || !marginleftIsDefault)) { element.setAttribute("marginleft",  Integer.toString(marginleft) ); }
        if (margintopIsSet && (includeAll || !margintopIsDefault)) { element.setAttribute("margintop",  Integer.toString(margintop) ); }
        if (marginrightIsSet && (includeAll || !marginrightIsDefault)) { element.setAttribute("marginright",  Integer.toString(marginright) ); }
        return element;
    }
}
