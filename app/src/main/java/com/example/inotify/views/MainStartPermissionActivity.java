package com.example.inotify.views;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.MyConstants;
import com.example.inotify.services.MyNotificationListenerService;


public class MainStartPermissionActivity extends AppCompatActivity {

// text view varriables
    TextView tvWarningMessage;

    TextView tvContacts;
    TextView tvLocation;
    TextView tvCalender;
    TextView tvPhone;
    TextView tvNotificationAccess;
    TextView tvUsageAccess;

// button varriables
    Button btContacts;
    Button btLocation;
    Button btCalender;
    Button btPhone;
    Button btNotificationAccess;
    Button btUsageAccess;
//used to fixed a bug that cause the buttons unclickable due to on resume getting called ...
    Boolean pauseCheckErrorFix=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemClock.sleep(1000);

        tvWarningMessage = (TextView) findViewById(R.id.textViewWarningMessage);

        tvContacts = (TextView) findViewById(R.id.textViewContacts);
        tvLocation = (TextView) findViewById(R.id.textViewLocation);
        tvCalender = (TextView) findViewById(R.id.textViewCalender);
        tvPhone = (TextView) findViewById(R.id.textViewPhone);
        tvNotificationAccess = (TextView) findViewById(R.id.textViewNotificationAccess);
        tvUsageAccess = (TextView) findViewById(R.id.textViewUsageAccess);


        Button btContacts = (Button) findViewById(R.id.buttonPermissionContact);
        Button btLocation = (Button) findViewById(R.id.buttonPermissionLocation);
        Button btCalender = (Button) findViewById(R.id.buttonPermissionCalender);
        Button btPhone = (Button) findViewById(R.id.buttonPermissionPhone);
        Button btNotificationAccess = (Button) findViewById(R.id.buttonPermissionNotificationAccess);
        Button btUsageAccess = (Button) findViewById(R.id.buttonPermissionUsageAccess);

// contacts  permission check
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            MyConstants.PERMISSION_CONTACTS = false;
            tvContacts.setTextColor(Color.RED);
            tvContacts.setText("Contact Permission Denied");

        } else {
            MyConstants.PERMISSION_CONTACTS = true;
            tvContacts.setTextColor(Color.GREEN);
            tvContacts.setText("Contact Permission Granted");
            btContacts.setVisibility(View.GONE);
        }
// location  permission check
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            MyConstants.PERMISSION_LOCATION = false;
            tvLocation.setTextColor(Color.RED);
            tvLocation.setText("Location Permission Denied");
        } else {
            MyConstants.PERMISSION_LOCATION = true;
            tvLocation.setTextColor(Color.GREEN);
            tvLocation.setText("Location Permission Granted");
            btLocation.setVisibility(View.GONE);
        }
// calender  permission check
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            MyConstants.PERMISSION_CALENDER = false;
            tvCalender.setTextColor(Color.RED);
            tvCalender.setText("Calender Permission Denied");
        } else {
            MyConstants.PERMISSION_CALENDER = true;
            tvCalender.setTextColor(Color.GREEN);
            tvCalender.setText("Calender Permission Granted");
            btCalender.setVisibility(View.GONE);
        }
// phone log  permission check
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            MyConstants.PERMISSION_PHONE = false;
            tvPhone.setTextColor(Color.RED);
            tvPhone.setText("Phone Permission Denied");
        } else {
            MyConstants.PERMISSION_PHONE = true;
            tvPhone.setTextColor(Color.GREEN);
            tvPhone.setText("Phone Permission Granted");
            btPhone.setVisibility(View.GONE);
        }
// notification access  permission check
        if (isNotificationAccessGranted()) {
            MyConstants.PERMISSION_NOTIFICATIONACCESS = true;
            tvNotificationAccess.setTextColor(Color.GREEN);
            tvNotificationAccess.setText("Notification Access Permission Granted");
            btNotificationAccess.setVisibility(View.GONE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, MyConstants.MY_PERMISSIONS_NOTIFICATION_ACCESS);
            MyConstants.PERMISSION_NOTIFICATIONACCESS = false;
            tvNotificationAccess.setTextColor(Color.RED);
            tvNotificationAccess.setText("Notification Access Permission Denied");
        }
