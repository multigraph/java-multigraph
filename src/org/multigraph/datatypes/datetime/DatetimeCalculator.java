package org.multigraph.datatypes.datetime;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * This is a utility class for doing basic date arithmetic: extracting
 * and/or adding date/time parts (days, months, years, hours, etc)
 * from/to dates.  It is essentially a wrapper around
 * java.util.Calendar, together with a function called
 * "firstTickAtOrAfter()" that computes the first value on a datetime
 * line that lines up with a given regular spacing.
 *
 * An instance of this class represents a specific datetime value
 */
public class DatetimeCalculator {

    /**
     * An internal Calendar instance.  This is what holds the specific
     * date associated with this instance.
     */
    private Calendar mCalendar;

    public DatetimeCalculator(double time) {
        this((long)time);
    }

    private DatetimeCalculator(long time) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        mCalendar.setTimeInMillis(time);
    }

    public long getYear() {
    	return mCalendar.get(Calendar.YEAR);
    }
    public long getMonth() {    	
        return mCalendar.get(Calendar.MONTH) + 1; // java.util.Calendar's MONTH is 0-based 
    }
    public long getDay() {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }
    public long getHour() {
        return mCalendar.get(Calendar.HOUR_OF_DAY);
    }
    public long getMinute() {
        return mCalendar.get(Calendar.MINUTE);
    }
    public long getSecond() {
        return mCalendar.get(Calendar.SECOND);
    }
    public long getMillisecond() {
        return mCalendar.get(Calendar.MILLISECOND);
    }

    public String toString() {
    	return String.format("%04d-%02d-%02d %02d:%02d:%02d",
    			getYear(), getMonth(), getDay(), getHour(), getMinute(), getSecond());
    }

    public long getTimeInMillis() {
        return mCalendar.getTimeInMillis();
    }

    public DatetimeCalculator clone() {
        return new DatetimeCalculator(this.getTimeInMillis());
    }


    public void add(DatetimeInterval.Unit unit, double n) {
        add(unit, (int)n);
    }

    public void add(DatetimeInterval.Unit unit, int n) {
        switch (unit) {
        case YEAR:
            mCalendar.add(Calendar.YEAR, n);
            break;
        case MONTH:
            mCalendar.add(Calendar.MONTH, n);
            break;
        case DAY:
            mCalendar.add(Calendar.DAY_OF_MONTH, n);
            break;
        case HOUR:
            mCalendar.add(Calendar.HOUR, n);
            break;
        case MINUTE:
            mCalendar.add(Calendar.MINUTE, n);
            break;
        case SECOND:
            mCalendar.add(Calendar.SECOND, n);
            break;
        default:
        case MILLISECOND:
            mCalendar.add(Calendar.MILLISECOND, n);
            break;
        }
    }
    
    public DatetimeCalculator firstTickAtOrAfter(DatetimeCalculator start, double measure, DatetimeInterval.Unit unit) {
        switch (unit) {
        default:
        case MILLISECOND:
        {
            long startms = start.getTimeInMillis();
            long tms     = this.getTimeInMillis() - startms;
            long d       = (long)(Math.floor( tms / measure ));
            if (tms % measure != 0) {
                ++d;
            }
            return new DatetimeCalculator(startms + d * measure);
        }
        case SECOND:
            return firstTickAtOrAfter(start, measure * DatetimeValue.MillisecondsInOneSecond, DatetimeInterval.Unit.MILLISECOND);
        case MINUTE:
            return firstTickAtOrAfter(start, measure * DatetimeValue.MillisecondsInOneMinute, DatetimeInterval.Unit.MILLISECOND);
        case HOUR:
            return firstTickAtOrAfter(start, measure * DatetimeValue.MillisecondsInOneHour, DatetimeInterval.Unit.MILLISECOND);
        case DAY:
            return firstTickAtOrAfter(start, measure * DatetimeValue.MillisecondsInOneDay, DatetimeInterval.Unit.MILLISECOND);
        case MONTH:
        {
            long tmonths = 12 * (getYear() - start.getYear()) + (this.getMonth() - start.getMonth());
            long d = (long)(Math.floor( tmonths / measure ));
            if (tmonths % measure != 0) { ++d; }
            else if (this.getDay()>start.getDay()) { ++d; }
            else if (this.getDay()==start.getDay() && this.getHour()>start.getHour()) { ++d; }
            else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()>start.getHour()) { ++d; }
            else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()==start.getHour() && this.getMinute()>start.getMinute()) { ++d; }
            else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()==start.getHour() && this.getMinute()==start.getMinute() && this.getSecond()==start.getSecond() && this.getMillisecond()>start.getMillisecond()) { ++d; }
            
            DatetimeCalculator firstTick = start.clone();
            firstTick.add(DatetimeInterval.Unit.MONTH, (int)(d * measure));
            return firstTick;
        }
        case YEAR:
            return firstTickAtOrAfter(start, measure*12, DatetimeInterval.Unit.MONTH);
        }
    }

}

      
