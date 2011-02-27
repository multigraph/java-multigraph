package org.multigraph;

public abstract class DataInterval {

    public abstract double getDoubleValue();
    public abstract String getStringValue();

    public static DataInterval create(DataType type, String string) {
        switch (type) {
        case NUMBER:
            return new DataInterval.Number(string);
        default:
            return null;
        }
    }

    public static DataInterval create(DataType type, double value) {
        switch (type) {
        case NUMBER:
            return new DataInterval.Number(value);
        default:
            return null;
        }
    }
    
    public static class Number extends DataInterval {

        protected double value;

        public Number() {}

        public Number(double value) {
           	this.value = value;
        }

        public Number(String value) {
            this.value = Double.parseDouble(value);
        }

        public double getDoubleValue() {
            return value;
        }

        public String getStringValue() {
            return String.format("%f", value);
        }

    }
    

}
