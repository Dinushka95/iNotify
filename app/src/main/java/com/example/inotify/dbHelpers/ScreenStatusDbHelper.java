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



public class ScreenStatusDbHelper extends MainDbHelp {



    public ScreenStatusDbHelper(Context context) {
        super(context);
    }
    //Insert to screenOn table
    String id = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.getDefault()).format(new Date());

    public boolean ScreenOnInsert(){

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
        (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
        Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NOTIFICATIONID ,id);
        contentValues.put(TbColNames.DATE , date);
        contentValues.put(TbColNames.TIMEON , time);

        long result = db.insert(TbNames.UA_SCREENON_TABLE ,null,contentValues);
        db.close();
        if(result == -1){
            return false;
        }
        else
            return true;


    }

    public boolean ScreenOffInsert(){
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NOTIFICATIONID ,id);
        contentValues.put(TbColNames.DATE , date);
        contentValues.put(TbColNames.TIMEOFF,time);

        long result = db.insert(TbNames.UA_SCREENOFF_TABLE ,null,contentValues);
        db.close();
        if(result == -1){
            return false;
        }
        else
            return true;


    }


    public String  ScreenOnStatusGet(){
        String ringermode= new String();

        SQLiteDatabase db = this.getReadableDatabase();
        String id = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());



        Cursor res = db.rawQuery("select * from " + TbNames.UA_SCREENON_TABLE +" where DATE =\"" + id + "\"", null);
        if(res != null)
        {
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;

    }


    public String  ScreenOffStatusGet(){
        String ringermode= new String();

        SQLiteDatabase db = this.getReadableDatabase();
        String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());


        Cursor res = db.rawQuery("select * from " + TbNames.UA_SCREENOFF_TABLE +" where SCREENOFF_ID =\"" + id + "\"", null);
        if(res != null)
        {
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;

    }

    public String checkScreenOnAvailablity (String id)
    {
        String TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select exists(select 1  from  " + TbNames.UA_SCREENON_TABLE +  " where NOTIFICATIONID= \" " +id+ "\")" , null);
        if(res != null)
        {
            if(res.moveToFirst()){
                //return res.getString(res.getColumnIndex(TbColNames.RM_RINGERMODE));
                return res.getString(1);
            }
            res.close();
        }
        return null;
    }
    public String checkScreenOffAvailablity(String id)
    {
        String TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select exists(select 1  from  " + TbNames.UA_SCREENOFF_TABLE +  " where NOTIFICATIONID= \" " +id+ "\")" , null);
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
        String tablename = new String();
        String screenonavailability = this.checkScreenOnAvailablity(id);
        String screenoffavailability = this.checkScreenOffAvailablity(id);

        if(screenonavailability == "0")
        {
            tablename = "UA_SCREENON_TABLE";
        }
        return tablename;

    }



}
