package com.example.inotify.dbHelpers;

import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.TrafficStats;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static android.content.Context.ACTIVITY_SERVICE;

public class DataUsageDbHelper extends MainDbHelp {
    public Context c1;
    private static DataUsageDbHelper mInstance = null;


    public DataUsageDbHelper(Context context) {
        super(context);
        this.c1=context;
    }
    public static DataUsageDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new DataUsageDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public boolean insertTotoalDataUsage(String date,String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.DATE, date);
        contentValues.put(TbColNames.AMOUNT, amount);
        long result = db.insert(TbNames.DATAUSAGE_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public void  insertTotoalDataUsageAvailability(String date,String amount)
    {
        if(! ApplicationDbHelper.getInstance(c1).cheackAvailability(TbNames.CHARGER_TABLE))
        {
            this.insertTotoalDataUsage(date,amount);
        }

    }

    public String getTotalDataUsage(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.DATAUSAGE_TABLE + " where DATE = \"" + date + "\" ", null);
        if (res != null) {
            if ((res.moveToFirst())) {

                db.close();
                return res.getString(res.getColumnIndex(TbColNames.AMOUNT));
            }
        }
        db.close();
        return "";
    }

    public String getDataUsageAvg() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select SUM(" + TbColNames.AMOUNT + ") as USAGEAMOUNT from " + TbNames.DATAUSAGE_TABLE, null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())) {
                do {
                    total = total + res.getInt(res.getColumnIndex("USAGEAMOUNT"));
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
