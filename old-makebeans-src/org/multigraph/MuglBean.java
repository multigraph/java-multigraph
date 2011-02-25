package org.multigraph;

import org.jdom.DataConversionException;
import org.jdom.Element;
import org.jdom.Attribute;

public abstract class MuglBean {

    public abstract static class PropertySetter {
        public abstract void set(String value, boolean isDefault);
    }

    public static void setProperty(String attrName,
                                   Element node,
                                   Element defaultsNode,
                                   PropertySetter setter) throws Exception {
        setProperty(attrName, node, defaultsNode, true, setter);
    }

    public static void setProperty(String attrName,
                                   Element node,
                                   Element defaultsNode,
                                   boolean errorIfNoValue,
                                   PropertySetter setter)
    throws Exception {
        Attribute attr = (node == null) ? null : node.getAttribute(attrName);
        boolean isDefault = false;
        if (attr == null) {
            attr = defaultsNode != null ? defaultsNode.getAttribute(attrName) : null;
            isDefault = true;
        }
        String stringValue = (attr == null) ? null : attr.getValue();
        if (stringValue != null) {
            setter.set( stringValue, isDefault );
        } else if (errorIfNoValue) {
            throw new Exception("no " + attrName + " attribute given for axis, and no default value available");
        }
        
    }

    public Element build() throws Exception {
        return build(false);
    }

    public abstract Element build(boolean includeAll) throws Exception;

}
