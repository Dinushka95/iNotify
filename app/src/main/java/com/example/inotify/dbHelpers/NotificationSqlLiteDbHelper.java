package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static com.example.inotify.utils.TableColumnNames.APPNAME;
import static com.example.inotify.utils.TableColumnNames.DATE;
import static com.example.inotify.utils.TableColumnNames.MYNOTIFICATION_ID;
import static com.example.inotify.utils.TableColumnNames.PACKAGENAME;
import static com.example.inotify.utils.TableColumnNames.TIMERECEVIED;
import static com.example.inotify.utils.TableColumnNames.TIMESENT;
import static com.example.inotify.utils.TableColumnNames.TIMEVIEW;
import static com.example.inotify.utils.TableNames.MYNOTIFICATION_TABLE;

public class NotificationSqlLiteDbHelper extends MainSqlliteOpenHelp {

    public NotificationSqlLiteDbHelper(Context context) {
        super(context);
    }

    public boolean insert(String myNotificationId,String date,String packageName,String timeRecevied) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MYNOTIFICATION_ID, myNotificationId);
        contentValues.put(DATE, date);
        contentValues.put(PACKAGENAME, packageName);
        contentValues.put(TIMERECEVIED, timeRecevied);

        long result = db.insert(MYNOTIFICATION_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insert(String myNotificationId,String date,String packageName,String timeRecevied,String timeSent,String timeViewed) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MYNOTIFICATION_ID, myNotificationId);
            contentValues.put(DATE, date);
            contentValues.put(PACKAGENAME, packageName);
            contentValues.put(TIMERECEVIED, timeRecevied);
            contentValues.put(TIMESENT, timeSent);
            contentValues.put(TIMEVIEW, timeViewed);

            long result = db.insert(MYNOTIFICATION_TABLE, null, contentValues);
            db.close();
            if (result == -1)
                return false;
            else
                return true;
    }
}
