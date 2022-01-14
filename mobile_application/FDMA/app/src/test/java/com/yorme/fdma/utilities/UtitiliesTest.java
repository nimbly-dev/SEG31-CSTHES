package com.yorme.fdma.utilities;

import com.yorme.fdma.core.dao.ActivationLogsDaoTest;
import com.yorme.fdma.core.dao.ChangeKeyPairingsLogDaoTest;

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
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CSVTest.class);
        return suite;
    }

}
