package com.example.inotify.models;

public class AppUsageModel {

    private String appUsageId;
    private String date;
    private String time;
    private String packageName;
    private String appName;

    public String getAppUsageId() {
        return appUsageId;
    }

    public void setAppUsageId(String appUsageId) {
        this.appUsageId = appUsageId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    public String getUsageTime() {
        return UsageTime;
    }

    public void setUsageTime(String usageTime) {
        UsageTime = usageTime;
    }

    private String appCategory;
    private String UsageTime;
}
