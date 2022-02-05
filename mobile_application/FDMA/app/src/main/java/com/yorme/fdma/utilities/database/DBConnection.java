package com.yorme.fdma.utilities.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {


    final String URL = "jdbc:sqlite:"+new File("").getAbsolutePath()+"\\src\\main\\assets\\fdma.db";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
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
