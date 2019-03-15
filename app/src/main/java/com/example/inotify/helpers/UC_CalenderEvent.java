package com.example.inotify.helpers;

import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UC_CalenderEvent {

    public String getcalanderEventCount(Context context) {


        SimpleDateFormat df = new SimpleDateFormat("yyyymmddHHmm", Locale.getDefault());
        Date d = new Date();

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
        String x = "";
        if (cursor.getCount() > 0) {
            x = String.valueOf(cursor.getCount());
            Log.d("inotify", "Calender details" + x);
        }
        Log.d("inotify", "Calender details" + x);
        return x;


    }
}
