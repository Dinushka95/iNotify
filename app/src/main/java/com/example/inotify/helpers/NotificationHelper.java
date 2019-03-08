package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.NotificationSqlLiteDbHelper;
import com.example.inotify.models.NotificationModel;

public class NotificationHelper {

    private Context c1;

    public NotificationHelper(Context context) {
        c1=context;
    }

    public boolean insert(NotificationModel NotificationModel){

        NotificationSqlLiteDbHelper notificationSqlLiteDbHelper = new NotificationSqlLiteDbHelper(c1);
        return notificationSqlLiteDbHelper.insert(NotificationModel);
    }
}
