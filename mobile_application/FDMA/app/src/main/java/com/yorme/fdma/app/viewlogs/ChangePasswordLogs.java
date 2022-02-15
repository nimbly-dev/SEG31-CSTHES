package com.yorme.fdma.app.viewlogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.yorme.fdma.R;

public class ChangePasswordLogs extends AppCompatActivity {

    ListView changePasswordLogsListView;
    String changePasswordLogs[] = {"Philippines", "Thailand", "HongKong", "Indonesia", "Malaysia", "Singapore"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password_logs);

        changePasswordLogsListView = (ListView) findViewById(R.id.changePasswordLogsListView);
        ArrayAdapter<String> changePasswordLogsAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview_change_password_logs,changePasswordLogs);
        changePasswordLogsListView.setAdapter(changePasswordLogsAdapter);

        Button btn_change_password_logs_back = (Button) findViewById(R.id.btn_change_password_logs_back);
        btn_change_password_logs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewLogsChangePasswordLogs();
            }
        });
    }

    private void goToViewLogsChangePasswordLogs() {
        Intent switchActivityIntent = new Intent(ChangePasswordLogs.this, ViewLogs.class);
        startActivity(switchActivityIntent);
    }
}