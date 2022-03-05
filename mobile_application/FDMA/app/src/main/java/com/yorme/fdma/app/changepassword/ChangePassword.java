package com.yorme.fdma.app.changepassword;

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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.core.dao.PasswordDao;
import com.yorme.fdma.core.service.PasswordChecker;
import com.yorme.fdma.core.service.TokenEncrytor;
import com.yorme.fdma.utilities.PropertiesReader;
import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.utilities.database.DBHelper;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePassword extends AppCompatActivity {

    ListView activationLogsListView;
    EditText enter_password, enter_confirm_password;
    private DBHelper dbHelper;
    private DBConnection conn;
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
                String inputPassword = enter_password.getText().toString().trim();
                String confirmPassword = enter_confirm_password.getText().toString().trim();

//                if (passwordChecker.isValidPassword(enter_password.getText().toString().trim())) {
//                    if(StringUtils.equals(enter_password.getText().toString(), enter_confirm_password.getText().toString())){
//                        if(enter_password.getText().toString().trim().equals(passwordDB)){
//                            Toast.makeText(ChangePassword.this, "Old password entered", Toast.LENGTH_LONG).show();
//                        } else {
//                            try {
//                                Log.d("LOG", "Password: " + enter_password);
//                                cipherText = TokenEncrytor.encrypt(enter_password.getText().toString());
//                                dbHelper.updatePassword(cipherText);
//                            } catch (InvalidAlgorithmParameterException
//                                    | NoSuchPaddingException | NoSuchAlgorithmException
//                                    | UnsupportedEncodingException | IllegalBlockSizeException
//                                    | BadPaddingException | InvalidKeyException e) {
//                                e.printStackTrace();
//                            }
//
//                            Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
//                            Intent switchActivityIntent = new Intent(ChangePassword.this, MainActivity.class);
//                            startActivity(switchActivityIntent);
//                        }
//                    }else{
//                        Toast.makeText(ChangePassword.this, "Password and Confirm Password do not match", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(ChangePassword.this, "Password must contain atleast 1 upper case letter, 1 lower case letter and 1 number and a minimum of 8 characters.", Toast.LENGTH_SHORT).show();
//                }

                if (isEnteredPasswordValid(inputPassword)
                        && isPasswordAndConfirmPasswordEqual(inputPassword, confirmPassword)
                        && isOldPasswordAndNewPasswordEqual(dbHelper.getPassword(), inputPassword)) {
                    try {
                        Log.d("LOG", "Password: " + enter_password);
                        cipherText = TokenEncrytor.encrypt(enter_password.getText().toString());
                        dbHelper.updatePassword(cipherText);
                    } catch (InvalidAlgorithmParameterException
                            | NoSuchPaddingException | NoSuchAlgorithmException
                            | UnsupportedEncodingException | IllegalBlockSizeException
                            | BadPaddingException | InvalidKeyException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
                    Intent switchActivityIntent = new Intent(ChangePassword.this, MainActivity.class);
                    startActivity(switchActivityIntent);
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

    private boolean isOldPasswordAndNewPasswordEqual(String oldPassword, String newPassword) {
        if (StringUtils.equals(oldPassword, newPassword)) {
            Toast.makeText(ChangePassword.this, "Old password entered", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isPasswordAndConfirmPasswordEqual(String password, String confirmPassword) {
        if (!StringUtils.equals(password, confirmPassword)) {
            Toast.makeText(ChangePassword.this, "Password and Confirm Password do not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isEnteredPasswordValid(String inputPassword) {
        if (!PasswordChecker.isValidPassword(inputPassword)) {
            Toast.makeText(ChangePassword.this,
                    "Password must contain atleast 1 upper case letter, 1 lower case letter and 1 number and a minimum of 8 characters.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void goToBackSettings() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}