package com.yorme.fdma.core.database;

public interface DBSQL {

    //SQL STATEMENTS:
    final static String INSERT_NEW_ACTIVATION_LOGS = "INSERT INTO activation_logs(time,date) VALUES(?,?)";
    final static String SELECT_ALL_ACTIVATION_LOGS = "SELECT * from activation_logs";

    //CREATE NEW TABLE
    final static String CREATE_NEW_ACTIVATION_LOGS_TABLE = "CREATE TABLE IF NOT EXISTS activation_logs (\n" +
            "   id integer PRIMARY KEY,\n" +
            "   time time NOT NULL,\n" +
            "   date date NOT NULL\n" +
            "                       );";
}
