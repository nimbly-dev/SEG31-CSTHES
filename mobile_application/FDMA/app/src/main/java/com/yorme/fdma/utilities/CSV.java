package com.yorme.fdma.utilities;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.ActivationLogCSVDto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CSV {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    public List<ActivationLog> getCSVActivationLogToList(String filePath) throws FileNotFoundException {
        List<ActivationLogCSVDto> beans = new CsvToBeanBuilder<ActivationLogCSVDto>(new FileReader(filePath))
                .withType(ActivationLogCSVDto.class)
                .build()
                .parse();

        List<ActivationLog> activationLogs = new ArrayList<ActivationLog>();

        beans.stream().forEach((bean)->{
            //Convert strings value of CSV to designated variable type
            ActivationLog activationLog = new ActivationLog(
                    Integer.parseInt(bean.getId()), LocalTime.parse(bean.getTime()),
                    LocalDate.parse(bean.getDate())
            );
            //Push the
            activationLogs.add(activationLog);
        });
        return activationLogs;
    }


}
