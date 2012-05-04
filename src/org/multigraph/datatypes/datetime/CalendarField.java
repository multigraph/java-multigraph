package org.multigraph.datatypes.datetime;

/**
 * A CalendarField represents a field for calendar computations ---
 * year, month, day, hour, etc.  Multigraph uses its own enum for
 * this, rather than simply using the various field values in
 * java.util.Calendar, in order to have a convenient parse() method,
 * and to be able to associate a long integer with each field
 * representing the number of milliseconds it contains.
 *
 * So, for example, CalendarField.DAY.millisecs is the number
 * of milliseconds in a day.
 */
public enum CalendarField {
    MILLISECOND("ms",      1L                             ),
        SECOND     ("s",       1000L                          ),
        MINUTE     ("m",       1000L * 60L                    ),                   
        HOUR       ("H",       1000L * 60L * 60L              ),             
        DAY        ("D",       1000L * 60L * 60L * 24L        ),       
        WEEK       ("W",       1000L * 60L * 60L * 24L * 7L   ),  
        MONTH      ("M",       1000L * 60L * 60L * 24L * 31L  ), // not always true, but that's OK
        YEAR       ("Y",       1000L * 60L * 60L * 24L * 365L ), // only true for non-leap years, but that's OK
        UNKNOWN    ("unknown", 0                              );

    /**
     * The string form of this field value; one of: "ms", "s", "m", "H", "D", "W", "M", "Y".
     */
    public final String value;

    /**
     * The number of milliseconds in one occurence of this calendar field.  For MONTH, the value
     * is the number of milliseconds in 31 days.  For YEAR, it is the number of milliseconds in 365 days.
     */
    public final long millisecs;
    CalendarField(String v, long m) {
        value = v;
        millisecs = m;
    }

    /**
     * Return a CalendarField instance by parsing a string.  The string must be one
     * of the strings allowed for the <code>value</code> field.
     */
    public static CalendarField parse(String v) {
        for (CalendarField c: CalendarField.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static String toString(CalendarField u) {
        if (u==null) { return "UNKNOWN-CALENDAR-FIELD"; }
        return u.value;
    }
}
