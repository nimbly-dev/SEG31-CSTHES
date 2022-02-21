package com.yorme.fdma.app.changephonenumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.R;
import com.yorme.fdma.app.passwordmodal.PasswordModal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePhoneNumber extends AppCompatActivity {

    String phoneNumber, confirmPhoneNumber;
    EditText enter_phone_number, enter_confirm_phone_number;
    PasswordModal passwordModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_phone_number);

        enter_phone_number = findViewById(R.id.enter_phone_number);
        enter_confirm_phone_number = findViewById(R.id.enter_confirm_phone_number);
        Button btn_change_phone_number = findViewById(R.id.btn_change_phone_number);
        Button btn_change_phone_number_back = findViewById(R.id.btn_change_phone_number_back);

        btn_change_phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePhoneNumber(); }
        });

        btn_change_phone_number_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goBackChangePhoneNumber();
            }
        });

    }

    private void changePhoneNumber() {
        phoneNumber = enter_phone_number.getText().toString();
        confirmPhoneNumber = enter_confirm_phone_number.getText().toString();
        String message = "Phone Number: " + phoneNumber + "\nConfirm Phone Number:" + confirmPhoneNumber;
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

        Intent switchActivityIntent = new Intent(ChangePhoneNumber.this, PasswordModal.class);
        startActivity(switchActivityIntent);
    }

    private void goBackChangePhoneNumber() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}