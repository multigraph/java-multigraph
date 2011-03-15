/**
 * 
 */
package org.multigraph.datatypes.number;

import org.multigraph.datatypes.DataMeasure;

public class NumberMeasure extends DataMeasure {

    protected double value;

    public NumberMeasure() {}

    public NumberMeasure(double value) {
       	this.value = value;
    }

    public NumberMeasure(String value) {
        this.value = Double.parseDouble(value);
    }

    public double getRealValue() {
        return value;
    }

    public String toString() {
        return String.format("%f", value);
    }

}