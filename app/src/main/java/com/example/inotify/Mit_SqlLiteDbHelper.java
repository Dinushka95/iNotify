package com.example.inotify;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Mit_SqlLiteDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AppInotify.db";

    public static final String DATE = "date";
    public static final String APPNAME = "appname";
    public static final String COUNT = "count";

    public static final String MIT_CHARGE_TABLE = "mit_charge_table";


    public static final String MIT_APPLISTCOUNT_TABLE = "mit_applistcount_table";


    public static final String MIT_USAGECOUNT_TABLE = "mit_usagecount_table";

    public static final String MIT_CONTACTCOUNT_TABLE = "mit_contactcount_table";

    public static final String MIT_SCREENTIME_TABLE = "mit_screentime_table";
    public static final String TIME = "time";

    public static final String MIT_CALLDURATION_TABLE = "mit_callduration_table";

    public static final String MIT_CALENDEREVENTCOUNT_TABLE = "mit_calendereventcount_table";




    public Mit_SqlLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean mit_charge_insert() {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        long result = db.insert(MIT_CHARGE_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean mit_appListcount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_APPLISTCOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean mit_appusagecount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_USAGECOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean mit_contactCount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_CONTACTCOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean mit_screentime_insert(String time) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        long result = db.insert(MIT_SCREENTIME_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean mit_callduration_insert(String time) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        long result = db.insert(MIT_CALLDURATION_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }



    public boolean mit_calenderEventCount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_CALENDEREVENTCOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }



}
