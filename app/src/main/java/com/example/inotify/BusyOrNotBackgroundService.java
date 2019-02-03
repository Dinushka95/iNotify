package com.example.inotify;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BusyOrNotBackgroundService  extends JobService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onStartJob(JobParameters params) {

        Log.d("inotify","BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

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
        SqlLiteDbHelper sqlLiteDbHelper1 = new SqlLiteDbHelper(this);
        String CurrentMaxActivity = sqlLiteDbHelper1.pra_activity_get();




        //double distance = Math.hypot(x1-x2, y1-y2);
        // get location from db and map to home work or unknown
        Double home_Log = 79.9652678;
        Double home_Lat = 6.8598528;
        Double work_Log = 79.973109;
        Double work_Lat = 6.914578;
        Double accuracy = .0001;

         ArrayList<Double> loc= sqlLiteDbHelper1.pra_location_get();
         double log= loc.get(0);
         double lat = loc.get(1);


        double distanceHome = Math.hypot(log - home_Log, lat-home_Lat);
        double distanceWork = Math.hypot(log - work_Log, lat-work_Lat);
        Log.d("inotify","AAAAAAAAAAAAAAAAAAAAAA"+distanceHome);
        Log.d("inotify","AAAAAAAAAAAAAAAAAAAAAAA"+distanceWork);

        String CurrentLocation="unknown";

        if(distanceHome<accuracy){
         CurrentLocation="home";
             }
        if(distanceWork<accuracy){
            CurrentLocation="work";
        }


        // get busy or not from notification remove table

        String CurrentBusyorNot="NotBusy";
        int notificationRemovedCount = sqlLiteDbHelper1.pra_notificationRemove_get();

        Log.d("inotify","NNNNNNNNNNNNNRRRRRRRRRRREEEEEEE"+notificationRemovedCount);

        if(notificationRemovedCount>0){}
        else {CurrentBusyorNot="Busy";}

        //save to table
        SqlLiteDbHelper sqlLiteDbHelper = new SqlLiteDbHelper(this);
        sqlLiteDbHelper.pra_BusyOrNot_insert(time,dayofweek,CurrentMaxActivity,CurrentLocation,CurrentBusyorNot);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        return false;
    }
}
