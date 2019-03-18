package com.example.inotify.views.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.AppCategoriesConstants;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.CalenderEventDbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.CalenderEventHelper;

public class UsercharacteristicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_characteristics);
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

    public void DisplayOpenness(View view) {
        this.displayOpenness();


    }

    public void test1(View view) {
       AppUsageHelper appUsageHelper = new AppUsageHelper(this);
//       appUsageHelper.saveTodaysAppUsage();
        appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
        appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);
        appUsageHelper.appAllUsageTodayGet();
        appUsageHelper.appAllUsageAvgGet();





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

           this.displayOpenness();


    }

}
