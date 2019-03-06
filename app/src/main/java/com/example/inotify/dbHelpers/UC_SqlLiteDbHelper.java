package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.dbHelpers.MainSqlliteOpenHelp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TableColumnNames.COUNT;
import static com.example.inotify.configs.TableColumnNames.DATE;
import static com.example.inotify.configs.TableColumnNames.TIME;
import static com.example.inotify.configs.TableNames.UC_APPLISTCOUNT_TABLE;
import static com.example.inotify.configs.TableNames.UC_APPLISTSOCIALMEDIACOUNT_TABLE;
import static com.example.inotify.configs.TableNames.UC_CALENDEREVENTCOUNT_TABLE;
import static com.example.inotify.configs.TableNames.UC_CALLDURATION_TABLE;
import static com.example.inotify.configs.TableNames.UC_CHARGE_TABLE;
import static com.example.inotify.configs.TableNames.UC_CONTACTCOUNT_TABLE;
import static com.example.inotify.configs.TableNames.UC_SCREENTIME_TABLE;
import static com.example.inotify.configs.TableNames.UC_USAGECOUNT_TABLE;

public class UC_SqlLiteDbHelper extends MainSqlliteOpenHelp {



    public UC_SqlLiteDbHelper(Context context) {
        super(context);

    }

    public boolean mit_charge_insert() {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        long result = db.insert(UC_CHARGE_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean appListcount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(UC_APPLISTCOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;

    }

    public boolean appusagecount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(UC_CONTACTCOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean contactCount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(UC_CONTACTCOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean screentime_insert(String time) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        long result = db.insert(UC_SCREENTIME_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean callduration_insert(String time) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        long result = db.insert(UC_CALLDURATION_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }



    public boolean calenderEventCount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(UC_CALENDEREVENTCOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean appListSocialMediacount_insert(String count) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(COUNT, count);
        long result = db.insert(UC_APPLISTSOCIALMEDIACOUNT_TABLE, null, contentValues);
        db.close();
        return result != -1;

    }



    public long appUsageAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from "+UC_USAGECOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        if (res != null) {
            res.close();
        }
        db.close();

        return 0;
    }

    public long applistAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from "+UC_APPLISTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
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
        Cursor res = db.rawQuery("select avg(COUNT) as avg from "+UC_CONTACTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
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
        Cursor res = db.rawQuery("select avg(TIME) as avg from "+UC_SCREENTIME_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long calldurationAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(TIME) as avg from "+UC_CALLDURATION_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long calendereventAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from "+UC_CALENDEREVENTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
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
        Cursor res = db.rawQuery("select count(DATE) as avg from "+UC_CHARGE_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long applistsocialmediaAverage_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT) as avg from "+UC_APPLISTSOCIALMEDIACOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    public long appUsageLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from "+UC_USAGECOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long applistLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT  from "+UC_APPLISTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long contactsLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from "+UC_CONTACTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())){
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
        Cursor res = db.rawQuery("select TIME from "+UC_SCREENTIME_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("TIME"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long calldurationLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+UC_CALLDURATION_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(2);
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long calendereventLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from "+UC_CALENDEREVENTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())){
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
        Cursor res = db.rawQuery("select DATE from "+UC_CHARGE_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("DATE"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long applistsocialmediaLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select COUNT from "+UC_APPLISTSOCIALMEDIACOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToLast())){
                return res.getLong(res.getColumnIndex("COUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long applistAverageWithoutLast_get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select avg(COUNT - 1) as avg from "+UC_APPLISTCOUNT_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("avg - 1"));
            }
        }
        if (res != null) {
            res.close();
        }
        db.close();

        return 0;
    }
}
