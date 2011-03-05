package org.multigraph.datatypes.string;

import org.multigraph.DataType;
import org.multigraph.DataValue;
import org.multigraph.datatypes.number.NumberValue;

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

public class StringValue extends DataValue {
    protected java.lang.String value;
    public StringValue() {}
    public StringValue(java.lang.String value) {
        this.value = value;
    }
    public double getDoubleValue() {
        return 0;
    }
    public java.lang.String getStringValue() {
        return value;
    }
    public int compareTo(DataValue x) {
    	return this.value.compareTo(((StringValue)x).value);
    }
    public static StringValue parse(java.lang.String string) {
    	return new StringValue(string);
    }
    public static java.lang.String toString(StringValue d) {
    	return d.getStringValue();
    }
    public boolean isAuto() {
    	return this.value.toLowerCase().equals("auto");
    }
    public DataValue toDataValue(DataType type) {
    	switch (type) {
    	case NUMBER:
    		return new NumberValue(this.value);
    	}
		return null;	
    }

}