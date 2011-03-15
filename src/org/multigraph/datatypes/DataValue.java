package org.multigraph.datatypes;

import org.multigraph.DataTypeException;
import org.multigraph.datatypes.number.NumberValue;
import org.multigraph.datatypes.datetime.DatetimeValue;

/**
 * A DataValue represents a data value that can be plotted along a
 * real number line.  There are different types of data values,
 * depending on the underlying data type (currently number and
 * datetime); this abstract superclass encapsulates operations
 * common to all data types.
 * <p>
 * Regardless of the specific underlying data type, every DataValue
 * corresponds to a particular real number that determines the
 * location of that value along a real number line for the purposes
 * of plotting.  The getRealValue() method returns that number.
 */
public abstract class DataValue {

    /**
     * Return the real number corresponding to this data value
     */
    public abstract double getRealValue();

    /**
     * Return a string representation of this DataValue, for debugging.  (For control over the format
     * of the string, use a DataFormatter instead.)
     */
    public abstract String toString();

    /**
     * Static factory class for creating a DataValue instance of a given type by parsing a string.
     */
    public static DataValue create(DataType type, java.lang.String string) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberValue(string);
        case DATETIME:
            return new DatetimeValue(string);
        default:
            throw new DataTypeException(String.format("DataValue.create: unknown DataType ('%s') when converting string '%s' to DataValue",
                                                      type.toString(), string));
        }
    }

    /**
     * Static factory class for creating a DataValue instance with a given type and a given
     * real value.
     */
    public static DataValue create(DataType type, double realValue) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberValue(realValue);
        case DATETIME:
            return new DatetimeValue(realValue);
        default:
            throw new DataTypeException(String.format("DataValue.create: unknown DataType ('%s') when converting double '%f' to DataValue",
                                                      type.toString(), realValue));
        }
    }

    /**
     * Comparator function.
     *
     * @param x The value to compare this value to
     * @return -1, 0, or 1, according as this DataValue is less than, equal to, or greater than x
     */
    public abstract int compareTo(DataValue x);
    
    /**
     * Less-than comparator function.  This function is implemented in terms of compareTo(); subclasses
     * do not need to override this function.
     *
     * @param x The value to compare this value to.
     * @return A boolean value indicating whether this value is less than x.
     */
    public boolean lt(DataValue x) { return compareTo(x) <  0; }

    /**
     * Less-than-or-equal comparator function.  This function is implemented in terms of compareTo(); subclasses
     * do not need to override this function.
     *
     * @param x The value to compare this value to.
     * @return A boolean value indicating whether this value is less than or equal to x.
     */
    public boolean le(DataValue x) { return compareTo(x) <= 0; }

    /**
     * Equal-to comparator function.  This function is implemented in terms of compareTo(); subclasses
     * do not need to override this function.
     *
     * @param x The value to compare this value to.
     * @return A boolean value indicating whether this value is equal to x.
     */
    public boolean eq(DataValue x) { return compareTo(x) == 0; }

    /**
     * Greater-than-or-equal comparator function.  This function is implemented in terms of compareTo(); subclasses
     * do not need to override this function.
     *
     * @param x The value to compare this value to.
     * @return A boolean value indicating whether this value is greater than or equal to x.
     */
    public boolean ge(DataValue x) { return compareTo(x) >= 0; }

    /**
     * Greater-than comparator function.  This function is implemented in terms of compareTo(); subclasses
     * do not need to override this function.
     *
     * @param x The value to compare this value to.
     * @return A boolean value indicating whether this value is greater than x.
     */
    public boolean gt(DataValue x) { return compareTo(x) >  0; }
    
}
