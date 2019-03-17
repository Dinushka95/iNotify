package com.example.inotify.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CalendarContract;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.CalenderEventDbHelper;
import com.example.inotify.dbHelpers.UserCharacteristics_DbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;

public class CalenderEventHelper {

    private Context c1;

    public CalenderEventHelper(Context context) {
        this.c1 = context;
    }

    public boolean getcalanderEvent(Context context) {

        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 10);

        Calendar c_start = Calendar.getInstance();
        c_start.setTime(new Date());


        String[] proj = new String[]{
                CalendarContract.Instances._ID,
                CalendarContract.Instances.BEGIN,
                CalendarContract.Instances.END,
                CalendarContract.Instances.EVENT_ID};
        Cursor cursor = CalendarContract.Instances.query(context.getContentResolver(), proj, c_start.getTimeInMillis(), cal.getTimeInMillis());
        return cursor.getCount() > 0;

    }

    public int getcalanderEventCount(Context context) {

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

        return cursor.getCount();
    }

    public void updateTodayCalendar() {

        CalenderEventDbHelper calenderEventDbHelper = new CalenderEventDbHelper(c1);
        boolean x = calenderEventDbHelper.checkIfExist();
        if (!x) {
            int count = getcalanderEventCount(c1);
            CalenderEventDbHelper calenderEventDbHelper1 = new CalenderEventDbHelper(c1);
            calenderEventDbHelper1.calenderEventCount_insert(String.valueOf(count));
        }

    }
}



