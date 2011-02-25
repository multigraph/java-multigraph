import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;
import org.multigraph.ArrayData;
import org.multigraph.DataIterator;
import org.multigraph.DataValue;
import org.multigraph.DataVariable;
import org.multigraph.DataType;

public class TestSuite {

    public static junit.framework.Test suite() {
         return new JUnit4TestAdapter(TestSuite.class);
    }

    private static String join(List objects, String glue) {
        StringBuffer sb = new StringBuffer();
	boolean first = true;
	for (Object o : objects) {
            if (!first) { sb.append(glue); }
            first = false;
            sb.append(o.toString());
	}
        return sb.toString();
    }
    
    @Test public void test1() {
    	try {
    		ArrayList<DataVariable> vars = new ArrayList<DataVariable>();
    		vars.add(new DataVariable("x", 0, DataType.NUMBER));
    		vars.add(new DataVariable("y", 1, DataType.NUMBER));
    		ArrayData data = new ArrayData(vars);
    		data.parseText("10,11\n12,13");
    		DataIterator di = data.getIterator(new String[] { "x", "y" },
    										   DataValue.create(DataType.NUMBER, -100),
    										   DataValue.create(DataType.NUMBER,  100),
    										   0);
    		if (!di.hasNext()) {
    			Assert.fail("di has no first row");
    		}
    		DataValue v[] = di.next();
    		assertEquals("position 0,0: ", v[0].getDoubleValue(), 10.0, 0.0001);
    		assertEquals("position 0,1: ", v[1].getDoubleValue(), 11.0, 0.0001);
    		if (!di.hasNext()) {
    			Assert.fail("di has no second row");
    		}
    		v = di.next();
    		assertEquals("position 0,0: ", v[0].getDoubleValue(), 12.0, 0.0001);
    		assertEquals("position 0,1: ", v[1].getDoubleValue(), 13.0, 0.0001);
    		if (di.hasNext()) {
    			Assert.fail("di has a next when it shouldn't");
    		}
            } catch (Exception e) {
            	Assert.fail(e.toString());
            }
        }

}
