package com.example.inotify.models;

public class TopAppModel {

    private  String applicationName;
    private  String category;
    private  String collection;
    private  String date;
    private  String dateCreated;
    private  String packageName;
    private  int rank;

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public TopAppModel() {
    }

    public TopAppModel(String applicationName, String category, String collection, String date, String dateCreated, String packageName, int rank) {
        this.applicationName = applicationName;
        this.category = category;
        this.collection = collection;
        this.date = date;
        this.dateCreated = dateCreated;
        this.packageName = packageName;
        this.rank = rank;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
