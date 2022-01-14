package com.yorme.fdma.utilities;

import java.io.InputStream;
import java.net.URL;

//USED FOR TESTING ONLY
public interface FilePaths {

    final InputStream CSV_FILE_PATH = FilePaths.class.getResourceAsStream("res/data/activation-logs-test.csv");
}
