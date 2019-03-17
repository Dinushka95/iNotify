package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class ScreenStatusDbHelper extends MainDbHelp {



    public ScreenStatusDbHelper(Context context) {
        super(context);
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


    public String checkScreenOnAdaptability(String id)
    {
        String TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select *  from  " + TbNames.SCREENSTATUS_TABLE +"  where NOTIFICATIONID  = \" " + id + "\"" , null);
        if(res != null)
        {
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;
    }
    public String checkScreenOffAdaptability(String id)
    {
        String TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select *  from  " + TbNames.SCREENSTATUS_TABLE +"  where NOTIFICATIONID  = \" " + id + "\"" ,  null);
        if(res != null)
        {
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;
    }

    public String checkAvaulability(String id){
        String tablename = "";
        String screenonavailability = this.checkScreenOnAdaptability(id);
        String screenoffavailability = this.checkScreenOffAdaptability(id);

        if(Objects.equals(screenonavailability, "0"))
        {
            tablename = "SCREENOFF_TABLE";
        }
        else
        {
            tablename = "SCREENON_TABLE";
        }
        return tablename;

    }


}
