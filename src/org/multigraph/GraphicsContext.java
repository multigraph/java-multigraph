package org.multigraph;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Stack;

public class GraphicsContext {

    Graphics2D g;
    Stack<AffineTransform> transformStack;

    public void printStack(String msg) {
        System.out.printf("[%s] top of stack: %s\n", msg, transformStack.peek().toString());
    }
    
    public AffineTransform getTransform() {
    	return transformStack.peek();
    }
    
    public void scale(double sx, double sy) {
    	transformStack.peek().scale(sx,sy);
    }
    
    public void translate(double tx, double ty) {
    	transformStack.peek().translate(tx, ty);
    }
    
    public void concatTransform(AffineTransform M) {
    	transformStack.peek().concatenate(M);
    }
    
    public void preConcatTransform(AffineTransform M) {
    	transformStack.peek().preConcatenate(M);
    }
    
    public void pushTransform(AffineTransform T) {
    	transformStack.push(T);
    }
    
    public void pushTransform() {
    	transformStack.push(new AffineTransform(transformStack.peek()));
    }
    
    public AffineTransform popTransform() {
    	return transformStack.pop();
    }
    
    public GraphicsContext(Graphics2D g) {
        this.g = g;
        this.transformStack = new Stack<AffineTransform>();
        transformStack.push(new AffineTransform());
    }

    private void pointToAWTPixels(Point p, double x, double y) {
        Point2D.Double ptSrc = new Point2D.Double(x,y);
        getTransform().transform(ptSrc, p);
        //System.out.printf("  [%f,%f] => [%d,%d]\n", x, y, p.x, p.y);
    }

    private int widthToAWTPixelWidth(double width) {
    	double w = getTransform().getScaleX() * width;
    	int iw = (int)Math.round(w);
    	System.out.printf("  width %f => %f ~ %d\n", width, w, iw);
    	return iw;
    }

    private int heightToAWTPixelHeight(double height) {
    	double h = getTransform().getScaleY() * height;
    	int ih = (int)Math.round(h); 
    	System.out.printf("  height %f => %f ~ %d\n", height, h, ih);
        return ih;
    }

    public void setColor(RGBColor color) {
        g.setColor(color.getAWTColor());
    }

    public void setLineWidth(double width) {
        g.setStroke(new BasicStroke((float)width));
    }

    private class AWTRect {
        public int x, y, width, height;
        public AWTRect(double x0, double y0, double x1, double y1) {
            Point p0 = new Point();
            Point p1 = new Point();
            pointToAWTPixels(p0, x0, y0);
            pointToAWTPixels(p1, x1, y1);
            this.x = p0.x < p1.x ? p0.x : p1.x; 
            this.y = p0.y < p1.y ? p0.y : p1.y; 
            this.width = Math.abs(p0.x - p1.x);
            this.height = Math.abs(p0.y - p1.y);
        }
    }

    public void drawRect(double x0, double y0, double x1, double y1) {
        AWTRect r = new AWTRect(x0,y0,x1,y1);
        g.drawRect(r.x, r.y, r.width, r.height);
    }

    public void fillRect(double x0, double y0, double x1, double y1) {
        AWTRect r = new AWTRect(x0,y0,x1,y1);
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    public Path2D createAWTPath(DPoint vertices[]) {
        if (vertices == null) { return null; }
        Path2D.Float path = new Path2D.Float();
        Point p = new Point();
        Point firstPoint = null;
        boolean first = true;
        for (DPoint dp : vertices) {
            pointToAWTPixels(p, dp.getX(), dp.getY());
            if (first) {
                path.moveTo(p.x, p.y);
                firstPoint = new Point(p);
                first = false;
            } else {
                path.lineTo(p.x, p.y);
            }
        }
        path.lineTo(firstPoint.x, firstPoint.y);
        return path;
    }

    public void drawPolygon(DPoint vertices[]) {
        Path2D path = createAWTPath(vertices);
        g.draw(path);
    }

    public void fillPolygon(DPoint vertices[]) {
        Path2D path = createAWTPath(vertices);
        g.fill(path);
    }

    public void drawCircle(double x, double y, double pixelRadius) {
        Point p = new Point();
        pointToAWTPixels(p, x, y);
        int r = (int)Math.round(pixelRadius);
        g.drawArc(p.x-r, p.y-r, 2*r, 2*r, 0, 360);
    }

    public void fillCircle(double x, double y, double pixelRadius) {
        Point p = new Point();
        pointToAWTPixels(p, x, y);
        int r = (int)Math.round(pixelRadius);
        g.fillArc(p.x-r, p.y-r, 2*r, 2*r, 0, 360);
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        Point p1 = new Point();
        Point p2 = new Point();
        pointToAWTPixels(p1, x1, y1);
        pointToAWTPixels(p2, x2, y2);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }


}
