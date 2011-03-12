package org.multigraph;

import java.util.ArrayList;

import org.multigraph.datatypes.DataValue;

public abstract class DataIterator {
    public DataIterator() {}
    public boolean hasNext() { return false; }
    public DataValue[] next() { return null; }
}
