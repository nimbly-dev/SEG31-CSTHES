package com.yorme.fdma.core.model;

public class ChangePhoneNumberLog {
    int id;
    String logDate;
    String logTime;

    public ChangePhoneNumberLog(){

    }

    public ChangePhoneNumberLog(int id, String logTime,String logDate) {
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
