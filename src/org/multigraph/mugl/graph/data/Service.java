package org.multigraph.mugl.graph.data;

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

public class Service extends MuglBean {

    private String location;
    private boolean locationIsSet     = false;
    private boolean locationIsDefault = false;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setLocation(String location, boolean isDefault) {
        setLocation(location);
        this.locationIsSet = true;
        this.locationIsDefault = isDefault;
    }
	public boolean haveLocation() {
        return locationIsSet;
    }
    public static Service parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.Data parent ) throws Exception {
        final Service service = new Service();
        
        setProperty("location", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        service.setLocation( value, isDefault );
                    }});
        
        return service;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("service");
        if (locationIsSet && (includeAll || !locationIsDefault)) { element.setAttribute("location",  location ); }
        return element;
    }
}
