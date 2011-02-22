package org.multigraph;

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

public class Mugl extends MuglBean {

    private ArrayList<org.multigraph.mugl.Graph> graphs = new ArrayList<org.multigraph.mugl.Graph>();
	public ArrayList<org.multigraph.mugl.Graph> getGraphs() {
		return graphs;
	}
    public static Mugl parse( Element node, Element defaultsNode ) throws Exception {
        final Mugl mugl = new Mugl();
        
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "graph"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "graph");
            for (Element element : elements) {
                if (element != null) {
                    mugl.getGraphs().add( org.multigraph.mugl.Graph.parse( element, defaultsElement, mugl) );
                } else {
                	org.multigraph.mugl.Graph graph = org.multigraph.mugl.Graph.parse( null, defaultsElement, mugl);
                    
                    mugl.getGraphs().add( graph );
                }
            }
        }
        return mugl;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("mugl");
        for (org.multigraph.mugl.Graph graph : graphs) {
            element.addContent( graph.build(includeAll)  );
        }
        return element;
    }
}
