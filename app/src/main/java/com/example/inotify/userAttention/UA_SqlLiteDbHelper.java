package com.example.inotify.userAttention;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.dbHelpers.MainSqlliteOpenHelp;

public class UA_SqlLiteDbHelper extends MainSqlliteOpenHelp {

    public static final String DATABASE_NAME = "AppInotify.db";


    // notification all notifications
    public static final String CHA_N_TABLE = "cha_N_TABLE";
    public static final String N_ID = "N_ID";
    public static final String N_APPNAME = "N_APPNAME";
    public static final String N_DATETIME = "N_DATETIME";


    // notification importance value
    public static final String CHA_NI_TABLE = "cha_NI_TABLE";
    public static final String NI_APPNAME = "NI_APPNAME";
    public static final String NI_VALUE = "NI_VALUE";





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
            long result = db.insert(CHA_N_TABLE, null, contentValues);
            db.close();
            if (result == -1)
                return false;
            else
                return true;
        }
    }

    public String NgetValue(String id) {
        //Log.d("cdap", " ---NgetValue--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from CHA_N_TABLE where N_ID =\"" + id + "\"", null);
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
            long result = db.insert(CHA_NI_TABLE, null, contentValues);
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
        Cursor cursor = db.rawQuery("select * from CHA_NI_TABLE where NI_APPNAME=\"" + appName + "\"", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public int NIgetValue(String appName) {
       // Log.d("cdap", " ---NIgetValue--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from CHA_NI_TABLE where NI_APPNAME=\"" + appName + "\"", null);

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
            int currentValue = NIgetValue(appName);
            //Log.d("cdap", " --------------sssss-----------" +currentValue);
            int newValue = currentValue + value;
            //Log.d("cdap", " --------------sssss-----------" +newValue);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NI_VALUE, newValue);
            db.update(CHA_NI_TABLE, contentValues, "NI_APPNAME = ? ", new String[]{appName});
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
        Cursor cursor = db.rawQuery("SELECT SUM(NI_VALUE) as Total FROM cha_NI_TABLE", null);
        int total=0;
        if (cursor.moveToFirst()) {

             total = cursor.getInt(cursor.getColumnIndex("Total"));
        }
        return total;

    }


}
