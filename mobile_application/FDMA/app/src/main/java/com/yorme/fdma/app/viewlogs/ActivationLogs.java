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
import android.widget.ListView;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.core.dao.ActivationLogsDao;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.adapters.ActivationLogAdapter;
import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.utilities.database.DBHelper;
import com.yorme.fdma.utilities.database.DBSQL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ActivationLogs extends AppCompatActivity {

    private ActivationLogsDao activationLogsDao;
    private ArrayList<ActivationLog> activationLogs;

    private DBHelper dbHelper;
    private DBConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_activation_logs);

        Ardutooth mArdutooth = Ardutooth.getInstance(this);

        if (mArdutooth.isConnected()){
            mArdutooth.sendInt(1);
            String recieveActivationLogs = mArdutooth.receiveLine();
            Toast.makeText(this, "Activation Logs:" + recieveActivationLogs, Toast.LENGTH_LONG).show();
        }

        dbHelper = new DBHelper(this);
        dbHelper.insertData(
                LocalTime.now().toString(),
                LocalDate.now().toString(),
                "activation_logs");

        activationLogs = dbHelper.selectAll(DBSQL.SELECT_ALL_ACTIVATION_LOGS);

        ActivationLogAdapter activationLogAdapter = new ActivationLogAdapter(this, activationLogs);
        // Attach the adapter to a ListView
        ListView activationLogListView = findViewById(R.id.activationLogsListView);
        activationLogListView.setAdapter(activationLogAdapter);

        Button btn_activation_logs_back = findViewById(R.id.btn_activation_logs_back);
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