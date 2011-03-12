package org.multigraph.datatypes.number;

import org.multigraph.*;
import org.multigraph.datatypes.DataValue;

/**
 * DataValue.Number is the DataValue type that represents a basic numerical value.  The
 * value is stored as a double.
 */

public class NumberValue extends DataValue {
    protected double mValue;

    public NumberValue(double value) {
       	this.mValue = value;
    }
    public NumberValue(java.lang.String value) {
    	try {
    		this.mValue = java.lang.Double.parseDouble(value);
    	} catch (Exception e) {
    		this.mValue = 0;
    	}
    }
    public double getDoubleValue() {
        return mValue;
    }
    public java.lang.String toString() {
        return java.lang.String.format("%f", mValue);
    }
    public int compareTo(DataValue x) {
    	return (new Double(this.mValue)).compareTo(((NumberValue)x).mValue);
    }
}