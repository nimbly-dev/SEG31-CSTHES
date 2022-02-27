package com.yorme.fdma.core.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ChangePhoneNumberLog {
    int id;
    LocalDate logDate;
    LocalTime logTime;

    public ChangePhoneNumberLog(){

    }

    public ChangePhoneNumberLog(int id, LocalTime logTime,LocalDate logDate) {
        this.id = id;
        this.logDate = logDate;
        this.logTime = logTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public LocalTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalTime logTime) {
        this.logTime = logTime;
    }
}
