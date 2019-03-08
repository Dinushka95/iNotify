package com.example.inotify.models;

public class NotificationModel {

    private String id;
    private String datetime;
    private String timeRecevied;
    private String timeSent;
    private String timeViewed;
    private String appName;
    private String packageName;

    public NotificationModel(String id, String datetime, String timeRecevied, String timeSent, String timeVievwed, String appName, String packageName){
        this.id = id;
        this.datetime = datetime;
        this.timeRecevied = timeRecevied;
        this.timeSent = timeSent;
        this.timeViewed = timeVievwed;
        this.appName = appName;
        this.packageName = packageName;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTimeRecevied() {
        return timeRecevied;
    }

    public void setTimeRecevied(String timeRecevied) {
        this.timeRecevied = timeRecevied;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public String getTimeViewed() {
        return timeViewed;
    }

    public void setTimeViewed(String timeViewed) {
        this.timeViewed = timeViewed;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
