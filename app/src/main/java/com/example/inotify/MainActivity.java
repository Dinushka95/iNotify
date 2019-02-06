package com.example.inotify;

import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.ActivityRecognitionClient;

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
    public static final int MY_MIT_ALL_SERVEC_ID = 290;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityRecognitionClient mActivityRecognitionClient= new ActivityRecognitionClient(this);
        mActivityRecognitionClient.requestActivityUpdates(0, getActivityDetectionPendingIntent());



        ComponentName componentName = new ComponentName(MainActivity.this, Pra_LocationService.class);
        JobInfo info = new JobInfo.Builder(MY_LOCATION_LISTENER_SERVEC_ID, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(9*57 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);


        ComponentName componentName1 = new ComponentName(MainActivity.this, Pra_BusyOrNotBackgroundService.class);
        JobInfo info1 = new JobInfo.Builder(MY_BUSYORNOT_SERVEC_ID, componentName1)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(10*60 * 1000)
                .build();

        JobScheduler scheduler1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode1 = scheduler1.schedule(info1);


        ComponentName componentName2 = new ComponentName(MainActivity.this, com.example.inotify.Mit_all_service.class);
        JobInfo info2 = new JobInfo.Builder(MY_MIT_ALL_SERVEC_ID, componentName2)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(24*60*60* 1000)
                .build();

        JobScheduler scheduler2 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode2 = scheduler2.schedule(info2);


        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new All_ScreenLock();
        registerReceiver(mReceiver, intentFilter);

       // registerScreenLockStateBroadcastReciver();
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        return PendingIntent.getService(this, 30, new Intent(this, Pra_ActivityRecognitionService.class), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void cancelAllJobs(View view) {
        JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancelAll();
    }

    public void getvalue(View view) {
    /*Pra_SqlLiteDbHelper praSqlLiteDbHelper = new Pra_SqlLiteDbHelper(this);
    praSqlLiteDbHelper.pra_location_get();*/


/*    Cha_SqlLiteDbHelper cha_sqlLiteDbHelper = new Cha_SqlLiteDbHelper(this);
    cha_sqlLiteDbHelper.NIRgetTotalvalue();*/

    Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(this);
    mit_sqlLiteDbHelper.mit_appUsageAverage_get();

    Log.v("inotify","bbbbbbbbbbbbbbbbbbbbb"+String.valueOf(mit_sqlLiteDbHelper.mit_appUsageAverage_get()));

    }


}
