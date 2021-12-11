package com.yorme.fdma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.yorme.fdma.changepin.ChangePin;
import com.yorme.fdma.viewlogs.ViewLogs;
import com.yorme.fdma.model.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ImageView btn_change_pin = (ImageView) findViewById(R.id.btn_change_pin);
        ImageView btn_view_logs = (ImageView) findViewById(R.id.btn_view_logs);


        btn_view_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewLogs();
            }
        });

        btn_change_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePin();
            }
        });
        //if not connected in the arduino this code activates below
        /*
        btn_change_pin.setAlpha(0.5f);
        btn_change_pin.setClickable(false);
        btn_gps.setAlpha(0.5f);
        btn_gps.setClickable(false);
         */
    }

    public void clickMessage(View view){
        Toast.makeText(getApplicationContext(),"Hello There",Toast.LENGTH_SHORT).show();
    }

    private void goToViewLogs() {
        Intent switchActivityIntent = new Intent(this, ViewLogs.class);
        startActivity(switchActivityIntent);
    }

    private void goToChangePin() {
        Intent switchActivityIntent = new Intent(this, ChangePin.class);
        startActivity(switchActivityIntent);
    }
}