package org.multigraph;

import java.util.ArrayList;

public class PointLineRenderer extends Renderer {

    public static enum Shape {
        circle,
            square,
            triangle,
            diamond,
            star,
            plus,
            x;
    }

    public static enum Option {
        linecolor,
            linewidth,
            pointsize,
            pointshape,
            pointcolor,
            pointopacity,
            pointoutlinewidth,
            pointoutlinecolor;
    }

	private RGBColor mLinecolor           = new RGBColor(0,0,0);
    private double   mLinewidth           = 1;
    private double   mPointsize           = 0;
    private Shape    mPointshape          = Shape.circle;
    private RGBColor mPointcolor          = new RGBColor(0,0,0);
    private double   mPointopacity        = 1.0;
    private double   mPointoutlinewidth   = 0;
    private RGBColor mPointoutlinecolor   = new RGBColor(0,0,0);

    private ArrayList<double[]> mPoints;
    private double[] mPrevPoint;

    public PointLineRenderer(Plot parent,
                             org.multigraph.jaxb.Renderer state) {
        super(parent, state);
        for (org.multigraph.jaxb.RendererOption jaxbOption : state.getOption()) {
            String name        = jaxbOption.getName();
            String stringValue = jaxbOption.getValue();
            Double min         = jaxbOption.isSetMin() ? jaxbOption.getMin() : null;
            Double max         = jaxbOption.isSetMax() ? jaxbOption.getMax() : null;
            Option option;
            try {
                option = Option.valueOf(name);
            } catch (Exception e) {
                System.err.printf("ignoring unrecognized option for PointLineRenderer: %s\n", name);
                continue;
            }
            switch (option) {
            case linecolor:
                setOption(option, mLinecolor = new RGBColor(stringValue), stringValue, min, max);
                break;
            case pointcolor:
                setOption(option, mPointcolor = new RGBColor(stringValue), stringValue, min, max);
                break;
            case pointoutlinecolor:
                setOption(option, mPointoutlinecolor = new RGBColor(stringValue), stringValue, min, max);
                break;
            case linewidth:
                setOption(option, mLinewidth = Double.parseDouble(stringValue), stringValue, min, max);
                break;
            case pointsize:
                setOption(option, mPointsize = Double.parseDouble(stringValue), stringValue, min, max);
                break;
            case pointopacity:
                setOption(option, mPointopacity = Double.parseDouble(stringValue), stringValue, min, max);
                break;
            case pointoutlinewidth:
                setOption(option, mPointoutlinewidth = Double.parseDouble(stringValue), stringValue, min, max);
                break;
            case pointshape:
                Shape shape;
                try {
                    shape = Shape.valueOf(stringValue);
                } catch (Exception e) {
                    System.err.printf("unknown pointshape '%s' for PointLineRenderer\n", stringValue);
                    break;
                }
                setOption(option, mPointshape = shape, stringValue, min, max);
                break;
            }
        }
        mPoints    = new ArrayList<double[]>();
        mPrevPoint = null;
    }

    //@override 
    public void begin(GraphicsContext g) {
        mPrevPoint = null;
        mPoints    = new ArrayList<double[]>();
    }

    //@override
    public void dataPoint(GraphicsContext g, DataValue[] datap) {
        if (false) { // deal with missing data here later!!!
            mPrevPoint = null;
        } else {
            double p[] = transformPoint(datap);
            if (mLinewidth > 0 && mPrevPoint != null) {
                g.setLineWidth(mLinewidth);
                g.setColor(mLinecolor);
                g.drawLine(mPrevPoint[0], mPrevPoint[1], p[0], p[1]);
            }
            mPrevPoint = p;
            mPoints.add(mPrevPoint);
        }
    }

    //@override
    public void end(GraphicsContext g) {
        if (mPointsize > 0) { // don't draw points if pointsize<=0
            for (double [] p : mPoints) {
                drawPoint(g, p[0], p[1]);
            }
        }
    }

