package com.yorme.fdma.app.viewlogs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.yorme.fdma.R;
import com.yorme.fdma.core.dao.ActivationLogsDao;
import com.yorme.fdma.core.model.ActivationLog;

import java.sql.SQLException;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ActivationLogs extends AppCompatActivity {

    private ActivationLogsDao activationLogsDao;
    private List<ActivationLog> activationLogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize values
        try {
            activationLogList = activationLogsDao.getAllActivationLogs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_activation_logs);
    }
}