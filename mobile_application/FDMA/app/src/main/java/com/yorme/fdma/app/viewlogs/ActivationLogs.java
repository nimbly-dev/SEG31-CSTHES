package com.yorme.fdma.app.viewlogs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;
import java.io.InputStream;
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

    private boolean bStop = false;

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
            Log.d("TAG","SEND VALUE");

            try {
                InputStream inputStream = mArdutooth.getSocket().getInputStream();
                int bytes = 0;


                byte[] buffer = new byte[1024];
                bytes = inputStream.read(buffer);
                String incomingMessage = new String(buffer, 0, bytes);
                Log.d("TAG","RECEIVE");
                Log.d("TAG","Input Stream: "+ incomingMessage);
                Toast.makeText(this, "Input Stream: " + incomingMessage, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

//        dbHelper = new DBHelper(this);
//        dbHelper.insertData(
//                LocalTime.now().toString(),
//                LocalDate.now().toString(),
//                "activation_logs");
//
//        activationLogs = dbHelper.selectAll(DBSQL.SELECT_ALL_ACTIVATION_LOGS);
//
//        ActivationLogAdapter activationLogAdapter = new ActivationLogAdapter(this, activationLogs);
//        // Attach the adapter to a ListView
//        ListView activationLogListView = findViewById(R.id.activationLogsListView);
//        activationLogListView.setAdapter(activationLogAdapter);

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