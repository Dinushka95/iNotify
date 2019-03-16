package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbColNames.COUNT;
import static com.example.inotify.configs.TbColNames.DATE;
import static com.example.inotify.configs.TbColNames.TIME;
import static com.example.inotify.configs.TbNames.APPLISTCOUNT_TABLE;
import static com.example.inotify.configs.TbNames.APPLISTSOCIALMEDIACOUNT_TABLE;
import static com.example.inotify.configs.TbNames.CALENDEREVENTCOUNT_TABLE;
import static com.example.inotify.configs.TbNames.CALLDURATION_TABLE;

import static com.example.inotify.configs.TbNames.CONTACTCOUNT_TABLE;
import static com.example.inotify.configs.TbNames.SCREENTIME_TABLE;


public class UC_DbHelper extends MainDbHelp {


    public UC_DbHelper(Context context) {
        super(context);

    }


    public boolean appListcount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(APPLISTCOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;

    }


    public boolean contactCount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(CONTACTCOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean screentime_insert(String time) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        long result = db.insert(SCREENTIME_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean callduration_insert(String time) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        long result = db.insert(CALLDURATION_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }


    public boolean calenderEventCount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(CALENDEREVENTCOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean appListSocialMediacount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(APPLISTSOCIALMEDIACOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;

    }

    public long applistAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from " + APPLISTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        if (res != null) {
            res.close();
        }
        db.close();

        return 0;
    }

    public long contactsAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from " + CONTACTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        if (res != null) {
            res.close();
        }
        db.close();

        return 0;
    }

    public long screentimeAverage_get() {

        //TODO - need to sumation of day and the get average of that for days
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(TIME) as avg from " + SCREENTIME_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long calldurationAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(TIME) as avg from " + CALLDURATION_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long calendereventAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from " + CALENDEREVENTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long chargeAverage_get() {

        //TODO - need to sumation of day and the get average of that for days
        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(DATE) as avg from " + TbNames.CHARGER_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long applistsocialmediaAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from " + APPLISTSOCIALMEDIACOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }


    public long applistLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT  from " + APPLISTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())) {
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long contactsLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from " + CONTACTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())) {
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long screentimeLast_get() {

        //TODO - need to sumation of day and the get average of that for days
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select TIME from " + SCREENTIME_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())) {
                return res.getLong(res.getColumnIndex("TIME"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long calldurationLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CALLDURATION_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())) {
                return res.getLong(2);
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long calendereventLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from " + CALENDEREVENTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())) {
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long chargeLast_get() {

        //TODO - need to sumation of day and the get average of that for days
        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select DATE from " + TbNames.CHARGER_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())) {
                return res.getLong(res.getColumnIndex("DATE"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long applistsocialmediaLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from " + APPLISTSOCIALMEDIACOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())) {
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long applistAverageWithoutLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT - 1) as avg from " + APPLISTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        if (res != null) {
            res.close();
        }
        db.close();

        return 0;
    }
}
