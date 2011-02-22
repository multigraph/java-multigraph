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

public class Grid extends MuglBean {

    private RGBColor color;
    private boolean colorIsSet     = false;
    private boolean colorIsDefault = false;
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
    public static Grid parse( Element node, Element defaultsNode, Axis parent ) throws Exception {
        final Grid grid = new Grid();
        
        setProperty("color", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        grid.setColor( new RGBColor(value), isDefault );
                    }});
        
        return grid;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("grid");
        if (colorIsSet && (includeAll || !colorIsDefault)) { element.setAttribute("color",  color.toString() ); }
        return element;
    }
}
