import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;
import org.multigraph.ArrayData;
import org.multigraph.DataIterator;
import org.multigraph.DataVariable;
import org.multigraph.datatypes.DataType;
import org.multigraph.datatypes.DataValue;
import org.multigraph.datatypes.datetime.CalendarField;
import org.multigraph.datatypes.datetime.DatetimeValue;
import org.multigraph.datatypes.datetime.DatetimeFormatter;
import org.multigraph.datatypes.datetime.DatetimeValueCalendar;

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
    
    private void checkDate(String format, String s) {
		DatetimeValue d = new DatetimeValue(s);
		DatetimeFormatter f = new DatetimeFormatter(format);
		String fs = f.format(d);
		assertEquals(s, fs);
    }
    
    @Test public void testTurboDate_1() {
    	try {
            DatetimeValue dtv = new DatetimeValue("2011" + "03" + "31" + "13" + "00");
            DatetimeValueCalendar td = new DatetimeValueCalendar(dtv);
            assertEquals(td.getYear(),   2011);
            assertEquals(td.getMonth(),  3);
            assertEquals(td.getDay(),    31);
            assertEquals(td.getHour(),   13);
            assertEquals(td.getMinute(), 0);

            td.add(CalendarField.YEAR, 1);
            assertEquals(td.getYear(),   2012);
            assertEquals(td.getMonth(),  3);
            assertEquals(td.getDay(),    31);
            assertEquals(td.getHour(),   13);
            assertEquals(td.getMinute(), 0);
            
            td.add(CalendarField.YEAR, -1);
            assertEquals(td.getYear(),   2011);
            assertEquals(td.getMonth(),  3);
            assertEquals(td.getDay(),    31);
            assertEquals(td.getHour(),   13);
            assertEquals(td.getMinute(), 0);
            
            td.add(CalendarField.DAY, 1);
            assertEquals(td.getYear(),   2011);
            assertEquals(td.getMonth(),  4);
            assertEquals(td.getDay(),    1);
            assertEquals(td.getHour(),   13);
            assertEquals(td.getMinute(), 0);
            
            td.add(CalendarField.DAY, -1);
            assertEquals(td.getYear(),   2011);
            assertEquals(td.getMonth(),  3);
            assertEquals(td.getDay(),    31);
            assertEquals(td.getHour(),   13);
            assertEquals(td.getMinute(), 0);
            
            
    	} catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test public void testDataValue_1() {
    	try {
    		checkDate("%Y%M%D%H", "2011030100");
    		checkDate("%Y%M%D%H%i", "201103010002");
    		checkDate("%Y%M", "201103");
    	} catch (Exception e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test public void testDatetime_1() {
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
    		assertEquals("position 0,0: ", v[0].getRealValue(), 10.0, 0.0001);
    		assertEquals("position 0,1: ", v[1].getRealValue(), 11.0, 0.0001);
    		if (!di.hasNext()) {
    			Assert.fail("di has no second row");
    		}
    		v = di.next();
    		assertEquals("position 0,0: ", v[0].getRealValue(), 12.0, 0.0001);
    		assertEquals("position 0,1: ", v[1].getRealValue(), 13.0, 0.0001);
    		if (di.hasNext()) {
    			Assert.fail("di has a next when it shouldn't");
    		}
            } catch (Exception e) {
            	Assert.fail(e.toString());
            }
        }    
    
    

}
