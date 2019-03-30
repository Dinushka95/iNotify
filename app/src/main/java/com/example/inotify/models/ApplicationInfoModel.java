package com.example.inotify.models;

import android.graphics.drawable.Drawable;

public class ApplicationInfoModel {

    private int id;
    private Drawable icon;
    private String date;
    private String appName;
    private String appCategory;
    private String packageName;
    private String inotifystate;


    public ApplicationInfoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCategory() {
        return appCategory;
    }

    public void setAppCategory(String appCategory) {
        this.appCategory = appCategory;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getInotifystate() {
        return inotifystate;
    }

    public void setInotifystate(String inotifystate) {
        this.inotifystate = inotifystate;
    }
}
