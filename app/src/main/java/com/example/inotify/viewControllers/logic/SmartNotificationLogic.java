package com.example.inotify.viewControllers.logic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.inotify.helpers.NotificationHelper;
import com.example.inotify.models.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class SmartNotificationLogic {

    public Context c1;

    public SmartNotificationLogic(Context context) {
        c1= context;
    }

    public List<NotificationModel> getNotificationList(){
        NotificationHelper notificationHelper = new NotificationHelper(c1);
        return  notificationHelper.allAppInfoGet();
    }
}
