package org.multigraph;

public abstract class DataInterval {

    public abstract double getDoubleValue();
    public abstract String getStringValue();

    public static DataInterval create(DataType type, String string) {
        switch (type) {
        case NUMBER:
            return new DoubleDataInterval(string);
        default:
            return null;
        }
    }

    public static DataInterval create(DataType type, double value) {
        switch (type) {
        case NUMBER:
            return new DoubleDataInterval(value);
        default:
            return null;
        }
    }

}
