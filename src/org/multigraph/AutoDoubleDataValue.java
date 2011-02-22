package org.multigraph;

public class AutoDoubleDataValue extends DoubleDataValue {

    protected boolean isauto;

    public AutoDoubleDataValue(String value) {
        if (value.equals("auto")) {
            isauto = true;
        } else {
            this.value = Double.parseDouble(value);
            isauto = false;
        }
    }

    public String getStringValue() {
        if (isauto) { return "auto"; }
        return super.getStringValue();
    }

}
