package com.yorme.fdma.utilities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;

//USED FOR TESTING ONLY
@RequiresApi(api = Build.VERSION_CODES.O)
public interface StaticStrings {

//    final static String CSV_FILE_PATH = "C:\\Users\\nimbl\\Desktop" +
//            "\\dev-folder\\SEG31-CSTHES\\mobile_application" +
//            "\\FDMA\\app\\src\\main\\assets\\activation-logs-test.csv";

    final static DateTimeFormatter HOUR_FORMAT = DateTimeFormatter.ofPattern("H:mm");

}
