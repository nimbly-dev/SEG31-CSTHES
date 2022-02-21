package com.yorme.fdma.utilities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;

//USED FOR TESTING ONLY
@RequiresApi(api = Build.VERSION_CODES.O)
public interface StaticStrings {

    DateTimeFormatter HOUR_FORMAT = DateTimeFormatter.ofPattern("H:mm");

}
