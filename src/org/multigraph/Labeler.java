package org.multigraph;


import org.multigraph.datatypes.number.NumberLabeler;
import org.multigraph.datatypes.datetime.DatetimeLabeler;

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
    protected Formatter    mFormatter;

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
    
    public static Labeler create(DataType type,
    					  DataInterval spacing,
    					  String format,
    					  DataValue start,
    					  DPoint position,
    					  double angle,
    					  DPoint anchor) throws DataTypeException {
    	switch (type) {
    		case NUMBER:
    			return new NumberLabeler(spacing.getDoubleValue(), format, start.getDoubleValue(),
    	                position, angle, anchor);
            default:
                throw new DataTypeException(String.format("Labeler.create: unknown DataType ('%s')", type.toString()));
    	}
    }
    	

    
}
