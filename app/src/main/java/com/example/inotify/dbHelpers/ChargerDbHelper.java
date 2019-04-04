package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ChargerDbHelper extends MainDbHelp {
    private static ChargerDbHelper mInstance = null;

    private  Context c1;
    public ChargerDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public static ChargerDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new ChargerDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public boolean powerOninsert(String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.POWERONDATE, date);
        contentValues.put(TbColNames.POWERONTIME, time);
        long result = db.insert(TbNames.CHARGER_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean powerOffinsert(String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.POWEROFFDATE, date);
        contentValues.put(TbColNames.POWEROFFTIME, time);
        long result = db.insert(TbNames.CHARGER_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public int powerOnCountGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        String date1 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select COUNT(POWERONTIME) from " + TbNames.CHARGER_TABLE + " where POWERONDATE = \""+date1+"\" ", null);
        if (res != null) {
            if ((res.moveToFirst())){
                Log.d("inotify","powerOnCountGet----"+res.getCount());
                return res.getCount();
            }
        }
        db.close();
        return 0;
    }

    public int powerOffCountGet() {

        SQLiteDatabase db = this.getReadableDatabase();
        String date1 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select COUNT(POWEROFFTIME) from " + TbNames.CHARGER_TABLE + " where POWEROFFDATE = \""+date1+"\" ", null);
        if (res != null) {
            if ((res.moveToFirst())){
                Log.d("inotify","powerOffCountGet----"+res.getCount());
                return res.getCount();
            }
        }
        db.close();
        return 0;
    }




}
