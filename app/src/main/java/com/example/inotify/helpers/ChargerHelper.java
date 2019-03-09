package com.example.inotify.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
        return chargerDbHelper.powerOninsert(date,time);
    }

    public boolean powerOffinsert() {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        return chargerDbHelper.powerOffinsert(date,time);
    }

    public int powerOnCount(String date){
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        return chargerDbHelper.powerOnCountGet(date);
    }
    public int powerOffCount(String date){
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        return chargerDbHelper.powerOffCountGet(date);
    }
}
