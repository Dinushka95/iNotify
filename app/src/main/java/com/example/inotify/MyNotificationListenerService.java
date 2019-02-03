package com.example.inotify;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class MyNotificationListenerService extends NotificationListenerService {

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        SqlLiteDbHelper sqlLiteDbHelper = new SqlLiteDbHelper(this);
        sqlLiteDbHelper.pra_notificationRemove_insert();
    }
}
