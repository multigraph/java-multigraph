package org.multigraph;

import java.util.ArrayList;

import org.multigraph.datatypes.number.NumberInterval;

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
    private DataInterval mBarwidth      = new NumberInterval(1.0);
    private double       mBaroffset     = 0.0;
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
                setOption(option, mBaroffset = Double.parseDouble(stringValue), stringValue, min, max);
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

    // This bar renderer uses a somewhat sophisticated technique when
    // drawing the outlines around the bars, in order to make sure
    // that it only draws one vertical line between two bars that
    // share an edge.  If a complete outline were drawn around each
    // bar separately, the common edge between adjacent bars would get
    // drawn twice, once for each bar, possibly in slightly different
    // locations on the screen due to roundoff error, thereby making
    // some of the outline lines appear thicker than others.
    // 
    // In order to avoid this roundoff artifact, this render only
    // draws the bars (the filled region of the bar, that is) in its
    // dataPoint() method, and keeps a record of the bar locations and
    // heights so that it can draw all of the bar outlines at once, in
    // its end() method.  The bar locations and heights are stored in
    // an array called _barGroups, which is an array of "bar group"
    // objects.  Each "bar group" corresponds to a sequence of
    // adjacent bars --- two bars are considered to be adjacent if the
    // right edge of the left bar is within _pixelEdgeTolerance pixels
    // of the left edge of the right bar.  A "bar group" is
    // represented by an array of points representing the pixel
    // coordinates of the upper left corners of all the bars in the
    // group, followed by the pixel coordinates of the upper right
    // corner of the right-most bar in the group.  (The last,
    // right-most, bar is the only one whose upper right corner is
    // included in the list).  So, for example, the following bar
    // group
    // 
    //        *--*
    //        |  |--*
    //     *--*  |  |
    //     |  |  |  |
    //     |  |  |  |
    //   ---------------
    //     1  2  3  4
    // 
    // would be represented by the array
    //
    //    [ [1,2], [2,3], [3,3], [4,3] ]
    //
    // When a data point is plotted, the dataPoint() method decides
    // whether the bar corresponding to that data point should be the
    // start of a new bar group, or appended to the previous bar
    // group.  Whenever a bar group is "closed", either because a new
    // one is being started, or because the last data point has been
    // plotted, it is appended to the _barGroups array.  The end()
    // method then iterates over the bar groups in the _barGroups
    // array, drawing each group's outline.

    private ArrayList<ArrayList<double[]>> mBarGroups;
    private ArrayList<double[]> mCurrentBarGroup;
    private double[] mPrevCorner;
    private boolean mDrawLines;
    private double mPixelEdgeTolerance = 1;
    private double mBarbaseAxisValue;

    //@override 
    public void begin(GraphicsContext g) {
    	if (this.isSetOption(Option.barbase)) {
    		mBarbaseAxisValue = mVerticalAxis.dataValueToAxisValue(mBarbase);
    	} else {
            // if barbase is not explicitly set, set it to the y-axis coordinate equivalent of the height of the
            // horizontal axis
			mBarbaseAxisValue = mHorizontalAxis.getPerpOffset();
            mBarbase          = mVerticalAxis.axisValueToDataValueDouble(mBarbaseAxisValue);
        }
        mBarGroups = new ArrayList<ArrayList<double[]>>();
        mCurrentBarGroup = new ArrayList<double[]>();
        mPrevCorner = null;

        double barPixelWidth = mBarwidth.getDoubleValue() * mHorizontalAxis.getAxisToDataRatio(); 
        if (barPixelWidth < 1) { barPixelWidth = 1; }
        if (barPixelWidth > mHidelines) {
            mDrawLines = true;
        } else {
            mDrawLines = false;
        }
    }

    //@override
    public void dataPoint(GraphicsContext g, DataValue[] datap) {
        if (false) { return; } // deal with missing data later!!!

        double p[] = transformPoint(datap);

        RGBColor barColor = (RGBColor)getOption(Option.fillcolor, datap[1]);
        g.setColor(barColor);

        double[] ll_corner = transformPoint(datap[0].getDoubleValue() - mBaroffset       * mBarwidth.getDoubleValue(), mBarbase                  );
        double[] ur_corner = transformPoint(datap[0].getDoubleValue() + (1 - mBaroffset) * mBarwidth.getDoubleValue(), datap[1].getDoubleValue() );
        g.fillRect(ll_corner[0], ll_corner[1], ur_corner[0], ur_corner[1]);

        double[] ul_corner = { ll_corner[0], ur_corner[1] };

        if (mDrawLines) {
            if (mPrevCorner == null) {
                mCurrentBarGroup.add( ul_corner );
            } else {
                if (Math.abs(ll_corner[0] - mPrevCorner[0]) <= mPixelEdgeTolerance) {
                    mCurrentBarGroup.add( ul_corner );
                } else {
                    mCurrentBarGroup.add( mPrevCorner );
                    mBarGroups.add( mCurrentBarGroup );
                    mCurrentBarGroup = new ArrayList<double[]>();
                    mCurrentBarGroup.add( ul_corner );
                }
            }
            mPrevCorner = ur_corner;
        }
    }

    //@override
    public void end(GraphicsContext g) {
        if (mLinewidth <= 0) { return; }

        if (mPrevCorner != null && mCurrentBarGroup != null) {
            mCurrentBarGroup.add( mPrevCorner );
            mBarGroups.add( mCurrentBarGroup );
        }        

        g.setColor(mLinecolor);
        g.setLineWidth(mLinewidth);
        for (ArrayList<double[]> barGroup : mBarGroups) {
            int n = barGroup.size();
            if (n < 2) { return; } // this should never happen

            // For the first point, draw 3 lines:
            //
            //       y |------
            //         |
            //         |
            //    base |------
            //         ^     ^
            //         x     x(next)
            //

            //   horizontal line @ y from x(next) to x
            g.drawLine(barGroup.get(1)[0],  barGroup.get(0)[1],  barGroup.get(0)[0], barGroup.get(0)[1]);
            //   vertical line @ x from y to base
            g.drawLine(barGroup.get(0)[0],  barGroup.get(0)[1],  barGroup.get(0)[0], mBarbaseAxisValue);
            //   horizontal line @ base from x to x(next)
            g.drawLine(barGroup.get(0)[0],  mBarbaseAxisValue,   barGroup.get(1)[0], mBarbaseAxisValue);

            for (int i=1; i<n-1; ++i) {
                // For intermediate points, draw 3 lines:
                //
                //       y |
                //         |
                //         |
                //         |------ y(next)
                //         |
                //         |
                //         |------ base
                //         ^     ^
                //         x     x(next)
                //
                //   vertical line @ x from min to max of (y, y(next), base)
                g.drawLine(barGroup.get(i)[0],  Math.min(Math.min(barGroup.get(i-1)[1], barGroup.get(i)[1]), mBarbaseAxisValue),
                           barGroup.get(i)[0],  Math.max(Math.max(barGroup.get(i-1)[1], barGroup.get(i)[1]), mBarbaseAxisValue));
                //   horizontal line @ y(next) from x to x(next)
                g.drawLine(barGroup.get(i)[0],  barGroup.get(i)[1],  barGroup.get(i+1)[0], barGroup.get(i)[1]);
                //   horizontal line @ base from x to x(next)
                g.drawLine(barGroup.get(i)[0],  mBarbaseAxisValue,            barGroup.get(i+1)[0], mBarbaseAxisValue);
            }
            // For last point, draw one line:
            //
            //       y |
            //         |
            //         |
            //    base |
            //         ^     ^
            //         x     x(next)
            //
            //   vertical line @ x from base to y
            g.drawLine(barGroup.get(n-1)[0], barGroup.get(n-1)[1],    barGroup.get(n-1)[0], mBarbaseAxisValue);
        }
    }


}
