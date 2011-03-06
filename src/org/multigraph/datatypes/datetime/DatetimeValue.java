package org.multigraph.datatypes.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.multigraph.DataValue;

/**
 * DataValue.Number is the DataValue type that represents a basic numerical value.  The
 * value is stored as a double.
 */

public class DatetimeValue extends DataValue {

    public static final int MillisecondsInOneSecond = 1000;
    public static final int MillisecondsInOneMinute = 1000 * 60;
    public static final int MillisecondsInOneHour   = 1000 * 60 * 60;
    public static final int MillisecondsInOneDay    = 1000 * 60 * 60 * 24;
    public static final int MillisecondsInOneWeek   = 1000 * 60 * 60 * 24 * 7;
    public static final int MillisecondsInOneMonth  = 1000 * 60 * 60 * 24 * 30;
    public static final int MillisecondsInOneYear   = 1000 * 60 * 60 * 24 * 365;
	
	private static SimpleDateFormat mSDF4;
	private static SimpleDateFormat mSDF6;
	private static SimpleDateFormat mSDF8;
	private static SimpleDateFormat mSDF10;
	private static SimpleDateFormat mSDF12;
	private static SimpleDateFormat mSDF14;
	private static SimpleDateFormat mSDF16;

    static {
    	TimeZone utc = TimeZone.getTimeZone("GMT");
        mSDF4  = new SimpleDateFormat("yyyy");
        mSDF4.setTimeZone(utc);
        mSDF6  = new SimpleDateFormat("yyyyMM");
        mSDF6.setTimeZone(utc);
        mSDF8  = new SimpleDateFormat("yyyyMMDD");
        mSDF8.setTimeZone(utc);
        mSDF10 = new SimpleDateFormat("yyyyMMDDHH");
        mSDF10.setTimeZone(utc);
        mSDF12 = new SimpleDateFormat("yyyyMMDDHHmm");
        mSDF12.setTimeZone(utc);
        mSDF14 = new SimpleDateFormat("yyyyMMDDHHmmss");
        mSDF14.setTimeZone(utc);
        mSDF16 = new SimpleDateFormat("yyyyMMDDHHmmss.S");
        mSDF16.setTimeZone(utc);
    }
	
    //protected Date mValue;
	protected double mValue;

    public DatetimeValue(double value) {
       	this.mValue = (double)((new Date((long)value)).getTime());
    }
    public DatetimeValue(java.lang.String value) {
    	try {
    		switch (value.length()) {
    		case 4:
    			this.mValue = mSDF4.parse(value).getTime();
    			break;
    		case 6:
    			this.mValue = mSDF6.parse(value).getTime();
    			break;
    		case 8:
    			this.mValue = mSDF8.parse(value).getTime();
    			break;
    		case 10:
    			this.mValue = mSDF10.parse(value).getTime();
    			break;
    		case 12:
    			this.mValue = mSDF12.parse(value).getTime();
    			break;
    		case 14:
    			this.mValue = mSDF14.parse(value).getTime();
    			break;
    		default:
    			this.mValue = mSDF16.parse(value).getTime();
    			break;
    		}
    	} catch (Exception e) {
    		this.mValue = 0;
    	}
    }
    public double getDoubleValue() {
        return mValue;
    }
    public java.lang.String toString() {
    	return mSDF16.format(new Date((long)mValue));
    }
    public int compareTo(DataValue x) {
    	return (new Double(mValue)).compareTo(((DatetimeValue)x).mValue); 
    }
}
