package com.yorme.fdma.utilities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UtitiliesTest extends TestCase {

    public UtitiliesTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    //TODO - ADD TEST CASE FOR DBHELPER
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CSVTest.class);
//        suite.addTestSuite(PropertiesTest.class);
        return suite;
    }

}
