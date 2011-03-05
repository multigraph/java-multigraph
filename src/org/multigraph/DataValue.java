package org.multigraph;

import org.multigraph.datatypes.number.NumberValue;


public abstract class DataValue {

    public abstract double getDoubleValue();
    public abstract java.lang.String getStringValue();

    public static DataValue create(DataType type, java.lang.String string) {
        switch (type) {
        case NUMBER:
            return new NumberValue(string);
        default:
            return null;
        }
    }

    public static DataValue create(DataType type, double value) {
        switch (type) {
        case NUMBER:
            return new NumberValue(value);
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
    
    
    
}
