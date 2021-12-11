package com.yorme.fdma.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivationLogs {

    int logID;
    LocalDate logDate;
    LocalTime logTime;

    public ActivationLogs(){

    }

    public ActivationLogs(int logID, LocalDate logDate, LocalTime logTime) {
        this.logID = logID;
        this.logDate = logDate;
        this.logTime = logTime;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
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
