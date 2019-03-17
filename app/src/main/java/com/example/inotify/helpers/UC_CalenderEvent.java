package com.example.inotify.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CalendarContract;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.CalenderEventDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;

public class UC_CalenderEvent {

    public String getcalanderEventCount(Context context) {

        CalenderEventDbHelper calenderEventDbHelper = new CalenderEventDbHelper(context);
        if(calenderEventDbHelper.checkIfExist() == true)
        {
            Log.d("inotify", "Calender details........");
            SimpleDateFormat df = new SimpleDateFormat("yyyymmddHHmm", Locale.getDefault());
            Date d = new Date();
            Log.d("inotify","check date.........."+ d);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DAY_OF_MONTH, -1);

            Calendar c_start = Calendar.getInstance();
            c_start.setTime(new Date());

            String[] proj = new String[]{
                    CalendarContract.Instances._ID,
                    CalendarContract.Instances.BEGIN,
                    CalendarContract.Instances.END,
                    CalendarContract.Instances.EVENT_ID};
            Cursor cursor = CalendarContract.Instances.query(context.getContentResolver(), proj, cal.getTimeInMillis(), c_start.getTimeInMillis());
            String x = "xxx";
            if (cursor.getCount() > 0) {
                x = String.valueOf(cursor.getCount());
                Log.d("inotify", "Calender details  " + x);
            }
            Log.d("inotify", "Calender details   " + cursor.getCount());
            return x;


        }
        else
        {
            return null;

        }


    }



}
