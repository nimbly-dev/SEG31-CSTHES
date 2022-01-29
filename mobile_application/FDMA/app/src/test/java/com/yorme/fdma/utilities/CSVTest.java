package com.yorme.fdma.utilities;

import android.content.Context;
import android.content.res.Resources;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.ActivationLogCSVDto;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

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
