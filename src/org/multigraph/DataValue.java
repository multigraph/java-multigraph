package org.multigraph;

public abstract class DataValue {

    public static enum Type { UNKNOWN, NUMBER, DATETIME };

    public static String TypeToString(Type type) {
        if (type == Type.UNKNOWN) { return "unknown"; }
        if (type == Type.NUMBER) { return "number"; }
        if (type == Type.DATETIME) { return "datetime"; }
        return null;
    }

    public static Type StringToType(String string) {
        if (string.equals("datetime")) { return Type.DATETIME; }
        if (string.equals("number")) { return Type.NUMBER; }
        return Type.UNKNOWN;
    }

    public abstract double getDoubleValue();
    public abstract String getStringValue();

    public static DataValue create(Type type, String string) {
        switch (type) {
        case NUMBER:
            return new DoubleDataValue(string);
        default:
            return null;
        }
    }

    public static DataValue create(Type type, double value) {
        switch (type) {
        case NUMBER:
            return new DoubleDataValue(value);
        default:
            return null;
        }
    }

    public abstract boolean lt(DataValue x);
    public abstract boolean le(DataValue x);
    public abstract boolean eq(DataValue x);
    public abstract boolean ge(DataValue x);
    public abstract boolean gt(DataValue x);
}
