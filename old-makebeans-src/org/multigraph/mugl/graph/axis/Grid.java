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

public class Grid extends MuglBean {

    private RGBColor color;
    private boolean colorIsSet     = false;
    private boolean colorIsDefault = false;
    private boolean visible;
    private boolean visibleIsSet     = false;
    private boolean visibleIsDefault = false;
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
	public boolean getVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void setVisible(boolean visible, boolean isDefault) {
        setVisible(visible);
        this.visibleIsSet = true;
        this.visibleIsDefault = isDefault;
    }
	public boolean haveVisible() {
        return visibleIsSet;
    }
    public static Grid parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Axis parent ) throws Exception {
        final Grid grid = new Grid();
        
        setProperty("color", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        grid.setColor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("visible", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        grid.setVisible( (value.equals("true") || value.equals("yes")), isDefault );
                    }});
        
        return grid;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("grid");
        if (colorIsSet && (includeAll || !colorIsDefault)) { element.setAttribute("color",  color.toString() ); }
        if (visibleIsSet && (includeAll || !visibleIsDefault)) { element.setAttribute("visible",  Boolean.toString(visible) ); }
        return element;
    }
}