    private void drawPoint(GraphicsContext g, double x, double y) {
		if (mPointsize <= 0) {
			return;
		}

		//
		// The plus and x shape do not involve fills --- only lines, so handle them separately:
		//

		if (mPointshape == Shape.plus || mPointshape == Shape.x) {
			g.setLineWidth(mPointoutlinewidth > 0 ? mPointoutlinewidth : 1);
			if (isSetOption(Option.pointoutlinecolor)) {
				g.setColor(mPointoutlinecolor);
			} else if (isSetOption(Option.pointcolor)) {
				g.setColor(mPointcolor);
			} else {
				g.setColor(RGBColor.BLACK);
			}
			switch (mPointshape) {
			case plus:
				g.drawLine(x, y - mPointsize, x, y + mPointsize);
				g.drawLine(x - mPointsize, y, x + mPointsize, y);
				break;
			case x:
				double p = 0.70710 * mPointsize;
				g.drawLine(x - p, y - p, x + p, y + p);
				g.drawLine(x - p, y + p, x + p, y - p);
				break;
			}
			return;
		}

		//
		// These shapes involve (possibly) both fill and outline, and can be
        // done with drawPolygon():
		//
		DPoint vertices[] = null;
		switch (mPointshape) {
		case square: {
			vertices = new DPoint[] {
                new DPoint(x - mPointsize, y - mPointsize),
                new DPoint(x - mPointsize, y + mPointsize),
                new DPoint(x + mPointsize, y + mPointsize),
                new DPoint(x + mPointsize, y - mPointsize) };
			break;
		}
		case triangle: {
			double p = 1.5 * mPointsize;
			double a = 0.866025 * p;
			double b = 0.5 * p;
			vertices = new DPoint[] { new DPoint(x, y + p),
                                      new DPoint(x + a, y - b), new DPoint(x - a, y - b) };
			break;
		}
		case diamond: {
			double p = 1.5 * mPointsize;
			vertices = new DPoint[] { new DPoint(x - mPointsize, y),
                                      new DPoint(x, y + p), new DPoint(x + mPointsize, y),
                                      new DPoint(x, y - p) };
			break;
		}
		case star: {
			double p = 1.5 * mPointsize;
			vertices = new DPoint[] {
                new DPoint(x - p * 0.0000, y + p * 1.0000),
                new DPoint(x + p * 0.3536, y + p * 0.3536),
                new DPoint(x + p * 0.9511, y + p * 0.3090),
                new DPoint(x + p * 0.4455, y - p * 0.2270),
                new DPoint(x + p * 0.5878, y - p * 0.8090),
                new DPoint(x - p * 0.0782, y - p * 0.4938),
                new DPoint(x - p * 0.5878, y - p * 0.8090),
                new DPoint(x - p * 0.4938, y - p * 0.0782),
                new DPoint(x - p * 0.9511, y + p * 0.3090),
                new DPoint(x - p * 0.2270, y + p * 0.4455) };
			break;
		}
		}
        if (vertices != null) {
            g.setColor(mPointcolor);
            g.fillPolygon(vertices);
            if (mPointoutlinewidth > 0) {
                g.setLineWidth(mPointoutlinewidth);
                g.setColor(mPointoutlinecolor);
                g.drawPolygon(vertices);
            }
            return;
        }

        //
        // And lastly, if we get this far, assume the circle shape, which is the default
        //
		g.setColor(mPointcolor);
		g.fillCircle(x, y, mPointsize);
		if (mPointoutlinewidth > 0) {
			g.setLineWidth(mPointoutlinewidth);
			g.setColor(mPointoutlinecolor);
			g.drawCircle(x, y, mPointsize);
		}


	}

    /*    
          override public function renderLegendIcon(sprite:MultigraphUIComponent, legendLabel:String, opacity:Number):void {
          var g:Graphics = sprite.graphics;
          if (_linewidth > 0) {
          g.lineStyle(_linewidth, _linecolor, 1, false, "normal", flash.display.CapsStyle.NONE, flash.display.JointStyle.ROUND);
          g.moveTo(1, sprite.height / 2);
          g.lineTo(sprite.width, sprite.height / 2);
          }
          if (_pointsize > 0) {
          drawPoint(g, sprite.width / 2, sprite.height / 2);
          }
          }
    */

}
