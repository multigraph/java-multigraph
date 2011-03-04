package org.multigraph;


public abstract class DataValue {

    public abstract double getDoubleValue();
    public abstract java.lang.String getStringValue();

    public static DataValue create(DataType type, java.lang.String string) {
        switch (type) {
        case NUMBER:
            return new Number(string);
        default:
            return null;
        }
    }

    public static DataValue create(DataType type, double value) {
        switch (type) {
        case NUMBER:
            return new Number(value);
        default:
            return null;
        }
    }

    public abstract int compareTo(DataValue x);

    public boolean lt(DataValue x) { return compareTo(x) <  0; }
    public boolean le(DataValue x) { return compareTo(x) <= 0; }
    public boolean eq(DataValue x) { return compareTo(x) == 0; }
    public boolean ge(DataValue x) { return compareTo(x) >= 0; }
    public boolean gt(DataValue x) { return compareTo(x) >  0; }

    /**
     * DataValue.Number is the DataValue type that represents a basic numerical value.  The
     * value is stored as a double.
     */
    
    public static class Number extends DataValue {
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
        public int compareTo(DataValue x) {
        	return (new Double(this.value)).compareTo(((Number)x).value);
        }
    }


    /**
     * DataValue.String is a utility type that is intended for holding
     * values that are used for the "min"/"max" axis attributes that
     * may be either the word "auto", or an actual data value (either
     * numeric or datetime).  This type isn't intended to be used as
     * an actual data value for plotting.  Its getDoubleValue() method
     * always returns 0.  It has convenience methods for checking to
     * see if its value is the string "auto", and for converting to
     * either DataValue.Number or DataValue.Datetime.  (This conversion
     * only works if the value is a string that can be interpreted
     * as a number or a datetime; it doesn't work if the value is the
     * string "auto".)
     */

    public static class String extends DataValue {
        protected java.lang.String value;
        public String() {}
        public String(java.lang.String value) {
            this.value = value;
        }
        public double getDoubleValue() {
            return 0;
        }
        public java.lang.String getStringValue() {
            return value;
        }
        public int compareTo(DataValue x) {
        	return this.value.compareTo(((DataValue.String)x).value);
        }
        public static DataValue.String parse(java.lang.String string) {
        	return new String(string);
        }
        public static java.lang.String toString(DataValue.String d) {
        	return d.getStringValue();
        }
        public boolean isAuto() {
        	return this.value.toLowerCase().equals("auto");
        }
        public DataValue toDataValue(DataType type) {
        	switch (type) {
        	case NUMBER:
        		return new DataValue.Number(this.value);
        	}
    		return null;	
        }

    }
    
    
    
}
