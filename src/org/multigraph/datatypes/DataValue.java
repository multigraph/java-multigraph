package org.multigraph.datatypes;

import org.multigraph.DataTypeException;
import org.multigraph.datatypes.number.NumberValue;
import org.multigraph.datatypes.datetime.DatetimeValue;


public abstract class DataValue {

    public abstract double getDoubleValue();
    public abstract String toString();

    public static DataValue create(DataType type, java.lang.String string) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberValue(string);
        case DATETIME:
            return new DatetimeValue(string);
        default:
            throw new DataTypeException(String.format("DataValue.create: unknown DataType ('%s') when converting string '%s' to DataValue",
                                                      type.toString(), string));
        }
    }

    public static DataValue create(DataType type, double value) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberValue(value);
        case DATETIME:
            return new DatetimeValue(value);
        default:
            throw new DataTypeException(String.format("DataValue.create: unknown DataType ('%s') when converting double '%f' to DataValue",
                                                      type.toString(), value));
        }
    }

    public abstract int compareTo(DataValue x);
    
    public boolean lt(DataValue x) { return compareTo(x) <  0; }
    public boolean le(DataValue x) { return compareTo(x) <= 0; }
    public boolean eq(DataValue x) { return compareTo(x) == 0; }
    public boolean ge(DataValue x) { return compareTo(x) >= 0; }
    public boolean gt(DataValue x) { return compareTo(x) >  0; }
    
    
    
}
