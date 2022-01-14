package com.yorme.fdma.core.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.core.model.ActivationLog;

import junit.framework.TestCase;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActivationLogsDaoTest extends TestCase {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    private ActivationLogsDao activationLogsDao;

    @Test
    public void testConnectToDb(){
        Connection conn = new DBConnection().connect();
        assertTrue(conn != null);
    }

    @Test
    public void testCreateInsertAndGetDataOfActivationLogs(){
        try {
            activationLogsDao = new ActivationLogsDao();

            activationLogsDao.createActivationLogsTable();

            LocalTime testInputTime = LocalTime.now();
            LocalDate testInputDate = LocalDate.now();
            activationLogsDao.insertNewActivationLog(testInputTime,testInputDate);
            List<ActivationLog> activationLogs = activationLogsDao.getAllActivationLogs();

            assertTrue(activationLogs.size() != 0);
            assertEquals(activationLogs.get(0).getLogDate(), testInputDate);
            assertEquals(activationLogs.get(0).getLogTime()
                    .toString(), testInputTime.format(formatter));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
