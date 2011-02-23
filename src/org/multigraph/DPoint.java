package org.multigraph;

public class DPoint {

    private String xString = null;
    private String yString = null;
    private double x;
    private double y;

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

}