package org.multigraph.datatypes.string;

import org.multigraph.DataTypeException;
import org.multigraph.datatypes.DataType;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.number.NumberValue;
import org.multigraph.datatypes.datetime.DatetimeValue;

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
    protected java.lang.String mValue;
    
    public StringValue(java.lang.String value) {
        this.mValue = value;
    }
    public double getDoubleValue() {
        return 0;
    }
    public int compareTo(DataValue x) {
    	return this.mValue.compareTo(((StringValue)x).mValue);
    }
    public static StringValue parse(java.lang.String string) {
    	return new StringValue(string);
    }
    public String toString() {
    	return mValue;
    }
    public static java.lang.String toString(StringValue d) {
    	return d.toString();
    }
    public boolean isAuto() {
    	return this.mValue.toLowerCase().equals("auto");
    }
    public DataValue toDataValue(DataType type) throws DataTypeException {
    	switch (type) {
    	case NUMBER:
    		return new NumberValue(this.mValue);
    	case DATETIME:
    		return new DatetimeValue(this.mValue);
        default:
            throw new DataTypeException(String.format("StringValue.toDataValue: unknown DataType ('%s') when converting string '%s' to DataValue",
                                                      type.toString(), this.mValue));

    	}
    }

}
