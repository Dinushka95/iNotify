package com.example.inotify.viewControllers.logic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.inotify.helpers.INotifyActiveAppsHelper;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class INotifyActiveAppsLogic {

    public Context c1;

    public INotifyActiveAppsLogic(Context context) {
        c1 = context;
    }

    public List<ApplicationInfoModel> getAllActtiveAppsNotificationList(){
        INotifyActiveAppsHelper iNotifyActiveAppsHelper = new INotifyActiveAppsHelper(c1);
        return  iNotifyActiveAppsHelper.getAllINotifyActiveApps();
    }

    public void createTable() {

    }

    public ArrayList<String> getVales() {
        return null;
    }

    public boolean nullcheck() {
        return false;
    }

    public void updateValue(String key, String value) {

    }


    // check if db tb null
    // all objects create
    // tb null check
    // get all values list
    // default all off
    // turn the values in table to on
    // display object


}
