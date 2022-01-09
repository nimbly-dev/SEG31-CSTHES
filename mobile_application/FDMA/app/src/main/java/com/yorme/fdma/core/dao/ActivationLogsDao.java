package com.yorme.fdma.core.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.yorme.fdma.core.database.DBConnection;
import com.yorme.fdma.core.database.DBSQL;
import com.yorme.fdma.core.model.ActivationLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ActivationLogsDao {

    final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a");
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    public void createActivationLogsTable() throws SQLException {
        Connection conn = new DBConnection().connect();
        Statement stmt = conn.createStatement();

        stmt.execute(DBSQL.CREATE_NEW_ACTIVATION_LOGS_TABLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertNewActivationLog(LocalTime time, LocalDate date) throws SQLException {
        Connection conn = new DBConnection().connect();
        Statement stmt = conn.createStatement();

        PreparedStatement pstmt = conn.prepareStatement(DBSQL.INSERT_NEW_ACTIVATION_LOGS);
        pstmt.setString(1, time.format(formatter)); // 0900
        pstmt.setString(2, date.toString()); // 05/01/2021

        pstmt.executeUpdate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<ActivationLog> getAllActivationLogs() throws SQLException {
        Connection conn = new DBConnection().connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(DBSQL.SELECT_ALL_ACTIVATION_LOGS);

        List<ActivationLog> activationLogs = new ArrayList<ActivationLog>();
        while(rs.next()){
            ActivationLog activationLog = new ActivationLog(
                    rs.getInt("id"),
                    LocalDate.parse(rs.getString("date")),
                    LocalTime.parse(rs.getString("time"))
            );
            activationLogs.add(activationLog);
        }
        return activationLogs;
    }

}
