package com.yorme.fdma.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yorme.fdma.R;
import com.yorme.fdma.utilities.database.DBHelper;
import com.yorme.fdma.utilities.database.DBSQL;

import org.apache.commons.lang3.StringUtils;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LandingActivity extends AppCompatActivity {
    private final static String RESET_KEY= "FDMAPPf4gvl6";

    TextView landingActivityMessage;
    Button btnPasswordLandingPage;
    EditText inputPassword;

    private  DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);

        btnPasswordLandingPage = findViewById(R.id.btn_password_landingpage);
        inputPassword = findViewById(R.id.enter_password_landingpage);
        landingActivityMessage = findViewById(R.id.landing_activity_error_message);

        dbHelper = new DBHelper(this);
        boolean getPassword = dbHelper.isDefaultPasswordExists();
        if(!getPassword){
            dbHelper.insertDefaultPassword();
        }

        Ardutooth mArdutooth = Ardutooth.getInstance(this);
        mArdutooth.setConnection();

        btnPasswordLandingPage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                validatePassword(inputPassword.getText().toString().trim(),dbHelper);
            }
        });

    }

    private void goToMainActivity() {
        Intent switchActivityIntent = new Intent(LandingActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void validatePassword(String inputPassword, DBHelper dbHelper){
        if(StringUtils.equals(inputPassword,dbHelper.getPassword())){
            goToMainActivity();
        } else if(StringUtils.equals(inputPassword,RESET_KEY)){
            hardResetIfTextIsEntered(inputPassword);
            recreate();
        } else {
            landingActivityMessage.setText("Incorrect Password.\nPlease Try Again");
        }
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