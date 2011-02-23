package org.multigraph;

import java.util.ArrayList;

public class Axis {
	
	private org.multigraph.mugl.graph.Axis mState;
    private Graph mParent;

    private String mId;
    public String getId() { return mId; }

    private int mLength;
    public int getLength() { return mLength; }

    private int mPerpOffset;
    public int getPerpOffset() { return mPerpOffset; }

    private int mParallelOffset;
    public int getParallelOffset() { return mParallelOffset; }

    private int mMinOffset;
    public int getMinOffset() { return mMinOffset; }

    private int mMaxOffset;
    public int getMaxOffset() { return mMaxOffset; }

    private double mAxisToDataRatio;
    public double getAxisToDataRatio() { return mAxisToDataRatio; }

    private ArrayList<Labeler> mLabelers;
    
    private double mDensity;
    private Labeler mLabeler;
    
	//
	// _dataMin is the min data value; access through dataMin getter/setter property in order to keep
	// record of whether it is set, and to update the axisToDataRatio accordingly.
	//
    private DataValue mDataMin;
    private boolean mHaveDataMin = false;
	public boolean haveDataMin(){ return mHaveDataMin; }
    public DataValue getDataMin() { return mDataMin; }
    public void setDataMin(DataValue min) {
        mDataMin = min;
        mHaveDataMin = true;
        if (mHaveDataMin && mHaveDataMax) {
            mAxisToDataRatio = (mLength - mMaxOffset - mMaxOffset) / (mDataMax.getDoubleValue() - mDataMax.getDoubleValue());
        }
    }

	//
	// _dataMax is the max data value; access through dataMax getter/setter property in order to keep
	// record of whether it is set, and to update the axisToDataRatio accordingly.
	//
    private DataValue mDataMax;
    private boolean mHaveDataMax = false;
	public boolean haveDataMax(){ return mHaveDataMax; }
    public DataValue getDataMax() { return mDataMax; }
    public void setDataMax(DataValue max) {
        mDataMax = max;
        mHaveDataMax = true;
        if (mHaveDataMax && mHaveDataMax) {
            mAxisToDataRatio = (mLength - mMaxOffset - mMaxOffset) / (mDataMax.getDoubleValue() - mDataMin.getDoubleValue());
        }
    }

    private DataValue.Type mType = DataValue.Type.UNKNOWN;
    public DataValue.Type getType() { return mType; }

    private org.multigraph.mugl.graph.Axis.Orientation mOrientation;
    public org.multigraph.mugl.graph.Axis.Orientation getOrientation() { return mOrientation; }


    public Axis(Graph parent, org.multigraph.mugl.graph.Axis state) {
        this.mParent      = parent;
        this.mState       = state;
        this.mOrientation = state.getOrientation();
        this.mId          = state.getId();
        this.mType        = state.getType();

        this.mLength = (int)Math.round(mState.getLength() * ((mOrientation == org.multigraph.mugl.graph.Axis.Orientation.HORIZONTAL)
                                            ? mParent.getPlotbox().getWidth()
                                            : mParent.getPlotbox().getHeight()));

        if (!state.getMin().equals("auto")) {
            setDataMin(DataValue.create(mType, state.getMin()));
        }

        if (!state.getMax().equals("auto")) {
            setDataMax(DataValue.create(mType, state.getMax()));
        }

        buildLabelers();
    }


