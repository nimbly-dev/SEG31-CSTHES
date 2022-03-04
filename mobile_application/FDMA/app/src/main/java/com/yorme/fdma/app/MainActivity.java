package com.yorme.fdma.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yorme.fdma.R;
import com.yorme.fdma.app.changepassword.ChangePassword;
import com.yorme.fdma.app.changephonenumber.ChangePhoneNumber;
import com.yorme.fdma.app.usermanual.UserManualEnglish;
import com.yorme.fdma.app.viewlogs.ViewLogs;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Ardutooth mArdutooth = Ardutooth.getInstance(this);

        TextView txt_connection = findViewById(R.id.txt_connection);
        if (mArdutooth.isConnected()) {
            txt_connection.setText("Connected");
        } else {
            txt_connection.setText("Not Connected");
        }

        ImageView btn_change_phone_number = findViewById(R.id.btn_change_phone_number);
        ImageView btn_user_manual = findViewById(R.id.btn_user_manual);
        ImageView btn_change_password = findViewById(R.id.btn_change_password);
        ImageView btn_view_logs = findViewById(R.id.btn_view_logs);

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

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePassword();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    private void goToChangePassword() {
        Intent switchActivityIntent = new Intent(this, ChangePassword.class);
        startActivity(switchActivityIntent);
    }
}