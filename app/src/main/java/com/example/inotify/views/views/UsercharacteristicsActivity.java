package com.example.inotify.views.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.example.inotify.R;
import com.example.inotify.dbHelpers.AppUsageDbHelper;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.helpers.ApplicationsHelper;

import com.google.api.LogDescriptor;

import java.util.ArrayList;

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
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
        ArrayList<String> category;
        category = applicationDbHelper.getAppPackage();
        Log.d("cat","cat " + category.get(2));

        applicationDbHelper.appCategoryUpdate(category);
   }

    public void test2(View view) {
//        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
//        applicationsHelper.saveCurrentPhoneApps();
     }


    public void Details(View view) {
        Intent intent = new Intent(this,Conscientiousness.class);
        startActivity(intent);


    }


}
