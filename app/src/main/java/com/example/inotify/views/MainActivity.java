package com.example.inotify.views;

import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.StrictMode;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.inotify.helpers.All_ScreenLock;
import com.example.inotify.R;
import com.example.inotify.services.NV_ActivityRecognitionService;
import com.example.inotify.services.NV_NotificationViewabilityService;
import com.example.inotify.services.NV_LocationService;
import com.example.inotify.services.UC_all_service;
import com.google.android.gms.location.ActivityRecognitionClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    ////////////////////////////////////////////////////////
    /////    Debugger Logger switch                 ////////
    ////////////////////////////////////////////////////////

    public static final boolean  MainUsercharacteristics_DebuggerLogger = true;
    public static final boolean  MainAttentiviness_DebuggerLogger = true;


    /////////////////////////////////////////////////////////

    public static final int iNotify_SERVEC_ID = 555;
    public static final int MY_LOCATION_LISTENER_SERVEC_ID = 210;
    public static final int MY_BUSYORNOT_SERVEC_ID = 280;
    public static final int MY_MIT_ALL_SERVEC_ID = 290;


    public static Double home_Log = 79.9652678;
    public static Double home_Lat = 6.8598528;
    public static Double work_Log = 79.973181;
    public static Double work_Lat = 6.9148957;
    public static Double accuracy = .0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityRecognitionClient mActivityRecognitionClient= new ActivityRecognitionClient(this);
        mActivityRecognitionClient.requestActivityUpdates(0, getActivityDetectionPendingIntent());



        ComponentName componentName = new ComponentName(MainActivity.this, NV_LocationService.class);
        JobInfo info = new JobInfo.Builder(MY_LOCATION_LISTENER_SERVEC_ID, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(9*57 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);


        ComponentName componentName1 = new ComponentName(MainActivity.this, NV_NotificationViewabilityService.class);
        JobInfo info1 = new JobInfo.Builder(MY_BUSYORNOT_SERVEC_ID, componentName1)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(10*60 * 1000)
                .build();

        JobScheduler scheduler1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode1 = scheduler1.schedule(info1);


        ComponentName componentName2 = new ComponentName(MainActivity.this, UC_all_service.class);
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





    }

    private PendingIntent getActivityDetectionPendingIntent() {
        return PendingIntent.getService(this, 30, new Intent(this, NV_ActivityRecognitionService.class), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void cancelAllJobs(View view) {
        JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancelAll();
    }

    public void getvalue(View view) {


    }

    public void  ttt(){

            try {
                Process process = Runtime.getRuntime().exec("logcat ");
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                StringBuilder log=new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    log.append(line);
                  //  Log.v("inotify",line);
                }
               // TextView tv = (TextView)findViewById(R.id.textView);
               // tv.append(log.toString());
               // tv.setMovementMethod(new ScrollingMovementMethod());

            } catch (IOException e) {
                // Handle Exception
            }


    }

    public void settings(View view) {
        Intent intent = new Intent(MainActivity.this, settingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {

       // BroadcastReceiver mReceiver = new All_ScreenLock();
       // unregisterReceiver(mReceiver);
        super.onPause();
    }


}