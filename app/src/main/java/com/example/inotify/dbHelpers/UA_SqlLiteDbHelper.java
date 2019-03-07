package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.configs.TbColNames.DATE;
import static com.example.inotify.configs.TbColNames.NI_APPNAME;
import static com.example.inotify.configs.TbColNames.NI_VALUE;
import static com.example.inotify.configs.TbColNames.N_APPNAME;
import static com.example.inotify.configs.TbColNames.N_DATETIME;
import static com.example.inotify.configs.TbColNames.N_ID;
import static com.example.inotify.configs.TbNames.UA_NI_TABLE;
import static com.example.inotify.configs.TbNames.UA_N_TABLE;

public class UA_SqlLiteDbHelper extends MainSqlliteOpenHelp {

    public UA_SqlLiteDbHelper(Context context) {
        super(context);
    }

    public boolean Ninsert(Long id,String appName, Long value) {
       // Log.d("cdap", " ---Ninsert--");
        if (value == 1) {
            return true;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(N_ID, id);
            contentValues.put(N_APPNAME, appName);
            contentValues.put(N_DATETIME, value);
            long result = db.insert(UA_N_TABLE, null, contentValues);
            db.close();
            if (result == -1)
                return false;
            else
                return true;
        }
    }

    public String NValueGet(String id) {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + UA_N_TABLE +" where N_ID =\"" + id + "\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }

    public boolean NIinsert(String appName, int value) {
        //Log.d("cdap", " ---NIinsert--");
        if (value == 1) {
            return true;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NI_APPNAME, appName);
            contentValues.put(NI_VALUE, value);
            long result = db.insert(UA_NI_TABLE, null, contentValues);
            db.close();
            if (result == -1)
                return false;
            else
                return true;
        }
    }


    public boolean NIappExisCheck(String appName) {
       // Log.d("cdap", " ---NIappExisCheck--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UA_NI_TABLE +" where NI_APPNAME=\"" + appName + "\"", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public int NIValueGet(String appName) {
       // Log.d("cdap", " ---NIValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from UA_NI_TABLE where NI_APPNAME=\"" + appName + "\"", null);

        if (res != null) {
            if (res.moveToFirst()) {
                return res.getInt(2);
            }
            res.close();
        }
        return 0;
    }


    public boolean NIupdate(String appName, int value) {
       // Log.d("cdap", " ---NIupdate--");

        if (NIappExisCheck(appName)) {
            //Log.d("cdap", " --------------sssss-----------" +value);
            int currentValue = NIValueGet(appName);
            //Log.d("cdap", " --------------sssss-----------" +currentValue);
            int newValue = currentValue + value;
            //Log.d("cdap", " --------------sssss-----------" +newValue);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NI_VALUE, newValue);
            db.update(UA_NI_TABLE, contentValues, "NI_APPNAME = ? ", new String[]{appName});
            return true;
        } else {
            return NIinsert(appName, value);
        }
    }


    public boolean NIRappExisCheck(String appName) {
       // Log.d("cdap", " ---NIappExisCheck--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from CHA_NIR_TABLE where NIR_APPNAME=\"" + appName + "\"", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public int NIRgetValueYes(String appName) {
       // Log.d("cdap", " ---NIRgetValue--Yes");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from CHA_NIR_TABLE where NIR_APPNAME=\"" + appName + "\"", null);

        if (res != null) {
            if (res.moveToFirst()) {
                return res.getInt(2);
            }
            res.close();
        }
        return 0;
    }



    public int NIRgetValueNo(String appName) {
       // Log.d("cdap", " ---NIRgetValue--No");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from CHA_NIR_TABLE where NIR_APPNAME=\"" + appName + "\"", null);

        if (res != null) {
            if (res.moveToFirst()) {
                return res.getInt(3);
            }
            res.close();
        }
        return 0;
    }

    public int NIRgetTotalvalue() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(NI_VALUE) as Total FROM "+UA_NI_TABLE, null);
        int total=0;
        if (cursor.moveToFirst()) {

             total = cursor.getInt(cursor.getColumnIndex("Total"));
        }
        return total;

    }

  public boolean screenOffInsert() {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String timenow = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put("TIMEOFF", timenow);
        long result = db.insert(UA_NI_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean screenOnInsert() {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String timenow = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put("TIMEON", timenow);
        long result = db.insert(UA_NI_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }


}
