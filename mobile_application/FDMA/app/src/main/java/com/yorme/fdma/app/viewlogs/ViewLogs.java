package com.yorme.fdma.app.viewlogs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.R;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ViewLogs extends AppCompatActivity {

    Button btnViewLogsBack, btnViewActivationLogs, btnViewChangePhoneNumLogs, btnViewPasswordLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_logs);

        btnViewLogsBack = findViewById(R.id.btn_view_logs_back);
        btnViewActivationLogs = findViewById(R.id.btn_view_activation_logs);
        btnViewChangePhoneNumLogs = findViewById(R.id.btn_view_change_phone_num_logs);
        btnViewPasswordLogs = findViewById(R.id.btn_view_change_password_logs);

        btnViewLogsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();

            }
        });

        btnViewActivationLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivationLogs();
            }
        });

        btnViewChangePhoneNumLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePhoneNumLogs();
            }
        });

        btnViewPasswordLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePasswordLogs();
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

    private void goBack() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}