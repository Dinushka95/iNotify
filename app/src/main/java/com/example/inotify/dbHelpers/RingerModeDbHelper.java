package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.configs.TbColNames.RM_DATE;
import static com.example.inotify.configs.TbColNames.RM_DAY;
import static com.example.inotify.configs.TbColNames.RM_NOTIFICATIONID;
import static com.example.inotify.configs.TbColNames.RM_RINGERMODE;
import static com.example.inotify.configs.TbColNames.RM_TIME;
import static com.example.inotify.configs.TbNames.UA_RINGERMODE_TABLE;

public class RingerModeDbHelper extends MainSqlliteOpenHelp{



    public RingerModeDbHelper(Context context) {
        super(context);
    }


    // RingerMode table insert
    public boolean RMinsert(String id , String ringermode)
    {
        Log.d("inotify" ,"ringermode Save Started");
        Log.d("inotify " ,"RingerMode(^_^)" + ringermode + "," +id);


        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        Log.d("inotify" ,"day = "  + day );

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RM_NOTIFICATIONID, id);
        contentValues.put(RM_DAY ,day);
        contentValues.put(RM_DATE , date);
        contentValues.put(RM_TIME , time);
        contentValues.put(RM_RINGERMODE ,ringermode);

        long result = db.insert(UA_RINGERMODE_TABLE , null,contentValues);
        Log.d("inotify" ,"RM_RINGERMODE = "  +ringermode );

        db.close();
        if(result == -1)
            return false;
        else
            return true;


    }

    public String  RingerModeGet(){
        String ringermode= new String();

        SQLiteDatabase db = this.getReadableDatabase();
        String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());


        Cursor res = db.rawQuery("select * from " + UA_RINGERMODE_TABLE +" where RM_NOTIFICATIONID =\"" + id + "\"", null);
        if(res != null)
        {
            if(res.moveToFirst()){
                return res.getString(1);
            }
            res.close();
        }
        return null;

    }


}
