package com.yorme.fdma.core.dao;

import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.utilities.database.DBSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasswordDao {

    public void createPasswordDao() throws SQLException {
        Connection conn = new DBConnection().connect();
        Statement stmt = conn.createStatement();

        stmt.execute(DBSQL.CREATE_NEW_PASSWORD_TABLE);
    }

    public void insertPassword(String password)throws SQLException{
        Connection conn = new DBConnection().connect();

        PreparedStatement pstmt = conn.prepareStatement(DBSQL.INSERT_PASSWORD);
        pstmt.setString(1,password);

        pstmt.executeUpdate();
    }

    public String getPassword()throws SQLException{
        Connection conn = new DBConnection().connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(DBSQL.GET_PASSWORD + 1); //Get Password with index of 1

        return rs.getString("password");
    }
}
