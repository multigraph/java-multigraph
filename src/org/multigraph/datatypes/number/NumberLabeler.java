package org.multigraph.datatypes.number;

import org.multigraph.Axis;
import org.multigraph.AxisOrientation;
import org.multigraph.DPoint;
import org.multigraph.GraphicsContext;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.Formatter;
import org.multigraph.datatypes.Labeler;

/**
 * An implementation of org.multigraph.datatypes.Labeler for NumberValues.
 */
public class NumberLabeler extends Labeler {
    private double mCurrent;
    private double mEnd;
    private double mSpacingPixels;
    private double mLabelWidthPixels;
    private double mPixelsPerInchFactor;
    private int    mLastTextLabelWidth  = 25;
    private int    mLastTextLabelHeight = 25;
    private double mSpacing;
    private double mStart;

    public NumberLabeler(Axis axis, double spacing, Formatter formatter, double start,
                         DPoint position, double angle, DPoint anchor) {
        super(axis, formatter, position, angle, anchor);
        mSpacing             = spacing;
        mStart               = start;
        mCurrent             = 0;
        mEnd                 = 0;
        mLabelWidthPixels    = 0;
        mPixelsPerInchFactor = 60.0/ 72.0;
    }

    @Override
        public double getLabelDensity() {
        double absAngle          = Math.abs(mAngle) * Math.PI / 180;
        double labelPixels       = (mAxis.getOrientation() == AxisOrientation.HORIZONTAL) 
            ? mLastTextLabelHeight * Math.sin(absAngle) + mLastTextLabelWidth * Math.cos(absAngle)
            : mLastTextLabelHeight * Math.cos(absAngle) + mLastTextLabelWidth * Math.sin(absAngle);
        double spacingPixels     = mSpacing * Math.abs(mAxis.getAxisToDataRatio());
        double density           = labelPixels / spacingPixels;
        return density;
    }

    @Override
        public void renderLabel(GraphicsContext g, DataValue value) {
        double a = mAxis.dataValueToAxisValue(value);
        double baseX, baseY;
        if (mAxis.getOrientation() == AxisOrientation.VERTICAL) {
            //px = axis.getPerpOffset() + mPosition.getX();
            //py = a + mPosition.getY();
            baseX = mAxis.getPerpOffset();
            baseY = a;
        } else {
            //px = a + mPosition.getX();
            //py = axis.getPerpOffset() + mPosition.getY();
            baseX = a;
            baseY = mAxis.getPerpOffset();
        }
        g.drawString(mFormatter.format(value),
                     baseX, baseY,
                     mAnchor.getX(),   mAnchor.getY(),
                     mPosition.getX(), mPosition.getY(),
                     mAngle * Math.PI / 180.0);
    }

    private static double gmod(double m, double n) {
        int sign = 1;
        if (m < 0) {
            sign = -1;
            m = -m;
        }
        double f = Math.floor(m/n);
        return sign * ( m - f * n );
    }

    @Override
        public void prepare(DataValue dataMin, DataValue dataMax) {
        double min = dataMin.getRealValue();
        double max = dataMax.getRealValue();
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

    @Override
        public boolean hasNext() {
        return mCurrent <= mEnd;
    }

    @Override 
        public DataValue peekNext() {
        return new NumberValue(mCurrent);
    }
    
    @Override
        public DataValue next() {
        double val = mCurrent;
        mCurrent += mSpacing;    
        return new NumberValue(val);
    }

}
