package com.example.inotify.smartNotificationSystem;

public class SNS_SNSModel {

    String day;
    String time;
    String busyornot;
    String attentiviness;
    String userchaacteristics;
    String notificationtype;
    String appname;
    String vtime;

    public SNS_SNSModel() {
    }

    public SNS_SNSModel(String day, String time, String busyornot, String attentiviness, String userchaacteristics, String notificationtype, String appname) {
        this.day = day;
        this.time = time;
        this.busyornot = busyornot;
        this.attentiviness = attentiviness;
        this.userchaacteristics = userchaacteristics;
        this.notificationtype = notificationtype;
        this.appname = appname;
    }

    public SNS_SNSModel(String day, String time, String busyornot, String attentiviness, String userchaacteristics, String notificationtype, String appname, String vtime) {
        this.day = day;
        this.time = time;
        this.busyornot = busyornot;
        this.attentiviness = attentiviness;
        this.userchaacteristics = userchaacteristics;
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

    public String getBusyornot() {
        return busyornot;
    }

    public void setBusyornot(String busyornot) {
        this.busyornot = busyornot;
    }

    public String getAttentiviness() {
        return attentiviness;
    }

    public void setAttentiviness(String attentiviness) {
        this.attentiviness = attentiviness;
    }

    public String getUserchaacteristics() {
        return userchaacteristics;
    }

    public void setUserchaacteristics(String userchaacteristics) {
        this.userchaacteristics = userchaacteristics;
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
