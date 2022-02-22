package com.yorme.fdma.core.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yorme.fdma.R;
import com.yorme.fdma.app.viewlogs.ChangePasswordLogs;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.ChangePasswordLog;

import java.util.ArrayList;

public class ChangePasswordLogAdapter extends ArrayAdapter<ChangePasswordLog> {

    public ChangePasswordLogAdapter(Context context, ArrayList<ChangePasswordLog> changePasswordLog) {
        super(context, 0, changePasswordLog);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ChangePasswordLog changePasswordLog = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview_change_password_logs, parent, false);
        }
        // Lookup view for data population
        TextView date = convertView.findViewById(R.id.changePasswordLogsTextView1);
        TextView time = convertView.findViewById(R.id.changePasswordLogsTextView2);
        // Populate the data into the template view using the data object
        date.setText(changePasswordLog.getLogDate().toString());
        time.setText(changePasswordLog.getLogTime().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}
