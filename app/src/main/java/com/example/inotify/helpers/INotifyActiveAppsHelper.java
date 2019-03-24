package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.INotifiyActiveAppsDbHelper;

import java.util.List;

public class INotifyActiveAppsHelper {

    private Context c1;

    public INotifyActiveAppsHelper(Context context) {
    c1=context;
    }

    public List<String> getINotifyActiveApps(){
        INotifiyActiveAppsDbHelper iNotifiyActiveAppsDbHelper = new INotifiyActiveAppsDbHelper(c1);
        return  iNotifiyActiveAppsDbHelper.getINotifyActiveApps();
    }

}
