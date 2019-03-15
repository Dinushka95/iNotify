package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.NotificationDbHelper;
import com.example.inotify.models.NotificationModel;

import java.util.List;

public class NotificationHelper {

    private Context c1;

    public NotificationHelper(Context context) {
        c1=context;
    }

    public boolean insert(NotificationModel NotificationModel){

        NotificationDbHelper notificationDbHelper = new NotificationDbHelper(c1);
        return notificationDbHelper.insert(NotificationModel);
    }
    public List<NotificationModel> allAppInfoGet(){
        NotificationDbHelper notificationDbHelper = new NotificationDbHelper(c1);
        return notificationDbHelper.allGet();
    }
}
