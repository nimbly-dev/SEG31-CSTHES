package com.yorme.fdma.app.viewlogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.R;

public class ViewLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_logs);

        Button btn_view_logs_back = (Button) findViewById(R.id.btn_view_logs_back);
        Button btn_view_activation_logs = (Button) findViewById(R.id.btn_view_activation_logs);
        Button btn_view_change_phone_num_logs = (Button) findViewById(R.id.btn_view_change_phone_num_logs);
        Button btn_view_change_password_logs = (Button) findViewById(R.id.btn_view_change_password_logs);
        Button btn_view_change_pin_logs = (Button) findViewById(R.id.btn_view_change_pin_logs);

        btn_view_logs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();

            }
        });

        btn_view_activation_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivationLogs();
            }
        });

        btn_view_change_phone_num_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePhoneNumLogs();
            }
        });

        btn_view_change_password_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePasswordLogs();
            }
        });

        btn_view_change_pin_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePinLogs();
            }
        });
    }

    private void goToActivationLogs() {
        Intent switchActivityIntent = new Intent(this, ActivationLogs.class);
        startActivity(switchActivityIntent);
    }

    private void goToChangePhoneNumLogs() {
        Intent switchActivityIntent = new Intent(this, ChangePhoneNumberLogs.class);
        startActivity(switchActivityIntent);
    }

    private void goToChangePasswordLogs() {
        Intent switchActivityIntent = new Intent(this, ChangePasswordLogs.class);
        startActivity(switchActivityIntent);
    }

    private void goToChangePinLogs() {
        Intent switchActivityIntent = new Intent(this, ChangePinLogs.class);
        startActivity(switchActivityIntent);
    }

    private void goBack() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}