package com.yorme.fdma.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.core.service.Decryptor;
import com.yorme.fdma.utilities.PropertiesReader;
import com.yorme.fdma.utilities.database.DBHelper;
import com.yorme.fdma.utilities.database.DBSQL;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LandingActivity extends AppCompatActivity {
    private final static String RESET_KEY= "FDMAPPf4gvl6";

    private  DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);

        Button btn_password_landingpage = (Button) findViewById(R.id.btn_password_landingpage);
        EditText inputPassword = (EditText) findViewById(R.id.enter_password_landingpage);

        //INSERT DEFAULT PASSWORD IF DEFAULT PASSWORD IS NOT SET
        dbHelper = new DBHelper(this);
        boolean getPassword = dbHelper.isDefaultPasswordExists();
        if(!getPassword){
            Log.d("BEFOR DEFAULT HOTDOG", "BEFORE INSERT DEFAULT PASSWORD IF NOT EXIST");
            dbHelper.insertDefaultPassword();
        }

        String passwordDB = dbHelper.getPassword();

        Log.d("PAAAASWOOORD HAKDOG", "THIS YOUR PASSWORD HOTDOG: " + passwordDB);
        Ardutooth mArdutooth = Ardutooth.getInstance(this);
        mArdutooth.setConnection();

        btn_password_landingpage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    Log.d("Before validator", "HOTDOG BEFORE VALIDATOR");
                    if(inputPassword.getText().toString().equals(passwordDB)){
                        goToMainActivity();
                    } else if(passwordDB.equals(RESET_KEY)){
                        hardResetIfTextIsEntered(inputPassword.getText().toString());
                        recreate();
                    } else {
                        Toast.makeText(LandingActivity.this, "INCORRECT PASSWORD HOTDOG", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void goToMainActivity() {
        Intent switchActivityIntent = new Intent(LandingActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void hardResetIfTextIsEntered(String enteredText){
        if(StringUtils.equals(enteredText,RESET_KEY)){
            dbHelper = new DBHelper(this);

            dbHelper.flushTable(DBSQL.FLUSH_PASSWORD_TABLE);
            dbHelper.flushTable(DBSQL.FLUSH_CHANGE_PASSWORD_LOG_TABLE);
            dbHelper.flushTable(DBSQL.FLUSH_CHANGE_PHONE_NUMBER_LOG_TABLE);
        }
    }

}