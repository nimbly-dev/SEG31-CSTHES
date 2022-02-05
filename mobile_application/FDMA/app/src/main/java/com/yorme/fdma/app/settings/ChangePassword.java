package com.yorme.fdma.app.settings;

import static com.yorme.fdma.core.service.Encryptor.generateIv;
import static com.yorme.fdma.core.service.Encryptor.generateKey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.core.service.Encryptor;
import com.yorme.fdma.core.service.PasswordChecker;
import com.yorme.fdma.utilities.PropertiesReader;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class ChangePassword extends AppCompatActivity {

    EditText enter_password, enter_confirm_password;

    private PasswordChecker passwordChecker;
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

        passwordChecker = new PasswordChecker();

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View arg0) {

                PropertiesReader propertiesReader = new PropertiesReader();
                String cipherText;

                if (passwordChecker.isValidPassword(enter_password.getText().toString().trim())) {
                    if(StringUtils.equals(enter_password.getText().toString(),enter_confirm_password.getText().toString()) == true){
                        try {
                            SecretKey secretKey = generateKey(128);
                            IvParameterSpec ivParameterSpec = generateIv();
                            String algorithm = propertiesReader.getApplicationProperty().getProperty("encrypt.algorithm");

                            cipherText = Encryptor.encrypt(algorithm, enter_password.toString(), secretKey, ivParameterSpec);
                        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                        Intent switchActivityIntent = new Intent(ChangePassword.this, MainActivity.class);
                        startActivity(switchActivityIntent);
                    }else{
                        Toast.makeText(ChangePassword.this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
                    }
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

    private void goToBackSettings() {
        Intent switchActivityIntent = new Intent(this, Settings.class);
        startActivity(switchActivityIntent);
    }
}