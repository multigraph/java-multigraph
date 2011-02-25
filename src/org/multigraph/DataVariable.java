package org.multigraph;

public class DataVariable {

	private String mId;
	public String getId() { return mId; }
		
	private int mColumn;
	public int getColumn() { return mColumn; }
		
	private DataType mType;
	public DataType getType() { return mType; }

    private static enum MissingOp { NONE, LT, LE, EQ, GE, GT };
    private MissingOp mMissingOp = MissingOp.NONE;
	private DataValue mMissingValue;

	public DataVariable(String id, int column, DataType type, DataValue missingValue) {
        this(id, column, type, missingValue, null);
    }
	public DataVariable(String id, int column, DataType type) {
        this(id, column, type, null, null);
    }

	public DataVariable(String id, int column, DataType type, DataValue missingValue, String missingOpString)
	{
	  this.mId           = id;
	  this.mColumn       = column;
	  this.mType         = type;
	  this.mMissingValue = missingValue;
	  this.mMissingOp    = parseMissingOpString(missingOpString);
	}
	
    public boolean isMissing(DataValue x) {
        switch (mMissingOp) {
        case LT:
            return x.lt(mMissingValue);
        case LE:
            return x.le(mMissingValue);
        case EQ:
            return x.eq(mMissingValue);
        case GE:
            return x.ge(mMissingValue);
        case GT:
            return x.gt(mMissingValue);
        }
        return false;
    }

    private MissingOp parseMissingOpString(String missingOpString) {
        if (missingOpString==null) { return MissingOp.NONE; }
        missingOpString = missingOpString.toLowerCase();
        if (missingOpString.equals("lt")) {
            return MissingOp.LT;
        }
        else if (missingOpString.equals("le")) {
            return MissingOp.LE;
        }
        else if (missingOpString.equals("eq")) {
            return MissingOp.EQ;
        }
        else if (missingOpString.equals("ge")) {
            return MissingOp.GE;
        }
        else if (missingOpString.equals("gt")) {
            return MissingOp.GT;
        }
        return MissingOp.NONE;
    }	
	
	
  }

