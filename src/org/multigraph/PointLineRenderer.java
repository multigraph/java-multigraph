package org.multigraph;

import java.util.ArrayList;

public class PointLineRenderer extends Renderer {

    public static enum Shape { CIRCLE, SQUARE, TRIANGLE, DIAMOND, STAR, PLUS, X };

	private String mOptionLinecolor;
    private String mOptionLinewidth;
    private String mOptionPointsize;
    private String mOptionPointshape;
    private String mOptionPointcolor;
    private String mOptionPointopacity;
    private String mOptionPointoutlinewidth;
    private String mOptionPointoutlinecolor;

	private RGBColor mLinecolor;
    private double mLinewidth;
    private double mPointsize;
    private Shape mPointshape;
    private RGBColor mPointcolor;
    private double mPointopacity;
    private double mPointoutlinewidth;
    private RGBColor mPointoutlinecolor;

    private static Shape parseShape(String s) {
        s = s.toLowerCase();
        if (s.equals("circle"))   { return Shape.CIRCLE;   }
        if (s.equals("square"))   { return Shape.SQUARE;   }
        if (s.equals("triangle")) { return Shape.TRIANGLE; }
        if (s.equals("diamond"))  { return Shape.DIAMOND;  }
        if (s.equals("star"))     { return Shape.STAR;     }
        if (s.equals("plus"))     { return Shape.PLUS;     }
        if (s.equals("x"))        { return Shape.X;        }
        return Shape.CIRCLE;
    }

    ArrayList<double[]> mPoints;
    double[] mPrevPoint;

    public PointLineRenderer(Plot parent,
                             org.multigraph.mugl.graph.plot.Renderer state) {
        super(parent, state);
        for (org.multigraph.mugl.graph.plot.renderer.Option option : state.getOptions()) {
            String optionName = option.getName();
            String optionValue = option.getValue();
            if (optionName.equals("linecolor")) {
                mOptionLinecolor = optionValue;
                mLinecolor = new RGBColor(optionValue);
            } else if (optionName.equals("linewidth")) {
                mOptionLinewidth = optionValue;
                mLinewidth = Double.parseDouble(optionValue);
            } else if (optionName.equals("pointsize")) {
                mOptionPointsize = optionValue;
                mPointsize = Double.parseDouble(optionValue);
            } else if (optionName.equals("pointshape")) {
                mOptionPointshape = optionValue;
                mPointshape = parseShape(optionValue);
            } else if (optionName.equals("pointcolor")) {
                mOptionPointcolor = optionValue;
                mPointcolor = new RGBColor(optionValue);
            } else if (optionName.equals("pointopacity")) {
                mOptionPointopacity = optionValue;
                mPointopacity = Double.parseDouble(optionValue);
            } else if (optionName.equals("pointoutlinewidth")) {
                mOptionPointoutlinewidth = optionValue;
                mPointoutlinewidth = Double.parseDouble(optionValue);
            } else if (optionName.equals("pointoutlinecolor")) {
                mOptionPointoutlinecolor = optionValue;
                mPointoutlinecolor = new RGBColor(optionValue);
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
        /*
      if (_pointoutlinewidth > 0) {
        g.lineStyle(_pointoutlinewidth, _pointoutlinecolor, 1);
      } else {
        g.lineStyle(0,0,0);
      }

      //
      // handle these shapes separately, since they involve no fill:
      //
      if (_pointshape == PLUS_SHAPE || _pointshape == X_SHAPE) {
        // With these shapes, if a pointcolor was specified and a
        // pointoutlinecolor was not, use pointcolor as the color for
        // the lines.
        if (_pointoutlinecolor_str == null && _pointcolor_str != null) {
          var w:int = _pointoutlinewidth > 0 ? _pointoutlinewidth : 1;
          g.lineStyle(w, _pointcolor, 1);
        }
        if (_pointshape == PLUS_SHAPE) {
          g.moveTo(x, y-_pointsize);
          g.lineTo(x, y+_pointsize);
          g.moveTo(x-_pointsize, y);
          g.lineTo(x+_pointsize, y);
        } else if (_pointshape == X_SHAPE) {
          var p:Number = 0.70710 * _pointsize;
          g.moveTo(x-p, y-p);
          g.lineTo(x+p, y+p);
          g.moveTo(x-p, y+p);
          g.lineTo(x+p, y-p);
        }
        return;
      }

      //
      // Other shapes involve fill, so start with beginFill() and end with endFill():
      //
      g.beginFill(_pointcolor, _pointopacity);
      if (_pointshape == SQUARE_SHAPE) {
        g.drawRect(x - _pointsize, y - _pointsize, 2*_pointsize, 2*_pointsize);
      } else if (_pointshape == TRIANGLE_SHAPE) {
        var p:Number = 1.5*_pointsize;
        var a:Number = 0.866025*p;
        var b:Number = 0.5*p;
        g.moveTo(x, y+p);
        g.lineTo(x+a, y-b);
        g.lineTo(x-a, y-b);
      } else if (_pointshape == DIAMOND_SHAPE) {
        var p:Number = 1.5*_pointsize;
        g.moveTo(x-_pointsize, y);
        g.lineTo(x, y+p);
        g.lineTo(x+_pointsize, y);
        g.lineTo(x, y-p);
      } else if (_pointshape == STAR_SHAPE) {
        var p:Number = 1.5*_pointsize;
        g.moveTo(x-p*0.0000, y+p*1.0000);
        g.lineTo(x+p*0.3536, y+p*0.3536);
        g.lineTo(x+p*0.9511, y+p*0.3090);
        g.lineTo(x+p*0.4455, y-p*0.2270);
        g.lineTo(x+p*0.5878, y-p*0.8090);
        g.lineTo(x-p*0.0782, y-p*0.4938);
        g.lineTo(x-p*0.5878, y-p*0.8090);
        g.lineTo(x-p*0.4938, y-p*0.0782);
        g.lineTo(x-p*0.9511, y+p*0.3090);
        g.lineTo(x-p*0.2270, y+p*0.4455);
      } else if (_pointshape == PLUS_SHAPE) {
        var p:Number = 1.5*_pointsize;
        g.moveTo(x-p*0.0000, y+p*1.0000);
        g.lineTo(x+p*0.3536, y+p*0.3536);
        g.lineTo(x+p*0.9511, y+p*0.3090);
        g.lineTo(x+p*0.4455, y-p*0.2270);
        g.lineTo(x+p*0.5878, y-p*0.8090);
        g.lineTo(x-p*0.0782, y-p*0.4938);
        g.lineTo(x-p*0.5878, y-p*0.8090);
        g.lineTo(x-p*0.4938, y-p*0.0782);
        g.lineTo(x-p*0.9511, y+p*0.3090);
        g.lineTo(x-p*0.2270, y+p*0.4455);
      } else { // CIRCLE_SHAPE:
        g.drawCircle(x, y, _pointsize);
      }
      g.endFill();
        */
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
