package com.example.inotify.views;

import android.app.Dialog;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.configs.AppcategoriesConstants;
import com.example.inotify.configs.MyConstants;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.ProfileHelper;

import com.example.inotify.helpers.TopAppsHelper;
import com.example.inotify.helpers.UC_CalenderEvent;
import com.example.inotify.models.AppInfoModel;
import com.example.inotify.models.ProfileModel;
import com.example.inotify.services.NV_ActivityRecognitionService;
import com.example.inotify.services.NV_LocationService;
import com.example.inotify.services.NV_NotificationViewabilityService;
import com.example.inotify.services.UC_all_service;
import com.google.android.gms.location.ActivityRecognitionClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);


        if (MyConstants.PERMISSION_CONTACTS &&
                MyConstants.PERMISSION_LOCATION &&
                MyConstants.PERMISSION_CALENDER &&
                MyConstants.PERMISSION_PHONE &&
                MyConstants.PERMISSION_NOTIFICATIONACCESS &&
                MyConstants.PERMISSION_USEAGEACCESS) {
            tt();
        }else {
            Toast.makeText(getApplicationContext(),"You Have Not Given Proper Access Permission.Please give Permission",Toast.LENGTH_LONG).show();
        }

    }


    public void tt(){

        // check if your profile exises
        // if true load that profile
        // else popup fragment to createa profile
        // load that profile


        ActivityRecognitionClient mActivityRecognitionClient = new ActivityRecognitionClient(this);
        mActivityRecognitionClient.requestActivityUpdates(0, getActivityDetectionPendingIntent());


        ComponentName componentName = new ComponentName(MainMenuActivity.this, NV_LocationService.class);
        JobInfo info = new JobInfo.Builder(MyConstants.MY_LOCATION_LISTENER_SERVEC_ID, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(9 * 57 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler != null ? scheduler.schedule(info) : 0;


        ComponentName componentName1 = new ComponentName(MainMenuActivity.this, NV_NotificationViewabilityService.class);
        JobInfo info1 = new JobInfo.Builder(MyConstants.MY_BUSYORNOT_SERVEC_ID, componentName1)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(10 * 60 * 1000)
                .build();

        JobScheduler scheduler1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode1 = scheduler1 != null ? scheduler1.schedule(info1) : 0;


        ComponentName componentName2 = new ComponentName(MainMenuActivity.this, UC_all_service.class);
        JobInfo info2 = new JobInfo.Builder(MyConstants.MY_MIT_ALL_SERVEC_ID, componentName2)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(24 * 60 * 60 * 1000)
                .build();

        JobScheduler scheduler2 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode2 = scheduler2.schedule(info2);


       /// IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
       // intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
       // BroadcastReceiver mReceiver = new All_ScreenLock();
      //  registerReceiver(mReceiver, intentFilter);


      /*  //run isPhoneLockedOrNot method
        All_ScreenLock screenLock = new All_ScreenLock();
        Boolean screenstatus = screenLock.isPhoneLockedOrNot(this);
        Log.d("inotify ", "ScreenStatus" + screenstatus);

        if (screenstatus == false) {
            //Save to screen on table
            ScreenOnDbHelper screenOnDbHelper = new ScreenOnDbHelper(this);
            String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
            screenOnDbHelper.ScreenOnInsert();
            screenOnDbHelper.close();
            Log.d("iNotify", "SCreen status Saved");

        } else {
            //Save to screen off table
        }*/

        // Call ringermode  method and save into UA_RINGERMODE_TABLE
       /* RingerModeHelper ringermodeHelper = new RingerModeHelper();
        String RingerMode = ringermodeHelper.getRingerMode(this);
        Log.d("inotify ", "RingerMode" + RingerMode);


        RingerModeDbHelper ringerModeDbHelper = new RingerModeDbHelper(this);
        String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        Log.d("inotify ", "RingerMode" + RingerMode + "," + id);

        ringerModeDbHelper.RMinsert(id, RingerMode);
        ringerModeDbHelper.close();
        Log.d("inotify ", "Record Saved");*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        // if only the user  does not exist
        ProfileHelper profileHelper = new ProfileHelper(this);
        if (profileHelper.profileisExisCheck()) {
            //load settings
        } else {
            //show signup
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.signup);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            ImageButton imageButton = dialog.findViewById(R.id.ib_close);
            imageButton.setOnClickListener(v -> System.exit(0));
            Button button = dialog.findViewById(R.id.button11);
            button.setOnClickListener(view -> {
                // save to db -- create a new profile
                String datenow = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

                EditText editText = dialog.findViewById(R.id.editText);
                EditText editText2 = dialog.findViewById(R.id.editText2);
                RadioButton radioButton = dialog.findViewById(R.id.radioButton);
                RadioButton radioButton2 = dialog.findViewById(R.id.radioButton2);
                EditText editText3 = dialog.findViewById(R.id.editText3);
                EditText editText4 = dialog.findViewById(R.id.editText4);
                EditText editText5 = dialog.findViewById(R.id.editText5);

                String gender = "";

                if (radioButton.isSelected()) {
                    gender = "Male";
                }
                if (radioButton2.isSelected()) {
                    gender = "Female";
                }

                if (profileHelper.insert(new ProfileModel(
                        datenow,
                        editText.getText().toString(),
                        editText2.getText().toString(),
                        gender,
                        editText3.getText().toString(),
                        editText4.getText().toString(),
                        editText5.getText().toString()
                ))) {
                    //if sucessfull
                    Toast.makeText(getApplicationContext(), "Successfully Saved User Details", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    //if failed
                    Toast.makeText(getApplicationContext(), "Failed To Save User Details", Toast.LENGTH_LONG).show();
                }
            });
            dialog.show();
        }
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        return PendingIntent.getService(this, 30, new Intent(this, NV_ActivityRecognitionService.class), PendingIntent.FLAG_UPDATE_CURRENT);
    }


    public void button_settings(View view) {
        Intent intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void button_notificationHistory(View view) {
        Intent intent = new Intent(MainMenuActivity.this, NotificationHistoryActivity.class);
        startActivity(intent);

    }

    public void button_userattentiveness(View view) {
        Intent intent = new Intent(MainMenuActivity.this, UserAttentivenessActivity.class);
        startActivity(intent);
    }

    public void button_notificationviewability(View view) {
        Intent intent = new Intent(MainMenuActivity.this, NotificationViewabilityActivity.class);
        startActivity(intent);
    }

    public void button_userprofile(View view) {
        Intent intent = new Intent(MainMenuActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void button_usercharacteristics(View view) {
        Intent intent = new Intent(MainMenuActivity.this, UsercharacteristicsActivity.class);
        startActivity(intent);
    }

    public void testMihitha(View view) {
       // Log.d("inotify","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" );
        TopAppsHelper topAppsHelper = new TopAppsHelper(view.getContext());
        List<AppInfoModel> topgamingapp = topAppsHelper.topAppGaming();

        for (AppInfoModel value : topgamingapp)
        {
            Log.d("inotify","gaming top apps - " + value.getAppName());
        }

        //TopAppsHelper topAppsHelper2 = new TopAppsHelper(view.getContext());

        List<AppInfoModel> topCommunicationApp = topAppsHelper.topAppCommunication();
        for (AppInfoModel value : topCommunicationApp)
        {
            Log.d("inotify","communication top apps - " + value.getAppName());
        }

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(view.getContext());
        List<AppInfoModel> myGamingApp = applicationsHelper.myGamingAppGet();
        for(AppInfoModel value : myGamingApp)
        {
            Log.d("inotify","my gaming apps - " + value.getAppName());
        }

        int gamingCount = applicationsHelper.commonGamingAppCount();
        Log.d("inotify","gaming count - " + gamingCount);

        List<AppInfoModel> myCommunicationApp = applicationsHelper.myCommunicationAppGet();

        for(AppInfoModel value : myCommunicationApp)
        {
            Log.d("inotify","my Communication apps - " + value.getAppName());
        }

        int communicationCount = applicationsHelper.commonCommunicationAppCount();
        Log.d("inotify","Communication count - " + communicationCount);

        List<AppInfoModel> topMusicVideoApp = topAppsHelper.topAppMusicVideo();
        for(AppInfoModel value : topMusicVideoApp)
        {
            Log.d("inotify","top music and video apps - " + value.getAppName());
        }

        List<AppInfoModel> myMusicVideoApp = applicationsHelper.myMusicVideoAppGet();
        for(AppInfoModel value : myMusicVideoApp)
        {
            Log.d("inotify","my music apps - " +value.getAppName());
        }

        int MusicVideoCount = applicationsHelper.commonMusicVideoAppCount();
        Log.d("inotify","MusicVideoCount count - " + MusicVideoCount);

        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
        applicationDbHelper.appCategoryCount();

        UC_CalenderEvent uc_calenderEvent = new UC_CalenderEvent();
        uc_calenderEvent.getcalanderEventCount(this);

        //applicationsHelper.saveCurrentPhoneApps();
        //for insert the apps to database

        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        appUsageHelper.saveTodaysAppUsage();

    }

    public void testChaya(View view) {

    }

    public void testPrashan(View view) {

    }

    public void testDinu(View view) {

    }

    public void testCategory(View view) {
      //ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
      //applicationDbHelper.updateCategory();

      AppUsageHelper appUsageHelper = new AppUsageHelper(this);
      appUsageHelper.appAllUsageAvgGet();
      appUsageHelper.appAllUsageTodayGet();
      appUsageHelper.appsUsageAvgGet(AppcategoriesConstants.SOCIAL);
      appUsageHelper.appsUsageTodayGet(AppcategoriesConstants.SOCIAL);



    }


}