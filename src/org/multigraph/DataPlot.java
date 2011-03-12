package org.multigraph;

import org.multigraph.datatypes.DataValue;

public class DataPlot extends Plot {
    private Data mData;
    private String[] mVariableIds;
    
    public DataPlot(Graph parent, org.multigraph.jaxb.Plot state, 
    				Data data, String[] variableIds, Axis haxis, Axis vaxis,
    				String legendLabel) throws DataTypeException {
      super(parent, state, haxis, vaxis, legendLabel);
      mData        = data;
      mVariableIds = variableIds;
      mRenderer    = Renderer.create(this, state.getRenderer());
    }

	//@override
    public void prepareData() {
		mData.prepareData(mHorizontalAxis.getDataMin(), mHorizontalAxis.getDataMax(), 1);	
	}

    //@override 
    public void render(GraphicsContext g) {
        DataIterator iter = mData.getIterator(mVariableIds, mHorizontalAxis.getDataMin(), mHorizontalAxis.getDataMax(), 1);
        if (iter != null) {
            mRenderer.begin(g);
            while (iter.hasNext()) {
                DataValue vals[] = iter.next();
                mRenderer.dataPoint(g, vals);
            }
            mRenderer.end(g);
        }
    }

}
