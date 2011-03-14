/**
 * 
 */
package org.multigraph.datatypes.number;

import org.multigraph.datatypes.DataInterval;

public class NumberInterval extends DataInterval {

    protected double value;

    public NumberInterval() {}

    public NumberInterval(double value) {
       	this.value = value;
    }

    public NumberInterval(String value) {
        this.value = Double.parseDouble(value);
    }

    public double getDoubleValue() {
        return value;
    }

    public String toString() {
        return String.format("%f", value);
    }

}