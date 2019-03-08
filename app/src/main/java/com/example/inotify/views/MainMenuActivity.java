package com.example.inotify.views;

import android.Manifest;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.configs.MyConstants;
import com.example.inotify.dbHelpers.RingerModeDbHelper;
import com.example.inotify.helpers.All_ScreenLock;
import com.example.inotify.helpers.ProfileHelper;
import com.example.inotify.helpers.RingerModeHelper;
import com.example.inotify.models.ProfileModel;
import com.example.inotify.services.NV_ActivityRecognitionService;
import com.example.inotify.services.NV_LocationService;
import com.example.inotify.services.NV_NotificationViewabilityService;
import com.example.inotify.services.UC_all_service;
import com.google.android.gms.location.ActivityRecognitionClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {


    ////////////////////////////////////////////////////////
    /////    Debugger Logger switch                 ////////
    ////////////////////////////////////////////////////////




    /////////////////////////////////////////////////////////



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);






        if(MyConstants.PERMISSION_CONTACTS==true&&MyConstants.PERMISSION_LOCATION==true&&MyConstants.PERMISSION_CALENDER==true&&MyConstants.PERMISSION_PHONE==true){
            tt();
        }else {


            // Toast.makeText(getApplicationContext(),"You have not given full permission so app will not work properly - Please restart and give full permission",Toast.LENGTH_LONG).show();


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
        int resultCode = scheduler.schedule(info);


        ComponentName componentName1 = new ComponentName(MainMenuActivity.this, NV_NotificationViewabilityService.class);
        JobInfo info1 = new JobInfo.Builder(MyConstants.MY_BUSYORNOT_SERVEC_ID, componentName1)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(10 * 60 * 1000)
                .build();

        JobScheduler scheduler1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode1 = scheduler1.schedule(info1);


        ComponentName componentName2 = new ComponentName(MainMenuActivity.this, UC_all_service.class);
        JobInfo info2 = new JobInfo.Builder(MyConstants.MY_MIT_ALL_SERVEC_ID, componentName2)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(24 * 60 * 60 * 1000)
                .build();

        JobScheduler scheduler2 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode2 = scheduler2.schedule(info2);


        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new All_ScreenLock();
        registerReceiver(mReceiver, intentFilter);


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
        RingerModeHelper ringermodeHelper = new RingerModeHelper();
        String RingerMode = ringermodeHelper.getRingerMode(this);
        Log.d("inotify ", "RingerMode" + RingerMode);


        RingerModeDbHelper ringerModeDbHelper = new RingerModeDbHelper(this);
        String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        Log.d("inotify ", "RingerMode" + RingerMode + "," + id);

        ringerModeDbHelper.RMinsert(id, RingerMode);
        ringerModeDbHelper.close();
        Log.d("inotify ", "Record Saved");

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

            ImageButton imageButton = dialog.findViewById(R.id.ib_close);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            Button button = (Button) dialog.findViewById(R.id.button11);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // save to db -- create a new profile

                    String datenow = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

                    EditText editText = (EditText) dialog.findViewById(R.id.editText);
                    EditText editText2 = (EditText) dialog.findViewById(R.id.editText2);
                    RadioButton radioButton = (RadioButton) dialog.findViewById(R.id.radioButton);
                    RadioButton radioButton2 = (RadioButton) dialog.findViewById(R.id.radioButton2);
                    EditText editText3 = (EditText) dialog.findViewById(R.id.editText3);
                    EditText editText4 = (EditText) dialog.findViewById(R.id.editText4);
                    EditText editText5 = (EditText) dialog.findViewById(R.id.editText5);

                    String gender = "";

                    if (radioButton.isSelected()) {
                        gender = "Male";
                    }
                    if (radioButton.isSelected()) {
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
    }

    public void testChaya(View view) {
    }

    public void testPrashan(View view) {
    }

    public void testDinu(View view) {
    }
}