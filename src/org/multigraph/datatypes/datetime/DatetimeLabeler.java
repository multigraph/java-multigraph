package org.multigraph.datatypes.datetime;

import org.multigraph.*;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.Formatter;
import org.multigraph.datatypes.Labeler;

/**
 * An implementation of org.multigraph.datatypes.Labeler for DatetimeValues.
 */
public class DatetimeLabeler extends Labeler
{
    private DatetimeMeasure mSpacing;
    private DatetimeValue    mStart;
    private DatetimeValueCalendar        mCurrent;
    private DatetimeValue    mEnd;
    private double           mLabelWidthPixels;
    private double           mPixelsPerInchFactor;
    private double           mLastTextLabelWidth;
    private double           mLastTextLabelHeight;
    private double           mMSSpacing;
    
    public DatetimeLabeler(Axis axis,
                           DatetimeMeasure spacing,
                           Formatter formatter,
                           DatetimeValue start,
                           DPoint position,
                           double angle,
                           DPoint anchor) {
        super(axis, formatter, position, angle, anchor);
        mSpacing             = spacing;
        mStart               = start;
        mCurrent             = null;
        mEnd                 = null;
        mLabelWidthPixels    = 0;
        mPixelsPerInchFactor = 0.8 * (60.0 / 72.0);
        int len = ((DatetimeFormatter)formatter).getMaxLength();
        mLastTextLabelWidth  = len * 9;
        mLastTextLabelHeight = 14;
        //mLastTextLabelWidth  = 60;
        //mLastTextLabelHeight = 14;
        mMSSpacing           = spacing.getRealValue();
    }


    @Override
        public double getLabelDensity() {
        /*
         *  spacingPixels = number of pixels between tick marks
         *      
         *      -------------|-------------|-------------|-------------|-------------|-------------
         *                   Jan 1 2011    Jan 2 2011    Jan 3 2011    Jan 4 2011    Jan 5 2011    
         *  
         *  labelPixels = width of a typical label, in pixels
         *  
         *  density = ratio of labelPixels / spacingPixels
         *  
         *  So density &lt; 1 means the labels don't overlap
         *     density = 1 means they just barely touch
         *     density &gt; 1 means they overlap
         *  
         *  In practice this is an estimate, since not all labels have the same length
         *  (for example "Jan 9 2001" and "Jan 10 2011").
         */
        double absAngle          = Math.abs(mAngle) * 3.14156 / 180;
        double labelPixels       = (mAxis.getOrientation() == AxisOrientation.HORIZONTAL)
            ? mLastTextLabelHeight * Math.sin(absAngle) + mLastTextLabelWidth * Math.cos(absAngle)
            : mLastTextLabelHeight * Math.cos(absAngle) + mLastTextLabelWidth * Math.sin(absAngle);
        double spacingPixels     = mMSSpacing * Math.abs(mAxis.getAxisToDataRatio());
        double density           = labelPixels / spacingPixels;
        return density;
    }

    @Override
        public boolean hasNext() {
        return (mCurrent.compareTo(mEnd) <= 0);
    }

    @Override
        public DataValue next() {
        DataValue val = mCurrent.toDatetimeValue();
        mCurrent.add(mSpacing.getUnit(), (int)(mSpacing.getMeasure()));
        return val;
    }           

    @Override 
        public DataValue peekNext() {
        return mCurrent.toDatetimeValue();
    }

    @Override
        public void prepare(DataValue min, DataValue max) {
        int direction = max.compareTo(min);
        DatetimeValueCalendar dataStartTurboDate = (direction > 0) ? new DatetimeValueCalendar((DatetimeValue)min) : new DatetimeValueCalendar((DatetimeValue)max);
        mCurrent = dataStartTurboDate.firstSpacingLocationAtOrAfter(new DatetimeValueCalendar(mStart), mSpacing.getMeasure(), mSpacing.getUnit());
        mEnd = (DatetimeValue)((direction > 0) ? max : min);
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
