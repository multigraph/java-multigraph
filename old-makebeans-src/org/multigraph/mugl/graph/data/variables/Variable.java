package org.multigraph.mugl.graph.data.variables;

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

public class Variable extends MuglBean {

    private String id;
    private boolean idIsSet     = false;
    private boolean idIsDefault = false;
    private int column;
    private boolean columnIsSet     = false;
    private boolean columnIsDefault = false;
    private DataValue.Type type;
    private boolean typeIsSet     = false;
    private boolean typeIsDefault = false;
    private String missingvalue;
    private boolean missingvalueIsSet     = false;
    private boolean missingvalueIsDefault = false;
    private String missingop;
    private boolean missingopIsSet     = false;
    private boolean missingopIsDefault = false;
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
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public void setColumn(int column, boolean isDefault) {
        setColumn(column);
        this.columnIsSet = true;
        this.columnIsDefault = isDefault;
    }
	public boolean haveColumn() {
        return columnIsSet;
    }
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
	public String getMissingvalue() {
		return missingvalue;
	}
	public void setMissingvalue(String missingvalue) {
		this.missingvalue = missingvalue;
	}
	public void setMissingvalue(String missingvalue, boolean isDefault) {
        setMissingvalue(missingvalue);
        this.missingvalueIsSet = true;
        this.missingvalueIsDefault = isDefault;
    }
	public boolean haveMissingvalue() {
        return missingvalueIsSet;
    }
	public String getMissingop() {
		return missingop;
	}
	public void setMissingop(String missingop) {
		this.missingop = missingop;
	}
	public void setMissingop(String missingop, boolean isDefault) {
        setMissingop(missingop);
        this.missingopIsSet = true;
        this.missingopIsDefault = isDefault;
    }
	public boolean haveMissingop() {
        return missingopIsSet;
    }
    public static Variable parse( Element node, Element defaultsNode, org.multigraph.mugl.graph.data.Variables parent ) throws Exception {
        final Variable variable = new Variable();
        
        setProperty("id", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        variable.setId( value, isDefault );
                    }});
        
        setProperty("column", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        variable.setColumn( Integer.parseInt(value), isDefault );
                    }});
        
        setProperty("type", node, defaultsNode,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        variable.setType( DataValue.StringToType(value), isDefault );
                    }});
        
        setProperty("missingvalue", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        variable.setMissingvalue( value, isDefault );
                    }});
        if (!variable.haveMissingvalue()) { variable.setMissingvalue(parent.getMissingvalue(),true); }
        setProperty("missingop", node, defaultsNode, false,
                    new PropertySetter() { public void set(String value, boolean isDefault) {
                        variable.setMissingop( value, isDefault );
                    }});
        if (!variable.haveMissingop()) { variable.setMissingop(parent.getMissingop(),true); }
        return variable;
    }
    public Element build(boolean includeAll) throws Exception {
    	Element element = new Element("variable");
        if (idIsSet && (includeAll || !idIsDefault)) { element.setAttribute("id",  id ); }
        if (columnIsSet && (includeAll || !columnIsDefault)) { element.setAttribute("column",  Integer.toString(column) ); }
        if (typeIsSet && (includeAll || !typeIsDefault)) { element.setAttribute("type",  DataValue.TypeToString(type) ); }
        if (missingvalueIsSet && (includeAll || !missingvalueIsDefault)) { element.setAttribute("missingvalue",  missingvalue ); }
        if (missingopIsSet && (includeAll || !missingopIsDefault)) { element.setAttribute("missingop",  missingop ); }
        return element;
    }
}
