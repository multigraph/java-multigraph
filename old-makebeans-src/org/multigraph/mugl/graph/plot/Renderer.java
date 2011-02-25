package org.multigraph.mugl.graph.plot;

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

public class Renderer extends MuglBean {

    private String type;
    private boolean typeIsSet     = false;
    private boolean typeIsDefault = false;
    private ArrayList<org.multigraph.mugl.graph.plot.renderer.Option> options = new ArrayList<org.multigraph.mugl.graph.plot.renderer.Option>();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setType(String type, boolean isDefault) {
        setType(type);
        this.typeIsSet = true;
        this.typeIsDefault = isDefault;
    }
	public boolean haveType() {
        return typeIsSet;
    }
	public ArrayList<org.multigraph.mugl.graph.plot.renderer.Option> getOptions() {
		return options;
	}
    public static Renderer parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Plot parent ) throws Exception {
        final Renderer renderer = new Renderer();
        
        setProperty("type", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        renderer.setType( value, isDefault );
                    }});
        
        {
            List<Element> elements = (List<Element>)(XPath.selectNodes(node, "option"));
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "option");
            for (Element element : elements) {
                if (element != null) {
                    renderer.getOptions().add( org.multigraph.mugl.graph.plot.renderer.Option.parse( element, defaultsElement, renderer) );
                } else {
                	org.multigraph.mugl.graph.plot.renderer.Option option = org.multigraph.mugl.graph.plot.renderer.Option.parse( null, defaultsElement, renderer);
                    
                    renderer.getOptions().add( option );
                }
            }
        }
        return renderer;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("renderer");
        if (typeIsSet && (includeAll || !typeIsDefault)) { element.setAttribute("type",  type ); }
        for (org.multigraph.mugl.graph.plot.renderer.Option option : options) {
            element.addContent( option.build(includeAll)  );
        }
        return element;
    }
}
