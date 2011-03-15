package org.multigraph.datatypes.string;

import org.multigraph.DataTypeException;
import org.multigraph.datatypes.DataType;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.number.NumberValue;
import org.multigraph.datatypes.datetime.DatetimeValue;

/**
 * StringValue is a utility type that is intended for holding
 * string values that will later be converted to either NumberValue
 * or DatetimeValue; it is not a completely functional implementation of
 * the DataValue superclass, and is not intended to be used as
 * a data value for plotting. 
 * <p>
 * StringValue has convenience methods for converting the stored
 * string value to either NumberValue or DatetimeValue, and a
 * method for testing whether the string value is equal to
 * the string "auto", which is a possible value for the "min"/"max" MUGL axis attributes.
 *
 */
public class StringValue extends DataValue {
    /**
     * The string for this value.
     */
    protected String mValue;

    /**
     * Construct a new StringValue with a given string value.
     */
    public StringValue(String value) {
        this.mValue = value;
    }

    /**
     * This method should not be used; it is required to be here by the DataValue superclass, but
     * this implementation always returns 0 and is not intended to be useful.  Just ignore it.
     */
    @Override
    public double getRealValue() {
        return 0;
    }

    @Override
    public int compareTo(DataValue x) {
    	return this.mValue.compareTo(((StringValue)x).mValue);
    }

    /**
     * Static factory method to return a new StringValue instance with a given string value.
     */
    public static StringValue parse(String string) {
    	return new StringValue(string);
    }

    @Override
    public String toString() {
    	return mValue;
    }

    /**
     * Static method to convert a StringValue to a String
     */
    public static String toString(StringValue d) {
    	return d.toString();
    }

    /**
     * Test whether this StringValue is equal to the word "auto".  Test is case insensitive.
     */
    public boolean isAuto() {
    	return this.mValue.toLowerCase().equals("auto");
    }

    /**
     * Convert this StringValue to a NumberValue or DatetimeValue, by parsing the stored string.
     * The conversion will fail if the string cannot be interpreted as the given type.  In particular,
     * it will fail if the string is "auto".
     *
     * @param type The DataType to convert to
     */
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
