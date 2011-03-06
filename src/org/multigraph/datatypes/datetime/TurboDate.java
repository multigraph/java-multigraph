package org.multigraph.datatypes.datetime;

import java.util.Calendar;

public class TurboDate {
  	
    private Calendar mCalendar;

    public TurboDate(double time) {
        this((long)time);
    }

    public TurboDate(long time) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
    }

    public long getYear() {
        return mCalendar.get(Calendar.YEAR);
    }
    public long getMonth() {
        return mCalendar.get(Calendar.MONTH);
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


    public long getTimeInMillis() {
        return mCalendar.getTimeInMillis();
    }

    public TurboDate clone() {
        return new TurboDate(this.getTimeInMillis());
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

    // return the "first tick" date at or after this date, with ticks starting at "start"
    // and occurring every daySpacing days
    public TurboDate firstMillisecondSpacingTickAtOrAfter(TurboDate start, long msSpacing) {
        long startms = start.getTimeInMillis();
        long tms     = this.getTimeInMillis() - startms;
        long d       = (long)(Math.floor( tms / msSpacing ));
        if (tms % msSpacing != 0) {
            ++d;
        }
        return new TurboDate(startms + d * msSpacing);
    }

    public TurboDate firstSecondSpacingTickAtOrAfter(TurboDate start, long secondSpacing) {
        return firstMillisecondSpacingTickAtOrAfter(start, secondSpacing * DatetimeValue.MillisecondsInOneSecond);
    }

    public TurboDate firstMinuteSpacingTickAtOrAfter(TurboDate start, long minuteSpacing) {
        return firstMillisecondSpacingTickAtOrAfter(start, minuteSpacing * DatetimeValue.MillisecondsInOneMinute);
    }

    public TurboDate firstHourSpacingTickAtOrAfter(TurboDate start, long hourSpacing) {
        return firstMillisecondSpacingTickAtOrAfter(start, hourSpacing * DatetimeValue.MillisecondsInOneHour);
    }

    public TurboDate firstDaySpacingTickAtOrAfter(TurboDate start, long daySpacing) {
        return firstMillisecondSpacingTickAtOrAfter(start, daySpacing * DatetimeValue.MillisecondsInOneDay);
    }

    // return the "first tick" date at or after this date, with ticks starting at "start"
    // and occurring every monthSpacing months
    public TurboDate firstMonthSpacingTickAtOrAfter(TurboDate start, long monthSpacing) {
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
    public TurboDate firstYearSpacingTickAtOrAfter(TurboDate start, long yearSpacing) {
        return firstMonthSpacingTickAtOrAfter(start, yearSpacing*12);
    }

}

      
