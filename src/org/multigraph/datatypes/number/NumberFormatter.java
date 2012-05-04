package org.multigraph.datatypes.number;

import org.multigraph.*;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.Formatter;

/**
 * The NumberFormatter formats DataValues of DataType.NUMBER (i.e. type NumberValue).
 */
public class NumberFormatter extends Formatter {
    /**
     * The format string for this Formatter; this is a "printf"-style format string,
     * with any of the associated '%' conversion characters allowed.
     */
    private String mFormatString;

    /**
     * Create a new NumberFormatter.
     *
     * @param formatString The format string for this formatter.
     */
    public NumberFormatter(String formatString) {
        mFormatString = formatString;
    }

    /**
     * Convert a value to a string.
     *
     * @param value The value to be converted
     * @returns The formatted String
     */
    @Override
        public String format(DataValue value) {
        try {
            return String.format(mFormatString, value.getRealValue());
        } catch (java.util.IllegalFormatConversionException e) {
            // If the above formatting fails due to IllegalFormatConversionException, it
            // might be because the format string uses a 'd' format code, which Java
            // does not allow for double values.  Multigraph DOES allow it, though, with
            // the assumption being that the double value should be rounded to the nearest
            // integer.  So try that conversion here.
            return String.format(mFormatString, Math.round(value.getRealValue()));
        }
    }

    /**
     * Convert a value to a string.
     *
     * @param value The value to be converted
     * @returns The formatted String
     */
    public String format(double value) {
        return String.format(mFormatString, value);
    }

    /**
     * Return the maximum string length that this formatter typically generates.
     */
    @Override
        public int getMaxLength() {
        // TODO Auto-generated method stub; this method hasn't been implemented yet.
        System.out.println("NumberFormatter.getMaxLength() is not yet implemented!!");
        return 0;
    }
}
