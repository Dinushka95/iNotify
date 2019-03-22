package com.example.inotify.models;

public class AttributeCountModel {

    private String attributecountId;
    private String date;
    private String appcount;
    private String scrrenontimecount;
    private String chargingcount;
    private String contactcount;

    public String getContactcount() {
        return contactcount;
    }

    public void setContactcount(String contactcount) {
        this.contactcount = contactcount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppcount() {
        return appcount;
    }

    public void setAppcount(String appcount) {
        this.appcount = appcount;
    }

    public String getScrrenontimecount() {
        return scrrenontimecount;
    }

    public void setScrrenontimecount(String scrrenontimecount) {
        this.scrrenontimecount = scrrenontimecount;
    }

    public String getChargingcount() {
        return chargingcount;
    }

    public void setChargingcount(String chargingcount) {
        this.chargingcount = chargingcount;
    }

    public String getAttributecountId() {
        return attributecountId;
    }

    public void setAttributecountId(String attributecountId) {
        this.attributecountId = attributecountId;
    }
}
