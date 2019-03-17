package com.example.inotify.services;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.example.inotify.configs.AppUserConfigs;
import com.example.inotify.dbHelpers.NotificationViewability_DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NotificationViewabilityService extends JobService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onStartJob(JobParameters params) {


        // get time current working time block
        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        //get day of the week
        String year = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
        String month = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        String day = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(year), (Integer.valueOf(month) - 1), Integer.valueOf(day));
        String dayofweek = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());


        // get activity from db
        NotificationViewability_DbHelper praSqlLiteDbHelper1 = new NotificationViewability_DbHelper(this);
        String CurrentMaxActivity = praSqlLiteDbHelper1.activity_get();


        //double distance = Math.hypot(x1-x2, y1-y2);
        // get location from db and map to home work or unknown

        ArrayList<Double> loc = praSqlLiteDbHelper1.location_get();
        double log = loc.get(0);
        double lat = loc.get(1);


        double distanceHome = Math.hypot(log - AppUserConfigs.home_Log, lat - AppUserConfigs.home_Lat);
        double distanceWork = Math.hypot(log - AppUserConfigs.work_Log, lat - AppUserConfigs.work_Lat);


        String CurrentLocation = "unknown";

        if (distanceHome < AppUserConfigs.accuracy) {
            CurrentLocation = "home";
        }
        if (distanceWork < AppUserConfigs.accuracy) {
            CurrentLocation = "work";
        }


        // get busy or not from notification remove table

        String CurrentBusyorNot = "NotBusy";
        int notificationRemovedCount = praSqlLiteDbHelper1.notificationRemove_get();


        if (notificationRemovedCount > 0) {
        } else {
            CurrentBusyorNot = "Busy";
        }

        //save to table
        NotificationViewability_DbHelper praSqlLiteDbHelper = new NotificationViewability_DbHelper(this);
        praSqlLiteDbHelper.busyOrNot_insert(time, dayofweek, CurrentMaxActivity, CurrentLocation, CurrentBusyorNot);

        praSqlLiteDbHelper.close();
        praSqlLiteDbHelper1.close();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        return false;
    }
}
