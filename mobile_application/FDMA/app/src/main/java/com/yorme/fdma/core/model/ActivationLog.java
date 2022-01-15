package com.yorme.fdma.core.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivationLog {

    int logID;
    LocalDate logDate;
    LocalTime logTime;

    public ActivationLog(){

    }

    public ActivationLog(int logID, LocalTime logTime, LocalDate logDate) {
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
