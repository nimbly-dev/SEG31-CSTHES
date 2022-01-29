package com.yorme.fdma.app.Settings;

public class SettingsListData {

    private String settingName;
    private int settingImage;

    public SettingsListData(String settingName, int settingImage) {
        this.settingName = settingName;
        this.settingImage = settingImage;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public int getSettingImage() {
        return settingImage;
    }

    public void setSettingImage(int settingImage) {
        this.settingImage = settingImage;
    }
}
