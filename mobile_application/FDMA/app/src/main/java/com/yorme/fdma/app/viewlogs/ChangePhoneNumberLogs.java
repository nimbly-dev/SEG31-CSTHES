package com.yorme.fdma.app.viewlogs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.ChangePhoneNumberLog;
import com.yorme.fdma.core.model.adapters.ActivationLogAdapter;
import com.yorme.fdma.core.model.adapters.ChangePhoneNumberLogAdapter;
import com.yorme.fdma.utilities.database.DBHelper;
import com.yorme.fdma.utilities.database.DBSQL;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePhoneNumberLogs extends AppCompatActivity {

    ListView changePhoneNumberLogsListView;
    private ArrayList<ChangePhoneNumberLog> changePhoneNumberLogs;
    private DBHelper dbHelper = new DBHelper(this);
    Ardutooth mArdutooth = Ardutooth.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_phone_number_logs);

        Ardutooth mArdutooth = Ardutooth.getInstance(this);

        if (mArdutooth.isConnected()){
            dbHelper.flushTable(DBSQL.FLUSH_CHANGE_PHONE_NUMBER_LOG_TABLE);

            mArdutooth.sendInt(2);
            Log.d("TAG","SEND VALUE");

            try {
                InputStream inputStream = mArdutooth.getSocket().getInputStream();
                int bytes = 0;
                byte[] buffer = new byte[1024];
//                Log.d("TAG","BEFORE RECEIVE");
//                bytes = inputStream.read(buffer);
//                Log.d("TAG","BEFORE STRING PARSING");
                String arduinoData = new String(buffer, 0, bytes);
//                Log.d("TAG","RECEIVE");
//                Log.d("TAG","Number of loops: "+ arduinoData);
                Toast.makeText(this, "Input Stream: " + arduinoData, Toast.LENGTH_LONG).show();


                String[] dataArray = {};
                dataArray = proccessArduinoData(arduinoData);
                for (int i = 0; i< dataArray.length; i++){
                    Log.d("Array Data", "Array Data["+i+"]: " + dataArray[i]);
                    String temp = dataArray[i];
                    insertArduinoDataToDb(temp);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        changePhoneNumberLogs = dbHelper.selectAllChangePhoneNumberLogs(DBSQL.SELECT_ALL_CHANGE_PHONE_NUMBER_LOGS);
        ChangePhoneNumberLogAdapter changePhoneNumberLogAdapter = new ChangePhoneNumberLogAdapter(this, changePhoneNumberLogs);
        // Attach the adapter to a ListView
        ListView activationLogListView = findViewById(R.id.changePhoneNumberLogsListView);
        activationLogListView.setAdapter(changePhoneNumberLogAdapter);

        Button btn_change_phone_number_logs_back = findViewById(R.id.btn_change_phone_number_logs_back);
        btn_change_phone_number_logs_back.setOnClickListener(new View.OnClickListener() {
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

    //Function processing data from Arduino - Rename
    private String[] proccessArduinoData(String data) {
        String[] dataArray = {};
        dataArray = data.split("\n");
        return dataArray;
    }

    //insert db
    private void insertArduinoDataToDb(String data){
        String[] dataArray = new String[2];
        dataArray = data.split(",");
        Log.d("TAG", "processStorageBlessing: " + dataArray[0]);
        Log.d("TAG", "processStorageBlessing: " + dataArray[1]);
        dbHelper.insertData(
                dataArray[0],
                dataArray[1],
                "activation_logs");
    }
}