package com.yorme.fdma.core.dao;


import com.yorme.fdma.utilities.StaticStrings;
import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.utilities.database.DBSQL;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ActivationLogsDaoTest extends TestCase {

    private ActivationLogsDao activationLogsDao;
    private DBConnection conn;

    @Test
    public void testCreateInsertAndGetDataOfActivationLogs(){
        try {
            //Flush table
            conn = new DBConnection();
            conn.flushTable(DBSQL.FLUSH_ACTIVATION_LOGS_TABLE);

            activationLogsDao = new ActivationLogsDao();

            activationLogsDao.createActivationLogsTable();

            LocalTime testInputTime = LocalTime.now();
            LocalDate testInputDate = LocalDate.now();
            activationLogsDao.insertNewActivationLog(testInputTime,testInputDate);
            List<ActivationLog> activationLogs = activationLogsDao.getAllActivationLogs();

            assertTrue(activationLogs.size() != 0);
            assertEquals(activationLogs.get(0).getLogDate(), testInputDate);
            assertEquals(activationLogs.get(0).getLogTime()
                    .toString(), testInputTime.format(StaticStrings.HOUR_FORMAT));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
