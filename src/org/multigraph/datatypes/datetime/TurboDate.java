package org.multigraph.datatypes.datetime;

import java.util.Calendar;
import java.util.TimeZone;

public class TurboDate {
  	
    private Calendar mCalendar;

    public TurboDate(double time) {
        this((long)time);
    }

    public TurboDate(long time) {
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

    public TurboDate clone() {
        return new TurboDate(this.getTimeInMillis());
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


    public void addYears(int n) {
        mCalendar.add(Calendar.YEAR, n);
    }

    public void addMonths(int n) {
        mCalendar.add(Calendar.MONTH, n);
    }

    public void addDays(int n) {
        mCalendar.add(Calendar.DAY_OF_MONTH, n);
    }

    public void addHours(int n) {
        mCalendar.add(Calendar.HOUR, n);
    }

    public void addMinutes(int n) {
        mCalendar.add(Calendar.MINUTE, n);
    }

    public void addSeconds(int n) {
        mCalendar.add(Calendar.SECOND, n);
    }

    public void addMilliseconds(int n) {
        mCalendar.add(Calendar.MILLISECOND, n);
    }

    public TurboDate firstTickAtOrAfter(TurboDate start, double measure, DatetimeInterval.Unit unit) {
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
            return new TurboDate(startms + d * measure);
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
            
            TurboDate firstTick = start.clone();
            firstTick.addMonths((int)(d * measure));
            return firstTick;
        }
        case YEAR:
            return firstTickAtOrAfter(start, measure*12, DatetimeInterval.Unit.MONTH);
        }
    }

    /*

    // return the "first tick" date at or after this date, with ticks starting at "start"
    // and occurring every daySpacing days
    public TurboDate firstMillisecondSpacingTickAtOrAfter(TurboDate start, double msSpacing) {
        long startms = start.getTimeInMillis();
        long tms     = this.getTimeInMillis() - startms;
        long d       = (long)(Math.floor( tms / msSpacing ));
        if (tms % msSpacing != 0) {
            ++d;
        }
        return new TurboDate(startms + d * msSpacing);
    }

    public TurboDate firstSecondSpacingTickAtOrAfter(TurboDate start, double secondSpacing) {
        return firstMillisecondSpacingTickAtOrAfter(start, secondSpacing * DatetimeValue.MillisecondsInOneSecond);
    }

    public TurboDate firstMinuteSpacingTickAtOrAfter(TurboDate start, double minuteSpacing) {
        return firstMillisecondSpacingTickAtOrAfter(start, minuteSpacing * DatetimeValue.MillisecondsInOneMinute);
    }

    public TurboDate firstHourSpacingTickAtOrAfter(TurboDate start, double hourSpacing) {
        return firstMillisecondSpacingTickAtOrAfter(start, hourSpacing * DatetimeValue.MillisecondsInOneHour);
    }

    public TurboDate firstDaySpacingTickAtOrAfter(TurboDate start, double daySpacing) {
        return firstMillisecondSpacingTickAtOrAfter(start, daySpacing * DatetimeValue.MillisecondsInOneDay);
    }

    // return the "first tick" date at or after this date, with ticks starting at "start"
    // and occurring every monthSpacing months
    public TurboDate firstMonthSpacingTickAtOrAfter(TurboDate start, double monthSpacing) {
        long tmonths = 12 * (getYear() - start.getYear()) + (this.getMonth() - start.getMonth());
        long d = (long)(Math.floor( tmonths / monthSpacing ));

        if (tmonths % monthSpacing != 0) { ++d; }
        else if (this.getDay()>start.getDay()) { ++d; }
        else if (this.getDay()==start.getDay() && this.getHour()>start.getHour()) { ++d; }
        else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()>start.getHour()) { ++d; }
        else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()==start.getHour() && this.getMinute()>start.getMinute()) { ++d; }
        else if (this.getDay()==start.getDay() && this.getHour()==start.getHour() && this.getHour()==start.getHour() && this.getMinute()==start.getMinute() && this.getSecond()==start.getSecond() && this.getMillisecond()>start.getMillisecond()) { ++d; }

        TurboDate firstTick = start.clone();
        firstTick.addMonths((int)(d * monthSpacing));
        return firstTick;
    }
    // return the "first tick" date at or after this date, with ticks starting at "start"
    // and occurring every yearSpacing years
    public TurboDate firstYearSpacingTickAtOrAfter(TurboDate start, double yearSpacing) {
        return firstMonthSpacingTickAtOrAfter(start, yearSpacing*12);
    }
    */

}

      
