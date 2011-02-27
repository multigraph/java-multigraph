package org.multigraph;

public enum RendererType {
	
    UNKNOWN("unknown"),
    POINTLINE("pointline"),
    LINE("line"),
    POINT("point"),
    BAR("bar");
    private final String value;

    RendererType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RendererType parse(String v) {
        for (RendererType rendererType: RendererType.values()) {
            if (rendererType.value.equals(v)) {
                return rendererType;
            }
        }
        throw new IllegalArgumentException(v);
    }	
    public static String toString(RendererType rendererType) {
    	if (rendererType==null) { return "NULL-RendererType"; }
    	return rendererType.value();
    }	

}
