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

import java.io.IOException;
import java.io.InputStream;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePhoneNumberLogs extends AppCompatActivity {

    ListView changePhoneNumberLogsListView;
    String[] changePhoneNumberLogs = {"India", "China", "australia", "Portugle", "America", "NewZealand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_phone_number_logs);

        Ardutooth mArdutooth = Ardutooth.getInstance(this);

        if (mArdutooth.isConnected()){
            mArdutooth.sendInt(2);
            Log.d("TAG","SEND VALUE");

//            try {
//                InputStream inputStream = mArdutooth.getSocket().getInputStream();
//                int bytes = 0;
//
//
//                byte[] buffer = new byte[1024];
//                bytes = inputStream.read(buffer);
//                String incomingMessage = new String(buffer, 0, bytes);
//                Log.d("TAG","RECEIVE");
//                Log.d("TAG","Input Stream: "+ incomingMessage);
//                Toast.makeText(this, "Input Stream: " + incomingMessage, Toast.LENGTH_SHORT).show();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        changePhoneNumberLogsListView = findViewById(R.id.changePhoneNumberLogsListView);
        ArrayAdapter<String> changePhoneNumberLogsAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview_change_phone_number_logs,changePhoneNumberLogs);
        changePhoneNumberLogsListView.setAdapter(changePhoneNumberLogsAdapter);

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
}