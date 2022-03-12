package com.yorme.fdma.app.changephonenumber;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.R;
import com.yorme.fdma.app.passwordmodal.PasswordModal;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePhoneNumber extends AppCompatActivity {

    String phoneNumber, confirmPhoneNumber;
    EditText enterPhoneNumber, enterConfirmPhoneNumber;
    TextView changePhoneNumberErrorMessage, txtConnectionChangePhoneNumber, displayPhoneNumber;
    Button btnChangePhoneNumber, btnChangePhoneNumberBack;

    Ardutooth mArdutooth = Ardutooth.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_phone_number);

        displayPhoneNumber = findViewById(R.id.display_phone_number);
        txtConnectionChangePhoneNumber = findViewById(R.id.txt_connection_change_phone_number);
        enterPhoneNumber = findViewById(R.id.enter_phone_number);
        enterConfirmPhoneNumber = findViewById(R.id.enter_confirm_phone_number);
        btnChangePhoneNumber = findViewById(R.id.btn_change_phone_number);
        btnChangePhoneNumberBack = findViewById(R.id.btn_change_phone_number_back);
        changePhoneNumberErrorMessage = findViewById(R.id.change_phone_number_error_message);

        Bundle extras = getIntent().getExtras();
        String phoneNum = extras.getString("PhoneNumber");
        displayPhoneNumber.setText(phoneNum);

        if (mArdutooth.isConnected()) {
            txtConnectionChangePhoneNumber.setText("Connected");
        } else {
            txtConnectionChangePhoneNumber.setText("Not Connected");
        }

        btnChangePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePhoneNumber(); }
        });

        btnChangePhoneNumberBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goBackChangePhoneNumber();
            }
        });

    }

    private void changePhoneNumber() {
        phoneNumber = enterPhoneNumber.getText().toString();
        confirmPhoneNumber = enterConfirmPhoneNumber.getText().toString();

        validatePhoneNumber();

//        WAG DEDELETE TO PANG KUHA NATIN AT DISPLAY NG PHONE NUMBER TO.
//        if (mArdutooth.isConnected()){
//            mArdutooth.sendInt(3);
//            mArdutooth.sendString(phoneNumber);
//            Log.d("TAG","SEND VALUE");
//            Log.d("NUMBER", "Number: "  + phoneNumber);
//
//            try {
//                InputStream inputStream = mArdutooth.getSocket().getInputStream();
//                int bytes = 0;
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
//        }

    }

    private void validatePhoneNumber() {
        if (phoneNumber.length() == 10){
            if (phoneNumber.equals(confirmPhoneNumber)){
                Intent switchActivityIntent = new Intent(ChangePhoneNumber.this, MainActivity.class);
                startActivity(switchActivityIntent);
            } else {
                changePhoneNumberErrorMessage.setText("Phone Number and Confirm Phone Number do not match.");
            }
        } else {
            changePhoneNumberErrorMessage.setText("Phone Number should only have 10 Digits.");
        }

    }

    private void goBackChangePhoneNumber() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}