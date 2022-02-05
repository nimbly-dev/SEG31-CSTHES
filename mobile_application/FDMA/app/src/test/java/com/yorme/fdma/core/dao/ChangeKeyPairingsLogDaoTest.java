package com.yorme.fdma.core.dao;

import com.yorme.fdma.utilities.StaticStrings;
import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.utilities.database.DBSQL;

import junit.framework.TestCase;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChangeKeyPairingsLogDaoTest extends TestCase {

    private ChangeKeyPairingsLogsDao changeKeyPairingsLogsDao;
    private DBConnection conn;


    @Test
    public void testCreateInsertAndGetDataOfChangeKeyPairLogs(){
        try {
            //Flush table
            conn = new DBConnection();
            conn.flushTable(DBSQL.FLUSH_CHANGE_KEY_PAIRING_LOGS_TABLE);

            changeKeyPairingsLogsDao = new ChangeKeyPairingsLogsDao();

            changeKeyPairingsLogsDao.createChangeKeyPairTable();

            LocalTime testInputTime = LocalTime.now();
            LocalDate testInputDate = LocalDate.now();
            changeKeyPairingsLogsDao.insertNewChangeKeyPairLog(testInputTime,testInputDate);
            List<ActivationLog> activationLogs = changeKeyPairingsLogsDao.getAllChangeKeyPairsLog();

            assertTrue(activationLogs.size() != 0);
            assertEquals(activationLogs.get(0).getLogDate(), testInputDate);
            assertEquals(activationLogs.get(0).getLogTime()
                    .toString(), testInputTime.format(StaticStrings.HOUR_FORMAT));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
