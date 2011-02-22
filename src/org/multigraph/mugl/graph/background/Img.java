package org.multigraph.mugl.graph.background;

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

public class Img extends MuglBean {

    private String frame;
    private boolean frameIsSet     = false;
    private boolean frameIsDefault = false;
    private DPoint anchor;
    private boolean anchorIsSet     = false;
    private boolean anchorIsDefault = false;
    private DPoint base;
    private boolean baseIsSet     = false;
    private boolean baseIsDefault = false;
    private DPoint position;
    private boolean positionIsSet     = false;
    private boolean positionIsDefault = false;
    private String src;
    private boolean srcIsSet     = false;
    private boolean srcIsDefault = false;
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
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public void setSrc(String src, boolean isDefault) {
        setSrc(src);
        this.srcIsSet = true;
        this.srcIsDefault = isDefault;
    }
	public boolean haveSrc() {
        return srcIsSet;
    }
    public static Img parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Background parent ) throws Exception {
        final Img img = new Img();
        
        setProperty("frame", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        img.setFrame( value, isDefault );
                    }});
        
        setProperty("anchor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        img.setAnchor( new DPoint(value), isDefault );
                    }});
        
        setProperty("base", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        img.setBase( new DPoint(value), isDefault );
                    }});
        
        setProperty("position", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        img.setPosition( new DPoint(value), isDefault );
                    }});
        
        setProperty("src", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        img.setSrc( value, isDefault );
                    }});
        
        return img;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("img");
        if (frameIsSet && (includeAll || !frameIsDefault)) { element.setAttribute("frame",  frame ); }
        if (anchorIsSet && (includeAll || !anchorIsDefault)) { element.setAttribute("anchor",  anchor.toString() ); }
        if (baseIsSet && (includeAll || !baseIsDefault)) { element.setAttribute("base",  base.toString() ); }
        if (positionIsSet && (includeAll || !positionIsDefault)) { element.setAttribute("position",  position.toString() ); }
        if (srcIsSet && (includeAll || !srcIsDefault)) { element.setAttribute("src",  src ); }
        return element;
    }
}
