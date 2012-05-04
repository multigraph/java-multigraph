package org.multigraph.datatypes.datetime;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * The DatetimeValueCalendar class extends DatetimeValue by adding
 * calendar computation abilities to it. The DatetimeValueCalendar
 * class adds methods for extracting calendar fields (years, months,
 * days, hours, etc) from the value, for adding multiples of calendar
 * fields to the current value (add N years, months, days, hours, etc),
 * and for computing the first DatetimeValue that is greater than or
 * equal to the current value and which lines up with a given regular
 * spacing (function "firstTickAtOrAfter()").
 *
 * This is all accomplished by storing an internal instance of
 * java.util.Calendar that is kept in sync with the DatetimeValue's
 * value.
 *
 * The reason this is a separate class, rather than having this
 * functionality in the DatetimeValue class, is so that DatetimeValue
 * can remain lightweight (i.e. be free of a java.util.Calendar
 * instance).  Instances of DatetimeValue occur as part of data sets
 * and are stored in arrays that may be very long, so it's good to
 * keep them small.  In the limited places where calendar arithmetic
 * is needed, you can easily convert from DatetimeValue to
 * DatetimeValueCalendar, and vice versa.
 */
public class DatetimeValueCalendar extends DatetimeValue {

    /**
     * The internal java.util.Calendar instance.
     */
    private Calendar mCalendar;

    /**
     * Create a new DatetimeValueCalendar instance initialized from a
     * DatetimeValue.  The returned instance does not store a
     * reference to the passed DatetimeValue; it just initialized to
     * the same moment in time.  The passed DatetimeValue is not
     * modified.
     *
     * @param datetimeValue The DatetimeValue to use
     */
    public DatetimeValueCalendar(DatetimeValue datetimeValue) {
        this(datetimeValue.getRealValue());
    }

    /**
     * Private constructor to create a DatetimeValueCalendar using a 
     * double value.
     */
    private DatetimeValueCalendar(double time) {        
        super(time);
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        mCalendar.setTimeInMillis((long)time);
    }

    /**
     * Convert this DatetimeValueCalendar to a plain DatetimeValue.
     */
    public DatetimeValue toDatetimeValue() {
        return new DatetimeValue(mValue);
    }

    /**
     * Return the year part of this value.  Years are based at 0 AD.  So 1900 is 1900, 2000 is 2000, etc.
     */
    public long getYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    /**
     * Return the month part of this value, in the range 1-12.
     */
    public long getMonth() {            
        return mCalendar.get(Calendar.MONTH) + 1; // java.util.Calendar's MONTH is 0-based 
    }

    /**
     * Return the day (of the month) part of this value, in the range 1-31.
     */
    public long getDay() {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Return the hour part of this value, in the range 0-23.
     */
    public long getHour() {
        return mCalendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Return the minute part of this value, in the range 0-59.
     */
    public long getMinute() {
        return mCalendar.get(Calendar.MINUTE);
    }

    /**
     * Return the second part of this value, in the range 0-59.
     */
    public long getSecond() {
        return mCalendar.get(Calendar.SECOND);
    }

    /**
     * Return the millisecond part of this value (fraction of a second expressed as milliseconds), in
     * the range 0-999.
     */
    public long getMillisecond() {
        return mCalendar.get(Calendar.MILLISECOND);
    }

    /**
     * Return a copy of this DatetimeValueCalendar.
     */
    public DatetimeValueCalendar clone() {
        return new DatetimeValueCalendar(mValue);
    }

    /**
     * Add a number of calendar fields to this value.  This changes the value of this
     * instance (i.e. the moment in time that the instance represents).
     *
     * @param field The field to add a multiple of (YEAR, MONTH, DAY, HOUR, etc)
     * @param n The number of fields to add.  To subtract, use a negative number.
     */
    public void add(CalendarField field, int n) {
        switch (field) {
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
        mValue = mCalendar.getTimeInMillis();
    }
    
    public DatetimeValueCalendar firstSpacingLocationAtOrAfter(DatetimeValueCalendar start, double spacingMeasure, CalendarField spacingUnit) {
        switch (spacingUnit) {
        default:
        case MILLISECOND:
            {
                double startms = start.getRealValue();
                double tms     = mValue - startms;
                long d       = (long)(Math.floor( tms / spacingMeasure ));
                if (tms % spacingMeasure != 0) {
                    ++d;
                }
                return new DatetimeValueCalendar(startms + d * spacingMeasure);
            }
        case SECOND:
        case MINUTE:
        case HOUR:
        case DAY:
            return firstSpacingLocationAtOrAfter(start, spacingMeasure * spacingUnit.millisecs, CalendarField.MILLISECOND);
        case MONTH:
            {
                long tmonths = 12 * (getYear() - start.getYear()) + (this.getMonth() - start.getMonth());
                long d = (long)(Math.floor( tmonths / spacingMeasure ));
                if (tmonths % spacingMeasure != 0) { ++d; }
                else if (this.getDay()>start.getDay()) { ++d; }
                else if (this.getDay()==start.getDay() && this.getHour()>start.getHour()) { ++d; }
                else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()>start.getHour()) { ++d; }
                else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()==start.getHour() && this.getMinute()>start.getMinute()) { ++d; }
                else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()==start.getHour() && this.getMinute()==start.getMinute() && this.getSecond()==start.getSecond() && this.getMillisecond()>start.getMillisecond()) { ++d; }
            
                DatetimeValueCalendar firstTick = start.clone();
                firstTick.add(CalendarField.MONTH, (int)(d * spacingMeasure));
                return firstTick;
            }
        case YEAR:
            return firstSpacingLocationAtOrAfter(start, 12*spacingMeasure, CalendarField.MONTH);
        }
    }

}

      
