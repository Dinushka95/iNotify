package com.example.inotify.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
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

        return ChargerDbHelper.getInstance(c1).powerOninsert(date,time);
    }

    public boolean powerOffinsert() {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        Log.d("inotify","poweroff" + chargerDbHelper.powerOffinsert(date,time));

        return ChargerDbHelper.getInstance(c1).powerOffinsert(date,time);
    }

    public int powerOnCount(){
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        Log.d("inotify","Power ON count" + chargerDbHelper.powerOnCountGet());
        return ChargerDbHelper.getInstance(c1).powerOnCountGet();
    }
    public int powerOffCount(){
        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(c1);
        Log.d("inotify","Power OFF count" + chargerDbHelper.powerOffCountGet());

        return ChargerDbHelper.getInstance(c1).powerOffCountGet();
    }
    public void  powerOninsertOnAvailability()
    {
        if(! ApplicationDbHelper.getInstance(c1).cheackAvailability(TbNames.CHARGER_TABLE))
        {
            this.powerOninsert();
        }

    }
}