// usage  permission check
        if (isAccessUsageGranted()) {
            MyConstants.PERMISSION_USEAGEACCESS = true;
            tvUsageAccess.setTextColor(Color.GREEN);
            tvUsageAccess.setText("Usage Access Permission Granted");
            btUsageAccess.setVisibility(View.GONE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.PACKAGE_USAGE_STATS}, MyConstants.MY_PERMISSIONS_USAGE_ACCESS_);
            MyConstants.PERMISSION_USEAGEACCESS = false;
            tvUsageAccess.setTextColor(Color.RED);
            tvUsageAccess.setText("Usage Access Permission Denied");
        }

// check if all permission are true and then proceed to main menu
        checkPermissionTOProceed();
//all button click events and it will ask for that perticular permission
        btContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyConstants.PERMISSION_CONTACTS) {
                    ActivityCompat.requestPermissions(MainStartPermissionActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    pauseCheckErrorFix=true;
                }
            }
        });

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyConstants.PERMISSION_LOCATION) {
                    ActivityCompat.requestPermissions(MainStartPermissionActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MyConstants.MY_PERMISSIONS_REQUEST_READ_LOCATION);
                    pauseCheckErrorFix=true;
                }
            }
        });

        btCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyConstants.PERMISSION_CALENDER) {
                    ActivityCompat.requestPermissions(MainStartPermissionActivity.this, new String[]{Manifest.permission.READ_CALENDAR}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CALENDAR);
                    pauseCheckErrorFix=true;
                }
            }
        });

        btPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyConstants.PERMISSION_PHONE) {
                    ActivityCompat.requestPermissions(MainStartPermissionActivity.this, new String[]{Manifest.permission.READ_CALL_LOG}, MyConstants.MY_PERMISSIONS_REQUEST_READ_PHONE);
                    pauseCheckErrorFix=true;
                }
            }
        });

        btNotificationAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyConstants.PERMISSION_NOTIFICATIONACCESS) {
                    startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
                    Intent intent = new Intent(getApplicationContext(), MainStartPermissionActivity.class);
                    int mPendingIntentId = 18945;
                    PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                    pauseCheckErrorFix=true;
                }
            }
        });

        btUsageAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyConstants.PERMISSION_USEAGEACCESS) {
                    startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
                    Intent intent = new Intent(getApplicationContext(), MainStartPermissionActivity.class);
                    int mPendingIntentId = 18946;
                    PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                    pauseCheckErrorFix=true;
                }
            }
        });
    }

