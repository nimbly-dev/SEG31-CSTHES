package com.yorme.fdma.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ChangeKeyPairingLogs {

    int keyPairingLogsID;
    LocalDate keyPairingLogDate;
    LocalTime keyPairingLogTime;

    public ChangeKeyPairingLogs(){

    }

    public ChangeKeyPairingLogs(int keyPairingLogsID, LocalDate keyPairingLogDate, LocalTime keyPairingLogTime) {
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
