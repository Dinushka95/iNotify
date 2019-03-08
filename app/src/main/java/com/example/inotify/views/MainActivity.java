package com.example.inotify.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.MyConstants;


public class MainActivity extends AppCompatActivity {

 //   TextView tvContacts = (TextView)findViewById(R.id.textViewContacts);
   // TextView tvLocation = (TextView)findViewById(R.id.textViewLocation);
  //  TextView tvCalender = (TextView)findViewById(R.id.textViewCalender);
  //  TextView tvPhone = (TextView)findViewById(R.id.textViewPhone);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
           // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            Log.d("inotify ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
            MyConstants.PERMISSION_CONTACTS=false;

        }else {
            MyConstants.PERMISSION_CONTACTS=true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("inotify ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MyConstants.MY_PERMISSIONS_REQUEST_READ_LOCATION);
            MyConstants.PERMISSION_LOCATION=false;


        }else {
            MyConstants.PERMISSION_LOCATION=true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            Log.d("inotify ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CALENDAR);
            MyConstants.PERMISSION_CALENDER=false;

        }else {
            MyConstants.PERMISSION_CALENDER=true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            Log.d("inotify ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, MyConstants.MY_PERMISSIONS_REQUEST_READ_CALL_LOG);
            MyConstants.PERMISSION_PHONE=false;
        }else {
            MyConstants.PERMISSION_PHONE=true;
        }

        if(MyConstants.PERMISSION_CONTACTS==true&&MyConstants.PERMISSION_LOCATION==true&&MyConstants.PERMISSION_CALENDER==true&&MyConstants.PERMISSION_PHONE==true){
            Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
            startActivity(intent);
            this.finish();
        }else {


            // Toast.makeText(getApplicationContext(),"You have not given full permission so app will not work properly - Please restart and give full permission",Toast.LENGTH_LONG).show();


        }

    }


    public void test(View view) {
       System.exit(0);
    }


}