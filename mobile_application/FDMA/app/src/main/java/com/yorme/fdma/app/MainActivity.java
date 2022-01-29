package com.yorme.fdma.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yorme.fdma.R;
import com.yorme.fdma.app.Settings.Settings;
import com.yorme.fdma.app.changephonenumber.ChangePhoneNumber;
import com.yorme.fdma.app.changepin.ChangePin;
import com.yorme.fdma.app.usermanual.UserManualEnglish;
import com.yorme.fdma.app.viewlogs.ViewLogs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ImageView btn_change_phone_number = (ImageView) findViewById(R.id.btn_change_phone_number);
        ImageView btn_user_manual = (ImageView) findViewById(R.id.btn_user_manual);
        ImageView btn_change_pin = (ImageView) findViewById(R.id.btn_change_pin);
        ImageView btn_view_logs = (ImageView) findViewById(R.id.btn_view_logs);
        ImageView btn_settings = (ImageView) findViewById(R.id.btn_settings);

        btn_change_phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePhoneNumber();
            }
        });

        btn_user_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUserManual();
            }
        });

        btn_view_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewLogs();
            }
        });

        btn_change_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePin();
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettings();
            }
        });
        //if not connected in the arduino this code activates below
        /*
        btn_change_pin.setAlpha(0.5f);
        btn_change_pin.setClickable(false);
        btn_gps.setAlpha(0.5f);
        btn_gps.setClickable(false);
         */
    }

    private void goToSettings() {
        Intent switchActivityIntent = new Intent(this, Settings.class);
        startActivity(switchActivityIntent);
    }

    private void goToChangePhoneNumber() {
        Intent switchActivityIntent = new Intent(this, ChangePhoneNumber.class);
        startActivity(switchActivityIntent);
    }

    private void goToUserManual() {
        Intent switchActivityIntent = new Intent(this, UserManualEnglish.class);
        startActivity(switchActivityIntent);
    }

    private void goToViewLogs() {
        Intent switchActivityIntent = new Intent(this, ViewLogs.class);
        startActivity(switchActivityIntent);
    }

    private void goToChangePin() {
        Intent switchActivityIntent = new Intent(this, ChangePin.class);
        startActivity(switchActivityIntent);
    }
}