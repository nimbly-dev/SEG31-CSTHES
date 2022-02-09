package com.yorme.fdma.core.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.utilities.database.DBSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePhoneNumberLogsDao {
//
//    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
//
//    public void createChangePhoneNumberTable() throws SQLException {
//        Connection conn = new DBConnection().connect();
//        Statement stmt = conn.createStatement();
//
//        stmt.execute(DBSQL.CREATE_NEW_CHANGE_PHONE_NUMBER_LOGS_TABLE);
//    }
//
//    public void insertNewChangePhoneNumberLog(LocalTime time, LocalDate date) throws SQLException {
//        Connection conn = new DBConnection().connect();
//        Statement stmt = conn.createStatement();
//
//        PreparedStatement pstmt = conn.prepareStatement(DBSQL.INSERT_NEW_CHANGE_PHONE_NUMBER_LOGS);
//        pstmt.setString(1, time.format(formatter)); // 0900
//        pstmt.setString(2, date.toString()); // 05/01/2021
//
//        pstmt.executeUpdate();
//    }
//
//    public List<ActivationLog> getAllChangePasswordLog() throws SQLException {
//        Connection conn = new DBConnection().connect();
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery(DBSQL.SELECT_ALL_CHANGE_PASSWORD_PAIR_LOGS);
//
//        List<ActivationLog> activationLogs = new ArrayList<ActivationLog>();
//        while(rs.next()){
//            ActivationLog activationLog = new ActivationLog(
//                    rs.getInt("id"),
//                    LocalTime.parse(rs.getString("time")),
//                    LocalDate.parse(rs.getString("date"))
//            );
//            activationLogs.add(activationLog);
//        }
//        return activationLogs;
//    }
}
