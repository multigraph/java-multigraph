package org.multigraph;

import java.util.ArrayList;

import org.multigraph.datatypes.DataValue;

public class ArrayDataIterator extends DataIterator {

    private ArrayList<Integer> mColumnIndices;
    private ArrayData mData;
    private DataValue mDataMin;
    private DataValue mDataMax;
    private int mIndexMin;
    private int mIndexMax;
    private int mIndex;
                
                
    public ArrayDataIterator(ArrayData data, ArrayList<Integer> columnIndices,
                             DataValue min, DataValue max, int buffer)
    {
        mColumnIndices  = columnIndices;
        mData           = data;
        mDataMin        = min;
        mDataMax        = max;
        mIndexMin       = 0;

        while ((mIndexMin < mData.getNumRows())
               &&
               (mData.getValue(mIndexMin,0).lt(mDataMin))) {
            ++mIndexMin;
        }

        if ((mIndexMin >= mData.getNumRows()) || (mData.getValue(mIndexMin,0).gt(mDataMax))) {
            mIndexMax = -1;
        } else {
            mIndexMax = mIndexMin;
            while ((mIndexMax < mData.getNumRows()-1)
                   &&
                   (mData.getValue(mIndexMax+1,0).le(mDataMax))) {
                ++mIndexMax;
            }
        }

        mIndexMin -= buffer;
        mIndexMax += buffer;
        if (mIndexMin < 0) { mIndexMin = 0; }
        if (mIndexMax >= mData.getNumRows()) { mIndexMax = mData.getNumRows()-1; }

        mIndex = mIndexMin;
    }
                        
    //@override
    public boolean hasNext() {
        return mIndex <= mIndexMax;
    }
    //@override
    public DataValue[] next() {
        if (mIndex > mIndexMax) { return null; }
        ArrayList<DataValue> vals = new ArrayList<DataValue>();
        for (int i=0; i<mColumnIndices.size(); ++i) {
            vals.add(mData.getValue(mIndex,mColumnIndices.get(i)));
        }
        ++mIndex;
        return vals.toArray(new DataValue[0]);
    }


}
