package com.example.inotify.services;


import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.location.Location;

import com.example.inotify.components.MyLocationUpdatesComponent;
import com.example.inotify.dbHelpers.NotificationViewabilityDbHelper;

public class LocationService extends JobService implements MyLocationUpdatesComponent.ILocationProvider {

    private MyLocationUpdatesComponent myLocationUpdatesComponent;


    @Override
    public void onCreate() {

        myLocationUpdatesComponent = new MyLocationUpdatesComponent(this);
        myLocationUpdatesComponent.onCreate(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        myLocationUpdatesComponent.onStart();

        return START_STICKY;

    }


    @Override
    public boolean onStopJob(JobParameters params) {

        return false;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        jobFinished(params, false);
        return false;
    }


    @Override
    public void onLocationUpdate(Location location) {


        NotificationViewabilityDbHelper praSqlLiteDbHelper = new NotificationViewabilityDbHelper(this);
        String log = String.valueOf(location.getLongitude());
        String lat = String.valueOf(location.getLatitude());
        praSqlLiteDbHelper.location_insert(log, lat);
        praSqlLiteDbHelper.close();
        //send intent
        //TODO - 2  future needed for situationist upload data
        Intent intent = new Intent();
        intent.setAction("xxxxxxxxxx");
        sendBroadcast(intent);

    }
}
