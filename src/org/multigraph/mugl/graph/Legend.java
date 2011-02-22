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

public class Legend extends MuglBean {

    private String visible;
    private boolean visibleIsSet     = false;
    private boolean visibleIsDefault = false;
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
    private int columns;
    private boolean columnsIsSet     = false;
    private boolean columnsIsDefault = false;
    private int rows;
    private boolean rowsIsSet     = false;
    private boolean rowsIsDefault = false;
    private int border;
    private boolean borderIsSet     = false;
    private boolean borderIsDefault = false;
    private boolean round;
    private boolean roundIsSet     = false;
    private boolean roundIsDefault = false;
    private int radius;
    private boolean radiusIsSet     = false;
    private boolean radiusIsDefault = false;
    private RGBColor color;
    private boolean colorIsSet     = false;
    private boolean colorIsDefault = false;
    private RGBColor bordercolor;
    private boolean bordercolorIsSet     = false;
    private boolean bordercolorIsDefault = false;
    private double opacity;
    private boolean opacityIsSet     = false;
    private boolean opacityIsDefault = false;
    private int padding;
    private boolean paddingIsSet     = false;
    private boolean paddingIsDefault = false;
    private org.multigraph.mugl.graph.legend.Icon icon;
    private boolean iconIsDefault = false;
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public void setVisible(String visible, boolean isDefault) {
        setVisible(visible);
        this.visibleIsSet = true;
        this.visibleIsDefault = isDefault;
    }
	public boolean haveVisible() {
        return visibleIsSet;
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
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	public void setColumns(int columns, boolean isDefault) {
        setColumns(columns);
        this.columnsIsSet = true;
        this.columnsIsDefault = isDefault;
    }
	public boolean haveColumns() {
        return columnsIsSet;
    }
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setRows(int rows, boolean isDefault) {
        setRows(rows);
        this.rowsIsSet = true;
        this.rowsIsDefault = isDefault;
    }
	public boolean haveRows() {
        return rowsIsSet;
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
	public org.multigraph.mugl.graph.legend.Icon getIcon() {
		return icon;
	}
	public void setIcon(org.multigraph.mugl.graph.legend.Icon icon) {
		this.icon = icon;
	}
	public void setIcon(org.multigraph.mugl.graph.legend.Icon icon, boolean isDefault) {
        setIcon(icon);
        this.iconIsDefault = isDefault;
    }
    public boolean haveIcon() {
        return this.icon != null;
    }
    public static Legend parse( Element node, Element defaultsNode, org.multigraph.mugl.Graph parent ) throws Exception {
        final Legend legend = new Legend();
        
        setProperty("visible", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setVisible( value, isDefault );
                    }});
        
        setProperty("frame", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setFrame( value, isDefault );
                    }});
        
        setProperty("base", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setBase( new DPoint(value), isDefault );
                    }});
        
        setProperty("anchor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setAnchor( new DPoint(value), isDefault );
                    }});
        
        setProperty("position", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setPosition( new DPoint(value), isDefault );
                    }});
        
        setProperty("columns", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setColumns( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("rows", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setRows( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("border", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setBorder( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("round", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setRound( (value.equals("true") || value.equals("yes")), isDefault );
                    }});
        
        setProperty("radius", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setRadius( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("color", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setColor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("bordercolor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setBordercolor( new RGBColor(value), isDefault );
                    }});
        
        setProperty("opacity", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setOpacity( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("padding", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        legend.setPadding( Integer.parseInt(value), isDefault );
                    }});
        
        {
            Element element = (Element)XPath.selectSingleNode(node, "icon");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "icon");
            if (element != null) {
                legend.setIcon( org.multigraph.mugl.graph.legend.Icon.parse( element, defaultsElement, legend), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.legend.Icon icon = org.multigraph.mugl.graph.legend.Icon.parse( null, defaultsElement, legend);
                
                legend.setIcon( icon, true );
            }
        }
        return legend;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("legend");
        if (visibleIsSet && (includeAll || !visibleIsDefault)) { element.setAttribute("visible",  visible ); }
        if (frameIsSet && (includeAll || !frameIsDefault)) { element.setAttribute("frame",  frame ); }
        if (baseIsSet && (includeAll || !baseIsDefault)) { element.setAttribute("base",  base.toString() ); }
        if (anchorIsSet && (includeAll || !anchorIsDefault)) { element.setAttribute("anchor",  anchor.toString() ); }
        if (positionIsSet && (includeAll || !positionIsDefault)) { element.setAttribute("position",  position.toString() ); }
        if (columnsIsSet && (includeAll || !columnsIsDefault)) { element.setAttribute("columns",  Integer.toString(columns) ); }
        if (rowsIsSet && (includeAll || !rowsIsDefault)) { element.setAttribute("rows",  Integer.toString(rows) ); }
        if (borderIsSet && (includeAll || !borderIsDefault)) { element.setAttribute("border",  Integer.toString(border) ); }
        if (roundIsSet && (includeAll || !roundIsDefault)) { element.setAttribute("round",  Boolean.toString(round) ); }
        if (radiusIsSet && (includeAll || !radiusIsDefault)) { element.setAttribute("radius",  Integer.toString(radius) ); }
        if (colorIsSet && (includeAll || !colorIsDefault)) { element.setAttribute("color",  color.toString() ); }
        if (bordercolorIsSet && (includeAll || !bordercolorIsDefault)) { element.setAttribute("bordercolor",  bordercolor.toString() ); }
        if (opacityIsSet && (includeAll || !opacityIsDefault)) { element.setAttribute("opacity",  Double.toString(opacity) ); }
        if (paddingIsSet && (includeAll || !paddingIsDefault)) { element.setAttribute("padding",  Integer.toString(padding) ); }
        if (haveIcon() && (includeAll || !iconIsDefault)) { element.addContent( icon.build(includeAll)  ); }
        return element;
    }
}
