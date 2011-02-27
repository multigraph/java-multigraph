package org.multigraph;

public abstract class Renderer {

    private Axis mHorizontalAxis;
    private Axis mVerticalAxis;

    protected Renderer(Plot parent,
                       org.multigraph.jaxb.Renderer state) {
        mHorizontalAxis = parent.getHorizontalAxis();
        mVerticalAxis   = parent.getVerticalAxis();
    }

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
  	
  	public static Renderer create(Plot parent, org.multigraph.jaxb.Renderer state) {
  		switch (state.getType()) {
  		case POINTLINE:
  			return new PointLineRenderer(parent, state);
/*
       case BAR:
           return new BarRenderer(parent, state);
*/
  		}
  		return null;
  	}


}
