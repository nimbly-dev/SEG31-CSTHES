package com.yorme.fdma.app.changepassword;

import static com.yorme.fdma.core.service.Encryptor.generateIv;
import static com.yorme.fdma.core.service.Encryptor.generateKey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.core.dao.PasswordDao;
import com.yorme.fdma.core.service.Encryptor;
import com.yorme.fdma.core.service.PasswordChecker;
import com.yorme.fdma.core.service.TokenEncrytor;
import com.yorme.fdma.utilities.PropertiesReader;
import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.utilities.database.DBHelper;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePassword extends AppCompatActivity {

    private DBHelper dbHelper;
    private DBConnection conn;

    ListView activationLogsListView;

    EditText enter_password, enter_confirm_password;

    private PasswordDao passwordDao;

    private PasswordChecker passwordChecker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);

        enter_password = findViewById(R.id.enter_passwordChange);
        enter_confirm_password = findViewById(R.id.enter_confirm_password);
        Button btn_change_password = findViewById(R.id.btn_change_password);
        Button btn_change_password_back = findViewById(R.id.btn_change_password_back);

        passwordChecker = new PasswordChecker();

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View arg0) {

                PropertiesReader propertiesReader = new PropertiesReader();
                String cipherText;
                String passwordDB = dbHelper.getPassword();

                if (passwordChecker.isValidPassword(enter_password.getText().toString().trim())) {
                    if(StringUtils.equals(enter_password.getText().toString(),enter_confirm_password.getText().toString()) == true){
                        if(enter_password.getText().toString().trim().equals(passwordDB)){
                            Toast.makeText(ChangePassword.this, "Old password entered", Toast.LENGTH_LONG).show();
                        } else {
                            try {
                                Log.d("LOG", "Password: " + enter_password);
                                cipherText = TokenEncrytor.encrypt(enter_password.getText().toString());
                                dbHelper.updatePassword(cipherText);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
                            Intent switchActivityIntent = new Intent(ChangePassword.this, MainActivity.class);
                            startActivity(switchActivityIntent);
                        }
                    }else{
                        Toast.makeText(ChangePassword.this, "Password and Confirm Password do not match", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ChangePassword.this, "Password must contain atleast 1 upper case letter, 1 lower case letter and 1 number and a minimum of 8 characters.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dbHelper = new DBHelper(this);
        dbHelper.insertData(
                LocalTime.now().toString(),
                LocalDate.now().toString(),
                "change_password_logs");

        btn_change_password_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBackSettings();
            }
        });
    }

    private void goToBackSettings() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}