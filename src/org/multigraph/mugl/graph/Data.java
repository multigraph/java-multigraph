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

public class Data extends MuglBean {

    private org.multigraph.mugl.graph.data.Variables variables;
    private boolean variablesIsDefault = false;
    private org.multigraph.mugl.graph.data.Values values;
    private boolean valuesIsDefault = false;
    private org.multigraph.mugl.graph.data.Csv csv;
    private boolean csvIsDefault = false;
    private org.multigraph.mugl.graph.data.Service service;
    private boolean serviceIsDefault = false;
	public org.multigraph.mugl.graph.data.Variables getVariables() {
		return variables;
	}
	public void setVariables(org.multigraph.mugl.graph.data.Variables variables) {
		this.variables = variables;
	}
	public void setVariables(org.multigraph.mugl.graph.data.Variables variables, boolean isDefault) {
        setVariables(variables);
        this.variablesIsDefault = isDefault;
    }
    public boolean haveVariables() {
        return this.variables != null;
    }
	public org.multigraph.mugl.graph.data.Values getValues() {
		return values;
	}
	public void setValues(org.multigraph.mugl.graph.data.Values values) {
		this.values = values;
	}
	public void setValues(org.multigraph.mugl.graph.data.Values values, boolean isDefault) {
        setValues(values);
        this.valuesIsDefault = isDefault;
    }
    public boolean haveValues() {
        return this.values != null;
    }
	public org.multigraph.mugl.graph.data.Csv getCsv() {
		return csv;
	}
	public void setCsv(org.multigraph.mugl.graph.data.Csv csv) {
		this.csv = csv;
	}
	public void setCsv(org.multigraph.mugl.graph.data.Csv csv, boolean isDefault) {
        setCsv(csv);
        this.csvIsDefault = isDefault;
    }
    public boolean haveCsv() {
        return this.csv != null;
    }
	public org.multigraph.mugl.graph.data.Service getService() {
		return service;
	}
	public void setService(org.multigraph.mugl.graph.data.Service service) {
		this.service = service;
	}
	public void setService(org.multigraph.mugl.graph.data.Service service, boolean isDefault) {
        setService(service);
        this.serviceIsDefault = isDefault;
    }
    public boolean haveService() {
        return this.service != null;
    }
    public static Data parse( Element node, Element defaultsNode, org.multigraph.mugl.Graph parent ) throws Exception {
        final Data data = new Data();
        
        {
            Element element = (Element)XPath.selectSingleNode(node, "variables");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "variables");
            if (element != null) {
                data.setVariables( org.multigraph.mugl.graph.data.Variables.parse( element, defaultsElement, data), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.data.Variables variables = org.multigraph.mugl.graph.data.Variables.parse( null, defaultsElement, data);
                
                data.setVariables( variables, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "values");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "values");
            if (element != null) {
                data.setValues( org.multigraph.mugl.graph.data.Values.parse( element, defaultsElement, data), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.data.Values values = org.multigraph.mugl.graph.data.Values.parse( null, defaultsElement, data);
                
                data.setValues( values, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "csv");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "csv");
            if (element != null) {
                data.setCsv( org.multigraph.mugl.graph.data.Csv.parse( element, defaultsElement, data), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.data.Csv csv = org.multigraph.mugl.graph.data.Csv.parse( null, defaultsElement, data);
                
                data.setCsv( csv, true );
            }
        }
        {
            Element element = (Element)XPath.selectSingleNode(node, "service");
            Element defaultsElement = (Element)XPath.selectSingleNode(defaultsNode, "service");
            if (element != null) {
                data.setService( org.multigraph.mugl.graph.data.Service.parse( element, defaultsElement, data), false );
            } else if (defaultsElement != null) {
            	org.multigraph.mugl.graph.data.Service service = org.multigraph.mugl.graph.data.Service.parse( null, defaultsElement, data);
                
                data.setService( service, true );
            }
        }
        return data;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("data");
        if (haveVariables() && (includeAll || !variablesIsDefault)) { element.addContent( variables.build(includeAll)  ); }
        if (haveValues() && (includeAll || !valuesIsDefault)) { element.addContent( values.build(includeAll)  ); }
        if (haveCsv() && (includeAll || !csvIsDefault)) { element.addContent( csv.build(includeAll)  ); }
        if (haveService() && (includeAll || !serviceIsDefault)) { element.addContent( service.build(includeAll)  ); }
        return element;
    }
}
