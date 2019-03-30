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
    private static NotificationDbHelper mInstance = null;


    public static NotificationDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new NotificationDbHelper(context.getApplicationContext());
        }
        return mInstance;
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
        contentValues.put(TbColNames.TITLE, NotificationModel.getTitle());
        contentValues.put(TbColNames.CONTENT, NotificationModel.getContent());
        contentValues.put(TbColNames.SMARTNOTIFICATION, NotificationModel.getSmartNotification());

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
                return res.getString(3);
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
                return res.getString(4);
            }
            res.close();
        }
        return null;

    }




    //update method
           // Get the time and update the notificationviwedtime

  /*  public String updateNotificationViwedTime(String id){


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
    }*/


    //get method for appname
    public String AppnameGet(String id)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.NOTIFICATION_TABLE +" where NOTIFICATIONID =\"" + id + "\"", null);
        if(res !=null)
        {
            if(res.moveToFirst()){
                return res.getString(6);
            }
            res.close();
        }
        return null;

    }

    public List<NotificationModel> allNotificationInfoGet()
    {
        List<NotificationModel> notificationModelList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.NOTIFICATION_TABLE +" ORDER BY "+TbColNames.NOTIFICATIONID+" DESC ", null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {
                    NotificationModel notificationModel = new NotificationModel();
                    notificationModel.setId( res.getString(res.getColumnIndex(TbColNames.NOTIFICATIONID)));
                    notificationModel.setDate( res.getString(res.getColumnIndex(TbColNames.DATE)));
                    notificationModel.setTimeRecevied( res.getString(res.getColumnIndex(TbColNames.TIMERECEVIED)));
                    notificationModel.setTimeSent( res.getString(res.getColumnIndex(TbColNames.TIMESENT)));
                    notificationModel.setTimeViewed( res.getString(res.getColumnIndex(TbColNames.TIMEVIEW)));
                    notificationModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    notificationModel.setPackageName( res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    notificationModel.setTitle( res.getString(res.getColumnIndex(TbColNames.TITLE)));
                    notificationModel.setContent( res.getString(res.getColumnIndex(TbColNames.CONTENT)));
                    notificationModel.setSmartNotification( res.getString(res.getColumnIndex(TbColNames.SMARTNOTIFICATION)));

                    notificationModelList.add(notificationModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return notificationModelList;

    }


    public boolean updateNotificationViewTime(String notificationid , String timeView)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        //contentValues.put(TbColNames.NOTIFICATIONID ,notificationid);
        contentValues.put(TbColNames.TIMEVIEW ,timeView);
        String where = "NOTIFICATIONID = ?";
        String[] whereargs =new String[]{String.valueOf(notificationid)};
        long res =db.update(TbNames.NOTIFICATION_TABLE,contentValues , where ,whereargs);
        db.close();
        return res!=-1;

    }
}
