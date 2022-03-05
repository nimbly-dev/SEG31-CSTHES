package com.yorme.fdma.app.passwordmodal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.app.LandingActivity;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.app.changephonenumber.ChangePhoneNumber;
import com.yorme.fdma.core.dao.ChangePhoneNumberLogsDao;
import com.yorme.fdma.core.dao.PasswordDao;
import com.yorme.fdma.core.service.Decryptor;
import com.yorme.fdma.core.service.Encryptor;
import com.yorme.fdma.utilities.PropertiesReader;
import com.yorme.fdma.utilities.database.DBHelper;
import com.yorme.fdma.utilities.database.DBSQL;

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
public class PasswordModal extends AppCompatActivity {


    private ChangePhoneNumberLogsDao changePhoneNumberLogsDao;

    private PasswordDao passwordDao;
    private Decryptor decryptor;
    private PropertiesReader propertiesReader;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_password_modal);

        dbHelper = new DBHelper(this);

        EditText inputString = (EditText) findViewById(R.id.enter_passwordModal);
        Log.d("BEFORE HAKDOG2", "etits");
        String passwordDB = dbHelper.getPassword();
        Log.d("PAAAASWOOORD HAKDOG2", "THIS YOUR PASSWORD HOTDOG: " + passwordDB);

        Button btn_password = findViewById(R.id.btn_password);

        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(inputString.getText().toString().equals(passwordDB)){
                        Intent switchActivityIntent = new Intent(PasswordModal.this, MainActivity.class);
                        startActivity(switchActivityIntent);
                    } else if(passwordDB == null){
                        Toast.makeText(PasswordModal.this, "NULL PASSWORD HOTDOG", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(PasswordModal.this, "INCORRECT PASSWORD HOTDOG", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void validPassword(String passwordInput){

    }
}