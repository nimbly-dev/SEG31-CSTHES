package com.yorme.fdma.core.database;

public interface DBSQL {

    //SQL STATEMENTS:
    final static String INSERT_NEW_ACTIVATION_LOGS = "INSERT INTO activation_logs(time,date) VALUES(?,?)";
    final static String SELECT_ALL_ACTIVATION_LOGS = "SELECT * from activation_logs";

    final static String INSERT_NEW_CHANGE_KEY_PAIRING_LOGS = "INSERT INTO change_key_pair_logs(time,date) VALUES (?,?)";
    final static String SELECT_ALL_CHANGE_KEY_PAIR_LOGS = "SELECT * from change_key_pair_logs";


    //CREATE NEW TABLE
    final static String CREATE_NEW_ACTIVATION_LOGS_TABLE = "CREATE TABLE IF NOT EXISTS activation_logs (\n" +
            "   id integer PRIMARY KEY,\n" +
            "   time time NOT NULL,\n" +
            "   date date NOT NULL\n" +
            "                       );";

    final static String CREATE_NEW_CHANGE_KEY_PAIRING_LOGS_TABLE = "CREATE TABLE IF NOT EXISTS change_key_pair_logs (\n" +
            "id integer PRIMARY KEY,\n" +
            "time time NOT NULL,\n" +
            "date date NOT NULL," +
            "                   );";

}
