package org.multigraph.datatypes.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.multigraph.datatypes.DataValue;

/**
 * DataValue.Number is the DataValue type that represents a basic numerical value.  The
 * value is stored as a double.
 */

public class DatetimeValue extends DataValue {

    public static final long MillisecondsInOneSecond = 1000L;
    public static final long MillisecondsInOneMinute = 1000L * 60L;
    public static final long MillisecondsInOneHour   = 1000L * 60L * 60L;
    public static final long MillisecondsInOneDay    = 1000L * 60L * 60L * 24L;
    public static final long MillisecondsInOneWeek   = 1000L * 60L * 60L * 24L * 7L;
    public static final long MillisecondsInOneMonth  = 1000L * 60L * 60L * 24L * 30L;
    public static final long MillisecondsInOneYear   = 1000L * 60L * 60L * 24L * 365L;
	
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
        mSDF8  = new SimpleDateFormat("yyyyMMdd");
        mSDF8.setTimeZone(utc);
        mSDF10 = new SimpleDateFormat("yyyyMMddHH");
        mSDF10.setTimeZone(utc);
        mSDF12 = new SimpleDateFormat("yyyyMMddHHmm");
        mSDF12.setTimeZone(utc);
        mSDF14 = new SimpleDateFormat("yyyyMMddHHmmss");
        mSDF14.setTimeZone(utc);
        mSDF16 = new SimpleDateFormat("yyyyMMddHHmmss.S");
        mSDF16.setTimeZone(utc);
    }
	
    //protected Date mValue;
	protected double mValue;

    public DatetimeValue(long value) {
       	this.mValue = (double)(value);
    }
    public DatetimeValue(double value) {
       	//this.mValue = (double)((new Date((long)value)).getTime());
        this.mValue = value;
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
    			/*
    			Date d = mSDF10.parse(value);
    			String s = mSDF10.format(d);
    			System.out.printf("string '%s' parses to Date '%s' and formats back to string '%s'\n", value, d.toString(), s);
    			long t = d.getTime();
    			System.out.printf("     ms time = %d\n", t);
    			this.mValue = t;
    			*/
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
    public Date getDateValue() {
    	return new Date((long)mValue);
    }
    public java.lang.String toString() {
    	return mSDF16.format(new Date((long)mValue));
    }
    public int compareTo(DataValue x) {
    	return (new Double(mValue)).compareTo(((DatetimeValue)x).mValue); 
    }
}
