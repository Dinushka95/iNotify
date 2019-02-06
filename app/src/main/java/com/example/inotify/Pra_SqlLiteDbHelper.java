package com.example.inotify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Pra_SqlLiteDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AppInotify.db";

    public static final String DATE = "date";
    public static final String DAY = "day";
    public static final String TIME = "time";


    public static final String PRA_ACTIVITY_TABLE = "pra_activity_table";
    public static final String TYPE = "type";
    public static final String CONFIDENCE = "confidence";

    public static final String PRA_LOCATION_TABLE = "pra_location_table";
    public static final String LOG = "log";
    public static final String LAT = "lat";

    public static final String PRA_NOTIFICATIONREMOVE_TABLE = "pra_notificationRemove_table";

    public static final String PRA_BUSYORNOT_TABLE = "pra_busyornot_table";
    public static final String ACTIVITY = "activity";
    public static final String LOCATION = "location";
    public static final String BUSYORNOT = "busyornot";


    public static final String CHA_N_TABLE = "cha_N_TABLE";
    public static final String CHA_NI_TABLE = "cha_NI_TABLE";


    public static final String MIT_CHARGE_TABLE = "mit_charge_table";
    public static final String MIT_APPLISTCOUNT_TABLE = "mit_applistcount_table";
    public static final String MIT_USAGECOUNT_TABLE = "mit_usagecount_table";
    public static final String MIT_CONTACTCOUNT_TABLE = "mit_contactcount_table";
    public static final String MIT_SCREENTIME_TABLE = "mit_screentime_table";
    public static final String MIT_CALLDURATION_TABLE = "mit_callduration_table";
    public static final String MIT_CALENDEREVENTCOUNT_TABLE = "mit_calendereventcount_table";
    public static final String MIT_APPLISTSOCIALMEDIACOUNT_TABLE = "mit_applistsocialmediacount_table";


    public Pra_SqlLiteDbHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + PRA_ACTIVITY_TABLE + " (ACTIVITY_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT,TYPE TEXT,CONFIDENCE TEXT)");
        db.execSQL("create table " + PRA_LOCATION_TABLE + " (LOCATION_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT,LOG TEXT,LAT TEXT)");
        db.execSQL("create table " + PRA_NOTIFICATIONREMOVE_TABLE + " (NOTIFICATIONREMOVE_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT)");
        db.execSQL("create table " + PRA_BUSYORNOT_TABLE + " (BUSYORNOT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DAY TEXT,TIME TEXT,ACTIVITY TEXT,LOCATION TEXT,BUSYORNOT TEXT)");


        db.execSQL("create table " + CHA_N_TABLE + " (N_ID INTEGER,N_APPNAME TEXT,N_DATETIME INTEGER)");
        db.execSQL("create table " + CHA_NI_TABLE + " (NI_ID INTEGER PRIMARY KEY AUTOINCREMENT,NI_APPNAME TEXT,NI_VALUE INTEGER)");


        db.execSQL("create table " + MIT_CHARGE_TABLE + " (CHARGE_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT)");
        db.execSQL("create table " + MIT_APPLISTCOUNT_TABLE + " (APPLISTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + MIT_USAGECOUNT_TABLE + " (USAGECOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + MIT_CONTACTCOUNT_TABLE + " (CONTACTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + MIT_SCREENTIME_TABLE + " (SCREENTIME_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIME TEXT)");
        db.execSQL("create table " + MIT_CALLDURATION_TABLE  + " (CALLDURATION_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIME TEXT)");
        db.execSQL("create table " + MIT_CALENDEREVENTCOUNT_TABLE  + " (CALENDEREVENTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + MIT_APPLISTSOCIALMEDIACOUNT_TABLE + " (APPLISTSOCIALMEDIACOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean pra_activity_insert(String type,String confidence) {


        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(DAY, day);
        contentValues.put(TIME, time);
        contentValues.put(TYPE, type);
        contentValues.put(CONFIDENCE, confidence);
        long result = db.insert(PRA_ACTIVITY_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public String pra_activity_get() {

        String v0="A";
        String v3="B";
        String v5="C";
        String v7="D";
        String v8="E";

        ArrayList<String> AL = new ArrayList<>();



        String datenow  = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String timenow = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SimpleDateFormat df = new SimpleDateFormat("HHmm");

        Date d = null;
        try {
            d = df.parse(timenow);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, -10);
        String timeold = df.format(cal.getTime());

        SQLiteDatabase db = this.getReadableDatabase();
        // select * from pra_activity_table where DATE = "20190202" AND TIME  between "1356" and "1514"
//huge bug if it has same count for muliple activity this will fail so assume that it will not have fix later
        //get for 0
        Cursor res0 = db.rawQuery("select * from pra_activity_table where DATE=\"" + datenow + "\" AND TYPE = \"0\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res0 != null) {AL.add(String.valueOf(res0.getCount())+"V");}
        res0.close();

        //get for 3
        Cursor res3 = db.rawQuery("select * from pra_activity_table where DATE=\"" + datenow + "\" AND TYPE = \"3\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res3 != null)  {AL.add(String.valueOf(res3.getCount())+"S");}
        res3.close();

        //get for 5
        Cursor res5 = db.rawQuery("select * from pra_activity_table where DATE=\"" + datenow + "\" AND TYPE = \"5\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res5 != null) {AL.add(String.valueOf(res5.getCount())+"T");}
        res5.close();

        //get for 7
        Cursor res7 = db.rawQuery("select * from pra_activity_table where DATE=\"" + datenow + "\" AND TYPE = \"7\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res7 != null)  {AL.add(String.valueOf(res7.getCount())+"W");}
        res7.close();

        //get for 8
        Cursor res8 = db.rawQuery("select * from pra_activity_table where DATE=\"" + datenow + "\" AND TYPE = \"8\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res8 != null)  {AL.add(String.valueOf(res8.getCount())+"R");}
        res8.close();



        Collections.sort(AL, String.CASE_INSENSITIVE_ORDER);

        String value = String.valueOf(AL.get(4));
        int length = value.length();

        return String.valueOf(value.charAt(length-1));
    }



    public boolean pra_location_insert(String log,String lat) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(DAY, day);
        contentValues.put(TIME, time);
        contentValues.put(LOG, log);
        contentValues.put(LAT, lat);
        long result = db.insert(PRA_LOCATION_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Double> pra_location_get() {
            ArrayList<Double> Al = new ArrayList();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from pra_location_table", null);
        if (res != null ) {
            res.moveToLast();
            if(res.getCount()>1){
                Al.add(Double.valueOf(res.getString(4)));
                Al.add(Double.valueOf(res.getString(5)));
            }


            if (res.getCount()==0){
                Al.add(0.0);
                Al.add(0.1);
            }

        }
        res.close();

        return Al;
    }


    public boolean pra_notificationRemove_insert() {

       // String time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(DAY, day);
        contentValues.put(TIME, time);
        long result = db.insert(PRA_NOTIFICATIONREMOVE_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public Integer pra_notificationRemove_get() {

        String timenow = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SimpleDateFormat df = new SimpleDateFormat("HHmm");

        Date d = null;
        try {
            d = df.parse(timenow);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, -10);
        String timeold = df.format(cal.getTime());

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from pra_location_table WHERE TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getCount();
            }
        }
        res.close();

        return 0;
    }

    public boolean pra_BusyOrNot_insert(String time,String day,String activity,String location,String busyornot) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIME, time);
        contentValues.put(DAY, day);
        contentValues.put(ACTIVITY, activity);
        contentValues.put(LOCATION, location);
        contentValues.put(BUSYORNOT, busyornot);
        long result = db.insert(PRA_BUSYORNOT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<String> pra_BusyOrNotPredict_Get(String activity,String location) {


        String timenow = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());


        SimpleDateFormat df = new SimpleDateFormat("HHmm");

        Date d = null;
        try {
            d = df.parse(timenow);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d);
        cal1.add(Calendar.MINUTE, 10);
        String timeold = df.format(cal1.getTime());


        ArrayList<String> bon = new ArrayList();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from pra_busyornot_table WHERE TIME between \""+timeold+"\" AND \""+timenow+"\" DAY = \""+day+"\" AND ACTIVITY = \""+activity+"\" AND LOCATION = \""+location+"\"", null);
        if (res != null) {

            if (res.moveToFirst()){
                do{
                    String data = res.getString(res.getColumnIndex("BUSYORNOT"));
                    bon.add(data);
                }while(res.moveToNext());
            }
            res.close();

        }
        res.close();

        return bon;
    }

    }