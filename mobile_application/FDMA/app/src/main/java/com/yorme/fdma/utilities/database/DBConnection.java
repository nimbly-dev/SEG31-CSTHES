package com.yorme.fdma.utilities.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Deprecated
public class DBConnection {


    final String URL = "jdbc:sqlite:"+new File("").getAbsolutePath()+"\\src\\main\\assets\\fdma.db";

    public Connection connect() {
        System.out.println("Now creating connection");
        Connection conn = null;
        try {
            System.out.println("Success connect");
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Failed to connect");
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //FOR TESTING PURPOSES
    public void flushTable(String truncateStmt) throws SQLException {
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(truncateStmt);

        pstmt.executeUpdate();
    }
}
