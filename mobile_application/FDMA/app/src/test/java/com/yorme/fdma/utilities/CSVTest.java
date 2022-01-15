package com.yorme.fdma.utilities;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.ActivationLogCSVDto;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVTest extends TestCase {

    @Test
    public void testGetCsvData() {

        CSV csv = new CSV();
        List<ActivationLog> activationLogList = null;
        try {
            activationLogList = csv.getCSVActivationLogToList(StaticStrings.CSV_FILE_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(activationLogList != null);
        assertTrue(activationLogList.size() != 0);
    }

}
