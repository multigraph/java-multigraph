package org.multigraph;

import java.util.ArrayList;

import org.multigraph.datatypes.DataValue;

/**
 * This is the (abstract) superclass for classes that Multigraph
 * uses for loading, storing, and managing data to be plotted.  When
 * drawing a plot, Multigraph accesses the data through methods of
 * this class.  Individual subclasses implement their own ways of
 * loading & managing data.
 */
public abstract class Data {

    /**
     * 'variables' is an array of DataVariable instances corresponding to the variables stored
     * in this data object.
     */
	protected ArrayList<DataVariable> mVariables;
    public ArrayList<DataVariable> getVariables() { return mVariables; }

    /**
     * Create a new Data object with the given array of DataVariable instances.
     */
    public Data(ArrayList<DataVariable> variables) {
        mVariables = variables;
    }

    /**
     * Do whatever work is necessary, if any, to get the data object to load
     * data between min and max, with a given buffer (padding amount) on either
     * end.  Subclasses that actually need to do something for this method
     * should override it with an implementation that actually does something.
     * Implementations check the value of _prepareDataCalled, and should do
     * nothing if it is true.  If it is false, they should do whatever work
     * they need to do, and set _prepareDataCalled to true.
     */
	public void prepareData(DataValue min, DataValue max, int buffer) {}

    /**
     * Once prepareData has been called, additional calls to prepareData should
     * do nothing until prepareDataReset is called.
     */
    public void prepareDataReset() { mPrepareDataCalled = false; }
    protected boolean mPrepareDataCalled = false;

    public DataIterator getIterator(String variableIds[], DataValue min, DataValue max, int buffer) { return null; }


	public DataValue[] getBounds(String varid) { return null; }


    /**
     * Return the id of this data object's i-th variable (i.e. the
     * data variable whose column number is i).  Return null if there
     * is no i-th variable.
     */
    public String getVariableId(int i) {
        if (i < 0 || i >= mVariables.size()) {
            return null;
        }
        return mVariables.get(i).getId();
    }


	/**
     * Return the column number (index in the variables array) of one
     * of this Data object's variables, given a string containing a
     * variable id.  Returns -1 if there is no variable with the given
     * id in this data object.
	 */
	protected int varIdToColumn(String id) {
        for (int j=0; j<mVariables.size(); ++j) {
            if (id.equals(mVariables.get(j).getId())) {
                return j;
            }
        }
        return -1;
    }

    /**
     * Return one
     * of this Data object's variables, given a string containing a
     * variable id.  Returns null if there is no variable with the given
     * id in this data object.
     */
    public DataVariable varIdToVar(String id) {
        for (int j=0; j<mVariables.size(); ++j) {
            if (id.equals(mVariables.get(j).getId())) {
                return mVariables.get(j);
            }
        }
        return null;
    }
    	
    
    public static Data create(Graph parent, org.multigraph.jaxb.Data dataState) throws DataTypeException {
    	ArrayList<DataVariable> dataVariables = new ArrayList<DataVariable>();
    	for (org.multigraph.jaxb.Variable varState : dataState.getVariables().getVariable()) {
    		dataVariables.add( new DataVariable(varState.getId(), varState.getColumn(), varState.getType(),
    				                            DataValue.create(varState.getType(), varState.getMissingvalue()),
    				                            varState.getMissingop()));
    	}
    	if (dataState.isSetValues()) {
    		ArrayData data = new ArrayData(dataVariables);
    		data.parseText(dataState.getValues());
    		//data.dump();
    		return data;
    	}
    	return null;
    }

}

