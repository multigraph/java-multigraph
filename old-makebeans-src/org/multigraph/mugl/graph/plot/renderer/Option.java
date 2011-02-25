package org.multigraph.mugl.graph.plot.renderer;

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

public class Option extends MuglBean {

    private String name;
    private boolean nameIsSet     = false;
    private boolean nameIsDefault = false;
    private String value;
    private boolean valueIsSet     = false;
    private boolean valueIsDefault = false;
    private String min;
    private boolean minIsSet     = false;
    private boolean minIsDefault = false;
    private String max;
    private boolean maxIsSet     = false;
    private boolean maxIsDefault = false;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setName(String name, boolean isDefault) {
        setName(name);
        this.nameIsSet = true;
        this.nameIsDefault = isDefault;
    }
	public boolean haveName() {
        return nameIsSet;
    }
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setValue(String value, boolean isDefault) {
        setValue(value);
        this.valueIsSet = true;
        this.valueIsDefault = isDefault;
    }
	public boolean haveValue() {
        return valueIsSet;
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
    public static Option parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.plot.Renderer parent ) throws Exception {
        final Option option = new Option();
        
        setProperty("name", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        option.setName( value, isDefault );
                    }});
        
        setProperty("value", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        option.setValue( value, isDefault );
                    }});
        
        setProperty("min", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        option.setMin( value, isDefault );
                    }});
        
        setProperty("max", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        option.setMax( value, isDefault );
                    }});
        
        return option;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("option");
        if (nameIsSet && (includeAll || !nameIsDefault)) { element.setAttribute("name",  name ); }
        if (valueIsSet && (includeAll || !valueIsDefault)) { element.setAttribute("value",  value ); }
        if (minIsSet && (includeAll || !minIsDefault)) { element.setAttribute("min",  min ); }
        if (maxIsSet && (includeAll || !maxIsDefault)) { element.setAttribute("max",  max ); }
        return element;
    }
}
