package com.yorme.fdma.app.passwordmodal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.core.service.Decryptor;
import com.yorme.fdma.utilities.PropertiesReader;
import com.yorme.fdma.utilities.database.DBHelper;

import org.apache.commons.lang3.StringUtils;


@RequiresApi(api = Build.VERSION_CODES.O)
public class PasswordModal extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_password_modal);

        EditText passwordInput = findViewById(R.id.enter_passwordModal);
        Button btn_password = findViewById(R.id.btn_password);

        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (StringUtils.equals(inputString.getText().toString(),passwordDB)) {
//                    Intent switchActivityIntent = new Intent(PasswordModal.this, MainActivity.class);
//                    startActivity(switchActivityIntent);
//                } else {
//                    Toast.makeText(PasswordModal.this, "Incorrect Password ", Toast.LENGTH_LONG).show();
//                }
                validatePassword(passwordInput.getText().toString());
            }
        });

    }

    public void validatePassword(String passwordInput) {
        dbHelper = new DBHelper(this);
        String passwordDB = dbHelper.getPassword();

        if (StringUtils.equals(passwordInput, passwordDB)) {
            Intent switchActivityIntent = new Intent(PasswordModal.this, MainActivity.class);
            startActivity(switchActivityIntent);
        } else {
            Toast.makeText(PasswordModal.this, "Incorrect Password ", Toast.LENGTH_LONG).show();
        }
    }
}