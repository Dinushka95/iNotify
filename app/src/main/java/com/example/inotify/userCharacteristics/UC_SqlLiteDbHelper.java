package com.example.inotify.userCharacteristics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.dbHelpers.MainSqlliteOpenHelp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UC_SqlLiteDbHelper extends MainSqlliteOpenHelp {

    public static final String DATABASE_NAME = "AppInotify.db";

    public static final String DATE = "date";

    public static final String APPNAME = "appname";

    public static final String COUNT = "count";

    public static final String MIT_CHARGE_TABLE = "mit_charge_table";


    public static final String MIT_APPLISTCOUNT_TABLE = "mit_applistcount_table";


    public static final String MIT_USAGECOUNT_TABLE = "mit_usagecount_table";

    public static final String MIT_CONTACTCOUNT_TABLE = "mit_contactcount_table";

    public static final String MIT_SCREENTIME_TABLE = "mit_screentime_table";
    public static final String TIME = "time";

    public static final String MIT_CALLDURATION_TABLE = "mit_callduration_table";

    public static final String MIT_CALENDEREVENTCOUNT_TABLE = "mit_calendereventcount_table";

    public static final String MIT_APPLISTSOCIALMEDIACOUNT_TABLE = "mit_applistsocialmediacount_table";


    public UC_SqlLiteDbHelper(Context context) {
        super(context);

    }


    public boolean mit_charge_insert() {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        long result = db.insert(MIT_CHARGE_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean appListcount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_APPLISTCOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean appusagecount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_USAGECOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean contactCount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_CONTACTCOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean screentime_insert(String time) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        long result = db.insert(MIT_SCREENTIME_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean callduration_insert(String time) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        long result = db.insert(MIT_CALLDURATION_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }



    public boolean calenderEventCount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_CALENDEREVENTCOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean appListSocialMediacount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(MIT_APPLISTSOCIALMEDIACOUNT_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;

    }



    public long appUsageAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from mit_usagecount_table", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long applistAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from mit_applistcount_table", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long contactsAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from mit_contactcount_table", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long screentimeAverage_get() {

        //TODO - need to sumation of day and the get average of that for days
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(TIME) as avg from mit_screentime_table", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long calldurationAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(TIME) as avg from mit_callduration_table", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long calendereventAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from mit_calendereventcount_table", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long chargeAverage_get() {

        //TODO - need to sumation of day and the get average of that for days
        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(DATE) as avg from mit_charge_table ", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long applistsocialmediaAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from mit_applistsocialmediacount_table", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    public long appUsageLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from mit_usagecount_table", null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long applistLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT  from mit_applistcount_table", null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long contactsLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from mit_contactcount_table", null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long screentimeLast_get() {

        //TODO - need to sumation of day and the get average of that for days
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select TIME from mit_screentime_table", null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("TIME"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long calldurationLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mit_callduration_table", null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(2);
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long calendereventLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from mit_calendereventcount_table", null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long chargeLast_get() {

        //TODO - need to sumation of day and the get average of that for days
        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select DATE from mit_charge_table ", null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("DATE"));
            }
        }
        res.close();
        db.close();

        return 0;
    }

    public long applistsocialmediaLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from mit_applistsocialmediacount_table", null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        res.close();
        db.close();

        return 0;
    }



}
