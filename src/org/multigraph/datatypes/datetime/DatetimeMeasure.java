package org.multigraph.datatypes.datetime;

import org.multigraph.DataTypeException;
import org.multigraph.datatypes.DataMeasure;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * A DatetimeMeasure stores a length of time, represented in terms of a "unit" and a "measure".
 * The unit is a CalendarField value such as YEAR, MONTH, DAY, HOUR, etc, and the measure is
 * a floating point value that represents the number of units.
 */
public class DatetimeMeasure extends DataMeasure {

    /**
     * The unit for this measure.
     */
    private CalendarField mUnit;

    /**
     * Return the unit for this DatetimeMeasure.
     */
    public CalendarField getUnit() { return mUnit; }
    
    /**
     * The amount of this measure.
     */
    private double mMeasure;

    /**
     * Return the measure for this DatetimeMeasure.
     */
    public double getMeasure() { return mMeasure; }

    /**
     * The number of milliseconds in this measure.  This is always equal to mMeasure * mUnit.millisecs.
     */
    private long mValue;

    /**
     * The original string representing the measure that was used to create this DatetimeMeasure, or
     * a generated string representation of the measure if the DatetimeMeasure was not created from
     * a string.  This is here just so that we can use it in the toString() method, so that when
     * a DatetimeMeasure is converted to a string, it will use the same representation of its measure
     * that was used to create it in the first place.
     */
    private String mMeasureString;

    /**
     * A regular expression pattern corresponding to a number followed by a unit, possibly with space(s) between the two,
     * and possibly with leading and/or trailing space(s).
     */
    private static Pattern mMeasureAndUnitPattern = Pattern.compile("\\s*([0-9eE\\.\\+\\-]+)\\s*([YMDHms]+)\\s*");

    /**
     * A regular expression pattern corresponding to a number only, possibly with leading and/or trailing space(s).
     */
    private static Pattern mMeasureOnlyPattern    = Pattern.compile("\\s*([0-9eE\\.\\+\\-]+)\\s*");

    /**
     * Construct a DatetimeMeasure instance using a given measure and unit.
     */
    public DatetimeMeasure(double measure, CalendarField unit) throws DataTypeException {
        mMeasure       = measure;
        mUnit          = unit;
        mValue         = (long)(measure * unit.millisecs);
        mMeasureString = Double.toString(mMeasure);
    }

    /**
     * Construct a DatetimeMeasure instance using a millisecond measure.  The unit
     * will be automatically set to CalendarField.MILLISECOND.
     */
    public DatetimeMeasure(double measure) throws DataTypeException {
        this(measure, CalendarField.MILLISECOND);
    }

    /**
     * Construct a DatetimeMeasure instance by parsing a string.
     */
    public DatetimeMeasure(String string) throws DataTypeException {
        Matcher m = mMeasureAndUnitPattern.matcher(string);
        if (m.matches()) {
            try {
                mMeasureString = m.group(1);
                mMeasure = Double.parseDouble(mMeasureString);
            } catch (IllegalArgumentException e) {
                throw new DataTypeException(String.format("DatetimeMeasure constructor: cannot convert measure value '%s' to double "
                                                          + " when converting string '%s' to DatetimeMeasure", m.group(1), string));
            }
            try {
                mUnit = CalendarField.parse(m.group(2));
            } catch (IllegalArgumentException e) {
                throw new DataTypeException(String.format("DatetimeMeasure constructor: unknown unit specifier '%s' when "
                                                          + "converting string '%s' to DatetimeMeasure", m.group(2), string));
            }
        } else {

            m = mMeasureOnlyPattern.matcher(string);
            if (m.matches()) {
                try {
                    mMeasureString = m.group(1);
                    mMeasure = Double.parseDouble(mMeasureString);
                } catch (IllegalArgumentException e) {
                    throw new DataTypeException(String.format("DatetimeMeasure constructor: cannot convert measure value '%s' to double "
                                                              + " when converting string '%s' to DatetimeMeasure", m.group(1), string));
                }
                mUnit = CalendarField.SECOND;
            } else {
                throw new DataTypeException(String.format("DatetimeMeasure constructor: cannot parse string '%s' as datetime measure", string));
            }
        }
        switch (mUnit) {
        case YEAR:
            mValue = Math.round(mMeasure * CalendarField.YEAR.millisecs);
            break;
        case MONTH:
            mValue = Math.round(mMeasure * CalendarField.MONTH.millisecs);
            break;
        case WEEK:
            mValue = Math.round(mMeasure * CalendarField.WEEK.millisecs);
            break;
        case DAY:
            mValue = Math.round(mMeasure * CalendarField.DAY.millisecs);
            break;
        case HOUR:
            mValue = Math.round(mMeasure * CalendarField.HOUR.millisecs);
            break;
        case MINUTE:
            mValue = Math.round(mMeasure * CalendarField.MINUTE.millisecs);
            break;
        case SECOND:
            mValue = Math.round(mMeasure * CalendarField.SECOND.millisecs);
            break;
        }

    }

    /**
     * Convert this DatetimeMeasure to a floating point value on the same scale as a DatetimeValue.
     */
    @Override
        public double getRealValue() {
        return (double)mValue;
    }

    /**
     * Convert this DatetimeMeasure to a string.
     */
    @Override
        public String toString() {
        return String.format("%s%s", mMeasureString, mUnit.value);
    }

}
