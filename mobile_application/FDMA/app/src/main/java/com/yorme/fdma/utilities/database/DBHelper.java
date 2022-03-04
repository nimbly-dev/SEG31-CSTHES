package com.yorme.fdma.utilities.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.yorme.fdma.core.dao.PasswordDao;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.ChangePasswordLog;
import com.yorme.fdma.core.model.ChangePhoneNumberLog;
import com.yorme.fdma.core.model.exceptions.SQLDataNotFound;
import com.yorme.fdma.core.service.Decryptor;
import com.yorme.fdma.core.service.Encryptor;
import com.yorme.fdma.core.service.TokenEncrytor;
import com.yorme.fdma.utilities.PropertiesReader;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

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

    private PropertiesReader propertiesReader;
    private Decryptor decryptor;
    private TokenEncrytor tokenEncrytor;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
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

        for(String sql: statements){
            db.execSQL(sql);
            Log.d("For Loop Log", "1");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean insertData(String time, String date, String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("time", time);
        contentValues.put("date", date);

        db.insert(tableName,null,contentValues);
        return true;
    }

    public ArrayList<ActivationLog> selectAllActivationLogs(String selectAllSqlStmt){
        ArrayList<ActivationLog> activationLogs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectAllSqlStmt, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            ActivationLog activationLog = new ActivationLog(
                    res.getInt(res.getColumnIndex(LOG_COLUMN_ID)),
                    LocalTime.parse(res.getString(res.getColumnIndex(TIME_COLUMN))),
                    LocalDate.parse(res.getString(res.getColumnIndex(DATE_COLUMN)))
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
                    LocalTime.parse(res.getString(res.getColumnIndex(TIME_COLUMN))),
                    LocalDate.parse(res.getString(res.getColumnIndex(DATE_COLUMN)))
            );
            changePasswordLogs.add(changePhoneNumberLog);
            res.moveToNext();
        }
        return changePasswordLogs;
    }

    public boolean isDefaultPasswordExists(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(DBSQL.GET_PASSWORD,null);
        if(res.getCount() <= 0){
            res.close();
            return false;
        }else{
            res.close();
            return true;
        }
    }

    public String getPassword() throws InvalidAlgorithmParameterException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, SQLDataNotFound {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(DBSQL.GET_PASSWORD,null);
        if(res.getCount() <= 0){
            String passwordToBeReturned = TokenEncrytor
                    .encrypt( res.getString(res.getColumnIndex("password")));
            res.close();
            return passwordToBeReturned;
        }else{
            res.close();
            throw new SQLDataNotFound("Failure to retrieve password on database table");
        }
    }


    public boolean updatePassword(String newPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);

        db.update("password", contentValues,"id = 1", new String[] { Integer.toString(1) });
        return true;
    }


    public void flushActivationLogTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DBSQL.FLUSH_ACTIVATION_LOGS_TABLE);
    }

    public void insertDefaultPassword() throws NoSuchAlgorithmException, SQLException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        Log.d("LOG","SETTING CIPHER TEXT FOR DEFAULT PASSWORD");
        String cipherText = TokenEncrytor.encrypt("Admin123");

        contentValues.put("password", cipherText);
        db.insert("app_password",null,contentValues);
    }

//    public boolean isDefaultPasswordExist(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM app_password",null);
////        Log.d("dbhelperbeforeif", "DB HELPER BEFORE IF");
//        if (cursor != null) {
//            cursor.moveToFirst();                       // Always one row returned.
////            Log.d("dbhelperafterfirstif", "DB HELPER FIRST IF");
//            if (cursor.getInt (0) == 0) {               // Zero count means empty table.
////                Log.d("dbhelperaftersecondif", "DB HELPER SECOND IF");
//                cursor.close();
//                return false;
//            }
//        }
//        cursor.close();
//        return true;
//    }
}
