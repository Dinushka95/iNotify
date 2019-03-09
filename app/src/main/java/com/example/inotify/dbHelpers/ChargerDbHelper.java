package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;



public class ChargerDbHelper extends MainDbHelp {

    public ChargerDbHelper(Context context) {

        super(context);
    }

    public boolean powerOninsert(String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.POWERONDATE, date);
        contentValues.put(TbColNames.POWERONTIME, time);
        long result = db.insert(TbNames.CHARGER_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean powerOffinsert(String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.POWEROFFDATE, date);
        contentValues.put(TbColNames.POWEROFFTIME, time);
        long result = db.insert(TbNames.CHARGER_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public int powerOnCountGet(String date) {
       //TODO - sql query needs to be implemented get total count per day- number of charges values
        return 1;
    }

    public int powerOffCountGet(String date) {
        //TODO - sql query needs to be implemented get total count per day- number of charges values
        return 1;
    }


}
