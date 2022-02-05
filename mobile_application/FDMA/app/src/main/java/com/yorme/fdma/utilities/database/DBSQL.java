package com.yorme.fdma.utilities.database;

public interface DBSQL {

    //SQL STATEMENTS
    final static String INSERT_NEW_ACTIVATION_LOGS = "INSERT INTO activation_logs(time,date) VALUES(?,?)";
    final static String SELECT_ALL_ACTIVATION_LOGS = "SELECT * from activation_logs";

    final static String INSERT_NEW_CHANGE_KEY_PAIRING_LOGS = "INSERT INTO change_key_pair_logs(time,date) VALUES (?,?)";
    final static String SELECT_ALL_CHANGE_KEY_PAIR_LOGS = "SELECT * from change_key_pair_logs";

    final static String INSERT_PASSWORD = "INSERT INTO app_password(password) VALUES (?)";
    final static String GET_PASSWORD = "SELECT * FROM app_password WHERE id = 1";
    final static String UPDATE_PASSWORD = "UPDATE app_password SET password = ? WHERE id = ?";

    //CREATE NEW TABLE
    final static String CREATE_NEW_ACTIVATION_LOGS_TABLE = "CREATE TABLE IF NOT EXISTS activation_logs (\n" +
            "   id integer PRIMARY KEY,\n" +
            "   time time NOT NULL,\n" +
            "   date date NOT NULL\n" +
            "                       );";

    final static String CREATE_NEW_CHANGE_KEY_PAIRING_LOGS_TABLE = "CREATE TABLE IF NOT EXISTS change_key_pair_logs (\n" +
            "id integer PRIMARY KEY,\n" +
            "time time NOT NULL,\n" +
            "date date NOT NULL" +
            "                   );";

    final static String CREATE_NEW_PASSWORD_TABLE = "CREATE TABLE IF NOT EXISTS app_password (\n" +
            "id integer PRIMARY KEY, \n" +
            "password text NOT NULL" +
            ");";

    //FLUSH DATA ON TABLES
    final static String FLUSH_ACTIVATION_LOGS_TABLE = "DELETE FROM activation_logs";

    final static String FLUSH_CHANGE_KEY_PAIRING_LOGS_TABLE = "DELETE FROM change_key_pair_logs";

    final static String FLUSH_PASSWORD_TABLE = "DELETE FROM app_password";
}
