package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.ContactsHelper;
import com.example.inotify.helpers.ScreenOnTimeHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AttributeCountDbHelper extends MainDbHelp {
    private static AttributeCountDbHelper mInstance = null;

    private  Context c1;
    public AttributeCountDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public static AttributeCountDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new AttributeCountDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }


    public boolean atrributeCountInsert()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);
        int appCount = applicationsHelper.appCountGetToday();
        ScreenOnTimeHelper screenOnTimeHelper = new ScreenOnTimeHelper(c1);
        int screenOnTimeCount = screenOnTimeHelper.screenOnTimeTodayGet();
        ContactsHelper contactsHelper = new ContactsHelper(c1);
        int contactCount = contactsHelper.getcontactToday();
        ChargerHelper chargerHelper = new ChargerHelper(c1);
        int chageCount = chargerHelper.powerOnCount();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.APPCOUNT,appCount);
        contentValues.put(TbColNames.SCREENONTIMECOUNT,screenOnTimeCount);
        Log.d("inotify","screenontime"+screenOnTimeHelper.screenOnTimeTodayGet());
        contentValues.put(TbColNames.CHARGINGCOUNT,chageCount);
        contentValues.put(TbColNames.CONTACTCOUNT,contactCount);
        Log.d("inotify","contactCount------"+contactCount);

        contentValues.put(TbColNames.DATE,date);

        long result = db.insert(TbNames.ATTRIBUTECOUNT_TABLE, null, contentValues);
        db.close();
        return true;
    }


    public long ScreenOnTimeCountAvgGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select SUM(SCREENONTIMECOUNT) as COUNTAVG from " + TbNames.ATTRIBUTECOUNT_TABLE , null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("COUNTAVG"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg=total/count;
        }catch (Exception e){
            return 0;
        }

        return avg;
    }

    public long appCountAvgGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select SUM(APPCOUNT) as COUNTAVG from " + TbNames.ATTRIBUTECOUNT_TABLE , null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("COUNTAVG"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg=total/count;
        }catch (Exception e){
            return 0;
        }

        return avg;
    }

    public long chargingCountAvgGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select SUM(CHARGINGCOUNT) as COUNTAVG from " + TbNames.ATTRIBUTECOUNT_TABLE , null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("COUNTAVG"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg=total/count;
        }catch (Exception e){
            return 0;
        }

        return avg;
    }

        public long ContactsAvgGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select SUM("+ TbColNames.APPCOUNT +") as COUNTAVG from " + TbNames.ATTRIBUTECOUNT_TABLE , null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("COUNTAVG"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg=total/count;
        }catch (Exception e){
            return 0;
        }

        return avg;
    }

    public int powerOffCountGetAVG() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select AVG(CHARGINGCOUNT) from " + TbNames.ATTRIBUTECOUNT_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){
                Log.d("inotify","powerOffCountGet----"+res.getCount());
                return res.getCount();
            }
        }
        db.close();
        return 0;
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
