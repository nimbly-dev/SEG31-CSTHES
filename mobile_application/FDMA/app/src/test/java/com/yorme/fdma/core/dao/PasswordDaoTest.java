package com.yorme.fdma.core.dao;

import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.utilities.StaticStrings;
import com.yorme.fdma.utilities.database.DBConnection;

import junit.framework.TestCase;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PasswordDaoTest extends TestCase {
    private PasswordDao passwordDao;

    @Test
    public void testConnectToDb(){
        Connection conn = new DBConnection().connect();
        assertTrue(conn != null);
    }


}
