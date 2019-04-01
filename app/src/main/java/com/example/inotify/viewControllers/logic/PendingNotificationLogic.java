package com.example.inotify.viewControllers.logic;

import android.content.Context;

import com.example.inotify.helpers.NotificationHelper;
import com.example.inotify.models.NotificationModel;

import java.util.List;

public class PendingNotificationLogic {
    public Context c1;

    public PendingNotificationLogic(Context context) {
        this.c1 = context;
    }

    public List<NotificationModel> getNotificationList(){
        NotificationHelper notificationHelper = new NotificationHelper(c1);

        //TODO - Change the Notification Model formate to the needed formate
        return  notificationHelper.allAppInfoGet();
    }
}
