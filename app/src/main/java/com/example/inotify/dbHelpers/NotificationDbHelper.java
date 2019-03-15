package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.NotificationModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.inotify.configs.TbNames.NOTIFICATION_TABLE;


public class NotificationDbHelper extends MainDbHelp {

    public NotificationDbHelper(Context context) {
        super(context);
    }

    public boolean insert(NotificationModel NotificationModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NOTIFICATIONID, NotificationModel.getId());
        contentValues.put(TbColNames.DATE, NotificationModel.getDate());
        contentValues.put(TbColNames.TIMERECEVIED, NotificationModel.getTimeRecevied());
        contentValues.put(TbColNames.TIMESENT, NotificationModel.getTimeSent());
        contentValues.put(TbColNames.TIMEVIEW, NotificationModel.getTimeViewed());
        contentValues.put(TbColNames.APPNAME, NotificationModel.getAppName());
        contentValues.put(TbColNames.PACKAGENAME, NotificationModel.getPackageName());

        long result = db.insert(NOTIFICATION_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean insert(String myNotificationId,String date,String packageName,String timeRecevied,String timeSent,String timeViewed) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TbColNames.NOTIFICATIONID, myNotificationId);
            contentValues.put(TbColNames.DATE, date);
            contentValues.put(TbColNames.PACKAGENAME, packageName);
            contentValues.put(TbColNames.TIMERECEVIED, timeRecevied);
            contentValues.put(TbColNames.TIMESENT, timeSent);
            contentValues.put(TbColNames.TIMEVIEW, timeViewed);

            long result = db.insert(NOTIFICATION_TABLE, null, contentValues);
            db.close();
        return result != -1;
    }


    //Get the notification recived time  -Cha

    public String recivedTimeGet(String id){
        String notificationRecivedTime = new String();

        SQLiteDatabase db = this.getReadableDatabase();
      //  String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select * from "+ TbNames.NOTIFICATION_TABLE + " where NOTIFICATIONID =\"" +id + "\"",null);
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

        Cursor res = db.rawQuery(	"select * from " + TbNames.NOTIFICATION_TABLE +" where NOTIFICATIONID =\"" + id + "\"",null);
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

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.NOTIFICATION_TABLE +" where NOTIFICATIONID =\"" + id + "\"", null);
        if(res !=null)
        {
            if(res.moveToFirst()){
                return res.getString(5);
            }
            res.close();
        }
        return null;

    }

    public List<NotificationModel> allAppInfoGet()
    {
        List<NotificationModel> notificationModelList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.NOTIFICATION_TABLE, null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {
                    NotificationModel notificationModel = new NotificationModel();
                    notificationModel.setId( res.getString(res.getColumnIndex(TbColNames.NOTIFICATIONID)));
                    notificationModel.setDate( res.getString(res.getColumnIndex(TbColNames.DATE)));
                    notificationModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    notificationModel.setPackageName( res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    notificationModel.setTimeRecevied( res.getString(res.getColumnIndex(TbColNames.TIMERECEVIED)));
                    notificationModel.setTimeSent( res.getString(res.getColumnIndex(TbColNames.TIMESENT)));
                    notificationModel.setTimeViewed( res.getString(res.getColumnIndex(TbColNames.TIMEVIEW)));

                    notificationModelList.add(notificationModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return notificationModelList;

    }

    public List<NotificationModel> allGet(){

        List<NotificationModel> listNotificationModel = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.NOTIFICATION_TABLE , null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    NotificationModel notificationModel = new NotificationModel();
                    notificationModel.setId(res.getString(res.getColumnIndex(TbColNames.NOTIFICATIONID)));
                    notificationModel.setDate(res.getString(res.getColumnIndex(TbColNames.DATE)));
                    notificationModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    notificationModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    notificationModel.setSmartNotification(res.getString(res.getColumnIndex(TbColNames.SMARTNOTIFICATION)));
                    notificationModel.setTimeRecevied(res.getString(res.getColumnIndex(TbColNames.TIMERECEVIED)));
                    notificationModel.setTimeSent(res.getString(res.getColumnIndex(TbColNames.TIMESENT)));
                    notificationModel.setTimeViewed(res.getString(res.getColumnIndex(TbColNames.TIMEVIEW)));

                    listNotificationModel.add(notificationModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listNotificationModel;

    }

}
