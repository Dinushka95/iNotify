package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.NotificationModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.configs.TbNames.NOTIFICATION_TABLE;


public class NotificationDbHelper extends MainDbHelp {

    public NotificationDbHelper(Context context) {
        super(context);
    }

    public boolean insert(NotificationModel NotificationModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NOTIFICATION_ID, NotificationModel.getId());
        contentValues.put(TbColNames.DATE, NotificationModel.getDatetime());
        contentValues.put(TbColNames.TIMERECEVIED, NotificationModel.getTimeRecevied());
        contentValues.put(TbColNames.TIMESENT, NotificationModel.getTimeSent());
        contentValues.put(TbColNames.TIMEVIEW, NotificationModel.getTimeViewed());
        contentValues.put(TbColNames.APPNAME, NotificationModel.getAppName());
        contentValues.put(TbColNames.PACKAGENAME, NotificationModel.getPackageName());

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
            contentValues.put(TbColNames.NOTIFICATION_ID, myNotificationId);
            contentValues.put(TbColNames.DATE, date);
            contentValues.put(TbColNames.PACKAGENAME, packageName);
            contentValues.put(TbColNames.TIMERECEVIED, timeRecevied);
            contentValues.put(TbColNames.TIMESENT, timeSent);
            contentValues.put(TbColNames.TIMEVIEW, timeViewed);

            long result = db.insert(NOTIFICATION_TABLE, null, contentValues);
            db.close();
            if (result == -1)
                return false;
            else
                return true;
    }


    //Get the notification recived time  -Cha

    public String recivedTimeGet(String id){
        String notificationRecivedTime = new String();

        SQLiteDatabase db = this.getReadableDatabase();
      //  String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select * from "+ TbNames.NOTIFICATION_TABLE + " where NOTIFICATION_ID =\"" +id + "\"",null);
        if(res !=null){
            if(res.moveToFirst()){
                return res.getString(2);
            }
            res.close();
        }
        return null;
    }

// Get the time notification was viwed
    public String viewTimeGet(String id){
        String notificationViewTime = new String();

        SQLiteDatabase db = this.getReadableDatabase();
        //String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery(	"select * from " + TbNames.NOTIFICATION_TABLE +" where NOTIFICATION_ID =\"" + id + "\"",null);
        if(res !=null){
            if(res.moveToFirst()){
                return res.getString(3);
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
        Cursor res = db.rawQuery("update " +TbNames.NOTIFICATION_TABLE + "set TIMEVIEW = \"" +id+ "\"" ,null);
        if(res !=null)
        {
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;
    }


    //get method for appname
    public String AppnameGet(String id)
    {
        String appname = new String();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.NOTIFICATION_TABLE +" where NOTIFICATION_ID =\"" + id + "\"", null);
        if(res !=null)
        {
            if(res.moveToFirst()){
                return res.getString(5);
            }
            res.close();
        }
        return null;

    }

}
