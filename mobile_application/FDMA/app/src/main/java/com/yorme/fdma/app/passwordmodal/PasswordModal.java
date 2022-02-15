package com.yorme.fdma.app.passwordmodal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.app.changephonenumber.ChangePhoneNumber;
import com.yorme.fdma.core.dao.ChangePhoneNumberLogsDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


@RequiresApi(api = Build.VERSION_CODES.O)
public class PasswordModal extends AppCompatActivity {

    private ChangePhoneNumberLogsDao changePhoneNumberLogsDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_password_modal);

        Button btn_password = (Button) findViewById(R.id.btn_password);
        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validPassword();
            }
        });

    }

    public void validPassword() {
        Intent switchActivityIntent = new Intent(PasswordModal.this, MainActivity.class);
        changePhoneNumberLogsDao = new ChangePhoneNumberLogsDao();
        try {
            changePhoneNumberLogsDao.insertNewChangePhoneNumberLog(LocalTime.now(), LocalDate.now());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        startActivity(switchActivityIntent);
    }

}