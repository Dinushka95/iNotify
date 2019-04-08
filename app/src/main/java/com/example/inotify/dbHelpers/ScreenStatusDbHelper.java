package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.logger.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class ScreenStatusDbHelper extends MainDbHelp {

    private static ScreenStatusDbHelper mInstance = null;

    public ScreenStatusDbHelper(Context context) {
        super(context);
    }

    public static ScreenStatusDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new ScreenStatusDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }


    public boolean screenOnInsert(){

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.DATE , date);
        contentValues.put(TbColNames.TIMEON , time);

        long result = db.insert(TbNames.SCREENSTATUS_TABLE,null,contentValues);
        db.close();
        return result != -1;


    }

    public boolean screenOffInsert(){
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.DATE , date);
        contentValues.put(TbColNames.TIMEOFF,time);

        long result = db.insert(TbNames.SCREENSTATUS_TABLE,null,contentValues);
        db.close();
        return result != -1;


    }

    public boolean screenOnWithNotificationInsert(String id){

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NOTIFICATIONID ,id);
        contentValues.put(TbColNames.DATE , date);
        contentValues.put(TbColNames.TIMEON , time);

        long result = db.insert(TbNames.SCREENSTATUS_TABLE,null,contentValues);
        db.close();
        return result != -1;


    }

    public boolean screenOffWithNotificationInsert(String id){
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NOTIFICATIONID ,id);
        contentValues.put(TbColNames.DATE , date);
        contentValues.put(TbColNames.TIMEOFF,time);

        long result = db.insert(TbNames.SCREENSTATUS_TABLE,null,contentValues);
        db.close();
        return result != -1;


    }
//Check Screenon Adaptabilty and check screenoffadaptability both methods are the same

//    public String checkScreenOnAdaptability(String id)
//    {
//        Log.d("inotifyC" , "iddddddddddddddddddddddddddddddddd  " +id );
//        String TableName;
//        SQLiteDatabase db = this.getReadableDatabase();
//       // Cursor res = db.rawQuery("select *  from  " + TbNames.SCREENSTATUS_TABLE +"  where NOTIFICATIONID  = \" " + id + "\"" , null);
//        Cursor res = db.rawQuery("select *  from  " + TbNames.SCREENSTATUS_TABLE +" where TIMEON not null and  NOTIFICATIONID  = \" " + id + "\"" , null);
//        if(res != null)
//        {
//            if(res.moveToFirst()){
//                return res.getString(3);
//                //return res.getString(res.getColumnIndex(TbColNames.RM_RINGERMODE));
//            }
//            res.close();
//        }
//        return null;
//    }
//    public String checkScreenOffAdaptability(String id)
//    {
//        Log.d("inotifyC" , "iddddddddddddddddddddddddddddddddd  " +id );
//
//        String TableName;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select *  from  " + TbNames.SCREENSTATUS_TABLE +"  where TIMEOFF NOT NULL AND NOTIFICATIONID  = \" " + id + "\"" ,  null);
//        if(res != null)
//        {
//            if(res.moveToFirst()){
//                return res.getString(4);
//                //return res.getString(res.getColumnIndex(TbColNames.RM_RINGERMODE));
//            }
//            res.close();
//        }
//        return null;
//    }

    public String checkScreenOnAdaptability(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("xxxxxxxxxx" , "XXXXXXXXXX  id" +id);
        Cursor res =db.rawQuery("select * from " + TbNames.SCREENSTATUS_TABLE +" where NOTIFICATIONID =\"" + id + "\" ",null);
        if(res!=null)
        {
            if(res.moveToFirst())
            {
                return  res.getString(3);
            }
        }
        return null;
    }

    public String checkScreenOffAdaptability(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("xxxxxxxxxx" , "XXXXXXXXXX  id" +id);

        Cursor res =db.rawQuery("select * from " + TbNames.SCREENSTATUS_TABLE +" where  NOTIFICATIONID =\"" + id + "\" ",null);
        if(res!=null)
        {
            if(res.moveToFirst())
            {
                return  res.getString(4);
            }
        }
        return null;

    }

    public String checkAvaulability(String id){
        String screenstatus = "";
        String screenonavailability = this.checkScreenOnAdaptability(id);
        String screenoffavailability = this.checkScreenOffAdaptability(id);

        if (screenoffavailability != null)
        {
            screenstatus = "off";

        }
        else if( screenonavailability !=null){
            screenstatus ="on";


        }

        return screenstatus;


    }


    public double screenStatusRecordCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*)" + "as count from " + TbNames.SCREENSTATUS_TABLE, null);
        res.moveToFirst();
        int count = res.getInt(res.getColumnIndex("count"));


        res.close();
        return count;
    }

    public double screenOnStatusRecordCountPerMode()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*)" + "as count from " + TbNames.SCREENSTATUS_TABLE +" where " + TbColNames.TIMEON + " not null" , null);
        res.moveToFirst();
        int count = res.getInt(res.getColumnIndex("count"));

        res.close();
        return count;
    }
    public double screenOffStatusRecordCountPerMode()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*)" + "as count from " + TbNames.SCREENSTATUS_TABLE +" where " + TbColNames.TIMEOFF + " not null" , null);
        res.moveToFirst();
        int count = res.getInt(res.getColumnIndex("count"));
        res.close();
        return count;
    }






}
