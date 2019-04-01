package com.example.inotify.views.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.inotify.R;
import com.example.inotify.helpers.ApplicationsHelper;

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
        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        applicationsHelper.saveCurrentPhoneAppsOnAvailability();

//        CheckAvailabilityHelper checkAvailabilityHelper= new CheckAvailabilityHelper();
//        checkAvailabilityHelper.saveCurrentPhoneAppsOnAvailability();

    }

    public void test2(View view) {
     }


    public void Details(View view) {
        Intent intent = new Intent(this,Conscientiousness.class);
        startActivity(intent);


    }


}
