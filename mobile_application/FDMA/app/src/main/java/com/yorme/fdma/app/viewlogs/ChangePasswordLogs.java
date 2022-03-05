package com.yorme.fdma.app.viewlogs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.R;
import com.yorme.fdma.core.model.ChangePasswordLog;
import com.yorme.fdma.core.model.adapters.ChangePasswordLogAdapter;
import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.utilities.database.DBHelper;
import com.yorme.fdma.utilities.database.DBSQL;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChangePasswordLogs extends AppCompatActivity {

    private ArrayList<ChangePasswordLog> changePasswordLog;

    private DBHelper dbHelper;
    private DBConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password_logs);

        dbHelper = new DBHelper(this);

        changePasswordLog = dbHelper.selectAllChangePasswordLogs(DBSQL.SELECT_ALL_CHANGE_PASSWORD_PAIR_LOGS);

        ChangePasswordLogAdapter changePasswordLogAdapter = new ChangePasswordLogAdapter(this, changePasswordLog);
        // Attach the adapter to a ListView
        ListView changePasswordLogsListView = findViewById(R.id.changePasswordLogsListView);
        changePasswordLogsListView.setAdapter(changePasswordLogAdapter);

        Button btn_change_password_logs_back = findViewById(R.id.btn_change_password_logs_back);
        btn_change_password_logs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewLogsChangePasswordLogs();
            }
        });
    }

    private void goToViewLogsChangePasswordLogs() {
        Intent switchActivityIntent = new Intent(ChangePasswordLogs.this, ViewLogs.class);
        startActivity(switchActivityIntent);
    }
}