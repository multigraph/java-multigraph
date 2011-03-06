package org.multigraph;

import java.util.ArrayList;

public abstract class Plot {

	protected Graph mParent;
	protected org.multigraph.jaxb.Plot mState;
    protected Axis mHorizontalAxis;
    protected Axis mVerticalAxis;
    protected Renderer mRenderer;
    protected String mLegendLabel;

    protected Plot(Graph parent, org.multigraph.jaxb.Plot state,
    		  	   Axis haxis, Axis vaxis, String legendLabel) {
    	mParent         = parent;
    	mState          = state;
        mHorizontalAxis = haxis;
        mVerticalAxis   = vaxis;
        mLegendLabel    = legendLabel;
    }
	
    public Axis getHorizontalAxis() { return mHorizontalAxis; }
    public Axis getVerticalAxis() { return mVerticalAxis; }
    
	public abstract void prepareData();

    public abstract void render(GraphicsContext g);

	public static Plot create(Graph parent, org.multigraph.jaxb.Plot state) throws DataTypeException {
		Axis haxis = parent.findAxisById(state.getHorizontalaxis().getRef());
		Axis vaxis = parent.findAxisById(state.getVerticalaxis().getRef());
		ArrayList<String> varids = new ArrayList<String>();
		for (org.multigraph.jaxb.PlotVariable var : state.getHorizontalaxis().getVariable()) {
			varids.add( var.getRef() );
		}
		for (org.multigraph.jaxb.PlotVariable var : state.getVerticalaxis().getVariable()) {
			varids.add( var.getRef() );
		}
		Data data = parent.findDataByVariableId(varids.get(0));
		return new DataPlot(parent, state, data, varids.toArray(new String[0]), haxis, vaxis, null);
	}
	
}
