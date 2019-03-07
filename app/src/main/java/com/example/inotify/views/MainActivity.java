package com.example.inotify.views;

import android.Manifest;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.NotificationSqlLiteDbHelper;
import com.example.inotify.dbHelpers.RingerModeDbHelper;
import com.example.inotify.dbHelpers.ScreenStatusDbHelper;
import com.example.inotify.helpers.All_ScreenLock;
import com.example.inotify.helpers.ProfileHelper;
import com.example.inotify.helpers.RingerModeHelper;
import com.example.inotify.helpers.ScreenStatusHelper;
import com.example.inotify.models.ProfileModel;
import com.example.inotify.services.NV_ActivityRecognitionService;
import com.example.inotify.services.NV_LocationService;
import com.example.inotify.services.NV_NotificationViewabilityService;
import com.example.inotify.services.UC_all_service;
import com.google.android.gms.location.ActivityRecognitionClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.dbHelpers.MainSqlliteOpenHelp.DATABASE_NAME;

public class MainActivity extends AppCompatActivity {


    ////////////////////////////////////////////////////////
    /////    Debugger Logger switch                 ////////
    ////////////////////////////////////////////////////////

    public static final boolean MainUsercharacteristics_DebuggerLogger = true;
    public static final boolean MainAttentiviness_DebuggerLogger = true;


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

    public static final int MY_PERMISSIONS_REQUEST_READ_LOCATION = 1045;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2045;
    public static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 3045;
    public static final int MY_PERMISSIONS_REQUEST_READ_CALL_LOG = 4045;


    public static boolean MAIN_RUN = false;
    public boolean PERMISSION_MAIN = false;
    public boolean PERMISSION_CONTACTS = false;
    public boolean PERMISSION_LOCATION = false;
    public boolean PERMISSION_CALENDER = false;
    public boolean PERMISSION_PHONE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            Log.d("inotify ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );

        }else {
            PERMISSION_CONTACTS=true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("inotify ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_LOCATION);

        }else {
            PERMISSION_LOCATION=true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            Log.d("inotify ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, MY_PERMISSIONS_REQUEST_READ_CALENDAR);

        }else {
            PERMISSION_CALENDER=true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            Log.d("inotify ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, MY_PERMISSIONS_REQUEST_READ_CALL_LOG);

        }else {
            PERMISSION_PHONE=true;
        }

        if(PERMISSION_CONTACTS==true&&PERMISSION_LOCATION==true&&PERMISSION_CALENDER==true&&PERMISSION_PHONE==true){
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



        ComponentName componentName = new ComponentName(MainActivity.this, NV_LocationService.class);
        JobInfo info = new JobInfo.Builder(MY_LOCATION_LISTENER_SERVEC_ID, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(9 * 57 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);


        ComponentName componentName1 = new ComponentName(MainActivity.this, NV_NotificationViewabilityService.class);
        JobInfo info1 = new JobInfo.Builder(MY_BUSYORNOT_SERVEC_ID, componentName1)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(10 * 60 * 1000)
                .build();

        JobScheduler scheduler1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode1 = scheduler1.schedule(info1);


        ComponentName componentName2 = new ComponentName(MainActivity.this, UC_all_service.class);
        JobInfo info2 = new JobInfo.Builder(MY_MIT_ALL_SERVEC_ID, componentName2)
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

//cha Starts here  for testing --need to move to the notificationListnerService
        //run isPhoneLockedOrNot method
        ScreenStatusHelper screenStatusHelper = new ScreenStatusHelper();
        Boolean screenstatus = screenStatusHelper.isPhoneLockedOrNot(this);
        Log.d("inotify ", "ScreenStatus" + screenstatus);

        if (screenstatus == false) {
            //Save to screen on table
            ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(this);
            String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
            screenStatusDbHelper.ScreenOnInsert();
            screenStatusDbHelper.close();
            Log.d("iNotify" , "SCreen on status Saved");


            //check ScrennOnStatusGet Method
            String screen = screenStatusDbHelper.ScreenOnStatusGet();
            Log.d("iNotify" , "SCreen on status Saved(^__^) " +screen);


        }
        else
        {
            //Save to screen off table
            ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(this);
            String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
            screenStatusDbHelper.ScreenOffInsert();
            screenStatusDbHelper.close();
            Log.d("iNotify" , "SCreen off status Saved");
        }

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

    //Cha ends here

    @Override
    protected void onStart() {
        super.onStart();

        RingerModeDbHelper ringerModeDbHelper = new RingerModeDbHelper(this);
        String rm = ringerModeDbHelper.RingerModeGet();
        Log.d("inotify " ,"Ringer Mode status Saved(^_0_^" + rm);


        //Check Notification Viwed time
        NotificationSqlLiteDbHelper notificationSqlLiteDbHelper= new NotificationSqlLiteDbHelper(this);
        String viewedtime = notificationSqlLiteDbHelper.viewTimeGet();
        Log.d("iNotify" , "Notification Viewed time =  " +viewedtime);

       // NotificationSqlLiteDbHelper notificationSqlLiteDbHelper= new NotificationSqlLiteDbHelper(this);
        String recivedtime = notificationSqlLiteDbHelper.recivedTimeGet();
        Log.d("iNotify" , "Notification Recived time =  " +viewedtime);

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
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void button_notificationHistory(View view) {
        Intent intent = new Intent(MainActivity.this, NotificationHistoryActivity.class);
        startActivity(intent);

    }

    public void testmmm(View view) {
        Log.d("iNotify", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        // code to create a fragment
    }

    public void button_userattentiveness(View view) {
        Intent intent = new Intent(MainActivity.this, UserAttentivenessActivity.class);
        startActivity(intent);
    }

    public void button_notificationviewability(View view) {
        Intent intent = new Intent(MainActivity.this, NotificationViewabilityActivity.class);
        startActivity(intent);
    }

    public void button_userprofile(View view) {
        Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void button_usercharacteristics(View view) {
        Intent intent = new Intent(MainActivity.this, UsercharacteristicsActivity.class);
        startActivity(intent);
    }
}
