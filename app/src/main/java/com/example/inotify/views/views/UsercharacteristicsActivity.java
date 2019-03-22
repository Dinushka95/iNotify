package com.example.inotify.views.views;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
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
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.AttributeCountDbHelper;
import com.example.inotify.dbHelpers.CalenderEventDbHelper;
import com.example.inotify.dbHelpers.CallDurationDbHelper;
import com.example.inotify.dbHelpers.ChargerDbHelper;
import com.example.inotify.dbHelpers.UserCharacteristics_DbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.CalenderEventHelper;
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


    public void displayOpenness()
    {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long appUsageAVG = appUsageHelper.appAllUsageAvgGet();
        long appUsageToday = appUsageHelper.appAllUsageTodayGet();
        long socialAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
        long socialAPpUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);

        appUsageHelper.saveTodaysAppUsage();

        long allAppsUsage = ( appUsageAVG - appUsageToday)/ appUsageAVG; //allappusge 1st attribute
        Log.d("inotify","allAppsUsage......." + allAppsUsage);

        long socialAppUsage = (socialAPpUsageAVG - socialAPpUsageToday)/socialAPpUsageAVG; //social app usage 2nd attribute
        Log.d("inotify","socialAppUsage......." + socialAppUsage);


        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int NoofApps = applicationsHelper.appCountGet();
        Log.d("inotify","NoofApps......." + NoofApps);

        long noOfAVG = applicationsHelper.appConutAVG();
        Log.d("inotify","noOfAVG......." + noOfAVG);

        long newApps = (noOfAVG - NoofApps)/(noOfAVG+1); //newly downloaded apps - 3rd attribute1
        Log.d("inotify","newApps......." + newApps);

        int noOfSocialApps = applicationsHelper.commonSocialAppCount();  // no of social apps 4th attributes
        Log.d("inotify","noOfSocialApps......." + noOfSocialApps);

        int noOfCommunicationApps = applicationsHelper.commonCommunicationAppCount(); //no of communication app count
        Log.d("inotify","noOfCommunicationApps......." + noOfCommunicationApps);


        //get the probability of all the attributes
        long allAppUsageProbability = (allAppsUsage *20)/100;
        long socialAppUsageProbability = (socialAppUsage *20)/100;
        long newAppsProbability = (newApps *20)/100;
        long noOfSocialAppsprobability = (noOfSocialApps * 20)/100;
        long noOfSocialAppsProbability = (noOfCommunicationApps *20)/100;


        Log.d("inotify","allAppUsageProbability......." + allAppUsageProbability);
        Log.d("inotify","socialAppUsageProbability......." + socialAppUsageProbability);
        Log.d("inotify","newAppsProbability......." + newAppsProbability);
        Log.d("inotify","noOfSocialAppsProbability......." + noOfSocialAppsProbability);


        long openness = (allAppUsageProbability + socialAppUsageProbability + newAppsProbability + noOfSocialAppsprobability + noOfSocialAppsProbability);
        Log.d("inotify","Openness......." + openness);

        final TextView textViewToChange = findViewById(R.id.allAppUsageText);
        final TextView textViewToChange2 = findViewById(R.id.socialAppusageText);
        final TextView textViewToChange3 = findViewById(R.id.NoOfAPpsText);
        final TextView textViewToChange4 = findViewById(R.id.openness);

        textViewToChange.setText(""+allAppUsageProbability);
        textViewToChange2.setText(""+socialAppUsageProbability);
        textViewToChange3.setText(""+newAppsProbability);
         textViewToChange4.setText(""+openness);
    }

    public void displayExtraversion()
    {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long socialAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
        long socialAPpUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);

        long socialAppUsage = (socialAPpUsageAVG - socialAPpUsageToday)/socialAPpUsageAVG; //social app usage 2nd attribute
        Log.d("inotify","socialAppUsage......." + socialAppUsage);

        long socialAppUsageProbability = (socialAppUsage *20)/100;//----1st attribute

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int socialAppCount = applicationsHelper.commonSocialAppCount(); //think again have to get the avg also

        long socialAppCountProbability = (socialAppCount * 20)/100;//----2nd attribute

        int gamingAppCount = applicationsHelper.commonGamingAppCount();

        long gamingAppCountProbability = (gamingAppCount * 20)/100; //----3rd attribute

        CallDurationHelper callDurationHelper = new CallDurationHelper(this);
        long callDurationToday = callDurationHelper.getCallDuraionAVGToday();
        long callDurationAVG = callDurationHelper.getCallDurationAVG();

        long callDuration = (callDurationAVG - callDurationToday)/callDurationAVG;

        long callDurationProbability = (callDuration * 20)/100;//----4th attribute



    }

    public void displayConscientiousness()
    {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long musicVideoAPpUsage = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.MUSICANDAUDIO);
        long musicVideoAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.MUSICANDAUDIO);

        long usageMusicVideoApp = musicVideoAPpUsageAVG - musicVideoAPpUsage;

        long socialAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
        long socialAPpUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);

        long usagesocialApp = socialAPpUsageAVG - socialAPpUsageToday;

        CalenderEventHelper calenderEventHelper = new CalenderEventHelper(this);
        int calendarEvent = calenderEventHelper.getcalanderEventCount(this);

        //long conscientiousness = ()



    }

    public void displayAgreeableness()
    {

    }

    public void displayNeuroticism()
    {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long socialAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
        long socialAPpUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);

        long socialAppUsage = (socialAPpUsageAVG - socialAPpUsageToday)/socialAPpUsageAVG; //social app usage 2nd attribute
        Log.d("inotify","socialAppUsage......." + socialAppUsage);

        long socialAppUsageProbability = (socialAppUsage *20)/100;//----1st attribute

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int socialAppCount = applicationsHelper.commonSocialAppCount();

        long socialAppCountProbability = (socialAppCount * 20)/100;//----2nd attribute

        long appUsageAVG = appUsageHelper.appAllUsageAvgGet();
        long appUsageToday = appUsageHelper.appAllUsageTodayGet();

        long allAppsUsage = ( appUsageAVG - appUsageToday)/ appUsageAVG; //allappusge 1st attribute
        Log.d("inotify","allAppsUsage......." + allAppsUsage);

        int photograpyApps = applicationsHelper.commonPhotograpyAppCount();

        long photograpyAppsProbability = (photograpyApps * 20)/100;

        long photograpyAppUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.PHOTOGRAPY);
        long photograpyAppUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.PHOTOGRAPY);

        long photograpyAppUsage = (photograpyAppUsageAVG - photograpyAppUsageToday)/photograpyAppUsageAVG;

        long photograpyAppUsageProbability = (photograpyAppUsage * 20)/100;//4th attribute


    }

    public void DisplayOpenness(View view) {
        this.displayOpenness();


    }

    public void test1(View view) {
//       AppUsageHelper appUsageHelper = new AppUsageHelper(this);
//       appUsageHelper.saveTodaysAppUsage();
//        appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
//        appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);
//        appUsageHelper.appAllUsageTodayGet();
//        appUsageHelper.appAllUsageAvgGet();


//        CallDurationHelper callDurationHelper = new CallDurationHelper(this);
//        callDurationHelper.getCallDuraionAVGToday();
//        callDurationHelper.getCallDurationAVG();

//        ChargerHelper chargerHelper = new ChargerHelper(this);
//        chargerHelper.powerOninsert();
//        chargerHelper.powerOffinsert();

       // getContacts(this);

        ContactsHelper contactsHelper = new ContactsHelper(this);
//        contactsHelper.getcontactToday();
//        contactsHelper.getContactAVG();

        ScreenOnTimeHelper screenOnTimeHelper =  new ScreenOnTimeHelper(this);
        Log.d("inotify","want to check......................");
        screenOnTimeHelper.ScreenOnTimeTodayGet();

        Log.d("inotify","want to check AGAIN...........................");
        //screenOnTimeHelper.ScreenOnTimeAVGGet();

        AttributeCountDbHelper attributeCountDbHelper = new AttributeCountDbHelper(this);
        attributeCountDbHelper.atrributeCountInser();
        Log.d("inotify","attributeCountDbHelper.atrributeCountInser().........."+ attributeCountDbHelper.atrributeCountInser());
//

    }

    public void test2(View view) {

//          ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
//          applicationsHelper.saveCurrentPhoneApps();

       // applicationsHelper.appCountGet();

//        List<AppInfoModel> apps  =  new ArrayList<AppInfoModel>();
//        applicationsHelper.appInfoInsert(apps);



//        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
//        applicationDbHelper.updateCategory();
//        CalenderEventHelper calenderEvent = new CalenderEventHelper(this);
//        calenderEvent.updateTodayCalendar();



        //CalenderEventDbHelper calenderEventDbHelper = new CalenderEventDbHelper(this);
        //calenderEventDbHelper.checkIfExist();

          // this.displayOpenness();
       // this.displayExtraversion();

        ContactsHelper contactsHelper = new ContactsHelper(this);
        contactsHelper.getcontactToday();
        contactsHelper.getContactAVG();



        ChargerHelper chargerHelper = new ChargerHelper(this);
        chargerHelper.powerOninsert();
        chargerHelper.powerOffinsert();
        chargerHelper.powerOnCount();
        chargerHelper.powerOffCount();


//        ChargerDbHelper chargerDbHelper =new ChargerDbHelper(this);
//        int x= chargerDbHelper.powerOffCountGet();
//        Log.d("inotify","TTTTTTTTTTTTTTTTTTTTTTTTTT"+x);




    }



}
