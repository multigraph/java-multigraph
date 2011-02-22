package org.multigraph;

public class DoubleDataValue extends DataValue {

    protected double value;

    public DoubleDataValue() {}

    public DoubleDataValue(double value) {
       	this.value = value;
    }

    public DoubleDataValue(String value) {
    	try {
    		this.value = Double.parseDouble(value);
    	} catch (Exception e) {
    		this.value = 0;
    	}
    }

    public double getDoubleValue() {
        return value;
    }

    public String getStringValue() {
        return String.format("%f", value);
    }

    public boolean lt(DataValue x) {
        return value < ((DoubleDataValue)x).value;
    }
    public boolean le(DataValue x) {
        return value <= ((DoubleDataValue)x).value;
    }
    public boolean eq(DataValue x) {
        return value == ((DoubleDataValue)x).value;
    }
    public boolean ge(DataValue x) {
        return value >= ((DoubleDataValue)x).value;
    }
    public boolean gt(DataValue x) {
        return value > ((DoubleDataValue)x).value;
    }

}
