package zenryokuservice.lwjgl.Kakeibo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import zenryokuservice.gui.lwjgl.kakeibo.util.CalendarPosit;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	CalendarPosit pos = new CalendarPosit();
    	int res = pos.getStartPoint(true);
    	pos.chkWeek(res, true);
    }
}
