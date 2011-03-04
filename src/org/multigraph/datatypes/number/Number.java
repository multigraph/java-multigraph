package org.multigraph.datatypes.number;

/**
 * DataValue.Number is the DataValue type that represents a basic numerical value.  The
 * value is stored as a double.
 */

public class Number extends org.multigraph.DataValue {
    protected double value;
    public Number() {}
    public Number(double value) {
       	this.value = value;
    }
    public Number(java.lang.String value) {
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
    public int compareTo(org.multigraph.DataValue x) {
    	return (new Double(this.value)).compareTo(((Number)x).value);
    }
}