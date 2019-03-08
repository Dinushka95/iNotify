package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.NotificationSqlLiteDbHelper;

public class NotificationHelper {

    private Context c1;

    public NotificationHelper(Context context) {
        c1=context;
    }

    public boolean insert(String NotificationId,String date,String packageName,String timeRecevied){

        NotificationSqlLiteDbHelper notificationSqlLiteDbHelper = new NotificationSqlLiteDbHelper(c1);
        return notificationSqlLiteDbHelper.insert(NotificationId,date,packageName,timeRecevied);
    }
}
