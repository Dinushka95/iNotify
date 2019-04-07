package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbColNames.COUNT;
import static com.example.inotify.configs.TbColNames.DATE;
import static com.example.inotify.configs.TbNames.CALENDEREVENTCOUNT_TABLE;

public class CalenderEventDbHelper extends MainDbHelp {

    private static CalenderEventDbHelper mInstance = null;

    private Context c1;

    public CalenderEventDbHelper(Context context) {
        super(context);
        this.c1 = context;
    }
    public static CalenderEventDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new CalenderEventDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }


    public long CalenderEventAVGGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        //avgappp
        Cursor res = db.rawQuery("select SUM(" + TbColNames.COUNT + ") as COUNT from " + TbNames.CALENDEREVENTCOUNT_TABLE, null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())) {
                do {
                    total = total + res.getInt(res.getColumnIndex("COUNT"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg = total / count;
        } catch (Exception e) {
            return 0;
        }
        Log.d("inotify", "calenderEventAVG......" + avg);

        return avg;
    }

    public boolean checkIfExist() {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select *  from " + TbNames.CALENDEREVENTCOUNT_TABLE + " where DATE =\"" + date + "\"", null);
        if (cursor != null) {

            if (cursor.moveToNext()) {
                if (date.equals(cursor.getString(cursor.getColumnIndex("DATE")))) {
                    return true;
                } else {
                    return false;
                }
            }

        }
        return false;
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
