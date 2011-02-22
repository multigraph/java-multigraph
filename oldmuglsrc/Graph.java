package org.multigraph.mugl;

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

public class Graph extends MuglBean {

    private Window window;
    private boolean windowIsDefault = false;
    private ArrayList<Axis> haxes = new ArrayList<Axis>();
    private ArrayList<Axis> vaxes = new ArrayList<Axis>();
	public Window getWindow() {
		return window;
	}
	public void setWindow(Window window) {
		this.window = window;
	}
	public void setWindow(Window window, boolean isDefault) {
        setWindow(window);
        this.windowIsDefault = isDefault;
    }
	public ArrayList<Axis> getHaxes() {
		return haxes;
	}
	public ArrayList<Axis> getVaxes() {
		return vaxes;
	}
    public static Graph parse( Element node, Element defaultsNode, Mugl parent ) throws Exception {
        final Graph graph = new Graph();
        
        {
            Element element = (Element)XPath.selectSingleNode(node, "window");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "window");
            if (element != null) {
                graph.setWindow( Window.parse( element, defaultsElement, graph), false );
            } else {
            	Window window = Window.parse( null, defaultsElement, graph);
                
                graph.setWindow( window, true );
            }
        }
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "horizontalaxis"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "axis");
            for (Element element : elements) {
                if (element != null) {
                    graph.getHaxes().add( Axis.parse(Axis.Orientation.HORIZONTAL, element, defaultsElement, graph) );
                } else {
                	Axis axis = Axis.parse(Axis.Orientation.HORIZONTAL, null, defaultsElement, graph);
                    
                    graph.getHaxes().add( axis );
                }
            }
        }
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "verticalaxis"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "axis");
            for (Element element : elements) {
                if (element != null) {
                    graph.getVaxes().add( Axis.parse(Axis.Orientation.VERTICAL, element, defaultsElement, graph) );
                } else {
                	Axis axis = Axis.parse(Axis.Orientation.VERTICAL, null, defaultsElement, graph);
                    
                    graph.getVaxes().add( axis );
                }
            }
        }
        return graph;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("graph");
        if (includeAll || !windowIsDefault) { element.addContent( window.build(includeAll)  ); }
        for (Axis axis : haxes) {
            element.addContent( axis.build(includeAll)  );
        }
        for (Axis axis : vaxes) {
            element.addContent( axis.build(includeAll)  );
        }
        return element;
    }
}
