package com.example.inotify.dbHelpers;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.telecom.Call;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.CallDurationModel;
import com.example.inotify.models.CallLogModel;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CallUsageDbHelper extends MainDbHelp {

    private static CallUsageDbHelper mInstance = null;
    private Context c1;

    public CallUsageDbHelper(Context context) {
        super(context);
        this.c1 = context;
    }

    public static CallUsageDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new CallUsageDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public boolean insertTodayTotalCallDuration(String date,String duration) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.DATE, date);
        contentValues.put(TbColNames.DURATION, duration);
        long result = db.insert(TbNames.CALLDURATION_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public String getTotalCallDuration(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select SUM(" + TbColNames.DURATION + ") as USAGETIME from " + TbNames.CALLDURATION_TABLE + " where DATE = \"" + date + "\" ", null);
        if (res != null) {
            if ((res.moveToFirst())) {

                db.close();
                return res.getString(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return "";
    }

    public String getCallTotalCallDurationAvg() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select SUM(" + TbColNames.TIME + ") as USAGETIME from " + TbNames.CALLDURATION_TABLE, null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())) {
                do {
                    total = total + res.getInt(res.getColumnIndex("USAGETIME"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg = total / count;
        } catch (Exception e) {
            return "";
        }

        return String.valueOf(avg);
    }

    public boolean cheackAvailability(String TableName )
    {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableName +" where DATE =\"" + date + "\"", null);

        if(res.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
