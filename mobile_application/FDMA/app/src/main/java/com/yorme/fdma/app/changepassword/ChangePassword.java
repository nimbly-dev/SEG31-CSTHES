package com.yorme.fdma.app.changepassword;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.core.service.PasswordChecker;
import com.yorme.fdma.core.service.TokenEncrytor;
import com.yorme.fdma.utilities.PropertiesReader;
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

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePassword extends AppCompatActivity {

    EditText enterPassword, enterConfirmPassword;
    TextView changePasswordErrorMessage, txtConnectionChangePassword;
    Button btnChangePassword, btnChangePasswordBack;

    DBHelper dbHelper = new DBHelper(this);

    private PasswordChecker passwordChecker;

    Ardutooth mArdutooth = Ardutooth.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);

        txtConnectionChangePassword = findViewById(R.id.txt_connection_change_password);
        changePasswordErrorMessage = findViewById(R.id.change_password_error_message);
        enterPassword = findViewById(R.id.enter_passwordChange);
        enterConfirmPassword = findViewById(R.id.enter_confirm_password);
        btnChangePassword = findViewById(R.id.btn_change_password);
        btnChangePasswordBack = findViewById(R.id.btn_change_password_back);

        passwordChecker = new PasswordChecker();

        if (mArdutooth.isConnected()) {
            txtConnectionChangePassword.setText("Connected");
        } else {
            txtConnectionChangePassword.setText("Not Connected");
        }

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View arg0) {
                PropertiesReader propertiesReader = new PropertiesReader();
                String cipherText;
                String inputPassword = enterPassword.getText().toString().trim();
                String confirmPassword = enterConfirmPassword.getText().toString().trim();

                if (isEnteredPasswordValid(inputPassword)
                        && isPasswordAndConfirmPasswordEqual(inputPassword, confirmPassword)
                        && isOldPasswordAndNewPasswordEqual(dbHelper.getPassword(), inputPassword)) {
                    try {
                        cipherText = TokenEncrytor.encrypt(enterPassword.getText().toString());
                        dbHelper.updatePassword(cipherText);
                        dbHelper.insertData(
                                LocalTime.now().toString(),
                                LocalDate.now().toString(),
                                "change_password_logs");
                    } catch (InvalidAlgorithmParameterException
                            | NoSuchPaddingException | NoSuchAlgorithmException
                            | UnsupportedEncodingException | IllegalBlockSizeException
                            | BadPaddingException | InvalidKeyException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(ChangePassword.this, "Password was changed successfully.", Toast.LENGTH_LONG).show();
                    Intent switchActivityIntent = new Intent(ChangePassword.this, MainActivity.class);
                    startActivity(switchActivityIntent);
                }
            }
        });


        btnChangePasswordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBackSettings();
            }
        });
    }

    private boolean isOldPasswordAndNewPasswordEqual(String oldPassword, String newPassword) {
        if (StringUtils.equals(oldPassword, newPassword)) {

            changePasswordErrorMessage.setText("Old Password was Entered.");
            return false;
        }
        return true;
    }

    private boolean isPasswordAndConfirmPasswordEqual(String password, String confirmPassword) {
        if (!StringUtils.equals(password, confirmPassword)) {
            changePasswordErrorMessage.setText("Password and Confirm Password do not match.");
            return false;
        }
        return true;
    }

    private boolean isEnteredPasswordValid(String inputPassword) {
        if (!PasswordChecker.isValidPassword(inputPassword)) {
            changePasswordErrorMessage.setText("Password must contain atleast 1 upper case, \n1 lowercase letter, 1 number \nand a minimum of 8 characters.");
            return false;
        }
        return true;
    }

    private void goToBackSettings() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}