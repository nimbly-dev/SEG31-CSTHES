package com.yorme.fdma.core.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    final String URL = "jdbc:sqlite:"+new File("").getAbsolutePath()+"\\src\\main\\res\\data\\fdma.db";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
