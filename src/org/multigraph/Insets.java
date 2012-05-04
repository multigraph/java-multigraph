package org.multigraph;

public class Insets     {

    private double top;
    public double getTop() { return top; }

    private double left;
    public double getLeft() { return left; }

    private double bottom;
    public double getBottom() { return bottom; }

    private double right;
    public double getRight() { return right; }

    public Insets(double top, double left, double bottom, double right) {
        this.top    = top;
        this.left   = left;
        this.bottom = bottom;
        this.right  = right;
    }

    public Insets(double horizontal, double vertical) {
        this.top    = vertical;
        this.left   = horizontal;
        this.bottom = vertical;
        this.right  = horizontal;
    }

    public Insets(double s) {
        this.top    = s;
        this.left   = s;
        this.bottom = s;
        this.right  = s;
    }

    public Insets() {
        this.top    = 0;
        this.left   = 0;
        this.bottom = 0;
        this.right  = 0;
    }

}
