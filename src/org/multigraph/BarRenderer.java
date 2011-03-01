package org.multigraph;

import java.util.ArrayList;

public class BarRenderer extends Renderer {

    public static enum Option {
        fillcolor,
        linecolor,
        barwidth,
        baroffset,
        fillopacity,
        linewidth,
        barbase,
        hidelines;
    }

    private RGBColor     mFillcolor     = RGBColor.BLACK;
    private RGBColor     mLinecolor     = RGBColor.BLACK;
    private DataInterval mBarwidth      = new DataInterval.Number(1.0);
    private DataInterval mBaroffset     = new DataInterval.Number(0.0);
    private double       mFillopacity   = 1.0;
    private double       mLinewidth     = 1.0;
    private Double       mBarbase       = null;
    private int          mHidelines     = 2;

    public BarRenderer(Plot parent,
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
                System.err.printf("ignoring unrecognized option for BarRenderer: %s\n", name);
                continue;
            }
            switch (option) {
            case fillcolor:
                setOption(option, mFillcolor = new RGBColor(stringValue), stringValue, min, max);
                break;
            case linecolor:
                setOption(option, mLinecolor = new RGBColor(stringValue), stringValue, min, max);
                break;
            case barwidth:
                setOption(option, mBarwidth = DataInterval.create(parent.getHorizontalAxis().getType(), stringValue), stringValue, min, max);
                break;
            case baroffset:
                setOption(option, mBaroffset = DataInterval.create(parent.getHorizontalAxis().getType(), stringValue), stringValue, min, max);
                break;
            case fillopacity:
                setOption(option, mFillopacity = Double.parseDouble(stringValue), stringValue, min, max);
                break;
            case linewidth:
                setOption(option, mLinewidth = Double.parseDouble(stringValue), stringValue, min, max);
                break;
            case barbase:
                setOption(option, mBarbase = Double.parseDouble(stringValue), stringValue, min, max);
                break;
            case hidelines:
                setOption(option, mHidelines = Integer.parseInt(stringValue), stringValue, min, max);
                break;

            }
        }
    }

    //@override 
    public void begin(GraphicsContext g) {
    }

    //@override
    public void dataPoint(GraphicsContext g, DataValue[] datap) {
    }

    //@override
    public void end(GraphicsContext g) {
    }


}
