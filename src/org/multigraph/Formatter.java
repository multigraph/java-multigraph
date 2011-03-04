package org.multigraph;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.multigraph.DataValue.Number;

public abstract class Formatter {

    abstract public String format(DataValue value);
    
    public static Formatter create(DataType type, String formatString) {
        switch (type) {
        case NUMBER:
            return new Number(formatString);
        case DATETIME:
        	return new Datetime(formatString);
        default:
            return null;
        }
    }    


    public static class Datetime extends Formatter {

        private String mFormatString;
        public Datetime(String formatString) {
            mFormatString = formatString;
        }

		private static final SimpleDateFormat mFormatter_d = new SimpleDateFormat("d");
		private static final SimpleDateFormat mFormatter_D = new SimpleDateFormat("dd");
		private static final SimpleDateFormat mFormatter_m = new SimpleDateFormat("M");
		private static final SimpleDateFormat mFormatter_M = new SimpleDateFormat("MM");
		private static final SimpleDateFormat mFormatter_Y = new SimpleDateFormat("yyyy");
		private static final SimpleDateFormat mFormatter_y = new SimpleDateFormat("yy");
		private static final SimpleDateFormat mFormatter_W = new SimpleDateFormat("EEEE");
		private static final SimpleDateFormat mFormatter_w = new SimpleDateFormat("EEE");
		private static final SimpleDateFormat mFormatter_N = new SimpleDateFormat("MMMM");
		private static final SimpleDateFormat mFormatter_n = new SimpleDateFormat("MMM");
		private static final SimpleDateFormat mFormatter_H = new SimpleDateFormat("HH");
		private static final SimpleDateFormat mFormatter_h = new SimpleDateFormat("h");
		private static final SimpleDateFormat mFormatter_i = new SimpleDateFormat("mm");
		private static final SimpleDateFormat mFormatter_s = new SimpleDateFormat("ss");
		private static final SimpleDateFormat mFormatter_S = new SimpleDateFormat("SSS");
		private static final SimpleDateFormat mFormatter_P = new SimpleDateFormat("a");

		public String format(DataValue value) {
			Date date = ((Datetime)value).getDate();
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
	
}
