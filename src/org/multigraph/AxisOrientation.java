package org.multigraph;

public enum AxisOrientation {
	
    UNKNOWN("unknown"),
    HORIZONTAL("horizontal"),
    VERTICAL("vertical");
    private final String value;

    AxisOrientation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AxisOrientation parse(String v) {
        for (AxisOrientation c: AxisOrientation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }	
    public static String toString(AxisOrientation dt) {
    	if (dt==null) { return "NULL-AxisOrientation"; }
    	return dt.value();
    }

}
