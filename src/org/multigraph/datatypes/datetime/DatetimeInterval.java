package org.multigraph.datatypes.datetime;

import org.multigraph.DataInterval;
import org.multigraph.DataTypeException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DatetimeInterval extends DataInterval {

    private static final int mMillisecondsInOneSecond = 1000;
    private static final int mMillisecondsInOneMinute = 1000 * 60;
    private static final int mMillisecondsInOneHour   = 1000 * 60 * 60;
    private static final int mMillisecondsInOneDay    = 1000 * 60 * 60 * 24;
    private static final int mMillisecondsInOneWeek   = 1000 * 60 * 60 * 24 * 7;
    private static final int mMillisecondsInOneMonth  = 1000 * 60 * 60 * 24 * 30;
    private static final int mMillisecondsInOneYear   = 1000 * 60 * 60 * 24 * 365;

    public enum Unit {
        UNKNOWN("unknown"),
            YEAR("Y"),
            MONTH("M"),
            WEEK("W"),
            DAY("D"),
            HOUR("H"),
            MINUTE("m"),
            SECOND("s");
        private final String value;
        Unit(String v) {
            value = v;
        }
        public String value() {
            return value;
        }
        public static Unit parse(String v) {
            for (Unit c: Unit.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }	
        public static String toString(Unit u) {
            if (u==null) { return "NULL-UNIT"; }
            return u.value();
        }
    }

    private long mValue; // stores the millisecond value
    private double mMeasure;
    private String mMeasureString;
    private Unit mUnit;
    private static Pattern mMeasureAndUnitPattern = Pattern.compile("\\s*([0-9eE\\.\\+\\-]+)\\s*([YMDHms])\\s*");
    private static Pattern mMeasureOnlyPattern    = Pattern.compile("\\s*([0-9eE\\.\\+\\-]+)\\s*");


    public DatetimeInterval(String string) throws DataTypeException {
        Matcher m = mMeasureAndUnitPattern.matcher(string);
        if (m.matches()) {
            try {
                mMeasureString = m.group(1);
                mMeasure = Double.parseDouble(mMeasureString);
            } catch (IllegalArgumentException e) {
                throw new DataTypeException(String.format("DatetimeInterval constructor: cannot convert measure value '%s' to double "
                                            			  + " when converting string '%s' to DatetimeInterval", m.group(1), string));
            }
            try {
                mUnit = Unit.parse(m.group(2));
            } catch (IllegalArgumentException e) {
                throw new DataTypeException(String.format("DatetimeInterval constructor: unknown unit specifier '%s' when "
                                            			  + "converting string '%s' to DatetimeInterval", m.group(2), string));
            }
        } else {

            m = mMeasureOnlyPattern.matcher(string);
            if (m.matches()) {
                try {
                    mMeasureString = m.group(1);
                    mMeasure = Double.parseDouble(mMeasureString);
                } catch (IllegalArgumentException e) {
                    throw new DataTypeException(String.format("DatetimeInterval constructor: cannot convert measure value '%s' to double "
                                                			  + " when converting string '%s' to DatetimeInterval", m.group(1), string));
                }
                mUnit = Unit.SECOND;
            } else {
                throw new DataTypeException(String.format("DatetimeInterval constructor: cannot parse string '%s' as datetime interval", string));
            }
        }
        switch (mUnit) {
        case YEAR:
            mValue = Math.round(mMeasure * DatetimeValue.MillisecondsInOneYear);
            break;
        case MONTH:
            mValue = Math.round(mMeasure * DatetimeValue.MillisecondsInOneMonth);
            break;
        case WEEK:
            mValue = Math.round(mMeasure * DatetimeValue.MillisecondsInOneWeek);
            break;
        case DAY:
            mValue = Math.round(mMeasure * DatetimeValue.MillisecondsInOneDay);
            break;
        case HOUR:
            mValue = Math.round(mMeasure * DatetimeValue.MillisecondsInOneHour);
            break;
        case MINUTE:
            mValue = Math.round(mMeasure * DatetimeValue.MillisecondsInOneMinute);
            break;
        case SECOND:
            mValue = Math.round(mMeasure * DatetimeValue.MillisecondsInOneSecond);
            break;
        }

    }

	@Override
	public double getDoubleValue() {
        return (double)mValue;
	}

	@Override
	public String getStringValue() {
        return String.format("%s%s", mMeasureString, mUnit.toString());
	}

}
