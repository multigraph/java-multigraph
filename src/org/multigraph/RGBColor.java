package org.multigraph;

import java.awt.Color;

public class RGBColor {

    private int r;
    private int g;
    private int b;

    public static final RGBColor BLACK = new RGBColor(0,0,0);
    public static final RGBColor WHITE = new RGBColor(0,0,0);

    public RGBColor(String string) {
        this.r = Integer.parseInt(string.substring(2,4), 16);
        this.g = Integer.parseInt(string.substring(4,6), 16);
        this.b = Integer.parseInt(string.substring(6,8), 16);
    }

    public RGBColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color getAWTColor() {
        return new Color(r,g,b);
    }

    public String toString() {
        return String.format("0x%02x%02x%02x", r, g, b);
    }

    public static RGBColor parse(String string) {
        return new RGBColor(string);
    }

    public static String toString(RGBColor rgbcolor) {
    	if (rgbcolor==null) { return "NULL-RGBCOLOR"; }
        return rgbcolor.toString();
    }

}
