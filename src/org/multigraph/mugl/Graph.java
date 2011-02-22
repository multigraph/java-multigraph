package org.multigraph.mugl;

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

public class Graph extends MuglBean {

    private int x;
    private boolean xIsSet     = false;
    private boolean xIsDefault = false;
    private int y;
    private boolean yIsSet     = false;
    private boolean yIsDefault = false;
    private int width;
    private boolean widthIsSet     = false;
    private boolean widthIsDefault = false;
    private int height;
    private boolean heightIsSet     = false;
    private boolean heightIsDefault = false;
    private org.multigraph.mugl.graph.Background background;
    private boolean backgroundIsDefault = false;
    private org.multigraph.mugl.graph.Debug debug;
    private boolean debugIsDefault = false;
    private org.multigraph.mugl.graph.Window window;
    private boolean windowIsDefault = false;
    private org.multigraph.mugl.graph.Legend legend;
    private boolean legendIsDefault = false;
    private org.multigraph.mugl.graph.Title title;
    private boolean titleIsDefault = false;
    private org.multigraph.mugl.graph.Plotarea plotarea;
    private boolean plotareaIsDefault = false;
    private ArrayList<org.multigraph.mugl.graph.Axis> haxes = new ArrayList<org.multigraph.mugl.graph.Axis>();
    private ArrayList<org.multigraph.mugl.graph.Axis> vaxes = new ArrayList<org.multigraph.mugl.graph.Axis>();
    private ArrayList<org.multigraph.mugl.graph.Plot> plots = new ArrayList<org.multigraph.mugl.graph.Plot>();
    private ArrayList<org.multigraph.mugl.graph.Data> datas = new ArrayList<org.multigraph.mugl.graph.Data>();
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setX(int x, boolean isDefault) {
        setX(x);
        this.xIsSet = true;
        this.xIsDefault = isDefault;
    }
	public boolean haveX() {
        return xIsSet;
    }
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setY(int y, boolean isDefault) {
        setY(y);
        this.yIsSet = true;
        this.yIsDefault = isDefault;
    }
	public boolean haveY() {
        return yIsSet;
    }
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setWidth(int width, boolean isDefault) {
        setWidth(width);
        this.widthIsSet = true;
        this.widthIsDefault = isDefault;
    }
	public boolean haveWidth() {
        return widthIsSet;
    }
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setHeight(int height, boolean isDefault) {
        setHeight(height);
        this.heightIsSet = true;
        this.heightIsDefault = isDefault;
    }
	public boolean haveHeight() {
        return heightIsSet;
    }
	public org.multigraph.mugl.graph.Background getBackground() {
		return background;
	}
	public void setBackground(org.multigraph.mugl.graph.Background background) {
		this.background = background;
	}
	public void setBackground(org.multigraph.mugl.graph.Background background, boolean isDefault) {
        setBackground(background);
        this.backgroundIsDefault = isDefault;
    }
    public boolean haveBackground() {
        return this.background != null;
    }
	public org.multigraph.mugl.graph.Debug getDebug() {
		return debug;
	}
	public void setDebug(org.multigraph.mugl.graph.Debug debug) {
		this.debug = debug;
	}
	public void setDebug(org.multigraph.mugl.graph.Debug debug, boolean isDefault) {
        setDebug(debug);
        this.debugIsDefault = isDefault;
    }
    public boolean haveDebug() {
        return this.debug != null;
    }
	public org.multigraph.mugl.graph.Window getWindow() {
		return window;
	}
	public void setWindow(org.multigraph.mugl.graph.Window window) {
		this.window = window;
	}
	public void setWindow(org.multigraph.mugl.graph.Window window, boolean isDefault) {
        setWindow(window);
        this.windowIsDefault = isDefault;
    }
    public boolean haveWindow() {
        return this.window != null;
    }
	public org.multigraph.mugl.graph.Legend getLegend() {
		return legend;
	}
	public void setLegend(org.multigraph.mugl.graph.Legend legend) {
		this.legend = legend;
	}
	public void setLegend(org.multigraph.mugl.graph.Legend legend, boolean isDefault) {
        setLegend(legend);
        this.legendIsDefault = isDefault;
    }
    public boolean haveLegend() {
        return this.legend != null;
    }
	public org.multigraph.mugl.graph.Title getTitle() {
		return title;
	}
	public void setTitle(org.multigraph.mugl.graph.Title title) {
		this.title = title;
	}
	public void setTitle(org.multigraph.mugl.graph.Title title, boolean isDefault) {
        setTitle(title);
        this.titleIsDefault = isDefault;
    }
    public boolean haveTitle() {
        return this.title != null;
    }
	public org.multigraph.mugl.graph.Plotarea getPlotarea() {
		return plotarea;
	}
	public void setPlotarea(org.multigraph.mugl.graph.Plotarea plotarea) {
		this.plotarea = plotarea;
	}
	public void setPlotarea(org.multigraph.mugl.graph.Plotarea plotarea, boolean isDefault) {
        setPlotarea(plotarea);
        this.plotareaIsDefault = isDefault;
    }
    public boolean havePlotarea() {
        return this.plotarea != null;
    }
	public ArrayList<org.multigraph.mugl.graph.Axis> getHaxes() {
		return haxes;
	}
	public ArrayList<org.multigraph.mugl.graph.Axis> getVaxes() {
		return vaxes;
	}
	public ArrayList<org.multigraph.mugl.graph.Plot> getPlots() {
		return plots;
	}
	public ArrayList<org.multigraph.mugl.graph.Data> getDatas() {
		return datas;
	}
    public static Graph parse( Element node, Element defaultsNode, org.multigraph.Mugl parent ) throws Exception {
        final Graph graph = new Graph();
        
        setProperty("x", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        graph.setX( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("y", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        graph.setY( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("width", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        graph.setWidth( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("height", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        graph.setHeight( Integer.parseInt(value), isDefault );
                    }});
        
        {
            Element element = (Element)XPath.selectSingleNode(node, "background");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "background");
            if (element != null) {
                graph.setBackground( org.multigraph.mugl.graph.Background.parse( element, defaultsElement, graph), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.Background background = org.multigraph.mugl.graph.Background.parse( null, defaultsElement, graph);
                
                graph.setBackground( background, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "debug");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "debug");
            if (element != null) {
                graph.setDebug( org.multigraph.mugl.graph.Debug.parse( element, defaultsElement, graph), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.Debug debug = org.multigraph.mugl.graph.Debug.parse( null, defaultsElement, graph);
                
                graph.setDebug( debug, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "window");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "window");
            if (element != null) {
                graph.setWindow( org.multigraph.mugl.graph.Window.parse( element, defaultsElement, graph), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.Window window = org.multigraph.mugl.graph.Window.parse( null, defaultsElement, graph);
                
                graph.setWindow( window, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "legend");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "legend");
            if (element != null) {
                graph.setLegend( org.multigraph.mugl.graph.Legend.parse( element, defaultsElement, graph), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.Legend legend = org.multigraph.mugl.graph.Legend.parse( null, defaultsElement, graph);
                
                graph.setLegend( legend, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "title");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "title");
            if (element != null) {
                graph.setTitle( org.multigraph.mugl.graph.Title.parse( element, defaultsElement, graph), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.Title title = org.multigraph.mugl.graph.Title.parse( null, defaultsElement, graph);
                
                graph.setTitle( title, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "plotarea");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "plotarea");
            if (element != null) {
                graph.setPlotarea( org.multigraph.mugl.graph.Plotarea.parse( element, defaultsElement, graph), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.Plotarea plotarea = org.multigraph.mugl.graph.Plotarea.parse( null, defaultsElement, graph);
                
                graph.setPlotarea( plotarea, true );
            }
        }
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "horizontalaxis"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "axis");
            for (Element element : elements) {
                if (element != null) {
                    graph.getHaxes().add( org.multigraph.mugl.graph.Axis.parse(org.multigraph.mugl.graph.Axis.Orientation.HORIZONTAL, element, defaultsElement, graph) );
                } else {
                	org.multigraph.mugl.graph.Axis horizontalaxis = org.multigraph.mugl.graph.Axis.parse(org.multigraph.mugl.graph.Axis.Orientation.HORIZONTAL, null, defaultsElement, graph);
                    
                    graph.getHaxes().add( horizontalaxis );
                }
            }
        }
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "verticalaxis"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "axis");
            for (Element element : elements) {
                if (element != null) {
                    graph.getVaxes().add( org.multigraph.mugl.graph.Axis.parse(org.multigraph.mugl.graph.Axis.Orientation.VERTICAL, element, defaultsElement, graph) );
                } else {
                	org.multigraph.mugl.graph.Axis verticalaxis = org.multigraph.mugl.graph.Axis.parse(org.multigraph.mugl.graph.Axis.Orientation.VERTICAL, null, defaultsElement, graph);
                    
                    graph.getVaxes().add( verticalaxis );
                }
            }
        }
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "plot"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "plot");
            for (Element element : elements) {
                if (element != null) {
                    graph.getPlots().add( org.multigraph.mugl.graph.Plot.parse( element, defaultsElement, graph) );
                } else {
                	org.multigraph.mugl.graph.Plot plot = org.multigraph.mugl.graph.Plot.parse( null, defaultsElement, graph);
                    
                    graph.getPlots().add( plot );
                }
            }
        }
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "data"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "data");
            for (Element element : elements) {
                if (element != null) {
                    graph.getDatas().add( org.multigraph.mugl.graph.Data.parse( element, defaultsElement, graph) );
                } else {
                	org.multigraph.mugl.graph.Data data = org.multigraph.mugl.graph.Data.parse( null, defaultsElement, graph);
                    
                    graph.getDatas().add( data );
                }
            }
        }
        return graph;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("graph");
        if (xIsSet && (includeAll || !xIsDefault)) { element.setAttribute("x",  Integer.toString(x) ); }
        if (yIsSet && (includeAll || !yIsDefault)) { element.setAttribute("y",  Integer.toString(y) ); }
        if (widthIsSet && (includeAll || !widthIsDefault)) { element.setAttribute("width",  Integer.toString(width) ); }
        if (heightIsSet && (includeAll || !heightIsDefault)) { element.setAttribute("height",  Integer.toString(height) ); }
        if (haveBackground() && (includeAll || !backgroundIsDefault)) { element.addContent( background.build(includeAll)  ); }
        if (haveDebug() && (includeAll || !debugIsDefault)) { element.addContent( debug.build(includeAll)  ); }
        if (haveWindow() && (includeAll || !windowIsDefault)) { element.addContent( window.build(includeAll)  ); }
        if (haveLegend() && (includeAll || !legendIsDefault)) { element.addContent( legend.build(includeAll)  ); }
        if (haveTitle() && (includeAll || !titleIsDefault)) { element.addContent( title.build(includeAll)  ); }
        if (havePlotarea() && (includeAll || !plotareaIsDefault)) { element.addContent( plotarea.build(includeAll)  ); }
        for (org.multigraph.mugl.graph.Axis horizontalaxis : haxes) {
            element.addContent( horizontalaxis.build(includeAll)  );
        }
        for (org.multigraph.mugl.graph.Axis verticalaxis : vaxes) {
            element.addContent( verticalaxis.build(includeAll)  );
        }
        for (org.multigraph.mugl.graph.Plot plot : plots) {
            element.addContent( plot.build(includeAll)  );
        }
        for (org.multigraph.mugl.graph.Data data : datas) {
            element.addContent( data.build(includeAll)  );
        }
        return element;
    }
}
