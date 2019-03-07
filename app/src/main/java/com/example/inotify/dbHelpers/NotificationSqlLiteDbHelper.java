package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import static com.example.inotify.configs.TbColNames.NOTIFICATION_ID;
import static com.example.inotify.configs.TbColNames.DATE;
import static com.example.inotify.configs.TbColNames.TIMERECEVIED;
import static com.example.inotify.configs.TbColNames.TIMESENT;
import static com.example.inotify.configs.TbColNames.TIMEVIEW;
import static com.example.inotify.configs.TbColNames.APPNAME;
import static com.example.inotify.configs.TbColNames.PACKAGENAME;
import static com.example.inotify.configs.TbNames.NOTIFICATION_TABLE;


import com.example.inotify.models.NotificationModel;


public class NotificationSqlLiteDbHelper extends MainSqlliteOpenHelp {

    public NotificationSqlLiteDbHelper(Context context) {
        super(context);
    }

    public boolean insert(NotificationModel NotificationModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTIFICATION_ID, NotificationModel.getId());
        contentValues.put(DATE, NotificationModel.getDatetime());
        contentValues.put(TIMERECEVIED, NotificationModel.getTimeRecevied());
        contentValues.put(TIMESENT, NotificationModel.getTimeSent());
        contentValues.put(TIMEVIEW, NotificationModel.getTimeViewed());
        contentValues.put(APPNAME, NotificationModel.getAppName());
        contentValues.put(PACKAGENAME, NotificationModel.getPackageName());

        long result = db.insert(NOTIFICATION_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    /*public boolean insert(String myNotificationId,String date,String packageName,String timeRecevied,String timeSent,String timeViewed) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TbColNames.NOTIFICATION_ID, myNotificationId);
            contentValues.put(TbColNames.DATE, date);
            contentValues.put(TbColNames.PACKAGENAME, packageName);
            contentValues.put(TbColNames.TIMERECEVIED, timeRecevied);
            contentValues.put(TbColNames.TIMESENT, timeSent);
            contentValues.put(TbColNames.TIMEVIEW, timeViewed);

            long result = db.insert(TbNames.NOTIFICATION_TABLE, null, contentValues);
            db.close();
            if (result == -1)
                return false;
            else
                return true;
    }*/
}
