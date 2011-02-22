package org.multigraph;

public class DoubleDataInterval extends DataInterval {

    protected double value;

    public DoubleDataInterval() {}

    public DoubleDataInterval(double value) {
       	this.value = value;
    }

    public DoubleDataInterval(String value) {
        this.value = Double.parseDouble(value);
    }

    public double getDoubleValue() {
        return value;
    }

    public String getStringValue() {
        return String.format("%f", value);
    }

}
