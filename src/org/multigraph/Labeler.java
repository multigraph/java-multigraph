package org.multigraph;

// Labeler:
// 
// A Labeler object renders text labels for values along an axis in a
// given format and at a given spacing from each other.  This is an
// abstract superclass; specific subclasses implement labelers for
// various specific data types such as number and datetime.
// 
//     labelDensity(axis:Axis)
//         Returns a number between 0 and 1 representing (an estimate
//         of) how "dense" the labels that this labeler would put on
//         the given axis would be, with 0 representing the least
//         density, i.e. no labels at all, and 1 meaning that the
//         labels completely run together, touching or overlapping
//         each other.  (This density refers to the text labels
//         themselves, not the tick marks.)
// 
//     prepare(dataMin:Number, dataMax:Number)
//         Prepares the labeler for stepping through a sequence of
//         labels in a given range of data values.
// 
//     hasNext()
//         Indicates whether there are any more values to be stepped
//         through in the range specified in the last call to
//         prepare(...).
// 
//     next()
//         Returns the next value in the sequence of values being
//         stepped through in the range specified in the last call to
//         prepare(...).
// 
//     renderLabel(sprite:Sprite, axis:Axis, value:Number)
//         Draws a text label at the given data value location, on the
//         given axis, in the given sprite.

public class Labeler {
	
    //protected DataInterval mSpacing;
    //protected DataValue    mStart;

    protected String       mFormatString;
    protected DPoint       mPosition;
    protected double       mAngle;
    protected DPoint       mAnchor;

    public Labeler(String formatString, DPoint position, double angle, DPoint anchor) {
        mFormatString = formatString;
        mPosition     = position;
        mAngle        = angle;
        mAnchor       = anchor;
    }


    public double       getLabelDensity(Axis axis) { return 0; }
    public void         renderLabel(GraphicsContext g, Axis axis, DataValue value) {}
	public void         prepare(DataValue min, DataValue max) {}
	public boolean      hasNext() { return false; }
	public DataValue    next() { return null;}
    public DataValue    peekNext() { return null; }
    
    
    public static class Number extends Labeler {

        private double mCurrent;
        private double mEnd;
          
        private double mSpacingPixels;
        private double mLabelWidthPixels;
        private double mPixelsPerInchFactor;

        private int mLastTextLabelWidth  = 25;
        private int mLastTextLabelHeight = 25;

        private double mSpacing;
        private double mStart;

        public Number(double spacing, String formatString, double start,
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
            return new DataValue.Number(mCurrent);
        }
        
        //@override
        public DataValue next() {
            double val = mCurrent;
            mCurrent += mSpacing;    
            return new DataValue.Number(val);
        }

    }
    
    
    
}