//if user give permission then change textview and button
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MyConstants.MY_PERMISSIONS_REQUEST_READ_CONTACTS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_CONTACTS = true;
                    tvContacts.setTextColor(Color.GREEN);
                    tvContacts.setText("Contact Permission Granted");
                    btContacts = (Button) this.findViewById(R.id.buttonPermissionContact);
                    btContacts.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_CONTACTS = false;
                    tvContacts.setTextColor(Color.RED);
                    tvContacts.setText("Contact Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }
            case MyConstants.MY_PERMISSIONS_REQUEST_READ_LOCATION: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_LOCATION = true;
                    tvLocation.setTextColor(Color.GREEN);
                    tvLocation.setText("Location Permission Granted");
                    btLocation = (Button) this.findViewById(R.id.buttonPermissionLocation);
                    btLocation.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_LOCATION = false;
                    tvLocation.setTextColor(Color.RED);
                    tvLocation.setText("Location Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }
            case MyConstants.MY_PERMISSIONS_REQUEST_READ_CALENDAR: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_CALENDER = true;
                    tvCalender.setTextColor(Color.GREEN);
                    tvCalender.setText("Calender Permission Granted");
                    btCalender = (Button) this.findViewById(R.id.buttonPermissionCalender);
                    btCalender.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_CALENDER = false;
                    tvCalender.setTextColor(Color.RED);
                    tvCalender.setText("Calender Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }
            case MyConstants.MY_PERMISSIONS_REQUEST_READ_PHONE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_PHONE = true;
                    tvPhone.setTextColor(Color.GREEN);
                    tvPhone.setText("Phone Permission Granted");
                    btPhone = (Button) this.findViewById(R.id.buttonPermissionPhone);
                    btPhone.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_PHONE = false;
                    tvPhone.setTextColor(Color.RED);
                    tvPhone.setText("Phone Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }
            case MyConstants.MY_PERMISSIONS_NOTIFICATION_ACCESS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_NOTIFICATIONACCESS = true;
                    tvNotificationAccess.setTextColor(Color.GREEN);
                    tvNotificationAccess.setText("Notification Access Permission Granted");
                    btNotificationAccess = (Button) this.findViewById(R.id.buttonPermissionPhone);
                    btNotificationAccess.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_NOTIFICATIONACCESS = false;
                    tvNotificationAccess.setTextColor(Color.RED);
                    tvNotificationAccess.setText("Notification Access Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }
        }
    }
// checks if all the permission are given before preceding to main menu
    public void checkPermissionTOProceed() {
        if (MyConstants.PERMISSION_CONTACTS == true && MyConstants.PERMISSION_LOCATION == true && MyConstants.PERMISSION_CALENDER == true && MyConstants.PERMISSION_PHONE == true && MyConstants.PERMISSION_NOTIFICATIONACCESS == true && MyConstants.PERMISSION_USEAGEACCESS == true) {
            Intent intent = new Intent(MainStartPermissionActivity.this, MainMenuActivity.class);
            startActivity(intent);
            MyConstants.PERMISSION_MAIN = true;
            tvWarningMessage.setText("All User Permission Granted Successfully...! Please wait loading....");
            tvWarningMessage.setTextColor(Color.GREEN);
            this.finish();
        } else {

        }
    }
// check if you have been granted access
    private boolean isAccessUsageGranted() {

        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            int mode = 0;
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                        applicationInfo.uid, applicationInfo.packageName);
            }
            return (mode == AppOpsManager.MODE_ALLOWED);

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    private boolean isNotificationAccessGranted() {
        ComponentName cn = new ComponentName(this, MyNotificationListenerService.class);
        String flat = Settings.Secure.getString(this.getContentResolver(), "enabled_notification_listeners");
        return flat != null && flat.contains(cn.flattenToString());
    }
    public void exit(View view) {
        System.exit(0);
    }

// you need this because if the user resumes from different activity the following functions need to be updated. is runs when it is not needed to stop it, it's inside a if statement
    @Override
    protected void onResume() {
        super.onResume();
        if(pauseCheckErrorFix) {
            tvNotificationAccess = (TextView) this.findViewById(R.id.textViewNotificationAccess);
            btNotificationAccess = (Button) this.findViewById(R.id.buttonPermissionNotificationAccess);
            tvUsageAccess = (TextView) this.findViewById(R.id.textViewUsageAccess);
            btUsageAccess = (Button) this.findViewById(R.id.buttonPermissionUsageAccess);

            if (isNotificationAccessGranted()) {
                MyConstants.PERMISSION_NOTIFICATIONACCESS = true;
                tvNotificationAccess.setTextColor(Color.GREEN);
                tvNotificationAccess.setText("Notification Access Permission Granted");
                btNotificationAccess.setVisibility(View.GONE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, MyConstants.MY_PERMISSIONS_NOTIFICATION_ACCESS);
                MyConstants.PERMISSION_NOTIFICATIONACCESS = false;
                tvNotificationAccess.setTextColor(Color.RED);
                tvNotificationAccess.setText("Notification Access Permission Denied");
            }

            if (isAccessUsageGranted()) {
                MyConstants.PERMISSION_USEAGEACCESS = true;
                tvUsageAccess.setTextColor(Color.GREEN);
                tvUsageAccess.setText("Usage Access Permission Granted");
                btUsageAccess.setVisibility(View.GONE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.PACKAGE_USAGE_STATS}, MyConstants.MY_PERMISSIONS_USAGE_ACCESS_);
                MyConstants.PERMISSION_USEAGEACCESS = false;
                tvUsageAccess.setTextColor(Color.RED);
                tvUsageAccess.setText("Usage Access Permission Denied");
            }
            pauseCheckErrorFix=false;
        }

    }


}