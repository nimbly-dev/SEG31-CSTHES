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

public class ActivationLogs extends AppCompatActivity {

    ListView activationLogsListView;
    String activationLogs[] = {"Guadalupe", "Pembo", "WestRembo", "EastRembo", "Cembo", "Belair"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_activation_logs);

        activationLogsListView = (ListView) findViewById(R.id.activationLogsListView);
        ArrayAdapter<String> activationLogsAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview_activation_logs,activationLogs);
        activationLogsListView.setAdapter(activationLogsAdapter);

        Button btn_activation_logs_back = (Button) findViewById(R.id.btn_activation_logs_back);
        btn_activation_logs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewLogsActivationLogs();
            }
        });

    }

    private void goToViewLogsActivationLogs() {
        Intent switchActivityIntent = new Intent(ActivationLogs.this, ViewLogs.class);
        startActivity(switchActivityIntent);
    }
}