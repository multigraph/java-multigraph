package org.multigraph.datatypes.number;

import org.multigraph.*;
import org.multigraph.datatypes.DataValue;

/**
 * NumberValue is the DataValue implementation for the NUMBER DataType.  It represents
 * a numerical data value.  The value is stored internally as a double.
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
    /**
     * Return the real number associated with this NumberValue --- i.e. its
     * numerical value.
     */
    @Override
        public double getRealValue() {
        return mValue;
    }

    @Override
        public java.lang.String toString() {
        return java.lang.String.format("%f", mValue);
    }

    @Override
        public int compareTo(DataValue x) {
        return (new Double(this.mValue)).compareTo(((NumberValue)x).mValue);
    }

}
