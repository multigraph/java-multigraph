package org.multigraph;

public class DoubleLabeler extends Labeler {

    private double mCurrent;
    private double mEnd;
      
    private double mSpacingPixels;
    private double mLabelWidthPixels;
    private double mPixelsPerInchFactor;

    private int mLastTextLabelWidth  = 25;
    private int mLastTextLabelHeight = 25;

    private double mSpacing;
    private double mStart;

    public DoubleLabeler(double spacing, String formatString, double start,
                         DPoint position, double angle, DPoint anchor) {
        super(formatString, position, angle, anchor);
        mSpacing             = spacing;
        mStart               = start;
        mCurrent             = 0;
        mEnd                 = 0;
        mLabelWidthPixels    = 0;
        mPixelsPerInchFactor = 60.0/ 72.0;
    }

    //@override
    public double getLabelDensity(Axis axis) {
        double absAngle          = Math.abs(mAngle) * Math.PI / 180;
        double labelPixels       = (axis.getOrientation() == AxisOrientation.HORIZONTAL) 
            ? mLastTextLabelHeight * Math.sin(absAngle) + mLastTextLabelWidth * Math.cos(absAngle)
            : mLastTextLabelHeight * Math.cos(absAngle) + mLastTextLabelWidth * Math.sin(absAngle);
        double spacingPixels     = mSpacing * Math.abs(axis.getAxisToDataRatio());
        double density           = labelPixels / spacingPixels;
        return density;
    }

    //@override
    public void renderLabel(GraphicsContext g, Axis axis, DataValue value) {}

    private static double gmod(double m, double n) {
		int sign = 1;
		if (m < 0) {
	    	sign = -1;
	    	m = -m;
		}
        double f = Math.floor(m/n);
		return sign * ( m - f * n );
    }

    //@override
    public void prepare(DataValue dataMin, DataValue dataMax) {
        double min = dataMin.getDoubleValue();
        double max = dataMax.getDoubleValue();
        int direction = (max >= min) ? 1 : -1;
        mCurrent = firstTick(min, max, mSpacing, mStart);
		mEnd     = (direction > 0) ? max : min;
    }

    private static double firstTick(double min, double max, double spacing, double start) {
        int F = 1;
        double f;
        int direction = (max >= min) ? 1 : -1;
        double dataStart = (direction > 0) ? min : max;
		if (dataStart >= start) {
		    f = Math.floor( (dataStart - start) / spacing );
		    if (dataStart - start > spacing * f * F) {
                return spacing * ( 1 + f ) + start;
		    } else {
                return spacing * f + start;
		    }
		} else {
		    f = Math.floor( (start - dataStart) / spacing );
		    if (dataStart - start > -spacing * (f + 1) * F) {
                return -spacing * (f) + start;
		    } else {
                return -spacing * (f - 1) + start;
		    }
		}
    }

    //@override
    public boolean hasNext() {
        return mCurrent <= mEnd;
      }

    //@override 
    public DataValue peekNext() {
        return new DoubleDataValue(mCurrent);
    }
    
    //@override
    public DataValue next() {
        double val = mCurrent;
        mCurrent += mSpacing;    
        return new DoubleDataValue(val);
    }

}
