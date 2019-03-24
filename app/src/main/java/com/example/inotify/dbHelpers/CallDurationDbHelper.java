package com.example.inotify.dbHelpers;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CallDurationDbHelper extends MainDbHelp {

    private  Context c1;
    public CallDurationDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public int callDurationTodayGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select SUM("+ TbColNames.TIME +") as USAGETIME from " + TbNames.CALLDURATION_TABLE + " where DATE = \""+date+"\" ", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public long getCallDuration() {
        Log.d("inotify","total call duration0000000000000" );
        if (ActivityCompat.checkSelfPermission(c1, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            Log.d("inotify","total call duration11111111111111111111" );
            Cursor cursor = c1.getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
            int totalDuration = 0;
            Log.d("inotify","total call duration" + totalDuration);

            if ((cursor != null ? cursor.getCount() : 0) > 0) {
                while (cursor.moveToNext()) {

                    String dateTime = new SimpleDateFormat("yyyyMMddhhmmsss", Locale.getDefault()).format(new Date(Long.valueOf(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)))));
                    String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                    String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));


                     // Log.d("inotify", number+duration+type+dateTime );
                    totalDuration = totalDuration + Integer.valueOf(duration);
                    Log.d("inotify","total call duration" + totalDuration);

                }
            }
            UserCharacteristics_DbHelper UserCharacteristics_DbHelper = new UserCharacteristics_DbHelper(c1);
            UserCharacteristics_DbHelper.callduration_insert(String.valueOf(totalDuration));
            UserCharacteristics_DbHelper.close();
            return totalDuration;
        }
        return 0;
    }
    public boolean callDurationInsert(long time)
    {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        //String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.DATE, date);
        contentValues.put(TbColNames.TIME, time);
        long result = db.insert(TbNames.CALLDURATION_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }

    public int callDurationAvgGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select SUM("+ TbColNames.TIME +") as USAGETIME from " + TbNames.CALLDURATION_TABLE , null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("USAGETIME"));
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


}
