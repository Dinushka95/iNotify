package com.example.inotify.dbHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ScreenOnTimeDbHelper extends MainDbHelp {
    public ScreenOnTimeDbHelper(Context context) {
        super(context);
    }

    public int ScreenOnTimeCountTodayGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select COUNT(TIMEON) as TIMECOUNT from " + TbNames.SCREENSTATUS_TABLE + " where DATE = \""+date+"\" AND NOTIFICATIONID IS null " , null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("TIMECOUNT"));
            }
        }
        db.close();
        return 0;
    }


}
