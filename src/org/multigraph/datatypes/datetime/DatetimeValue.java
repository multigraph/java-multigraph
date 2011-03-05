package org.multigraph.datatypes.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.multigraph.DataValue;

/**
 * DataValue.Number is the DataValue type that represents a basic numerical value.  The
 * value is stored as a double.
 */

public class DatetimeValue extends DataValue {
	
	private static final SimpleDateFormat mSDF4  = new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat mSDF6  = new SimpleDateFormat("yyyyMM");
	private static final SimpleDateFormat mSDF8  = new SimpleDateFormat("yyyyMMDD");
	private static final SimpleDateFormat mSDF10 = new SimpleDateFormat("yyyyMMDDHH");
	private static final SimpleDateFormat mSDF12 = new SimpleDateFormat("yyyyMMDDHHmm");
	private static final SimpleDateFormat mSDF14 = new SimpleDateFormat("yyyyMMDDHHmmss");
	private static final SimpleDateFormat mSDF16 = new SimpleDateFormat("yyyyMMDDHHmmss.S");
	
    protected Date mValue;

    public DatetimeValue() {}
    public DatetimeValue(double value) {
       	this.mValue = new Date((long)value);
    }
    public DatetimeValue(java.lang.String value) {
    	try {
    		switch (value.length()) {
    		case 4:
    			this.mValue = mSDF4.parse(value);
    			break;
    		case 6:
    			this.mValue = mSDF6.parse(value);
    			break;
    		case 8:
    			this.mValue = mSDF8.parse(value);
    			break;
    		case 10:
    			this.mValue = mSDF10.parse(value);
    			break;
    		case 12:
    			this.mValue = mSDF12.parse(value);
    			break;
    		case 14:
    			this.mValue = mSDF14.parse(value);
    			break;
    		default:
    			this.mValue = mSDF16.parse(value);
    			break;
    		}
    	} catch (Exception e) {
    		this.mValue = null;
    	}
    }
    public double getDoubleValue() {
        return (double)(mValue.getTime());
    }
    public java.lang.String getStringValue() {
        return mValue.toString(); // NYI
    }
    public int compareTo(DataValue x) {
    	return (new Long(this.mValue.getTime())).compareTo(((DatetimeValue)x).mValue.getTime());
    }
}