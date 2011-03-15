package org.multigraph;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.multigraph.datatypes.DataValue;

public class ArrayData extends Data {

    private int mNumRows;
    private int mNumCols;
    private DataValue mValues[][]; // mValue[row][col]
    public DataValue[][] getValues() { return mValues; }

    private static Pattern leadingWSPattern  = Pattern.compile("^\\s+");
    private static Pattern trailingWSPattern = Pattern.compile("\\s+$");
    private static Pattern delimPattern      = Pattern.compile("\\s*,\\s*");

    public ArrayData(ArrayList<DataVariable> variables) {
        super(variables);
        mValues = null;
	}

    public int getNumRows() { return mNumRows; }
    public int getNumCols() { return mNumCols; }

    public DataValue getValue(int row, int col) {
        return mValues[row][col];
    }

    public void dump() {
        System.out.printf("ArrayData dump: %1d rows, %1d cols:\n", mNumRows, mNumCols);
        for (int row=0; row<mNumRows; ++row) {
            System.out.printf("    [");
            for (int col=0; col<mNumCols; ++col) {
                System.out.printf("%s%f", col==0 ? "" : ",", mValues[row][col].getRealValue());
            }
            System.out.printf("]\n");
        }
    }

	//@override
    public DataValue[] getBounds(String varid) {
        int j = varIdToColumn(varid);
        DataValue min = mValues[0][j];
        DataValue max = min;
        for (int i=1; i<mNumRows; ++i) {
            if (mValues[i][j].lt(min)) {
                min = mValues[i][j];
            }
            if (mValues[i][j].gt(max)) {
                max = mValues[i][j];
            }
        }
        return new DataValue[] { min, max };
	}

	public void parseText(String text) throws DataTypeException {
		String lines[] = text.split("\n");
		mNumRows = 0;
		mNumCols = 0;
		for (String line : lines) {
			line = leadingWSPattern.matcher(line).replaceFirst("");
			line = trailingWSPattern.matcher(line).replaceFirst("");
            if (delimPattern.matcher(line).find()) {
                String fields[] = delimPattern.split(line, 1000);
                if (fields.length > 0) {
                    ++mNumRows;
                    if (fields.length > mNumCols) {
                        mNumCols = fields.length;
                    }
                }
            }
		}
		mValues = new DataValue[mNumRows][mNumCols];
		int row = 0;
		for (String line : lines) {
			line = leadingWSPattern.matcher(line).replaceFirst("");
			line = trailingWSPattern.matcher(line).replaceFirst("");
            if (delimPattern.matcher(line).find()) {
                String fields[] = delimPattern.split(line, 1000);
				if (fields.length > 0) {
					int j = 0;
					int col = 0;
					while (j < fields.length) {
						if (fields[j].length() > 0) {
							if (mVariables.get(col) != null) {
								mValues[row][col] = DataValue.create(mVariables
                                                                     .get(col).getType(), fields[j]);
							}
							++col;
						}
						++j;
					}
					++row;
				}
			}
		}
	}
	
    // @override
    public DataIterator getIterator(String variableIds[], DataValue min, DataValue max, int buffer) {

        // if min > max, swap them:
        if (min.gt(max)) {
            DataValue tmp = max;
            max = min;
            min = tmp;
        }

        ArrayList<Integer> columnIndices = new ArrayList<Integer>();
        for (int i=0; i<variableIds.length; ++i) {
            for (int j=0; j<mVariables.size(); ++j) {
                if (variableIds[i].equals(mVariables.get(j).getId())) {
                    columnIndices.add(j);
                    break;
                }
            }
        }
        return new ArrayDataIterator(this, columnIndices, min, max, buffer);
    }

}
