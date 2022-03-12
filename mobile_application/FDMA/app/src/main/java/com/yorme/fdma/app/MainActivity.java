package com.yorme.fdma.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.app.changepassword.ChangePassword;
import com.yorme.fdma.app.changephonenumber.ChangePhoneNumber;
import com.yorme.fdma.app.passwordmodal.PasswordModal;
import com.yorme.fdma.app.usermanual.UserManualEnglish;
import com.yorme.fdma.app.viewlogs.ViewLogs;

import java.io.InputStream;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    TextView txtConnectionMainActivity;
    ImageView btnChangePhoneNumber, btnUserManual, btnChangePassword, btnViewLogs;
    Ardutooth mArdutooth = Ardutooth.getInstance(this);
    int intentFlag = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        txtConnectionMainActivity = findViewById(R.id.txt_connection_main_activity);
        btnChangePhoneNumber = findViewById(R.id.btn_change_phone_number);
        btnUserManual = findViewById(R.id.btn_user_manual);
        btnChangePassword = findViewById(R.id.btn_change_password);
        btnViewLogs = findViewById(R.id.btn_view_logs);

        if (mArdutooth.isConnected()) {
            txtConnectionMainActivity.setText("Connected");
//            mArdutooth.sendInt(4);


        } else {
            txtConnectionMainActivity.setText("Not Connected");
            btnChangePassword.setEnabled(false);
            btnChangePassword.setAlpha(0.5f);

            btnChangePhoneNumber.setEnabled(false);
            btnChangePhoneNumber.setAlpha(0.5f);
        }

        btnChangePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePhoneNumber();
            }
        });

        btnUserManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUserManual();
            }
        });

        btnViewLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewLogs();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePassword();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void goToChangePhoneNumber() {
        try{
//            InputStream inputStream = mArdutooth.getSocket().getInputStream();
//            int bytes = 0;
//            byte[] buffer = new byte[1024];
//            bytes = inputStream.read(buffer);
//            String arduinoData = new String(buffer, 0, bytes);
//            Log.d("Phone Number: ","Phone Number: " + arduinoData);
//            String phoneNum = "0" + arduinoData;
            Intent switchActivityIntent = new Intent(this, PasswordModal.class);
            intentFlag = 1;
            switchActivityIntent.putExtra("intentFlag", intentFlag);
//            switchActivityIntent.putExtra("phoneNumber", phoneNum);
            startActivity(switchActivityIntent);
        } catch (Exception e){
            e.printStackTrace();
            goToLandingPage();

        }
    }


    private void goToLandingPage() {
        Intent switchActivityIntent = new Intent(this, LandingActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToUserManual() {
        Intent switchActivityIntent = new Intent(this, UserManualEnglish.class);
        startActivity(switchActivityIntent);
    }

    private void goToViewLogs() {
        Intent switchActivityIntent = new Intent(this, ViewLogs.class);
        startActivity(switchActivityIntent);
    }

    private void goToChangePassword() {
        Intent switchActivityIntent = new Intent(this, PasswordModal.class);
        intentFlag = 2;
        switchActivityIntent.putExtra("intentFlag", intentFlag);
        startActivity(switchActivityIntent);
    }
}