package org.multigraph;

//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Graphics2D;
import java.util.ArrayList;

public class Graph {
        
    private org.multigraph.jaxb.Graph mState;

    private int    mWidth;
    private int    mHeight;

    private Box    mWindow;
    private Insets mWindowMargin;
    private Insets mBorder;
    private Insets mPadding;
    private Box    mPaddingBox;
    private Insets mPlotMargin;
    private Box    mPlotBox;
    
    public Box getPlotbox() { return mPlotBox; }

    private ArrayList<Axis> mAxes;
    private ArrayList<Data> mData;
    private ArrayList<Plot> mPlots;
        
    private void prepareState() {
        if (!mState.isSetPlotarea())   { mState.setPlotarea(   new org.multigraph.jaxb.Plotarea() );   }
        if (!mState.isSetWindow())     { mState.setWindow(     new org.multigraph.jaxb.Window() );     }
        if (!mState.isSetBackground()) { mState.setBackground( new org.multigraph.jaxb.Background() ); }
    }

    public Graph(org.multigraph.jaxb.Graph state, int width, int height) throws DataTypeException {
        this.mState = state;
        this.mWidth = width;
        this.mHeight = height;
        prepareState();

        computeDims();

        this.mAxes = new ArrayList<Axis>();
        for (org.multigraph.jaxb.Axis axisState : mState.getHorizontalaxis()) {
            this.mAxes.add(new Axis(this, axisState));
        }
        for (org.multigraph.jaxb.Axis axisState : mState.getVerticalaxis()) {
            this.mAxes.add(new Axis(this, axisState));
        }
        
        this.mData = new ArrayList<Data>();
        for (org.multigraph.jaxb.Data dataState : mState.getData()) {
            this.mData.add(Data.create(this, dataState));
        }
        
        this.mPlots = new ArrayList<Plot>();
        for (org.multigraph.jaxb.Plot plotState : mState.getPlot()) {
            this.mPlots.add(Plot.create(this, plotState));
        }
        
    }
        
    public Data findDataByVariableId(String id) {
        for (Data data : mData) {
            for (DataVariable dataVariable : data.getVariables()) {
                if (dataVariable.getId().equals(id)) {
                    return data;
                }
            }
        }
        return null;
    }

    public Axis findAxisById(String id) {
        for (Axis axis : mAxes) {
            if (axis.getId().equals(id)) { return axis; }
        }
        return null;
    }
        
    public void render(GraphicsContext g) {

        //
        // If we have a border, fill the entire area with the border color
        //
        int borderWidth = mState.getWindow().getBorder();
        if (borderWidth > 0) {
            g.setColor(mState.getWindow().getBordercolor());
            g.fillRect(0, 0, mWidth, mHeight);
        }

        //
        // Now fill inside the border with the background color; this will fill the whole area
        // with the background color if there is no border, because in that case, borderWidth=0.
        //
        g.setColor(mState.getBackground().getColor());
        g.fillRect(borderWidth, borderWidth, mWidth-borderWidth, mHeight-borderWidth);

        /*
        //
        // draw the debug X, if any
        //
        double xWidth = state.getDebug().getXwidth();
        if (xWidth > 0) {
        g.setLineWidth((double)xWidth);
        g.setColor(state.getDebug().getXcolor());
        g.drawLine(0, 0, width, height);
        g.drawLine(0, height, width, 0);
        }
        */

        //
        // Switch to a coordinate system whose origin is the lower left inside
        // corner of the border, and in which y increases upward, and x increases
        // to the right.
        //
        g.pushTransform();
        g.translate(borderWidth, mHeight-borderWidth);
        g.scale(1.0, -1.0);

        /*
        //
        // now draw a little green box, 10 pixels on a side, with lower left corner
        // at the new origin, and a blue one right on top of it
        //
        g.setColor(new RGBColor(0,255,0));
        g.fillRect(0,0,10,10);
        g.setColor(new RGBColor(0,0,255));
        g.fillRect(0,10,10,20);
        */

        //
        // Switch to the plotBox coordinate system
        //
        g.pushTransform();
        g.translate(mPlotMargin.getLeft(), mPlotMargin.getBottom());


        //
        // Draw the plotbox border, if any
        //
        int plotareaBorder = mState.getPlotarea().getBorder();
        if (plotareaBorder > 0) {
            g.setColor(mState.getPlotarea().getBordercolor());
            g.setLineWidth(plotareaBorder);
            g.drawRect(0, 0, mPlotBox.getWidth(), mPlotBox.getHeight());
        }
        
        //
        // Render the axes, step 0
        //
        for (Axis axis : mAxes) {
            axis.render(g, 0);
        }

        //
        // Render the axes, step 1
        //
        for (Axis axis : mAxes) {
            axis.render(g, 1);
        }
        
        //
        // Render the plots
        //
        for (Plot plot : mPlots) {
            plot.render(g);
        }

    }

    private void computeDims() {
        this.mWindow       = new Box(mWidth, mHeight);
        this.mWindowMargin = new Insets(mState.getWindow().getMargin());
        this.mBorder       = new Insets(mState.getWindow().getBorder());
        this.mPadding      = new Insets(mState.getWindow().getPadding());
        this.mPaddingBox   = new Box(mWindow.getWidth()
                                     - ( mWindowMargin.getLeft() + mBorder.getLeft() + mPadding.getLeft() )
                                     - ( mWindowMargin.getRight() + mBorder.getRight() + mPadding.getRight() ),
                                     mWindow.getHeight()
                                     - ( mWindowMargin.getTop() + mBorder.getTop() + mPadding.getTop() )
                                     - ( mWindowMargin.getBottom() + mBorder.getBottom() + mPadding.getBottom() )
                                     );
        this.mPlotMargin   = new Insets(mState.getPlotarea().getMargintop(),
                                        mState.getPlotarea().getMarginleft(),
                                        mState.getPlotarea().getMarginbottom(),
                                        mState.getPlotarea().getMarginright());
        this.mPlotBox      = new Box(mPaddingBox.getWidth() - ( mPlotMargin.getLeft() + mPlotMargin.getRight()),
                                     mPaddingBox.getHeight() - ( mPlotMargin.getTop() + mPlotMargin.getBottom() ));
    }

        
    private void drawPlus(GraphicsContext g, double x, double y, double size) {
        g.drawLine(x-size, y, x+size, y);
        g.drawLine(x, y-size, x, y+size);
    }

}
