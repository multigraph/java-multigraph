/**
 * 
 */
package org.multigraph.datatypes.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.Formatter;

public class DatetimeFormatter extends Formatter {

    private String mFormatString;
    public DatetimeFormatter(String formatString) {
        mFormatString = formatString;
    }

	private static SimpleDateFormat mFormatter_d;
	private static SimpleDateFormat mFormatter_D;
	private static SimpleDateFormat mFormatter_m;
	private static SimpleDateFormat mFormatter_M;
	private static SimpleDateFormat mFormatter_Y;
	private static SimpleDateFormat mFormatter_y;
	private static SimpleDateFormat mFormatter_W;
	private static SimpleDateFormat mFormatter_w;
	private static SimpleDateFormat mFormatter_N;
	private static SimpleDateFormat mFormatter_n;
	private static SimpleDateFormat mFormatter_H;
	private static SimpleDateFormat mFormatter_h;
	private static SimpleDateFormat mFormatter_i;
	private static SimpleDateFormat mFormatter_s;
	private static SimpleDateFormat mFormatter_S;
	private static SimpleDateFormat mFormatter_P;

    static {
    	TimeZone utc = TimeZone.getTimeZone("GMT");
        mFormatter_d = new SimpleDateFormat("d");        mFormatter_d.setTimeZone(utc);
        mFormatter_D = new SimpleDateFormat("dd");       mFormatter_D.setTimeZone(utc);
        mFormatter_m = new SimpleDateFormat("M");        mFormatter_m.setTimeZone(utc);
        mFormatter_M = new SimpleDateFormat("MM");       mFormatter_M.setTimeZone(utc);
        mFormatter_Y = new SimpleDateFormat("yyyy");     mFormatter_Y.setTimeZone(utc);
        mFormatter_y = new SimpleDateFormat("yy");       mFormatter_y.setTimeZone(utc);
        mFormatter_W = new SimpleDateFormat("EEEE");     mFormatter_W.setTimeZone(utc);
        mFormatter_w = new SimpleDateFormat("EEE");      mFormatter_w.setTimeZone(utc);
        mFormatter_N = new SimpleDateFormat("MMMM");     mFormatter_N.setTimeZone(utc);
        mFormatter_n = new SimpleDateFormat("MMM");      mFormatter_n.setTimeZone(utc);
        mFormatter_H = new SimpleDateFormat("HH");       mFormatter_H.setTimeZone(utc);
        mFormatter_h = new SimpleDateFormat("h");        mFormatter_h.setTimeZone(utc);
        mFormatter_i = new SimpleDateFormat("mm");       mFormatter_i.setTimeZone(utc);
        mFormatter_s = new SimpleDateFormat("ss");       mFormatter_s.setTimeZone(utc);
        mFormatter_S = new SimpleDateFormat("SSS");      mFormatter_S.setTimeZone(utc);
        mFormatter_P = new SimpleDateFormat("a");        mFormatter_P.setTimeZone(utc);
    }


	public String format(DataValue value) {
		Date date = ((DatetimeValue)value).getDateValue();
		StringBuffer sb = new StringBuffer();
        int index = 0;
		while (index < mFormatString.length()) {
			if (mFormatString.charAt(index) == '%') {
				++index;
				sb.append(formatSingleChar(mFormatString.charAt(index), date));
			} else {
				sb.append(mFormatString.charAt(index));
			}
			++index;
		}
		return sb.toString();
	}

	private static String formatSingleChar(char c, Date date) {
		switch (c) {
	        case 'd': // Day without leading zeros
				return mFormatter_d.format(date);
	        case 'D': // Day with leading zeros
				return mFormatter_D.format(date);
	        case 'm': // Month (numerical) without leading zeros
	            return mFormatter_m.format(date);
	        case 'M': // Month (numerical) with leading zeros
	            return mFormatter_M.format(date);
	        case 'Y': // Four digit year
	            return mFormatter_Y.format(date);
		    case 'y': // Two digit year
	            return mFormatter_y.format(date);
	        case 'W': // Weekday name
	            return mFormatter_W.format(date);
	        case 'w': // Weekday 3-letter abbrev
	            return mFormatter_w.format(date);
	        case 'N': // Month name
	            return mFormatter_N.format(date);
	        case 'n': // Month name -- 3 letter abbreviation
	            return mFormatter_n.format(date);
	        case 'H': // 24 hours
	            return mFormatter_H.format(date);
	        case 'h': // 12 hours
	            return mFormatter_h.format(date);
	        case 'i': // Minutes
	            return mFormatter_i.format(date);
	        case 's': // Seconds
	            return mFormatter_s.format(date);
            case 'v': // deciseconds -- 10ths of second -- 1 char
				return Integer.toString(Integer.parseInt(mFormatter_S.format(date))/100);
            case 'V': // centiseconds -- 100ths of second -- 2 chars
				return Integer.toString(Integer.parseInt(mFormatter_S.format(date))/10);
            case 'q': // milliseconds -- 1000ths of second -- 3 chars
				return mFormatter_S.format(date);
	        case 'P': // AM or PM
				return mFormatter_P.format(date).toUpperCase();
	        case 'p': // am or pm
				return mFormatter_P.format(date).toLowerCase();
	        case 'L': // newline
	            return "\n";
	        case '%':
	            return "%";
	        default:
	            return new String(new char[] { c });
		}
	}

}
