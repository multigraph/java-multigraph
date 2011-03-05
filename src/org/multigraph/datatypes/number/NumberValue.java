package org.multigraph.datatypes.number;

import org.multigraph.*;

/**
 * DataValue.Number is the DataValue type that represents a basic numerical value.  The
 * value is stored as a double.
 */

public class NumberValue extends DataValue {
    protected double value;
    public NumberValue() {}
    public NumberValue(double value) {
       	this.value = value;
    }
    public NumberValue(java.lang.String value) {
    	try {
    		this.value = java.lang.Double.parseDouble(value);
    	} catch (Exception e) {
    		this.value = 0;
    	}
    }
    public double getDoubleValue() {
        return value;
    }
    public java.lang.String getStringValue() {
        return java.lang.String.format("%f", value);
    }
    public int compareTo(DataValue x) {
    	return (new Double(this.value)).compareTo(((NumberValue)x).value);
    }
}