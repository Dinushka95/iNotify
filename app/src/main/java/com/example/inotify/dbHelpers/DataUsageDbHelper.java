package com.example.inotify.dbHelpers;

import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.TrafficStats;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.util.List;
import java.util.Locale;

import static android.content.Context.ACTIVITY_SERVICE;

public class DataUsageDbHelper extends MainDbHelp {
    public Context c1;

    public DataUsageDbHelper(Context context) {
        super(context);
        this.c1=context;
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
}
