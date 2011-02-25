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

public class Background extends MuglBean {

    private RGBColor color;
    private boolean colorIsSet     = false;
    private boolean colorIsDefault = false;
    private org.multigraph.mugl.graph.background.Img img;
    private boolean imgIsDefault = false;
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
	public org.multigraph.mugl.graph.background.Img getImg() {
		return img;
	}
	public void setImg(org.multigraph.mugl.graph.background.Img img) {
		this.img = img;
	}
	public void setImg(org.multigraph.mugl.graph.background.Img img, boolean isDefault) {
        setImg(img);
        this.imgIsDefault = isDefault;
    }
    public boolean haveImg() {
        return this.img != null;
    }
    public static Background parse( Element node, Element defaultsNode, org.multigraph.mugl.Graph parent ) throws Exception {
        final Background background = new Background();
        
        setProperty("color", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        background.setColor( new RGBColor(value), isDefault );
                    }});
        
        {
            Element element = (Element)XPath.selectSingleNode(node, "img");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "img");
            if (element != null) {
                background.setImg( org.multigraph.mugl.graph.background.Img.parse( element, defaultsElement, background), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.background.Img img = org.multigraph.mugl.graph.background.Img.parse( null, defaultsElement, background);
                
                background.setImg( img, true );
            }
        }
        return background;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("background");
        if (colorIsSet && (includeAll || !colorIsDefault)) { element.setAttribute("color",  color.toString() ); }
        if (haveImg() && (includeAll || !imgIsDefault)) { element.addContent( img.build(includeAll)  ); }
        return element;
    }
}
