package org.multigraph.datatypes.datetime;

import org.multigraph.*;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.Formatter;
import org.multigraph.datatypes.Labeler;

public class DatetimeLabeler extends Labeler
{
    private DatetimeInterval mSpacing;
	private DatetimeValue    mStart;
    private TurboDate        mCurrentTurboDate;
    private DatetimeValue    mEnd;
    private double           mLabelWidthPixels;
    private double           mPixelsPerInchFactor;
    private double           mLastTextLabelWidth;
    private double           mLastTextLabelHeight;
    private double           mMSSpacing;
    private int              mStep;
    private TurboDate        mStartTurboDate;
    private TurboDate        mFirstTickTurboDate;
    
    public DatetimeLabeler(Axis axis,
    					   DatetimeInterval spacing,
                           Formatter formatter,
                           DatetimeValue start,
                           DPoint position,
                           double angle,
                           DPoint anchor) {
        super(axis, formatter, position, angle, anchor);
        mSpacing             = spacing;
        mStart               = start;
        mCurrentTurboDate    = null;
        mEnd                 = null;
        mLabelWidthPixels    = 0;
        mPixelsPerInchFactor = 0.8 * (60.0 / 72.0);
        int len = ((DatetimeFormatter)formatter).getMaxLength();
        mLastTextLabelWidth  = len * 9;
        mLastTextLabelHeight = 14;
        //mLastTextLabelWidth  = 60;
        //mLastTextLabelHeight = 14;
        mStep                = 0;
        mStartTurboDate      = new TurboDate(mStart.getDoubleValue());
        mMSSpacing           = spacing.getDoubleValue();
    }


    /**
     * spacingPixels = number of pixels between tick marks
     * 
     * -------------|-------------|-------------|-------------|-------------|-------------
     *              Jan 1 2011    Jan 2 2011    Jan 3 2011    Jan 4 2011    Jan 5 2011    
     * 
     * labelPixels = width of a typical label, in pixels
     * 
     * density = ratio of labelPixels / spacingPixels
     * 
     * So density &lt; 1 means the labels don't overlap
     *    density = 1 means they just barely touch
     *    density &gt; 1 means they overlap
     * 
     * In practice this is an estimate, since not all labels have the same length
     * (for example "Jan 9 2001" and "Jan 10 2011").
     */
    @Override
    public double getLabelDensity() {
        double absAngle          = Math.abs(mAngle) * 3.14156 / 180;
        double labelPixels       = (mAxis.getOrientation() == AxisOrientation.HORIZONTAL)
        ? mLastTextLabelHeight * Math.sin(absAngle) + mLastTextLabelWidth * Math.cos(absAngle)
        : mLastTextLabelHeight * Math.cos(absAngle) + mLastTextLabelWidth * Math.sin(absAngle);
        double spacingPixels     = mMSSpacing * Math.abs(mAxis.getAxisToDataRatio());
        double density           = labelPixels / spacingPixels;
        return density;
	}

	public void dump() {
        double absAngle          = Math.abs(mAngle) * 3.14156 / 180;
        double labelPixels       = (mAxis.getOrientation() == AxisOrientation.HORIZONTAL)
            ? mLastTextLabelHeight * Math.sin(absAngle) + mLastTextLabelWidth * Math.cos(absAngle)
            : mLastTextLabelHeight * Math.cos(absAngle) + mLastTextLabelWidth * Math.sin(absAngle);
        double spacingPixels     = mMSSpacing * Math.abs(mAxis.getAxisToDataRatio());
        double density           = labelPixels / spacingPixels;

	    System.out.printf("DatetimeLabeler with spacing %s reporting density %f = %f / %f\n", mSpacing.toString(), density,
                          labelPixels, spacingPixels);
	}

	@Override
	public boolean hasNext() {
        return mCurrentTurboDate.getTimeInMillis() <= mEnd.getDoubleValue();
    }

	@Override
    public DataValue next() {
        DataValue val = new DatetimeValue(mCurrentTurboDate.getTimeInMillis());
        ++mStep;
        mCurrentTurboDate = mFirstTickTurboDate.clone();
        mCurrentTurboDate.add(mSpacing.getUnit(), mStep * mSpacing.getMeasure());
        return val;
    }		

    //@override 
    public DataValue peekNext() {
        return new DatetimeValue(mCurrentTurboDate.getTimeInMillis());
    }

	@Override
	public void prepare(DataValue min, DataValue max) {
        int direction = max.compareTo(min);
        TurboDate dataStartTurboDate = (direction > 0) ? new TurboDate(min.getDoubleValue()) : new TurboDate(max.getDoubleValue());
        mFirstTickTurboDate = dataStartTurboDate.firstTickAtOrAfter(mStartTurboDate, mSpacing.getMeasure(), mSpacing.getUnit());
        mCurrentTurboDate = mFirstTickTurboDate.clone();
        mEnd = (DatetimeValue)((direction > 0) ? max : min);
        mStep = 0;
	}

	@Override
	public void renderLabel(GraphicsContext g, DataValue value) {
        double a = mAxis.dataValueToAxisValue(value);
        double baseX, baseY;
        if(mAxis.getOrientation() == AxisOrientation.VERTICAL) {
            baseX = mAxis.getPerpOffset() + mPosition.getX();
            baseY = a + mPosition.getY();
        } else {
            baseX = a + mPosition.getX();
            baseY = mAxis.getPerpOffset() + mPosition.getY();
        }
        String string = mFormatter.format(value);
        g.drawString(string,
                     baseX, baseY,
                     mAnchor.getX(),   mAnchor.getY(),
                     mPosition.getX(), mPosition.getY(),
                     mAngle * Math.PI / 180.0);
        Box bounds = g.getStringBounds(string);
        mLastTextLabelWidth  = bounds.getWidth();
        mLastTextLabelHeight = bounds.getHeight();
	}

}
