package org.multigraph.datatypes.number;

/**
 * 
 */

import org.multigraph.*;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.Formatter;

public class NumberFormatter extends Formatter {
    private String mFormatString;
    public NumberFormatter(String formatString) {
        mFormatString = formatString;
    }
    public String format(DataValue value) {
    	try {
    		return String.format(mFormatString, value.getDoubleValue());
    	} catch (java.util.IllegalFormatConversionException e) {
            // If the above formatting fails due to IllegalFormatConversionException, it
            // might be because the format string uses a 'd' format code, which Java
            // does not allow for double values.  Multigraph DOES allow it, though, with
            // the assumption being that the double value should be rounded to the nearest
            // integer.  So try that conversion here.
            return String.format(mFormatString, Math.round(value.getDoubleValue()));
    	}
    }
    public String format(double value) {
        return String.format(mFormatString, value);
    }
}