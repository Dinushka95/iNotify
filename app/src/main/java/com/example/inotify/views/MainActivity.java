package com.example.inotify.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.configs.MyConstants;


public class MainActivity extends AppCompatActivity {

     TextView tvContacts;
     TextView tvLocation;
     TextView tvCalender;
     TextView tvPhone;

     Button btContacts;
     Button btLocation;
     Button btCalender;
     Button btPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemClock.sleep(1000);

        tvContacts = (TextView)findViewById(R.id.textViewContacts);
        tvLocation = (TextView)findViewById(R.id.textViewLocation);
        tvCalender = (TextView)findViewById(R.id.textViewCalender);
        tvPhone = (TextView)findViewById(R.id.textViewPhone);


        Button btContacts = (Button) findViewById(R.id.buttonPermissionContact);
        Button btLocation  = (Button) findViewById(R.id.buttonPermissionLocation);
        Button btCalender  = (Button) findViewById(R.id.buttonPermissionCalender);
        Button btPhone  = (Button) findViewById(R.id.buttonPermissionPhone);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
           // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            MyConstants.PERMISSION_CONTACTS=false;
            tvContacts.setTextColor(Color.RED);
            tvContacts.setText("Contact Permission Denied");

        }else {
            MyConstants.PERMISSION_CONTACTS=true;
            tvContacts.setTextColor(Color.GREEN);
            tvContacts.setText("Contact Permission Granted");
            btContacts.setVisibility(View.GONE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MyConstants.MY_PERMISSIONS_REQUEST_READ_LOCATION);
            MyConstants.PERMISSION_LOCATION=false;
            tvLocation.setTextColor(Color.RED);
            tvLocation.setText("Location Permission Denied");
        }else {
            MyConstants.PERMISSION_LOCATION=true;
            tvLocation.setTextColor(Color.GREEN);
            tvLocation.setText("Location Permission Granted");
            btLocation.setVisibility(View.GONE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CALENDAR);
            MyConstants.PERMISSION_CALENDER=false;
            tvCalender.setTextColor(Color.RED);
            tvCalender.setText("Calender Permission Denied");
        }else {
            MyConstants.PERMISSION_CALENDER=true;
            tvCalender.setTextColor(Color.GREEN);
            tvCalender.setText("Calender Permission Granted");
            btCalender.setVisibility(View.GONE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CALL_LOG);
            MyConstants.PERMISSION_PHONE=false;
            tvPhone.setTextColor(Color.RED);
            tvPhone.setText("Phone Permission Denied");
        }else {
            MyConstants.PERMISSION_PHONE=true;
            tvPhone.setTextColor(Color.GREEN);
            tvPhone.setText("Phone Permission Granted");
            btPhone.setVisibility(View.GONE);
        }

        checkPermissionTOProceed();

        btContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MyConstants.PERMISSION_CONTACTS){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                }
            }
        });

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MyConstants.PERMISSION_LOCATION){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MyConstants.MY_PERMISSIONS_REQUEST_READ_LOCATION);
                }
            }
        });

        btCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MyConstants.PERMISSION_CALENDER){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CALENDAR}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CALENDAR);
                }
            }
        });

        btPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MyConstants.PERMISSION_PHONE){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CALL_LOG}, MyConstants.MY_PERMISSIONS_REQUEST_READ_PHONE);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MyConstants.MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_CONTACTS=true;
                    tvContacts.setTextColor(Color.GREEN);
                    tvContacts.setText("Contact Permission Granted");
                    btContacts = (Button) this.findViewById(R.id.buttonPermissionContact);
                    btContacts.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_CONTACTS=false;
                    tvContacts.setTextColor(Color.RED);
                    tvContacts.setText("Contact Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }
            case MyConstants.MY_PERMISSIONS_REQUEST_READ_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_LOCATION=true;
                    tvLocation.setTextColor(Color.GREEN);
                    tvLocation.setText("Location Permission Granted");
                    btLocation = (Button) this.findViewById(R.id.buttonPermissionLocation);
                    btLocation.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_LOCATION=false;
                    tvLocation.setTextColor(Color.RED);
                    tvLocation.setText("Location Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }
            case MyConstants.MY_PERMISSIONS_REQUEST_READ_CALENDAR: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_CALENDER=true;
                    tvCalender.setTextColor(Color.GREEN);
                    tvCalender.setText("Calender Permission Granted");
                    btCalender = (Button) this.findViewById(R.id.buttonPermissionCalender);
                    btCalender.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_CALENDER=false;
                    tvCalender.setTextColor(Color.RED);
                    tvCalender.setText("Calender Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }
            case MyConstants.MY_PERMISSIONS_REQUEST_READ_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyConstants.PERMISSION_PHONE=true;
                    tvPhone.setTextColor(Color.GREEN);
                    tvPhone.setText("Phone Permission Granted");
                    btPhone = (Button) this.findViewById(R.id.buttonPermissionPhone);
                    btPhone.setVisibility(View.GONE);
                    checkPermissionTOProceed();
                } else {
                    MyConstants.PERMISSION_PHONE=false;
                    tvPhone.setTextColor(Color.RED);
                    tvPhone.setText("Phone Permission Denied");
                    checkPermissionTOProceed();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


 public void checkPermissionTOProceed() {
     if(MyConstants.PERMISSION_CONTACTS==true&&MyConstants.PERMISSION_LOCATION==true&&MyConstants.PERMISSION_CALENDER==true&&MyConstants.PERMISSION_PHONE==true){
         Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
         startActivity(intent);
         MyConstants.PERMISSION_MAIN=true;
         this.finish();
     }else {

     }
 }
    public void test(View view) {
       System.exit(0);
    }


}