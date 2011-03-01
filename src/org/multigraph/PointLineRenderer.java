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
    /*
    public Option parseOption(String name) throws IllegalArgumentException {
        return (Option)super.parseOption(Option.values(), name);
    }
    */

	private RGBColor mLinecolor           = new RGBColor(0,0,0);
    private double   mLinewidth           = 1;
    private double   mPointsize           = 0;
    private Shape    mPointshape          = Shape.circle;
    private RGBColor mPointcolor          = new RGBColor(0,0,0);
    private double   mPointopacity        = 1.0;
    private double   mPointoutlinewidth   = 0;
    private RGBColor mPointoutlinecolor   = new RGBColor(0,0,0);

    ArrayList<double[]> mPoints;
    double[] mPrevPoint;

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
