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
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.location.ActivityRecognitionClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final int iNotify_SERVEC_ID = 555;
    public static final int MY_LOCATION_LISTENER_SERVEC_ID = 210;
    public static final int MY_BUSYORNOT_SERVEC_ID = 280;
    public static final int MY_MIT_ALL_SERVEC_ID = 290;


    public static Double home_Log = 79.9652678;
    public static Double home_Lat = 6.8598528;
    public static Double work_Log = 79.973109;
    public static Double work_Lat = 6.914578;
    public static Double accuracy = .0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

/*    Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(this);
    mit_sqlLiteDbHelper.mit_appUsageAverage_get();*/

      //  MainUsercharacteristics mainUsercharacteristics = new MainUsercharacteristics();
        //mainUsercharacteristics.getUsercharacteristics(this);

    //Log.v("inotify","bbbbbbbbbbbbbbbbbbbbb"+String.valueOf(mainUsercharacteristics.getUsercharacteristics(this)));
       // buidJsonObject();

        //MainAttentiviness mainAttentiviness = new MainAttentiviness();
        //String attentiviness = mainAttentiviness.getFinalAttentiviness(this,"com.example.dinu.testa");
       // Log.v("inotify","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa"+attentiviness);

        Din_SNSModel snsModel = new Din_SNSModel();
        snsModel.setDay("1");
        snsModel.setTime("1111");
        snsModel.setBusyornot("1");
        snsModel.setAttentiviness("1");
        snsModel.setUserchaacteristics("1");
        snsModel.setNotificationtype("1");
        snsModel.setAppname("1");
        MainSmartNotificationSystem mainSmartNotificationSystem = new MainSmartNotificationSystem(this,snsModel);

        String pp = mainSmartNotificationSystem.getPrediction();
        Log.v("inotify","pppppppppppppppppppppppppppppppppppppppppppp"+pp);

    }







}
