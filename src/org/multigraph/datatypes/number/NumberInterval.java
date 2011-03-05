/**
 * 
 */
package org.multigraph.datatypes.number;

import org.multigraph.DataInterval;

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

    public String getStringValue() {
        return String.format("%f", value);
    }

}