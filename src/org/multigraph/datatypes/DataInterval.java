package org.multigraph.datatypes;

import org.multigraph.DataTypeException;
import org.multigraph.datatypes.number.NumberInterval;
import org.multigraph.datatypes.datetime.DatetimeInterval;

public abstract class DataInterval {

    public abstract double getDoubleValue();
    public abstract String toString();

    public static DataInterval create(DataType type, String string) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberInterval(string);
        case DATETIME:
            return new DatetimeInterval(string);
        default:
            throw new DataTypeException(String.format("DataInterval.create: unknown DataType ('%s') when converting string '%s' to DataInterval",
                                                      type.toString(), string));
        }
    }

    public static DataInterval create(DataType type, double value) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberInterval(value);
        case DATETIME:
            return new DatetimeInterval(value);
        default:
            throw new DataTypeException(String.format("DataInterval.create: unknown DataType ('%s') when converting double '%f' to DataInterval",
                                                      type.toString(), value));
        }
    }
    

}
