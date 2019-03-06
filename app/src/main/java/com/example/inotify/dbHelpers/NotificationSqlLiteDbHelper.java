package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;


public class NotificationSqlLiteDbHelper extends MainSqlliteOpenHelp {

    public NotificationSqlLiteDbHelper(Context context) {
        super(context);
    }

    public boolean insert(String myNotificationId,String date,String packageName,String timeRecevied) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NOTIFICATION_ID, myNotificationId);
        contentValues.put(TbColNames.DATE, date);
        contentValues.put(TbColNames.PACKAGENAME, packageName);
        contentValues.put(TbColNames.TIMERECEVIED, timeRecevied);

        long result = db.insert(TbNames.NOTIFICATION_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insert(String myNotificationId,String date,String packageName,String timeRecevied,String timeSent,String timeViewed) {

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
    }
}
