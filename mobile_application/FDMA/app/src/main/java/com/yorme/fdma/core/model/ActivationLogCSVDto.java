package com.yorme.fdma.core.model;

import com.opencsv.bean.CsvBindByName;

@Deprecated
public class ActivationLogCSVDto {

    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "time")
    private String time;

    @CsvBindByName(column = "date")
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
