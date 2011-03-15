package org.multigraph.datatypes;

import org.multigraph.DataTypeException;
import org.multigraph.datatypes.number.NumberMeasure;
import org.multigraph.datatypes.datetime.DatetimeMeasure;

/**
 * A DataMeasure stores a measure associated with a DataType.
 * Multigraph uses DataMeasure instances to represent lengths along an
 * axis, for things like tick mark and label spacing, widths of bars
 * in bar graphs, etc.
 * <p>
 * The details of correctly measuring a length depend on the data
 * type.  For example, the measure of "one month" for the DATETIME
 * type is different depending on the month, since different months
 * have different lengths.  This abstract superclass exists so that
 * data-type-specific subclasses can implement whatever details are
 * needed for measurements of that data type.
 */
public abstract class DataMeasure {

    /**
     * Convert this DataMeasure to a real number that is commensurate with the real
     * number value of the underlying data type.  
     */
    public abstract double getRealValue();

    /**
     * Convert this DataMeasure to a string.
     */
    public abstract String toString();

    /**
     * Static factory method to parse a string and return a DataMeasure instance of a given DataType.
     */
    public static DataMeasure create(DataType type, String string) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberMeasure(string);
        case DATETIME:
            return new DatetimeMeasure(string);
        default:
            throw new DataTypeException(String.format("DataMeasure.create: unknown DataType ('%s') when converting string '%s' to DataMeasure",
                                                      type.toString(), string));
        }
    }

    /**
     * Static factory method to construct a DataMeasure instance using a given real number
     * and a given DataType.
     */
    public static DataMeasure create(DataType type, double realValue) throws DataTypeException {
        switch (type) {
        case NUMBER:
            return new NumberMeasure(realValue);
        case DATETIME:
            return new DatetimeMeasure(realValue);
        default:
            throw new DataTypeException(String.format("DataMeasure.create: unknown DataType ('%s') when converting double '%f' to DataMeasure",
                                                      type.toString(), realValue));
        }
    }
    

}
