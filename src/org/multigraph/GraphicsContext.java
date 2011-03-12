package org.multigraph;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Stack;

public class GraphicsContext {

    Graphics2D mGraphics2D;
    Font mFont;
    FontMetrics mFontMetrics;
    Stack<AffineTransform> mTransformStack;

    public GraphicsContext(Graphics2D g) {
        this.mGraphics2D = g;
        this.mTransformStack = new Stack<AffineTransform>();
        mTransformStack.push(new AffineTransform());
        mFont = new Font("SansSerif", Font.PLAIN, 12);
        mGraphics2D.setFont(mFont);
        mFontMetrics = mGraphics2D.getFontMetrics();
    }

    public void printStack(String msg) {
        System.out.printf("[%s] top of stack: %s\n", msg, mTransformStack.peek().toString());
    }
    
    public AffineTransform getTransform() {
    	return mTransformStack.peek();
    }
    
    public void scale(double sx, double sy) {
    	mTransformStack.peek().scale(sx,sy);
    }
    
    public void rotate(double radians) {
    	mTransformStack.peek().rotate(radians);
    }
    
    public void translate(double tx, double ty) {
    	mTransformStack.peek().translate(tx, ty);
    }
    
    public void concatTransform(AffineTransform M) {
    	mTransformStack.peek().concatenate(M);
    }
    
    public void preConcatTransform(AffineTransform M) {
    	mTransformStack.peek().preConcatenate(M);
    }
    
    public void pushTransform(AffineTransform T) {
    	mTransformStack.push(T);
    }
    
    public void pushTransform() {
    	mTransformStack.push(new AffineTransform(mTransformStack.peek()));
    }
    
    public AffineTransform popTransform() {
    	return mTransformStack.pop();
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
        mGraphics2D.setColor(color.getAWTColor());
    }

    public void setLineWidth(double width) {
        mGraphics2D.setStroke(new BasicStroke((float)width));
    }
    
    public Box getStringBounds(String string) {
    	Rectangle2D bounds = mFontMetrics.getStringBounds(string, mGraphics2D);
        return new Box(bounds.getWidth(), bounds.getHeight());
    }

    public void drawString(String string,
    					   double baseX,     double baseY,
    					   double anchorX,   double anchorY,
    					   double positionX, double positionY,
    					   double angle) {
    	Rectangle2D bounds = mFontMetrics.getStringBounds(string, mGraphics2D);
    	double ax = (anchorX + 1) * bounds.getWidth()  / 2;
    	double ay = (anchorY + 1) * bounds.getHeight() / 2;
    	Point base = new Point();
    	pointToAWTPixels(base, baseX, baseY);
    	AffineTransform t = mGraphics2D.getTransform();
    	/*
    	mGraphics2D.translate(p.x, p.y);
    	mGraphics2D.rotate(angle);
    	mGraphics2D.drawString(string, -Math.round(ax+positionX), Math.round(ay-positionY));
    	*/

    	mGraphics2D.translate(base.x+positionX, base.y-positionY);
    	mGraphics2D.rotate(angle);
    	mGraphics2D.translate(-ax, ay);
    	mGraphics2D.drawString(string, 0, 0);
    	
    	
    	mGraphics2D.setTransform(t);
    }

    public void drawString(String string, double x, double y) {
    	drawString(string, x, y, -1,-1,  0,0,  0);
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
        mGraphics2D.drawRect(r.x, r.y, r.width, r.height);
    }

    public void fillRect(double x0, double y0, double x1, double y1) {
        AWTRect r = new AWTRect(x0,y0,x1,y1);
        mGraphics2D.fillRect(r.x, r.y, r.width, r.height);
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
        mGraphics2D.draw(path);
    }

    public void fillPolygon(DPoint vertices[]) {
        Path2D path = createAWTPath(vertices);
        mGraphics2D.fill(path);
    }

    public void drawCircle(double x, double y, double pixelRadius) {
        Point p = new Point();
        pointToAWTPixels(p, x, y);
        int r = (int)Math.round(pixelRadius);
        mGraphics2D.drawArc(p.x-r, p.y-r, 2*r, 2*r, 0, 360);
    }

    public void fillCircle(double x, double y, double pixelRadius) {
        Point p = new Point();
        pointToAWTPixels(p, x, y);
        int r = (int)Math.round(pixelRadius);
        mGraphics2D.fillArc(p.x-r, p.y-r, 2*r, 2*r, 0, 360);
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        Point p1 = new Point();
        Point p2 = new Point();
        pointToAWTPixels(p1, x1, y1);
        pointToAWTPixels(p2, x2, y2);
        mGraphics2D.drawLine(p1.x, p1.y, p2.x, p2.y);
    }


}
