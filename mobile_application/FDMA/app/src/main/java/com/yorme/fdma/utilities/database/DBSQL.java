package com.yorme.fdma.utilities.database;

public interface DBSQL {

    //SQL STATEMENTS
    String INSERT_NEW_ACTIVATION_LOGS = "INSERT INTO activation_logs(time,date) VALUES(?,?)";
    String SELECT_ALL_ACTIVATION_LOGS = "SELECT * from activation_logs";

    String INSERT_NEW_CHANGE_KEY_PAIRING_LOGS = "INSERT INTO change_key_pair_logs(time,date) VALUES (?,?)";
    String SELECT_ALL_CHANGE_KEY_PAIR_LOGS = "SELECT * from change_key_pair_logs";

    String INSERT_NEW_CHANGE_PASSWORD_LOGS = "INSERT INTO change_key_pair_logs(time,date) VALUES (?,?)";
    String SELECT_ALL_CHANGE_PASSWORD_PAIR_LOGS = "SELECT * from change_key_pair_logs";

    String INSERT_NEW_CHANGE_PHONE_NUMBER_LOGS = "INSERT INTO change_key_pair_logs(time,date) VALUES (?,?)";
    String SELECT_ALL_CHANGE_PHONE_NUMBER_LOGS = "SELECT * from change_key_pair_logs";

    String INSERT_PASSWORD = "INSERT INTO app_password(password) VALUES (?)";
    String GET_PASSWORD = "SELECT * FROM app_password WHERE id = 1";
    String UPDATE_PASSWORD = "UPDATE app_password SET password = ? WHERE id = ?";

    //CREATE NEW TABLE
    String CREATE_NEW_ACTIVATION_LOGS_TABLE = "CREATE TABLE IF NOT EXISTS activation_logs (\n" +
            "   id integer PRIMARY KEY,\n" +
            "   time time NOT NULL,\n" +
            "   date date NOT NULL\n" +
            "                       );";

    String CREATE_NEW_CHANGE_KEY_PAIRING_LOGS_TABLE = "CREATE TABLE IF NOT EXISTS change_key_pair_logs (\n" +
            "id integer PRIMARY KEY,\n" +
            "time time NOT NULL,\n" +
            "date date NOT NULL" +
            "                   );";

    String CREATE_NEW_CHANGE_PASSWORD_LOGS_TABLE = "CREATE TABLE IF NOT EXISTS change_password_logs (\n" +
            "id integer PRIMARY KEY,\n" +
            "time time NOT NULL,\n" +
            "date date NOT NULL" +
            "                   );";

    String CREATE_NEW_PASSWORD_TABLE = "CREATE TABLE IF NOT EXISTS app_password (\n" +
            "id integer PRIMARY KEY, \n" +
            "password text NOT NULL" +
            ");";

    String CREATE_NEW_CHANGE_PASSWORD_TABLE =  "CREATE TABLE IF NOT EXISTS change_phone_number_logs (\n" +
            "id integer PRIMARY KEY,\n" +
            "time time NOT NULL,\n" +
            "date date NOT NULL" +
            "                   );";


    //FLUSH DATA ON TABLES
    String FLUSH_ACTIVATION_LOGS_TABLE = "DELETE FROM activation_logs";

    String FLUSH_CHANGE_KEY_PAIRING_LOGS_TABLE = "DELETE FROM change_key_pair_logs";

    String FLUSH_PASSWORD_TABLE = "DELETE FROM app_password";

    String FLUSH_CHANGE_PASSWORD_LOG_TABLE = "DELETE FROM change_password_logs";
}
