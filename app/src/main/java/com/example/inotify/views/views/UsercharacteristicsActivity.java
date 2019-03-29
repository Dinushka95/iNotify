package com.example.inotify.views.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.CallUsageDbHelper;
import com.example.inotify.dbHelpers.CharacteristicFinalDbHelper;
import com.example.inotify.dbHelpers.UserCharacteristics_DbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.CallUsageHelper;
import com.example.inotify.helpers.ChargerHelper;

public class UsercharacteristicsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_characteristics);

    }





    public void DisplayOpenness(View view) {
       // this.displayOpenness();


    }

    public void test1(View view) {
   }

    public void test2(View view) {
     }


    public void Details(View view) {
        Intent intent = new Intent(this,Conscientiousness.class);
        startActivity(intent);

    }


}
