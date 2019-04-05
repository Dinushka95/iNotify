package com.example.inotify.views.views;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.example.inotify.R;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.CharacteristicFinalDbHelper;
import com.example.inotify.dbHelpers.ContactsDbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.AttributeCountHelper;
import com.example.inotify.helpers.CalenderEventHelper;
import com.example.inotify.helpers.CallUsageHelper;
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.ContactsHelper;
import com.example.inotify.helpers.ScreenOnTimeHelper;

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


//        CheckAvailabilityHelper checkAvailabilityHelper= new CheckAvailabilityHelper();
//        checkAvailabilityHelper.saveCurrentPhoneAppsOnAvailability();

//        AttributeCountHelper attributeCountHelper = new AttributeCountHelper(this);
//        attributeCountHelper.atrributeCountInsert();

        Openness openness = new Openness();
        openness.displayOpenness(this.getApplicationContext());
//
//        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
//        long f = appUsageHelper.appAllsUsageToday();
//        Log.d("inotify","f-------------" + f);
    }

    public void test2(View view) {

        Intent I = new Intent(this, ChartCharacteristicsActivity.class);
        startActivity(I);
//        CalenderEventHelper calenderEventHelper = new CalenderEventHelper(this);
//
//        int x = calenderEventHelper.getcalanderEventCount(this);
//        calenderEventHelper.updateTodayCalendar();
//        Log.d("inotify","calemder-------------" + x);
//        boolean y = calenderEventHelper.calenderEventCount_insert();
//        Log.d("inotify","calenderEventCount_insert-------------" + y);
//
//        CallUsageHelper callUsageHelper = new CallUsageHelper(this);
//        callUsageHelper.getTodayCallListFromPhone(this);
//        callUsageHelper.saveTodayTotalCallDurationToDb();
//
//        ContactsHelper contactsHelper = new ContactsHelper(this);
//        contactsHelper.ContactsCountInsert();

//        ChargerHelper chargerHelper = new ChargerHelper(this);
//        chargerHelper.powerOninsert();

//        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
//        ArrayList<String> category;
//        category = applicationDbHelper.getAppPackage();
//        Log.d("cat","cat " + category.get(2));
//
//        applicationDbHelper.appCategoryUpdate(category);

//        CharacteristicFinalDbHelper characteristicFinalDbHelper = new CharacteristicFinalDbHelper(this);
//        boolean x = characteristicFinalDbHelper.characteristicsInsert();



     }


    public void Details(View view) {
        Intent intent = new Intent(this,Conscientiousness.class);
        startActivity(intent);


    }


}
