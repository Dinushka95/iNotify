package com.example.inotify.models;

public class SNSModel {

    private String day;
    private String time;
    private String viewability;
    private String attentiviness;
    private String userchacteristics;
    private String notificationtype;
    private String appname;
    private String vtime;

    public SNSModel() {
    }

    public SNSModel(String day, String time, String viewability, String attentiviness, String userchacteristics, String notificationtype, String appname) {
        this.day = day;
        this.time = time;
        this.viewability = viewability;
        this.attentiviness = attentiviness;
        this.userchacteristics = userchacteristics;
        this.notificationtype = notificationtype;
        this.appname = appname;
    }

    public SNSModel(String day, String time, String viewability, String attentiviness, String userchacteristics, String notificationtype, String appname, String vtime) {
        this.day = day;
        this.time = time;
        this.viewability = viewability;
        this.attentiviness = attentiviness;
        this.userchacteristics = userchacteristics;
        this.notificationtype = notificationtype;
        this.appname = appname;
        this.vtime = vtime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getViewability() {
        return viewability;
    }

    public void setViewability(String viewability) {
        this.viewability = viewability;
    }

    public String getAttentiviness() {
        return attentiviness;
    }

    public void setAttentiviness(String attentiviness) {
        this.attentiviness = attentiviness;
    }

    public String getUserchacteristics() {
        return userchacteristics;
    }

    public void setUserchacteristics(String userchacteristics) {
        this.userchacteristics = userchacteristics;
    }

    public String getNotificationtype() {
        return notificationtype;
    }

    public void setNotificationtype(String notificationtype) {
        this.notificationtype = notificationtype;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getVtime() {
        return vtime;
    }

    public void setVtime(String vtime) {
        this.vtime = vtime;
    }
}
