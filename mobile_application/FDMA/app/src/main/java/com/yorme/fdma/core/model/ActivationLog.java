package com.yorme.fdma.core.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivationLog{

    int logID;
    String logDate;
    String logTime;

    public ActivationLog(){

    }

    public ActivationLog(int logID, String logTime, String logDate) {
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

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }
}
