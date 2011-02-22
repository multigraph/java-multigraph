package org.multigraph.mugl.graph;

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

public class org.multigraph.mugl.graph.Axis extends MuglBean {
    public static enum Orientation { HORIZONTAL, VERTICAL };

    public static String OrientationToString(Orientation orientation) {
        if (orientation == Axis.Orientation.HORIZONTAL) { return "horizontalaxis"; }
        if (orientation == Axis.Orientation.VERTICAL)   { return "verticalaxis"; }
        return null;
    }

    private Orientation orientation;
    public Orientation getOrientation() {
        return orientation;
    }
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    private DataValue.Type type;
    private boolean typeIsSet     = false;
    private boolean typeIsDefault = false;
    private String min;
    private boolean minIsSet     = false;
    private boolean minIsDefault = false;
    private String max;
    private boolean maxIsSet     = false;
    private boolean maxIsDefault = false;
    private int tickmin;
    private boolean tickminIsSet     = false;
    private boolean tickminIsDefault = false;
    private int tickmax;
    private boolean tickmaxIsSet     = false;
    private boolean tickmaxIsDefault = false;
    private String highlightstyle;
    private boolean highlightstyleIsSet     = false;
    private boolean highlightstyleIsDefault = false;
    private int linewidth;
    private boolean linewidthIsSet     = false;
    private boolean linewidthIsDefault = false;
    private String id;
    private boolean idIsSet     = false;
    private boolean idIsDefault = false;
    private int position;
    private boolean positionIsSet     = false;
    private boolean positionIsDefault = false;
    private String positionbase;
    private boolean positionbaseIsSet     = false;
    private boolean positionbaseIsDefault = false;
    private DPoint base;
    private boolean baseIsSet     = false;
    private boolean baseIsDefault = false;
    private double anchor;
    private boolean anchorIsSet     = false;
    private boolean anchorIsDefault = false;
    private double length;
    private boolean lengthIsSet     = false;
    private boolean lengthIsDefault = false;
    private int pregap;
    private boolean pregapIsSet     = false;
    private boolean pregapIsDefault = false;
    private int postgap;
    private boolean postgapIsSet     = false;
    private boolean postgapIsDefault = false;
    private double minposition;
    private boolean minpositionIsSet     = false;
    private boolean minpositionIsDefault = false;
    private double maxposition;
    private boolean maxpositionIsSet     = false;
    private boolean maxpositionIsDefault = false;
    private Title title;
    private boolean titleIsDefault = false;
    private Zoom zoom;
    private boolean zoomIsDefault = false;
    private Pan pan;
    private boolean panIsDefault = false;
    private Grid grid;
    private boolean gridIsDefault = false;
    private Labels labels;
    private boolean labelsIsDefault = false;
	public DataValue.Type getType() {
		return type;
	}
	public void setType(DataValue.Type type) {
		this.type = type;
	}
	public void setType(DataValue.Type type, boolean isDefault) {
        setType(type);
        this.typeIsSet = true;
        this.typeIsDefault = isDefault;
    }
	public boolean haveType() {
        return typeIsSet;
    }
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public void setMin(String min, boolean isDefault) {
        setMin(min);
        this.minIsSet = true;
        this.minIsDefault = isDefault;
    }
	public boolean haveMin() {
        return minIsSet;
    }
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public void setMax(String max, boolean isDefault) {
        setMax(max);
        this.maxIsSet = true;
        this.maxIsDefault = isDefault;
    }
	public boolean haveMax() {
        return maxIsSet;
    }
	public int getTickmin() {
		return tickmin;
	}
	public void setTickmin(int tickmin) {
		this.tickmin = tickmin;
	}
	public void setTickmin(int tickmin, boolean isDefault) {
        setTickmin(tickmin);
        this.tickminIsSet = true;
        this.tickminIsDefault = isDefault;
    }
	public boolean haveTickmin() {
        return tickminIsSet;
    }
	public int getTickmax() {
		return tickmax;
	}
	public void setTickmax(int tickmax) {
		this.tickmax = tickmax;
	}
	public void setTickmax(int tickmax, boolean isDefault) {
        setTickmax(tickmax);
        this.tickmaxIsSet = true;
        this.tickmaxIsDefault = isDefault;
    }
	public boolean haveTickmax() {
        return tickmaxIsSet;
    }
	public String getHighlightstyle() {
		return highlightstyle;
	}
	public void setHighlightstyle(String highlightstyle) {
		this.highlightstyle = highlightstyle;
	}
	public void setHighlightstyle(String highlightstyle, boolean isDefault) {
        setHighlightstyle(highlightstyle);
        this.highlightstyleIsSet = true;
        this.highlightstyleIsDefault = isDefault;
    }
	public boolean haveHighlightstyle() {
        return highlightstyleIsSet;
    }
	public int getLinewidth() {
		return linewidth;
	}
	public void setLinewidth(int linewidth) {
		this.linewidth = linewidth;
	}
	public void setLinewidth(int linewidth, boolean isDefault) {
        setLinewidth(linewidth);
        this.linewidthIsSet = true;
        this.linewidthIsDefault = isDefault;
    }
	public boolean haveLinewidth() {
        return linewidthIsSet;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setId(String id, boolean isDefault) {
        setId(id);
        this.idIsSet = true;
        this.idIsDefault = isDefault;
    }
	public boolean haveId() {
        return idIsSet;
    }
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void setPosition(int position, boolean isDefault) {
        setPosition(position);
        this.positionIsSet = true;
        this.positionIsDefault = isDefault;
    }
	public boolean havePosition() {
        return positionIsSet;
    }
	public String getPositionbase() {
		return positionbase;
	}
	public void setPositionbase(String positionbase) {
		this.positionbase = positionbase;
	}
	public void setPositionbase(String positionbase, boolean isDefault) {
        setPositionbase(positionbase);
        this.positionbaseIsSet = true;
        this.positionbaseIsDefault = isDefault;
    }
	public boolean havePositionbase() {
        return positionbaseIsSet;
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
	public double getAnchor() {
		return anchor;
	}
	public void setAnchor(double anchor) {
		this.anchor = anchor;
	}
	public void setAnchor(double anchor, boolean isDefault) {
        setAnchor(anchor);
        this.anchorIsSet = true;
        this.anchorIsDefault = isDefault;
    }
	public boolean haveAnchor() {
        return anchorIsSet;
    }
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public void setLength(double length, boolean isDefault) {
        setLength(length);
        this.lengthIsSet = true;
        this.lengthIsDefault = isDefault;
    }
	public boolean haveLength() {
        return lengthIsSet;
    }
	public int getPregap() {
		return pregap;
	}
	public void setPregap(int pregap) {
		this.pregap = pregap;
	}
	public void setPregap(int pregap, boolean isDefault) {
        setPregap(pregap);
        this.pregapIsSet = true;
        this.pregapIsDefault = isDefault;
    }
	public boolean havePregap() {
        return pregapIsSet;
    }
	public int getPostgap() {
		return postgap;
	}
	public void setPostgap(int postgap) {
		this.postgap = postgap;
	}
	public void setPostgap(int postgap, boolean isDefault) {
        setPostgap(postgap);
        this.postgapIsSet = true;
        this.postgapIsDefault = isDefault;
    }
	public boolean havePostgap() {
        return postgapIsSet;
    }
	public double getMinposition() {
		return minposition;
	}
	public void setMinposition(double minposition) {
		this.minposition = minposition;
	}
	public void setMinposition(double minposition, boolean isDefault) {
        setMinposition(minposition);
        this.minpositionIsSet = true;
        this.minpositionIsDefault = isDefault;
    }
	public boolean haveMinposition() {
        return minpositionIsSet;
    }
	public double getMaxposition() {
		return maxposition;
	}
	public void setMaxposition(double maxposition) {
		this.maxposition = maxposition;
	}
	public void setMaxposition(double maxposition, boolean isDefault) {
        setMaxposition(maxposition);
        this.maxpositionIsSet = true;
        this.maxpositionIsDefault = isDefault;
    }
	public boolean haveMaxposition() {
        return maxpositionIsSet;
    }
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public void setTitle(Title title, boolean isDefault) {
        setTitle(title);
        this.titleIsDefault = isDefault;
    }
	public Zoom getZoom() {
		return zoom;
	}
	public void setZoom(Zoom zoom) {
		this.zoom = zoom;
	}
	public void setZoom(Zoom zoom, boolean isDefault) {
        setZoom(zoom);
        this.zoomIsDefault = isDefault;
    }
	public Pan getPan() {
		return pan;
	}
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	public void setPan(Pan pan, boolean isDefault) {
        setPan(pan);
        this.panIsDefault = isDefault;
    }
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public void setGrid(Grid grid, boolean isDefault) {
        setGrid(grid);
        this.gridIsDefault = isDefault;
    }
	public Labels getLabels() {
		return labels;
	}
	public void setLabels(Labels labels) {
		this.labels = labels;
	}
	public void setLabels(Labels labels, boolean isDefault) {
        setLabels(labels);
        this.labelsIsDefault = isDefault;
    }
    public static org.multigraph.mugl.graph.Axis parse(Orientation orientation, Element node, Element defaultsNode, Graph parent ) throws Exception {
        final org.multigraph.mugl.graph.Axis axis = new org.multigraph.mugl.graph.Axis();
        axis.setOrientation(orientation);
        setProperty("type", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setType( DataValue.StringToType(value), isDefault );
                    }});
        
        setProperty("min", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setMin( value, isDefault );
                    }});
        
        setProperty("max", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setMax( value, isDefault );
                    }});
        
        setProperty("tickmin", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setTickmin( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("tickmax", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setTickmax( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("highlightstyle", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setHighlightstyle( value, isDefault );
                    }});
        
        setProperty("linewidth", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setLinewidth( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("id", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setId( value, isDefault );
                    }});
        
        setProperty("position", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setPosition( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("positionbase", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setPositionbase( value, isDefault );
                    }});
        
        setProperty("base", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setBase( new DPoint(value), isDefault );
                    }});
        
        setProperty("anchor", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setAnchor( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("length", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setLength( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("pregap", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setPregap( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("postgap", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setPostgap( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("minposition", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setMinposition( Double.parseDouble(value), isDefault );
                    }});
        
        setProperty("maxposition", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        axis.setMaxposition( Double.parseDouble(value), isDefault );
                    }});
        
        {
            Element element = (Element)XPath.selectSingleNode(node, "title");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "title");
            if (element != null) {
                axis.setTitle( Title.parse( element, defaultsElement, axis), false );
            } else {
            	Title title = Title.parse( null, defaultsElement, axis);
                title.setText( axis.getId() );
                axis.setTitle( title, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "zoom");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "zoom");
            if (element != null) {
                axis.setZoom( Zoom.parse( element, defaultsElement, axis), false );
            } else {
            	Zoom zoom = Zoom.parse( null, defaultsElement, axis);
                
                axis.setZoom( zoom, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "pan");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "pan");
            if (element != null) {
                axis.setPan( Pan.parse( element, defaultsElement, axis), false );
            } else {
            	Pan pan = Pan.parse( null, defaultsElement, axis);
                
                axis.setPan( pan, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "grid");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "grid");
            if (element != null) {
                axis.setGrid( Grid.parse( element, defaultsElement, axis), false );
            } else {
            	Grid grid = Grid.parse( null, defaultsElement, axis);
                
                axis.setGrid( grid, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "labels");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "labels");
            if (element != null) {
                axis.setLabels( Labels.parse( element, defaultsElement, axis), false );
            } else {
            	Labels labels = Labels.parse( null, defaultsElement, axis);
                
                axis.setLabels( labels, true );
            }
        }
        return axis;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element(OrientationToString(orientation));
        if (typeIsSet && (includeAll || !typeIsDefault)) { element.setAttribute("type",  DataValue.TypeToString(type) ); }
        if (minIsSet && (includeAll || !minIsDefault)) { element.setAttribute("min",  min ); }
        if (maxIsSet && (includeAll || !maxIsDefault)) { element.setAttribute("max",  max ); }
        if (tickminIsSet && (includeAll || !tickminIsDefault)) { element.setAttribute("tickmin",  Integer.toString(tickmin) ); }
        if (tickmaxIsSet && (includeAll || !tickmaxIsDefault)) { element.setAttribute("tickmax",  Integer.toString(tickmax) ); }
        if (highlightstyleIsSet && (includeAll || !highlightstyleIsDefault)) { element.setAttribute("highlightstyle",  highlightstyle ); }
        if (linewidthIsSet && (includeAll || !linewidthIsDefault)) { element.setAttribute("linewidth",  Integer.toString(linewidth) ); }
        if (idIsSet && (includeAll || !idIsDefault)) { element.setAttribute("id",  id ); }
        if (positionIsSet && (includeAll || !positionIsDefault)) { element.setAttribute("position",  Integer.toString(position) ); }
        if (positionbaseIsSet && (includeAll || !positionbaseIsDefault)) { element.setAttribute("positionbase",  positionbase ); }
        if (baseIsSet && (includeAll || !baseIsDefault)) { element.setAttribute("base",  base.toString() ); }
        if (anchorIsSet && (includeAll || !anchorIsDefault)) { element.setAttribute("anchor",  Double.toString(anchor) ); }
        if (lengthIsSet && (includeAll || !lengthIsDefault)) { element.setAttribute("length",  Double.toString(length) ); }
        if (pregapIsSet && (includeAll || !pregapIsDefault)) { element.setAttribute("pregap",  Integer.toString(pregap) ); }
        if (postgapIsSet && (includeAll || !postgapIsDefault)) { element.setAttribute("postgap",  Integer.toString(postgap) ); }
        if (minpositionIsSet && (includeAll || !minpositionIsDefault)) { element.setAttribute("minposition",  Double.toString(minposition) ); }
        if (maxpositionIsSet && (includeAll || !maxpositionIsDefault)) { element.setAttribute("maxposition",  Double.toString(maxposition) ); }
        if (includeAll || !titleIsDefault) { element.addContent( title.build(includeAll)  ); }
        if (includeAll || !zoomIsDefault) { element.addContent( zoom.build(includeAll)  ); }
        if (includeAll || !panIsDefault) { element.addContent( pan.build(includeAll)  ); }
        if (includeAll || !gridIsDefault) { element.addContent( grid.build(includeAll)  ); }
        if (includeAll || !labelsIsDefault) { element.addContent( labels.build(includeAll)  ); }
        return element;
    }
}
