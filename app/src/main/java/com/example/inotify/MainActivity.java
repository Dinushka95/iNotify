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
        String cc="";
        try {
             cc = HttpPost("https://us-central1-inotify23.cloudfunctions.net/hello_http");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("inotify","rrrrrrrrrrrrrr"+cc);
    }

    private String HttpPost(String myUrl) throws IOException, JSONException {
        String result = "";

        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        // 2. build JSON object
        JSONObject jsonObject = buidJsonObject();

        // 3. add JSON content to POST request body
        result=setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response message
        conn.getResponseMessage();


        return result;

    }

    private JSONObject buidJsonObject() {

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.accumulate("area", 3000);
            jsonData.accumulate("bedrooms",  4.0);
            jsonData.accumulate("age",  15);
            jsonData.accumulate("price",  565000);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonData1 = new JSONObject();
        try {
            jsonData1.accumulate("area", 2600);
            jsonData1.accumulate("bedrooms",  3.0);
            jsonData1.accumulate("age",  20);
            jsonData1.accumulate("price",  550000);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject jsonPredict = new JSONObject();
        try {
            jsonPredict.accumulate("area", 2600);
            jsonPredict.accumulate("bedrooms",  3.0);
            jsonPredict.accumulate("age",  20);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray1= new JSONArray();
        jsonArray1.put(jsonData);
        jsonArray1.put(jsonData1);

        JSONArray jsonArray2= new JSONArray();
        jsonArray2.put(jsonPredict);



        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("data", jsonArray1);
            mainObj.put("predict", jsonArray2);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return mainObj;
    }

    private String setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {

        OutputStream os = conn.getOutputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i(MainActivity.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();


        InputStream in =conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        //System.out.println(result.toString());

       // Log.v("inotify","jjjjjjjjjjjjjjjjjjj"+ result.toString());
        os.close();

        return result.toString();
    }





}
