package com.example.inotify.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.ChargerDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChargerHelper {

    private Context c1;

    public ChargerHelper(Context context) {
        this.c1 = context;
    }



    public boolean powerOninsert() {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        Log.d("inotify","poweron" + chargerDbHelper.powerOninsert(date,time));

        return chargerDbHelper.powerOninsert(date,time);
    }

    public boolean powerOffinsert() {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        Log.d("inotify","poweroff" + chargerDbHelper.powerOffinsert(date,time));

        return chargerDbHelper.powerOffinsert(date,time);
    }

    public int powerOnCount(String date){
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        Log.d("inotify","Power count" + chargerDbHelper.powerOnCountGet(date));
        return chargerDbHelper.powerOnCountGet(date);
    }
    public int powerOffCount(String date){
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        Log.d("inotify","Power count" + chargerDbHelper.powerOffCountGet(date));

        return chargerDbHelper.powerOffCountGet(date);
    }
}
