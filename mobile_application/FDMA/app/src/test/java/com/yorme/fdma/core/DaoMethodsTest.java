package com.yorme.fdma.core;


import com.yorme.fdma.core.dao.ActivationLogsDaoTest;
import com.yorme.fdma.core.dao.ChangeKeyPairingsLogDaoTest;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class DaoMethodsTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DaoMethodsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(ActivationLogsDaoTest.class);
        suite.addTestSuite(ChangeKeyPairingsLogDaoTest.class);
        return suite;
    }
}