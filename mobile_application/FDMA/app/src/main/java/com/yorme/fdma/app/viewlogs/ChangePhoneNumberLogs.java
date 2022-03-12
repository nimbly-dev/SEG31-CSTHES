package com.yorme.fdma.app.viewlogs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.R;
import com.yorme.fdma.app.LandingActivity;
import com.yorme.fdma.core.model.ChangePhoneNumberLog;
import com.yorme.fdma.core.model.adapters.ActivationLogAdapter;
import com.yorme.fdma.core.model.adapters.ChangePhoneNumberLogAdapter;
import com.yorme.fdma.utilities.database.DBHelper;
import com.yorme.fdma.utilities.database.DBSQL;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePhoneNumberLogs extends AppCompatActivity {

    ListView changePhoneNumberListView;
    Button btnChangePhoneNumberLogsBack;

    Ardutooth mArdutooth = Ardutooth.getInstance(this);

    private ArrayList<ChangePhoneNumberLog> changePhoneNumberLogs;
    private final DBHelper dbHelper = new DBHelper(this);

    ChangePhoneNumberLogAdapter changePhoneNumberLogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_phone_number_logs);

        changePhoneNumberListView = findViewById(R.id.changePhoneNumberLogsListView);
        btnChangePhoneNumberLogsBack = findViewById(R.id.btn_change_phone_number_logs_back);



        if (mArdutooth.isConnected()) {
            mArdutooth.sendInt(2);
            Log.d("TAG", "SEND VALUE");
            try {
                InputStream inputStream = mArdutooth.getSocket().getInputStream();
                int bytes = 0;
                byte[] buffer = new byte[1024];
                bytes = inputStream.read(buffer);
                String arduinoData = new String(buffer, 0, bytes);
                Toast.makeText(this, "Phone Number Logs Input Stream: " + arduinoData, Toast.LENGTH_LONG).show();

                String[] dataArray = {};
                dataArray = proccessArduinoData(arduinoData);
                dbHelper.flushTable(DBSQL.FLUSH_CHANGE_PHONE_NUMBER_LOG_TABLE);
                for (int i = 0; i < dataArray.length; i++) {
                    Log.d("Array Data", "Phone Number Logs Array Data[" + i + "]: " + dataArray[i]);
                    String temp = dataArray[i];
                    insertArduinoDataToDb(temp);
                }


            } catch (IOException e) {
                e.printStackTrace();
                goToLandingPage();
                Toast.makeText(this, "No Data recieved", Toast.LENGTH_LONG).show();
            }

        }

        changePhoneNumberLogs = dbHelper.selectAllChangePhoneNumberLogs(DBSQL.SELECT_ALL_CHANGE_PHONE_NUMBER_LOGS);
        changePhoneNumberLogAdapter = new ChangePhoneNumberLogAdapter(this, changePhoneNumberLogs);
        // Attach the adapter to a ListView
        changePhoneNumberListView.setAdapter(changePhoneNumberLogAdapter);

        btnChangePhoneNumberLogsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToViewLogsChangePhoneNumberLogs();
            }
        });
    }

    private void goBackToViewLogsChangePhoneNumberLogs() {
        Intent switchActivityIntent = new Intent(ChangePhoneNumberLogs.this, ViewLogs.class);
        startActivity(switchActivityIntent);
    }

    //Function processing data from Arduino
    private String[] proccessArduinoData(String data) {
        String[] dataArray = {};
        dataArray = data.split("\n");
        return dataArray;
    }

    //insert db
    private void insertArduinoDataToDb(String data) {
        String[] dataArray = new String[2];
        dataArray = data.split(",");
        dbHelper.insertData(
                dataArray[0],
                dataArray[1],
                "change_phone_number_logs");
    }

    private void goToLandingPage() {
        Intent switchActivityIntent = new Intent(this, LandingActivity.class);
        startActivity(switchActivityIntent);
    }
}