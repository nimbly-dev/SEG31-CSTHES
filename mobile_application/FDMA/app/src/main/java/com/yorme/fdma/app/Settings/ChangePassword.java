package com.yorme.fdma.app.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        enter_confirm_password = (EditText) findViewById(R.id.enter_confirm_password);
        Button btn_change_password = (Button) findViewById(R.id.btn_change_password);
        Button btn_change_password_back = (Button) findViewById(R.id.btn_change_password_back);

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (isValidPassword(enter_password.getText().toString().trim())) {
                    Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    Intent switchActivityIntent = new Intent(ChangePassword.this, MainActivity.class);
                    startActivity(switchActivityIntent);
                } else {
                    Toast.makeText(ChangePassword.this, "Password must contain atleast 1 upper case letter, 1 lower case letter and 1 number and a minimum of 8 characters.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_change_password_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBackSettings();
            }
        });
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private void goToBackSettings() {
        Intent switchActivityIntent = new Intent(this, Settings.class);
        startActivity(switchActivityIntent);
    }
}