package com.example.inotify;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.ActivityRecognitionClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int iNotify_SERVEC_ID = 555;
    public static final int MY_LOCATION_LISTENER_SERVEC_ID = 210;
    public static final int MY_CONTACTS_LISTENER_SERVEC_ID = 220;
    public static final int MY_APPUSAGE_LISTENER_SERVEC_ID = 230;
    public static final int MY_APPINFORMATION_LISTENER_SERVEC_ID = 240;
    public static final int MY_CALLLOG_LISTENER_SERVEC_ID = 250;
    public static final int MY_SMSLOG_LISTENER_SERVEC_ID = 260;
    public static final int MY_DATAUSAGE_LISTENER_SERVEC_ID = 270;
    public static final int MY_BUSYORNOT_SERVEC_ID = 280;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityRecognitionClient mActivityRecognitionClient= new ActivityRecognitionClient(this);
        mActivityRecognitionClient.requestActivityUpdates(0, getActivityDetectionPendingIntent());



        ComponentName componentName = new ComponentName(MainActivity.this, com.example.inotify.LocationService.class);
        JobInfo info = new JobInfo.Builder(MY_LOCATION_LISTENER_SERVEC_ID, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(9*57 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);


        ComponentName componentName1 = new ComponentName(MainActivity.this, com.example.inotify.BusyOrNotBackgroundService.class);
        JobInfo info1 = new JobInfo.Builder(MY_BUSYORNOT_SERVEC_ID, componentName1)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(10*60 * 1000)
                .build();

        JobScheduler scheduler1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode1 = scheduler1.schedule(info1);



    }

    private PendingIntent getActivityDetectionPendingIntent() {
        return PendingIntent.getService(this, 30, new Intent(this, ActivityRecognitionService.class), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void cancelAllJobs(View view) {
        JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancelAll();
    }

    public void getvalue(View view) {
SqlLiteDbHelper sqlLiteDbHelper = new SqlLiteDbHelper(this);
sqlLiteDbHelper.pra_location_get();


    }
}
