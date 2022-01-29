package com.yorme.fdma.utilities;

import android.content.res.AssetManager;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesTest extends TestCase {

    private Properties properties;

    @Test
    void getProperty(){
        AssetManager assetManager = AppContext.getContext().getAssets();

        InputStream inputStream = assetManager.open(file);
        properties.load(inputStream);
    }
}
