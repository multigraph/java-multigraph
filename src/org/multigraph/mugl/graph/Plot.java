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

public class Plot extends MuglBean {

    private org.multigraph.mugl.graph.plot.Legend legend;
    private boolean legendIsDefault = false;
    private org.multigraph.mugl.graph.plot.Axis horizontalaxis;
    private boolean horizontalaxisIsDefault = false;
    private org.multigraph.mugl.graph.plot.Axis verticalaxis;
    private boolean verticalaxisIsDefault = false;
    private org.multigraph.mugl.graph.plot.Renderer renderer;
    private boolean rendererIsDefault = false;
	public org.multigraph.mugl.graph.plot.Legend getLegend() {
		return legend;
	}
	public void setLegend(org.multigraph.mugl.graph.plot.Legend legend) {
		this.legend = legend;
	}
	public void setLegend(org.multigraph.mugl.graph.plot.Legend legend, boolean isDefault) {
        setLegend(legend);
        this.legendIsDefault = isDefault;
    }
    public boolean haveLegend() {
        return this.legend != null;
    }
	public org.multigraph.mugl.graph.plot.Axis getHorizontalaxis() {
		return horizontalaxis;
	}
	public void setHorizontalaxis(org.multigraph.mugl.graph.plot.Axis horizontalaxis) {
		this.horizontalaxis = horizontalaxis;
	}
	public void setHorizontalaxis(org.multigraph.mugl.graph.plot.Axis horizontalaxis, boolean isDefault) {
        setHorizontalaxis(horizontalaxis);
        this.horizontalaxisIsDefault = isDefault;
    }
    public boolean haveHorizontalaxis() {
        return this.horizontalaxis != null;
    }
	public org.multigraph.mugl.graph.plot.Axis getVerticalaxis() {
		return verticalaxis;
	}
	public void setVerticalaxis(org.multigraph.mugl.graph.plot.Axis verticalaxis) {
		this.verticalaxis = verticalaxis;
	}
	public void setVerticalaxis(org.multigraph.mugl.graph.plot.Axis verticalaxis, boolean isDefault) {
        setVerticalaxis(verticalaxis);
        this.verticalaxisIsDefault = isDefault;
    }
    public boolean haveVerticalaxis() {
        return this.verticalaxis != null;
    }
	public org.multigraph.mugl.graph.plot.Renderer getRenderer() {
		return renderer;
	}
	public void setRenderer(org.multigraph.mugl.graph.plot.Renderer renderer) {
		this.renderer = renderer;
	}
	public void setRenderer(org.multigraph.mugl.graph.plot.Renderer renderer, boolean isDefault) {
        setRenderer(renderer);
        this.rendererIsDefault = isDefault;
    }
    public boolean haveRenderer() {
        return this.renderer != null;
    }
    public static Plot parse( Element node, Element defaultsNode, org.multigraph.mugl.Graph parent ) throws Exception {
        final Plot plot = new Plot();
        
        {
            Element element = (Element)XPath.selectSingleNode(node, "legend");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "legend");
            if (element != null) {
                plot.setLegend( org.multigraph.mugl.graph.plot.Legend.parse( element, defaultsElement, plot), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.plot.Legend legend = org.multigraph.mugl.graph.plot.Legend.parse( null, defaultsElement, plot);
                
                plot.setLegend( legend, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "horizontalaxis");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "axis");
            if (element != null) {
                plot.setHorizontalaxis( org.multigraph.mugl.graph.plot.Axis.parse(org.multigraph.mugl.graph.Axis.Orientation.HORIZONTAL, element, defaultsElement, plot), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.plot.Axis horizontalaxis = org.multigraph.mugl.graph.plot.Axis.parse(org.multigraph.mugl.graph.Axis.Orientation.HORIZONTAL, null, defaultsElement, plot);
                
                plot.setHorizontalaxis( horizontalaxis, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "verticalaxis");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "axis");
            if (element != null) {
                plot.setVerticalaxis( org.multigraph.mugl.graph.plot.Axis.parse(org.multigraph.mugl.graph.Axis.Orientation.VERTICAL, element, defaultsElement, plot), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.plot.Axis verticalaxis = org.multigraph.mugl.graph.plot.Axis.parse(org.multigraph.mugl.graph.Axis.Orientation.VERTICAL, null, defaultsElement, plot);
                
                plot.setVerticalaxis( verticalaxis, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "renderer");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "renderer");
            if (element != null) {
                plot.setRenderer( org.multigraph.mugl.graph.plot.Renderer.parse( element, defaultsElement, plot), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.plot.Renderer renderer = org.multigraph.mugl.graph.plot.Renderer.parse( null, defaultsElement, plot);
                
                plot.setRenderer( renderer, true );
            }
        }
        return plot;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("plot");
        if (haveLegend() && (includeAll || !legendIsDefault)) { element.addContent( legend.build(includeAll)  ); }
        if (haveHorizontalaxis() && (includeAll || !horizontalaxisIsDefault)) { element.addContent( horizontalaxis.build(includeAll)  ); }
        if (haveVerticalaxis() && (includeAll || !verticalaxisIsDefault)) { element.addContent( verticalaxis.build(includeAll)  ); }
        if (haveRenderer() && (includeAll || !rendererIsDefault)) { element.addContent( renderer.build(includeAll)  ); }
        return element;
    }
}
