package com.yorme.fdma.utilities.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.ChangePasswordLog;
import com.yorme.fdma.core.model.ChangePhoneNumberLog;
import com.yorme.fdma.core.service.TokenEncrytor;
import com.yorme.fdma.utilities.PropertiesReader;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "fdma.db";

    //FOR LOGS
    public static final String LOG_COLUMN_ID = "id";
    public static final String TIME_COLUMN = "time";
    public static final String DATE_COLUMN = "date";

    //FOR PASSWORD
    private HashMap hp;
    private DBHelper dbHelper;

    private TokenEncrytor tokenEncrytor;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("For Loop Log", "1");
        String[] statements = new String[]{
                DBSQL.CREATE_NEW_ACTIVATION_LOGS_TABLE,
                DBSQL.CREATE_NEW_CHANGE_PASSWORD_LOGS_TABLE,
                DBSQL.CREATE_NEW_PASSWORD_TABLE,
                DBSQL.CREATE_NEW_CHANGE_PHONE_NUMBER_TABLE
        };

        for (String sql : statements) {
            db.execSQL(sql);
            Log.d("For Loop Log", "1");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String time, String date, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("time", time);
        contentValues.put("date", date);

        db.insert(tableName, null, contentValues);
        return true;
    }

    public ArrayList<ActivationLog> selectAllActivationLogs(String selectAllSqlStmt) {
        ArrayList<ActivationLog> activationLogs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(selectAllSqlStmt, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            ActivationLog activationLog = new ActivationLog(
                    res.getInt(res.getColumnIndex(LOG_COLUMN_ID)),
                    res.getString(res.getColumnIndex(TIME_COLUMN)),
                    res.getString(res.getColumnIndex(DATE_COLUMN))
            );
            activationLogs.add(activationLog);
            res.moveToNext();
        }
        return activationLogs;
    }

    public ArrayList<ChangePasswordLog> selectAllChangePasswordLogs(String selectAllSqlStmt) {
        ArrayList<ChangePasswordLog> changePasswordLogs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(selectAllSqlStmt, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            ChangePasswordLog changePasswordLog = new ChangePasswordLog(
                    res.getInt(res.getColumnIndex(LOG_COLUMN_ID)),
                    LocalTime.parse(res.getString(res.getColumnIndex(TIME_COLUMN))),
                    LocalDate.parse(res.getString(res.getColumnIndex(DATE_COLUMN)))
            );
            changePasswordLogs.add(changePasswordLog);
            res.moveToNext();
        }
        return changePasswordLogs;
    }

    public ArrayList<ChangePhoneNumberLog> selectAllChangePhoneNumberLogs(String selectAllSqlStmt) {
        ArrayList<ChangePhoneNumberLog> changePasswordLogs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(selectAllSqlStmt, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            ChangePhoneNumberLog changePhoneNumberLog = new ChangePhoneNumberLog(
                    res.getInt(res.getColumnIndex(LOG_COLUMN_ID)),
                    res.getString(res.getColumnIndex(TIME_COLUMN)),
                    res.getString(res.getColumnIndex(DATE_COLUMN))
            );
            Log.d("DB data", "Phone number logs: " + res.getString(res.getColumnIndex(TIME_COLUMN)) + " " + res.getString(res.getColumnIndex(DATE_COLUMN)));
            changePasswordLogs.add(changePhoneNumberLog);
            res.moveToNext();
        }
        return changePasswordLogs;
    }

    public boolean isDefaultPasswordExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(DBSQL.GET_PASSWORD, null);
        if (res.getCount() <= 0) {
            res.close();
            return false;
        } else {
            res.close();
            return true;
        }
    }

    public String getPassword() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery(DBSQL.GET_PASSWORD, null);
            String passwordToReturn = "";
            if (res.moveToFirst()) {
                do {
                    // Passing values
                    passwordToReturn = res.getString(0);
                    // Do something Here with values
                } while (res.moveToNext());
            }
            passwordToReturn = TokenEncrytor.decrypt(passwordToReturn);
            res.close();
            db.close();
            return passwordToReturn;
        } catch (InvalidAlgorithmParameterException
                | NoSuchPaddingException | NoSuchAlgorithmException
                | UnsupportedEncodingException | IllegalBlockSizeException
                | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean updatePassword(String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);
        db.update("app_password", contentValues, "id = 1", null);
        return true;
    }


    public void flushTable(String tableToFlush) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(tableToFlush);
    }

    public void insertDefaultPassword() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues contentValues = new ContentValues();

            Log.d("LOG", "SETTING CIPHER TEXT FOR DEFAULT PASSWORD");
            String cipherText = TokenEncrytor.encrypt("Admin123");

            contentValues.put("password", cipherText);
            db.insert("app_password", null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