    private void buildLabelers() {
    	this.mLabelers = new ArrayList<Labeler>();
    	int numLabelSubtags = mState.getLabels().getLabels().size();
    	if (numLabelSubtags > 0) {
    		// This is the case where we have <labels><label>...</label>...</labels>,
    		// i.e. single <label> tags nested inside the <labels> tag
            for(int k = 0; k < numLabelSubtags; ++k) {
            	String hlabelSpacings[] = mState.getLabels().getLabels().get(k).getSpacing().split("[ \t]+");
            	for (int j=0; j<hlabelSpacings.length; ++j) {
                    double spacing = Double.parseDouble(hlabelSpacings[j]);
                    DoubleLabeler labeler = new DoubleLabeler(spacing, 
                                                              mState.getLabels().getLabels().get(k).getFormat(),
                                                              mState.getLabels().getLabels().get(k).getStart().getDoubleValue(),
                                                              mState.getLabels().getLabels().get(k).getPosition(),
                                                              mState.getLabels().getLabels().get(k).getAngle(),
                                                              mState.getLabels().getLabels().get(k).getAnchor());
                    this.mLabelers.add(labeler);
                }
            }
    	} else {
    		// This is the case where we have no <label> tags nested inside the <labels> tag
    		String hlabelSpacings[] = mState.getLabels().getSpacing().split("[ \t]+");
    		for (int k=0; k<hlabelSpacings.length; ++k) {
                double spacing = Double.parseDouble(hlabelSpacings[k]);
                DoubleLabeler labeler = new DoubleLabeler(spacing, 
                        mState.getLabels().getFormat(),
                        mState.getLabels().getStart().getDoubleValue(),
                        mState.getLabels().getPosition(),
                        mState.getLabels().getAngle(),
                        mState.getLabels().getAnchor());
                this.mLabelers.add(labeler);
    		}
    	}
    }



    public double dataValueToAxisValue(DataValue v) {
        return mAxisToDataRatio * ( v.getDoubleValue() - mDataMin.getDoubleValue() ) + mMinOffset + mParallelOffset;
    }

    public DataValue axisValueToDataValue(double v) {
        return DataValue.create(mType, (v - mMinOffset - mParallelOffset) / mAxisToDataRatio + mDataMin.getDoubleValue());
    }

    private void prepareRender() {
        // Decide which labeler to use: take the one with the largest density <= 0.8.
        // Unless all have density > 0.8, in which case we take the first one.  This assumes
        // that the labelers list is ordered in increasing order of label density.
        // This function sets the _labeler and _density private properties.
        mLabeler = mLabelers.get(0);
        mDensity = mLabeler.getLabelDensity(this);
        if (mDensity < 0.8) {
            for (int i = 1; i < mLabelers.size(); i++) {
                double density = mLabelers.get(i).getLabelDensity(this);
                if (density > 0.8) { break; }
                mLabeler = mLabelers.get(i);
                mDensity = density;
            }
        }
    }

    public void render(GraphicsContext g, int step) {

      switch (step) {
      case 0:  // in step 0, render the grid lines associated with this axis, if any
        prepareRender();
        if (mState.getGrid().getVisible()) {
            if (mLabelers.size() > 0 && mDensity <= 1.5) {
                mLabeler.prepare(mDataMin, mDataMax);
                boolean first = true;
                while (mLabeler.hasNext()) {
                    DataValue v = mLabeler.next();
                    if (first) {
                        first = false;
                    }
                    double a = dataValueToAxisValue(v);
                    g.setLineWidth(1.0);
                    g.setColor(mState.getGrid().getColor());
                    if (mOrientation == org.multigraph.mugl.graph.Axis.Orientation.HORIZONTAL) {
                        g.drawLine(a, mPerpOffset, a, mParent.getPlotbox().getHeight() - mPerpOffset);
                    } else {
                        g.drawLine(mPerpOffset, a, mParent.getPlotbox().getWidth() - mPerpOffset, a);
                    }
                }
            }
        }
        break;

      default:
      case 1:  // in step 1, render everything else
          // render the axis itself:
          if (mState.getLinewidth() > 0) {
              g.setLineWidth(mState.getLinewidth());
              g.setColor(mState.getColor());

              if (mOrientation == org.multigraph.mugl.graph.Axis.Orientation.HORIZONTAL) {
                  g.drawLine(mParallelOffset, mPerpOffset, mParallelOffset + mLength, mPerpOffset);
              } else {
                  g.drawLine(mPerpOffset, mParallelOffset, mPerpOffset, mParallelOffset + mLength);
              }
          }

          // render the axis title
          // ... NYI ...

          // render the tick marks and labels
          // ... NYI ...
      }

    }


}