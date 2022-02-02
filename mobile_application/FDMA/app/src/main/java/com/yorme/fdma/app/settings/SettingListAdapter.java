package com.yorme.fdma.app.settings;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yorme.fdma.R;

public class SettingListAdapter extends RecyclerView.Adapter<SettingListAdapter.ViewHolder> {

    private SettingsListData[] listdata;


    public SettingListAdapter(SettingsListData[] listdata) {

        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_settings, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SettingsListData settingsListData = listdata[position];
        holder.textView.setText(listdata[position].getSettingName());
        holder.imageView.setImageResource(listdata[position].getSettingImage());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(settingsListData.getSettingName() == "Change Password"){
                    Intent switchActivityIntent = new Intent(view.getContext(), ChangePassword.class);
                    view.getContext().startActivity(switchActivityIntent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.settingsImageView);
            this.textView = (TextView) itemView.findViewById(R.id.settingsTextView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.settings_relative_layout);
        }
    }
}
