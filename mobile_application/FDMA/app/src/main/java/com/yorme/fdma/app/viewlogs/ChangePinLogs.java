package com.yorme.fdma.app.viewlogs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.yorme.fdma.R;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePinLogs extends AppCompatActivity {

    ListView changePinLogsListView;
    String[] changePinLogs = {"Manila", "Taguig", "Mandaluyong", "Makati", "Quezon", "LasPinas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_pin_logs);

        changePinLogsListView = findViewById(R.id.changePinLogsListView);
        ArrayAdapter<String> changePinAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview_change_pin_logs,changePinLogs);
        changePinLogsListView.setAdapter(changePinAdapter);

        Button btn_change_pin_logs_back = findViewById(R.id.btn_change_pin_logs_back);
        btn_change_pin_logs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewLogsChangePinLogs();
            }
        });
    }

    private void goToViewLogsChangePinLogs() {
        Intent switchActivityIntent = new Intent(ChangePinLogs.this, ViewLogs.class);
        startActivity(switchActivityIntent);
    }
}