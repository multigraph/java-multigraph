package org.multigraph;


import org.multigraph.datatypes.datetime.DatetimeFormatter;
import org.multigraph.datatypes.number.NumberFormatter;


public abstract class Formatter {

    abstract public String format(DataValue value);
    
    public static Formatter create(DataType type, String formatString) {
        switch (type) {
        case NUMBER:
            return new NumberFormatter(formatString);
        case DATETIME:
        	return new DatetimeFormatter(formatString);
        default:
            return null;
        }
    }
	
}
