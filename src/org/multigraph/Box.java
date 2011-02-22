package org.multigraph;

public class Box {
    private double width;
    private double height;

    public double getWidth() { return width; }
    public double getHeight() { return height; }
		
    public Box() {
        this.width = 0;
        this.height = 0;
    }

    public Box(double s) {
        this.width = s;
        this.height = s;
    }

    public Box(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
