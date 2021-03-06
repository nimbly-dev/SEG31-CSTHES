package com.yorme.fdma.app.usermanual;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.R;

@RequiresApi(api = Build.VERSION_CODES.O)
public class UserManualFilipino extends AppCompatActivity {

    Button btnUserGuideEnglish, btnUserGuideFilipinoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_manual_filipino);

        btnUserGuideEnglish = findViewById(R.id.btn_user_guide_english);
        btnUserGuideFilipinoBack = findViewById(R.id.btn_user_guide_filipino_back);

        btnUserGuideEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEnglishUserGuide();
            }
        });

        btnUserGuideFilipinoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackUserManualFilipino();
            }
        });
    }

    private void goToEnglishUserGuide() {
        Intent switchActivityIntent = new Intent(this, UserManualEnglish.class);
        startActivity(switchActivityIntent);
    }

    private void goBackUserManualFilipino() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}
