package com.yorme.fdma.app.changepin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.R;

public class ChangePin extends AppCompatActivity {

    String old_pin, new_pin, confirm_pin;
    EditText et_oldPin, et_newPin, et_confirmPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_pin);

        Button btn_proceed = (Button)findViewById(R.id.btn_change_pin_proceed);
        Button btn_back = (Button)findViewById(R.id.btn_change_pin_back);

        et_oldPin = (EditText)findViewById(R.id.et_old_pin);
        et_newPin = (EditText)findViewById(R.id.et_new_pin);
        et_confirmPin = (EditText)findViewById(R.id.et_confirm_pin);

        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceed();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
    }

    private void goback() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void proceed() {
        showMessage();
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void showMessage() {
        old_pin = et_oldPin.getText().toString();
        new_pin = et_newPin.getText().toString();
        confirm_pin = et_confirmPin.getText().toString();
        String message = "Old Pin: " + old_pin + "\nNew Pin:" + new_pin + "\nConfirm Pin: " + confirm_pin;
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

}