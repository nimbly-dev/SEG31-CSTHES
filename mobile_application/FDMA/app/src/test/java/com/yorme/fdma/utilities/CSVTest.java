package com.yorme.fdma.utilities;

import com.opencsv.CSVReader;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVTest extends TestCase {

    //Utility for testing
    public String readAllExample() throws Exception {
//        Reader reader = Files.newBufferedReader(Paths.get(
//                ClassLoader.getSystemResource("data/activation-logs-test.csv").toURI()));
        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\nimbl\\Desktop" +
                        "\\dev-folder\\SEG31-CSTHES\\mobile_application" +
                        "\\FDMA\\app\\src\\main\\res\\data\\activation-logs-test.csv"));
        System.out.println(reader);
        return CSV.oneByOne(reader).toString();
    }


    @Test
    public void testGetCsvData() throws Exception {
        CSVReader reader = null;
        reader = new CSVReader(new FileReader("C:\\Users\\nimbl\\Desktop" +
                "\\dev-folder\\SEG31-CSTHES\\mobile_application" +
                "\\FDMA\\app\\src\\main\\res\\data\\activation-logs-test.csv"));
        String [] nextLine;

        while ((nextLine = reader.readNext()) != null)
        {
            for(String token : nextLine)
            {
                System.out.print(token);
            }
            System.out.print("\n");
        }
    }

}
