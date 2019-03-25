package com.example.inotify.views.views;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.AppCategoriesConstants;
import com.example.inotify.dbHelpers.AppUsageDbHelper;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.AttributeCountDbHelper;
import com.example.inotify.dbHelpers.CalenderEventDbHelper;
import com.example.inotify.dbHelpers.CallDurationDbHelper;
import com.example.inotify.dbHelpers.CharacteristicFinalDbHelper;
import com.example.inotify.dbHelpers.ChargerDbHelper;
import com.example.inotify.dbHelpers.UserCharacteristics_DbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.AttributeCountHelper;
import com.example.inotify.helpers.CalenderEventHelper;
import com.example.inotify.helpers.CallDurationDbHelp;
import com.example.inotify.helpers.CallDurationHelper;
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.ContactsHelper;
import com.example.inotify.helpers.ScreenOnTimeHelper;
import com.example.inotify.models.ContactsModel;
import com.example.inotify.services.UserCharacteristics_service;

import org.w3c.dom.Attr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UsercharacteristicsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_characteristics);

//        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
//        appUsageHelper.saveTodaysAppUsage();
    }





    public void DisplayOpenness(View view) {
       // this.displayOpenness();


    }

    public void test1(View view) {
        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int x = applicationsHelper.commonCommunicationAppTodayCount();
        Log.d("inotify","xxxxxxxxxxxxxxxxxxxxxxxxxx   "+x);


//       AppUsageHelper appUsageHelper = new AppUsageHelper(this);
//       appUsageHelper.saveTodaysAppUsage();
//        appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
//        appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);
//        appUsageHelper.appAllUsageTodayGet();
//        appUsageHelper.appAllUsageAvgGet();
     //   this.displayOpenness();


        CallDurationHelper callDurationHelper = new CallDurationHelper(this);
        UserCharacteristics_DbHelper userCharacteristics_dbHelper = new UserCharacteristics_DbHelper(this);
       CallDurationDbHelper callDurationDbHelper = new CallDurationDbHelper(this);
       callDurationDbHelper.getCallDuration();
       callDurationDbHelper.callDurationInsert();


//        callDurationHelper.getCallDuraionAVGToday();
//        callDurationHelper.getCallDurationAVG();
//
//        ChargerHelper chargerHelper = new ChargerHelper(this);
//        chargerHelper.powerOninsert();
//        chargerHelper.powerOffinsert();

       // getContacts(this);
//
//        ContactsHelper contactsHelper = new ContactsHelper(this);
//        contactsHelper.getContacts(this);//need to run once per day
//        contactsHelper.getcontactToday();
//        contactsHelper.getContactAVG();

//        ScreenOnTimeHelper screenOnTimeHelper =  new ScreenOnTimeHelper(this);
//        Log.d("inotify","want to check......................");
//        screenOnTimeHelper.ScreenOnTimeTodayGet();
//
//        Log.d("inotify","want to check AGAIN...........................");
        //screenOnTimeHelper.ScreenOnTimeAVGGet();
//
    //    AttributeCountDbHelper attributeCountDbHelper = new AttributeCountDbHelper(this);
//        attributeCountDbHelper.atrributeCountInser();
//        Log.d("inotify","attributeCountDbHelper.atrributeCountInser().........."+ attributeCountDbHelper.atrributeCountInser());



       // this.displayExtraversion();
        CharacteristicFinalDbHelper characteristicFinalDbHelper = new CharacteristicFinalDbHelper(this);
//        boolean m = characteristicFinalDbHelper.characterFinalInsert();
//        Log.d("inotify","mmmmmmmmmmmmmqqqqqqqqqqqqqqqq" + m);
//        characteristicFinalDbHelper.xxx();
//        characteristicFinalDbHelper.characterGet("20190325");

//

    }

    public void test2(View view) {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        //appUsageHelper.saveTodaysAppUsage(); //---3

//        AttributeCountDbHelper attributeCountDbHelperHelper = new AttributeCountDbHelper(this);
//        attributeCountDbHelperHelper.atrributeCountInsert();

//        long appUsage = appUsageHelper.appAllsUsageToday();
//        Log.d("inotify","appUsage.................................." + appUsage);




//      AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(this);
//      int nooo = appUsageDbHelper.socialAppsUsageAVG();
//      Log.d("inotify","nooooooooooooooooooooooo" + nooo);
//
//      long apps = appUsageDbHelper.appAllsUsageAVG();
//        Log.d("inotify","mmmmmmmmmmmmmmmmmm" + apps);
//
//
//        //   AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(this);
//        int socialcolum = appUsageDbHelper.socialUsageTimeColumCountGet();
//      Log.d("inotify","socialcolum................." + socialcolum);
//
//      int all = appUsageDbHelper.allUsageTimeColumCountGet();
//        Log.d("inotify","all................." + all);







//        CharacteristicFinalDbHelper characteristicFinalDbHelper =  new CharacteristicFinalDbHelper(this);
//        characteristicFinalDbHelper.charcateristicsInser();



        CallDurationDbHelp callDurationDbHelp = new CallDurationDbHelp();
        callDurationDbHelp.getCallDuration();

//       CallDurationDbHelper callDurationDbHelper = new CallDurationDbHelper(view.getContext());
//        long time = callDurationDbHelper.getCallDuration();
//        callDurationDbHelper.callDurationInsert(time);


          ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
         //applicationsHelper.saveCurrentPhoneApps();//------1

        //applicationsHelper.saveCurrentPhoneApps();
        applicationsHelper.appCountGetToday();

//        List<AppInfoModel> apps  =  new ArrayList<AppInfoModel>();
//        applicationsHelper.appInfoInsert(apps);


    ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
       // applicationDbHelper.updateCategory();//2
//        CalenderEventHelper calenderEvent = new CalenderEventHelper(this);
//        calenderEvent.updateTodayCalendar();



        //CalenderEventDbHelper calenderEventDbHelper = new CalenderEventDbHelper(this);
        //calenderEventDbHelper.checkIfExist();

          // this.displayOpenness();
       // this.displayExtraversion();





        ChargerHelper chargerHelper = new ChargerHelper(this);
        chargerHelper.powerOninsert();
        chargerHelper.powerOffinsert();
        chargerHelper.powerOnCount();
        chargerHelper.powerOffCount();


//        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(this);
//        int x= chargerDbHelper.powerOffCountGet();
//        Log.d("inotify","TTTTTTTTTTTTTTTTTTTTTTTTTT"+x);




    }


    public void Details(View view) {
        Intent intent = new Intent(this,Conscientiousness.class);
        startActivity(intent);

      /*  Openness openness = new Openness();
        long opennessANS = openness.displayOpenness();
        Log.d("inotify","Openness................................." + opennessANS);

        Extraversion extraversion = new Extraversion();
        long extraversionANS = extraversion.displayExtraversion();
        Log.d("inotify","extraversion................................." + extraversionANS);*/

       // Bundle bundle = getIntent().getExtras();
      //  String message = bundle.getString("Openness");

         TextView textViewToChange = findViewById(R.id.openness1);
     //   textViewToChange.setText(message);
         TextView textViewToChange2 = findViewById(R.id.Conscientiousness1);
//        final TextView textViewToChange3 = findViewById(R.id.Extraversion1);
//        final TextView textViewToChange4 = findViewById(R.id.Agreeableness);
//        final TextView textViewToChange5 = findViewById(R.id.Neuroticism1);



     ///   textViewToChange2.setText(""+extraversionANS);
     //   textViewToChange.setText(""+opennessANS);
//        textViewToChange3.setText(""+socialAppTodayProbability);
//        textViewToChange4.setText(""+communicationAppTodayProbability);
//        textViewToChange5.setText(""+todayCommunicationAppUsageProbability);



    }


}
