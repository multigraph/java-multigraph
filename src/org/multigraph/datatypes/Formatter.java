package org.multigraph.datatypes;


import org.multigraph.DataTypeException;
import org.multigraph.datatypes.datetime.DatetimeFormatter;
import org.multigraph.datatypes.number.NumberFormatter;


public abstract class Formatter {

    abstract public String format(DataValue value);
    
    public static Formatter create(DataType type, String formatString) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberFormatter(formatString);
        case DATETIME:
        	return new DatetimeFormatter(formatString);
        default:
            throw new DataTypeException(String.format("Formatter.create: unknown DataType ('%s') when constructing Formatter with format '%s'",
                                                      type.toString(), formatString));
        }
    }
	
}
