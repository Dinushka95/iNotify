package com.example.inotify;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Din_SqlLiteDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AppInotify.db";


    // notification all notifications
    public static final String DIN_SNS_TABLE = "din_SNS_TABLE";
    public static final String SNS_ID = "sns_id";
    public static final String SNS_DATE = "sns_date";
    public static final String SNS_DAY = "sns_day";
    public static final String SNS_TIME = "sns_time";
    public static final String SNS_BUSYORNOT = "sns_busyornot";
    public static final String SNS_ATTENTIVINESS = "sns_attentiviness";
    public static final String SNS_USERCHAACTERISTICS = "sns_userchaacteristics";
    public static final String SNS_NOTIFICATIONTYPE = "sns_notificationtype";
    public static final String SNS_APPNAME = "sns_appname";
    public static final String SNS_VTIME = "sns_vtime";


    public Din_SqlLiteDbHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean saveData(String id,String busyornot, String attentiviness, String usercharacteristics,String notificationtype, String appname){

        //String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SNS_ID, id);
        contentValues.put(SNS_DATE, date);
        contentValues.put(SNS_DAY, day);
        contentValues.put(SNS_TIME, time);
        contentValues.put(SNS_BUSYORNOT, busyornot);
        contentValues.put(SNS_ATTENTIVINESS, attentiviness);
        contentValues.put(SNS_USERCHAACTERISTICS, usercharacteristics);
        contentValues.put(SNS_NOTIFICATIONTYPE, notificationtype);
        contentValues.put(SNS_APPNAME, appname);

        long result = db.insert(DIN_SNS_TABLE, null, contentValues);
        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }

    public void updateData(String id, String vtime){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues newValues = new ContentValues();
        newValues.put(SNS_VTIME, vtime);

        db.update(DIN_SNS_TABLE, newValues, "sns_id="+id, null);

    }

    public void getJSONDataALL(){
    }



}
