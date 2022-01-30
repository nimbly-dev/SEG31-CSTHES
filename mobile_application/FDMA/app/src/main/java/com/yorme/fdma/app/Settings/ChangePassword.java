package com.yorme.fdma.app.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;

public class ChangePassword extends AppCompatActivity {

    String password, confirmPassword;
    EditText enter_password, enter_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);

        enter_password = (EditText) findViewById(R.id.enter_password);
        enter_confirm_password = (EditText) findViewById(R.id.enter_confirm_phone_number);
        Button btn_change_password = (Button) findViewById(R.id.btn_change_password);
        Button btn_change_password_back = (Button) findViewById(R.id.btn_change_password_back);

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePassword();
            }
        });

        btn_change_password_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBackSettings();
            }
        });
    }

    private void goToChangePassword() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToBackSettings() {
        Intent switchActivityIntent = new Intent(this, Settings.class);
        startActivity(switchActivityIntent);
    }
}