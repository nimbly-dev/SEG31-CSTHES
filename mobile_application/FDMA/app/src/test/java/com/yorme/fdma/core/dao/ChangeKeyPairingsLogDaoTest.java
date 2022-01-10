package com.yorme.fdma.core.dao;

import com.yorme.fdma.core.database.DBConnection;
import com.yorme.fdma.core.model.ActivationLog;

import junit.framework.TestCase;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChangeKeyPairingsLogDaoTest extends TestCase {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    private ChangeKeyPairingsLogsDao changeKeyPairingsLogsDao;

    @Test
    public void testConnectToDb(){
        Connection conn = new DBConnection().connect();
        assertTrue(conn != null);
    }

    @Test
    public void testCreateInsertAndGetDataOfChangeKeyPairLogs(){
        try {
            changeKeyPairingsLogsDao = new ChangeKeyPairingsLogsDao();

            changeKeyPairingsLogsDao.createChangeKeyPairTable();

            LocalTime testInputTime = LocalTime.now();
            LocalDate testInputDate = LocalDate.now();
            changeKeyPairingsLogsDao.insertNewChangeKeyPairLog(testInputTime,testInputDate);
            List<ActivationLog> activationLogs = changeKeyPairingsLogsDao.getAllChangeKeyPairsLog();

            assertTrue(activationLogs.size() != 0);
            assertEquals(activationLogs.get(0).getLogDate(), testInputDate);
            assertEquals(activationLogs.get(0).getLogTime()
                    .toString(), testInputTime.format(formatter));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
