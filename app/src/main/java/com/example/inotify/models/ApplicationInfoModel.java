package com.example.inotify.models;

public class ApplicationInfoModel {

    private int id;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String appName;
    private String appCategory;
    private String pakageName;
    private String inotifystate;

    public ApplicationInfoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPakageName() {
        return pakageName;
    }

    public void setPakageName(String pakageName) {
        this.pakageName = pakageName;
    }

    public String getInotifystate() {
        return inotifystate;
    }

    public void setInotifystate(String inotifystate) {
        this.inotifystate = inotifystate;
    }
}
