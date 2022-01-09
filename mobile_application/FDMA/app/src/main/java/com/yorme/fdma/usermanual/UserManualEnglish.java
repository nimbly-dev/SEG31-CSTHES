package com.yorme.fdma.usermanual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.MainActivity;
import com.yorme.fdma.R;

public class UserManualEnglish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_manual_english);

        Button btn_user_guide_filipino = (Button) findViewById(R.id.btn_user_guide_filipino);
        Button btn_user_guide_english_back = (Button) findViewById(R.id.btn_user_guide_english_back);

        btn_user_guide_filipino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFilipinoUserGuide();
            }
        });

        btn_user_guide_english_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackUserManualEnglish();
            }
        });
    }

    private void goToFilipinoUserGuide() {
        Intent switchActivityIntent = new Intent(this, UserManualFilipino.class);
        startActivity(switchActivityIntent);
    }

    private void goBackUserManualEnglish() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}