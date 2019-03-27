package com.example.inotify.models;

public class NotificationModel {

    private String id;
    private String date;
    private String timeRecevied;
    private String timeSent;
    private String timeViewed;
    private String appName;
    private String packageName;
    private String title;
    private String content;
    private String smartNotification;

    public NotificationModel() {

    }

    public NotificationModel(String id, String date, String timeRecevied, String timeSent, String timeViewed, String appName, String packageName, String title, String content, String smartNotification) {
        this.id = id;
        this.date = date;
        this.timeRecevied = timeRecevied;
        this.timeSent = timeSent;
        this.timeViewed = timeViewed;
        this.appName = appName;
        this.packageName = packageName;
        this.title = title;
        this.content = content;
        this.smartNotification = smartNotification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSmartNotification() {
        return smartNotification;
    }

    public void setSmartNotification(String smartNotification) {
        this.smartNotification = smartNotification;
    }
}
