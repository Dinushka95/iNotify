package com.example.inotify.helpers;

import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NV_CalenderEvent {


    public boolean getcalanderEvent(Context context){


        SimpleDateFormat df = new SimpleDateFormat("yyyymmddHHmm");
        Date d = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 10);

        Calendar c_start= Calendar.getInstance();
        c_start.setTime(new Date());
       // c_start.set(year,month,day,hour,mintes);

      //  Log.d("inotify","DDDDDDDDDDDDDDDDDDDDddddd"+String.valueOf(c_start.getTimeInMillis()));
      //  Log.d("inotify","DDDDDDDDDDDDDDDDDDDDddddd"+String.valueOf(cal.getTimeInMillis()));

        String[] proj = new String[]{
                        CalendarContract.Instances._ID,
                        CalendarContract.Instances.BEGIN,
                        CalendarContract.Instances.END,
                        CalendarContract.Instances.EVENT_ID};
        Cursor cursor = CalendarContract.Instances.query(context.getContentResolver(), proj, c_start.getTimeInMillis(), cal.getTimeInMillis());
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;

    }
}
