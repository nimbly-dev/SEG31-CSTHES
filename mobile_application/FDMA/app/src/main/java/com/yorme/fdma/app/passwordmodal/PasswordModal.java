package com.yorme.fdma.app.passwordmodal;

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
import com.yorme.fdma.app.changepassword.ChangePassword;
import com.yorme.fdma.app.changephonenumber.ChangePhoneNumber;
import com.yorme.fdma.utilities.database.DBHelper;

import org.apache.commons.lang3.StringUtils;


@RequiresApi(api = Build.VERSION_CODES.O)
public class PasswordModal extends AppCompatActivity {

    EditText passwordInput;
    Button btnPassword;
    TextView passwordModalErrorMessage;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_password_modal);

        passwordInput = findViewById(R.id.enter_passwordModal);
        btnPassword = findViewById(R.id.btn_password);
        passwordModalErrorMessage = findViewById(R.id.password_modal_error_message);

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatePassword(passwordInput.getText().toString());
            }
        });
    }

    public void validatePassword(String passwordInput) {
        dbHelper = new DBHelper(this);
        String passwordDB = dbHelper.getPassword();

        Bundle extras = getIntent().getExtras();

        if (StringUtils.equals(passwordInput, passwordDB)) {
            if (extras != null){
                int value =extras.getInt("intentFlag");
                if(value == 1){
                    Intent switchActivityIntent = new Intent(PasswordModal.this, ChangePhoneNumber.class);
                    startActivity(switchActivityIntent);
                } else if(value == 2){
                    Intent switchActivityIntent = new Intent(PasswordModal.this, ChangePassword.class);
                    startActivity(switchActivityIntent);
                }
            }

        } else {
            passwordModalErrorMessage.setText("Incorrect Password.\nPlease Try Again");
        }
    }
}