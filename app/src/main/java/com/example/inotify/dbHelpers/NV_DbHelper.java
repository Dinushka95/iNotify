package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.google.android.gms.location.ActivityRecognitionResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;



public class NV_DbHelper extends MainDbHelp {

    public NV_DbHelper(Context context) {
        super(context);
    }

    public boolean activity_insert(String type, String confidence) {


        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.DATE, date);
        contentValues.put(TbColNames.DAY, day);
        contentValues.put(TbColNames.TIME, time);
        contentValues.put(TbColNames.TYPE, type);
        contentValues.put(TbColNames.CONFIDENCE, confidence);
        long result = db.insert(TbNames.NV_ACTIVITY_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public String activity_get() {

        String v0="A";
        String v3="B";
        String v5="C";
        String v7="D";
        String v8="E";

        ArrayList<String> AL = new ArrayList<>();



        String datenow  = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String timenow = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SimpleDateFormat df = new SimpleDateFormat("HHmm",Locale.getDefault());

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
        Cursor res0 = db.rawQuery("select * from " + TbNames.NV_ACTIVITY_TABLE + " where DATE=\"" + datenow + "\" AND TYPE = \"0\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res0 != null) {AL.add(String.valueOf(res0.getCount())+"V");}
        res0.close();

        //get for 3
        Cursor res3 = db.rawQuery("select * from " + TbNames.NV_ACTIVITY_TABLE + " where DATE=\"" + datenow + "\" AND TYPE = \"3\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res3 != null)  {AL.add(String.valueOf(res3.getCount())+"S");}
        res3.close();

        //get for 5
        Cursor res5 = db.rawQuery("select * from " + TbNames.NV_ACTIVITY_TABLE + " where DATE=\"" + datenow + "\" AND TYPE = \"5\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res5 != null) {AL.add(String.valueOf(res5.getCount())+"T");}
        res5.close();

        //get for 7
        Cursor res7 = db.rawQuery("select * from " + TbNames.NV_ACTIVITY_TABLE + " where DATE=\"" + datenow + "\" AND TYPE = \"7\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res7 != null)  {AL.add(String.valueOf(res7.getCount())+"W");}
        res7.close();

        //get for 8
        Cursor res8 = db.rawQuery("select * from " + TbNames.NV_ACTIVITY_TABLE +" where DATE=\"" + datenow + "\" AND TYPE = \"8\" AND TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res8 != null)  {AL.add(String.valueOf(res8.getCount())+"R");}
        res8.close();



        Collections.sort(AL, String.CASE_INSENSITIVE_ORDER);

        String value = String.valueOf(AL.get(4));
        int length = value.length();

        return String.valueOf(value.charAt(length-1));
    }


    public boolean location_insert(String log, String lat) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.DATE, date);
        contentValues.put(TbColNames.DAY, day);
        contentValues.put(TbColNames.TIME, time);
        contentValues.put(TbColNames.LOG, log);
        contentValues.put(TbColNames.LAT, lat);
        long result = db.insert(TbNames.NV_LOCATION_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Double> location_get() {
            ArrayList<Double> Al = new ArrayList();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " +TbNames.NV_LOCATION_TABLE, null);
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


    public boolean notificationRemove_insert() {

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
        contentValues.put(TbColNames.DATE, date);
        contentValues.put(TbColNames.DAY, day);
        contentValues.put(TbColNames.TIME, time);
        long result = db.insert(TbNames.NV_NOTIFICATIONREMOVE_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public Integer notificationRemove_get() {

        String timenow = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        SimpleDateFormat df = new SimpleDateFormat("HHmm",Locale.getDefault());

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
        Cursor res = db.rawQuery("select * from "+TbNames.NV_LOCATION_TABLE+" WHERE TIME between \""+timeold+"\" AND \""+timenow+"\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getCount();
            }
        }
        res.close();

        return 0;
    }

    public boolean busyOrNot_insert(String time, String day, String activity, String location, String busyornot) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.TIME, time);
        contentValues.put(TbColNames.DAY, day);
        contentValues.put(TbColNames.ACTIVITY, activity);
        contentValues.put(TbColNames.LOCATION, location);
        contentValues.put(TbColNames.BUSYORNOT, busyornot);
        long result = db.insert(TbNames.NV_NOTIFICATIONVIEWABILITY_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    // test
    public boolean probability_insert() {

        Date currentTime = Calendar.getInstance().getTime();
        Log.d("Debug", "NOW"+currentTime);
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String currentDateTimeString = sdf.format(currentTime);
        SimpleDateFormat hour=new SimpleDateFormat("hh",Locale.getDefault());
        String currentHour = hour.format(currentTime);
        Calendar cal = Calendar.getInstance();
        String currentDay = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        Log.d("Debug", "NOW"+currentHour);
        SimpleDateFormat min=new SimpleDateFormat("mm",Locale.getDefault());
        String currentMin = min.format(currentTime);
        Log.d("Debug", "NOW"+currentMin);
        SimpleDateFormat AP=new SimpleDateFormat("a",Locale.getDefault());
        String currentAP = AP.format(currentTime);
        Log.d("Debug", "NOW"+currentAP);

        char MinOne = currentMin.charAt(0);
        Log.d("Debug", "NOW"+MinOne);

        int nextMin =0;
        int nextHour = 0;

        if(java.lang.Character.getNumericValue(MinOne) != 5){
            nextMin = java.lang.Character.getNumericValue(MinOne) +1;
            nextHour = Integer.parseInt(currentHour);
        }
        else{
            nextMin = 0;
            nextHour = Integer.parseInt(currentHour)+1;
        }


        String TimeSlot = currentHour+":"+ MinOne+"0 - "+nextHour+":"+nextMin+"0 "+ currentAP;
        Log.d("Debug", "TimeSlot = "+TimeSlot);




        String proId = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.getDefault()).format(new Date());
        String day = currentDay;
        String time = TimeSlot;
        String activity = activity_get();
        int viewor = 1;
        int notor = 0;
        double probability = 100;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(TbColNames.PROID, proId);
        contentValues2.put(TbColNames.DAY, day);
        contentValues2.put(TbColNames.TIME, time);
        contentValues2.put(TbColNames.ACTIVITY, activity);
        contentValues2.put(TbColNames.VIEWOR, viewor);
        contentValues2.put(TbColNames.NOTOR, notor);
        contentValues2.put(TbColNames.PROBABLITY, probability);
        long result = db.insert(TbNames.NV_PROBABILITY_TABLE, null, contentValues2);
        db.close();
        Log.d("hello", "probability_insert: correctly");
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<String> busyOrNotPredict_Get(String activity, String location) {


        String timenow = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());


        SimpleDateFormat df = new SimpleDateFormat("HHmm",Locale.getDefault());

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
        Cursor res = db.rawQuery("select * from "+TbNames.NV_NOTIFICATIONVIEWABILITY_TABLE+" WHERE TIME between \""+timeold+"\" AND \""+timenow+"\"AND  DAY = \""+day+"\" AND ACTIVITY = \""+activity+"\" AND LOCATION = \""+location+"\"", null);
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
