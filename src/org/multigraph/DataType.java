package org.multigraph;

public enum DataType {
	
    UNKNOWN("unknown"),
    NUMBER("number"),
    DATETIME("datetime");
    private final String value;

    DataType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DataType parse(String v) {
        for (DataType c: DataType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }	
    public static String toString(DataType dt) {
    	if (dt==null) { return "NULL-DATATYPE"; }
    	return dt.value();
    }

}
