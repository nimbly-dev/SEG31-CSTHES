package com.yorme.fdma.core;


import com.yorme.fdma.core.dao.PasswordDaoTest;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class DaoTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DaoTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(PasswordDaoTest.class);
        return suite;
    }
}