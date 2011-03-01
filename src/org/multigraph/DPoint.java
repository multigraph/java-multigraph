package org.multigraph;

public class DPoint {

    private String xString = null;
    private String yString = null;
    private double x;
    private double y;
    
    public DPoint(double x, double y) {
    	this.x = x;
    	this.y = y;
    }

    public DPoint(String string) {
        String coords[] = string.split("[ ,]+");
        this.xString = coords[0];
        this.yString = coords[1];
        this.x = Double.parseDouble(this.xString);
        this.y = Double.parseDouble(this.yString);
    }

    public String toString() {
        return (((xString != null) ? xString : Double.toString(x))
        		+ " " +
        		((yString != null) ? yString : Double.toString(y)));
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    
    public static DPoint parse(String string) {
    	return new DPoint(string);
    }
    public static String toString(DPoint d) {
    	if (d==null) { return "NULL-DPOINT"; }
    	return d.toString();
    }

}
