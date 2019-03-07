package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.configs.TableColumnNames.DATE;
import static com.example.inotify.configs.TableColumnNames.MYNOTIFICATION_ID;
import static com.example.inotify.configs.TableColumnNames.PACKAGENAME;
import static com.example.inotify.configs.TableColumnNames.TIMERECEVIED;
import static com.example.inotify.configs.TableColumnNames.TIMESENT;
import static com.example.inotify.configs.TableColumnNames.TIMEVIEW;
import static com.example.inotify.configs.TableNames.NOTIFICATION_TABLE;

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

        long result = db.insert(NOTIFICATION_TABLE, null, contentValues);
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

            long result = db.insert(NOTIFICATION_TABLE, null, contentValues);
            db.close();
            if (result == -1)
                return false;
            else
                return true;
    }


    //Get the notification recived time  -Cha

    public String recivedTimeGet(){
        String notificationRecivedTime = new String();

        SQLiteDatabase db = this.getReadableDatabase();
        String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select TIMERECEVIED from "+ NOTIFICATION_TABLE + " where NOTIFICATION_ID =\"" +id + "\"",null);
        if(res !=null){
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;
    }

// Get the time notification was viwed
    public String viewTimeGet(){
        String notificationViewTime = new String();

        SQLiteDatabase db = this.getReadableDatabase();
        String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery(	"select TIMEVIEW from " + NOTIFICATION_TABLE +" where NOTIFICATION_ID =\"" + id + "\"",null);
        if(res !=null){
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;

    }




    //update method
           // Get the time and update the notificationviwedtime

    public String updateNotificationViwedTime(){
        String notificationViwedtime = new String();
        String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("update " +NOTIFICATION_TABLE+ "set TIMEVIEW = \"" +id+ "\"" ,null);
        if(res !=null)
        {
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;
    }

}
