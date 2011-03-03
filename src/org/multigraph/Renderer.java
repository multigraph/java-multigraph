package org.multigraph;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Renderer {

    public static class Option {
        public Object value;
        public String stringValue;
        public Double min;
        public Double max;
        public Option(Object value, String stringValue, Double min, Double max) {
            this.value       = value;
            this.stringValue = stringValue;
            this.min         = min;
            this.max         = max;
        }
    }

    protected Plot mPlot;
    protected Axis mHorizontalAxis;
    protected Axis mVerticalAxis;

    private HashMap<Enum,ArrayList<Option>> mOptions;

    protected Renderer(Plot parent,
                       org.multigraph.jaxb.Renderer state) {
    	mPlot           = parent;
        mHorizontalAxis = parent.getHorizontalAxis();
        mVerticalAxis   = parent.getVerticalAxis();
        mOptions        = new HashMap<Enum,ArrayList<Option>>();
    }

    public void setOption(Enum key, Object value, String stringValue, Double min, Double max) {
        ArrayList<Option> options = mOptions.get(key);
        if (options == null) {
            options = new ArrayList<Option>();
            mOptions.put(key, options);
        }
        // NEED TO CHANGE THIS TO ORDERED INSERT!!
        options.add(new Option(value,stringValue,min,max));
    }

    public Object getOption(Enum key) {
        return getOption(key, (Double)null);
    }

    public boolean isSetOption(Enum key) {
        return mOptions.containsKey(key);
    }

    public Object getOption(Enum key, DataValue value) {
    	if (value == null) { return getOption(key, (Double)null); }
    	return getOption(key, value.getDoubleValue());
    }
    public Object getOption(Enum key, Double value) {
        Option opt = getOptionObject(key,value);
        if (opt != null) {
            return opt.value;
        }
        return null;
    }

    public Object getOptionStringValue(Enum key, DataValue value) {
    	if (value == null) { return getOptionStringValue(key, (Double)null); }
    	return getOptionStringValue(key, value.getDoubleValue());
    }
    public Object getOptionStringValue(Enum key, Double value) {
        Option opt = getOptionObject(key,value);
        if (opt != null) {
            return opt.stringValue;
        }
        return null;
    }

    private Option getOptionObject(Enum key, Double value) {
        ArrayList<Option> options = mOptions.get(key);
        if (options == null) { return null; }
        for (Option option : options) {
            if (value == null) { return option; }
            if ((option.min==null || option.min <= value)
                &&
                (option.max==null || value <  option.max)) {
                return option;
            }
        }
        return null;
    }

    /*
    protected Enum parseEnum(Enum values[], String v) {
        for (Enum c: values) {
            if (c.name().equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }	
    */

    public void begin(GraphicsContext g) {}
    public void end(GraphicsContext g) {}
    public void dataPoint(GraphicsContext g, DataValue p[]) {}

  	public double[] transformPoint(DataValue[] input) {
        double output[] = new double[input.length];
		output[0] = mHorizontalAxis.dataValueToAxisValue(input[0]);
        for (int i = 1; i<input.length; ++i) {
            output[i] = mVerticalAxis.dataValueToAxisValue(input[i]);
        }
        return output;
  	}

  	public double[] transformPoint(double[] input) {
        double output[] = new double[input.length];
		output[0] = mHorizontalAxis.dataValueToAxisValue(input[0]);
        for (int i = 1; i<input.length; ++i) {
            output[i] = mVerticalAxis.dataValueToAxisValue(input[i]);
        }
        return output;
  	}

  	public double[] transformPoint(double x, double y) {
        double output[] = new double[2];
		output[0] = mHorizontalAxis.dataValueToAxisValue(x);
        output[1] = mVerticalAxis.dataValueToAxisValue(y);
        return output;
  	}
  	
  	public static Renderer create(Plot parent, org.multigraph.jaxb.Renderer state) {
        Renderer renderer = null;
  		switch (state.getType()) {
  		case POINTLINE:
  			renderer = new PointLineRenderer(parent, state);
            break;
  		case BAR:
  			renderer = new BarRenderer(parent, state);
            break;

  		}
  		return renderer;
  	}


}
