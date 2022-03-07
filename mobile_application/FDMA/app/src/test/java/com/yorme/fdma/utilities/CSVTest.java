package com.yorme.fdma.utilities;

import com.yorme.fdma.core.model.ActivationLog;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;

@Deprecated
public class CSVTest extends TestCase {

    private Properties properties;
    private PropertiesReader propertiesReader;

    @Test
    public void testGetCsvData() {
        properties = new PropertiesReader().getApplicationProperty();
        CSV csv = new CSV();
        List<ActivationLog> activationLogList = null;
        try {
            activationLogList = csv.getCSVActivationLogToList(properties.getProperty("app.test.csv.path"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        assertTrue(activationLogList != null);
        assertTrue(activationLogList.size() != 0);
    }


}
