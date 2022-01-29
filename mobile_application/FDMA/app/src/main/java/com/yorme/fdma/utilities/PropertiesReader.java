package com.yorme.fdma.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.Properties;

public class PropertiesReader  {

    public Properties getApplicationProperty(){
        String assetPath = new File("").getAbsolutePath()+"\\src\\main\\assets\\application.properties";
        Properties prop = null;
        try (
                InputStream input = new FileInputStream(
                        assetPath)) {

            prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
//            System.out.println(prop.getProperty("app.test.csv.path"));

            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }


}
