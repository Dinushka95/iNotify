package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.configs.TbColNames.RM_DATE;
import static com.example.inotify.configs.TbColNames.RM_DAY;
import static com.example.inotify.configs.TbColNames.RM_NOTIFICATIONID;
import static com.example.inotify.configs.TbColNames.RM_RINGERMODE;
import static com.example.inotify.configs.TbColNames.RM_TIME;
import static com.example.inotify.configs.TbNames.RINGERMODE_TABLE;

public class RingerModeDbHelper extends MainDbHelp {

    private static RingerModeDbHelper mInstance = null;

    public RingerModeDbHelper(Context context) {
        super(context);
    }

    public static RingerModeDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new RingerModeDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    // RingerMode table insert
    public boolean RMinsert(String id , String ringermode)
    {
        Log.d("inotify" ,"ringermode Save Started");
        Log.d("inotify " ,"RingerMode(^_^)" + ringermode + "," +id);

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());

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

        long result = db.insert(RINGERMODE_TABLE, null,contentValues);
        Log.d("inotify" ,"RM_RINGERMODE = "  +ringermode );

        db.close();
        return result != -1;

    }

    public String  RingerModeGet(String id){
        String ringermode= new String();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TbNames.RINGERMODE_TABLE +" where RM_NOTIFICATIONID =\"" + id + "\"", null);
        if(res != null)
        {
            if(res.moveToFirst()){
                return res.getString(5);
                //return res.getString(res.getColumnIndex(TbColNames.RM_RINGERMODE));
            }
            res.close();
        }
        return null;

    }


}
