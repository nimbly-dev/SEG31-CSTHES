package com.yorme.fdma.core.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ChangeKeyPairingLog {

    int keyPairingLogsID;
    LocalDate keyPairingLogDate;
    LocalTime keyPairingLogTime;

    public ChangeKeyPairingLog(){

    }

    public ChangeKeyPairingLog(int keyPairingLogsID, LocalDate keyPairingLogDate, LocalTime keyPairingLogTime) {
        this.keyPairingLogsID = keyPairingLogsID;
        this.keyPairingLogDate = keyPairingLogDate;
        this.keyPairingLogTime = keyPairingLogTime;
    }

    public int getKeyPairingLogsID() {
        return keyPairingLogsID;
    }

    public void setKeyPairingLogsID(int keyPairingLogsID) {
        this.keyPairingLogsID = keyPairingLogsID;
    }

    public LocalDate getKeyPairingLogDate() {
        return keyPairingLogDate;
    }

    public void setKeyPairingLogDate(LocalDate keyPairingLogDate) {
        this.keyPairingLogDate = keyPairingLogDate;
    }

    public LocalTime getKeyPairingLogTime() {
        return keyPairingLogTime;
    }

    public void setKeyPairingLogTime(LocalTime keyPairingLogTime) {
        this.keyPairingLogTime = keyPairingLogTime;
    }
}
