package com.example.inotify.views.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.AppCategoriesConstants;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;

public class Openness extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openness);

//        textView = findViewById(R.id.Op_attr_1);
//        //UsercharacteristicsActivity usercharacteristicsActivity = new UsercharacteristicsActivity();
//        //long open = usercharacteristicsActivity.displayOpenness();
//        Log.d("ghgh", "onCreate: " );
//        textView.setText("kkknkn");
    }

//    public long displayOpenness()
//    {
//        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
//        long appUsageAVG = appUsageHelper.appAllUsageAvgGet();
//        long appUsageToday = appUsageHelper.appAllUsageTodayGet();
//        long socialAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
//        long socialAPpUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);
//        long communicationAPpUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.COMMUNICATION);
//        long communicationAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.COMMUNICATION);
//
//        appUsageHelper.saveTodaysAppUsage();
//
//        long allAppsUsage = ( appUsageAVG - appUsageToday)/ appUsageAVG; //allappusge 1st attribute
//        Log.d("inotify","allAppsUsage......." + allAppsUsage);
//
//        long socialAppUsage = (socialAPpUsageAVG - socialAPpUsageToday)/socialAPpUsageAVG; //social app usage 2nd attribute
//        Log.d("inotify","socialAppUsage......." + socialAppUsage);
//
//
//        long communicationAppUsage = (communicationAPpUsageAVG - communicationAPpUsageToday)/communicationAPpUsageAVG; //social app usage 2nd attribute
//        Log.d("inotify","communicationAppUsage......." + communicationAppUsage);
//
//        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
//        int NoofApps = applicationsHelper.appCountGet();
//        Log.d("inotify","NoofApps......." + NoofApps);
//
//        long noOfAVG = applicationsHelper.appConutAVG();
//        Log.d("inotify","noOfAVG......." + noOfAVG);
//
//        long newApps = (noOfAVG - NoofApps)/(noOfAVG+1); //newly downloaded apps - 3rd attribute
//        Log.d("inotify","newApps......." + newApps);
//
//        int noOfSocialApps = applicationsHelper.commonSocialAppCount();  // no of social apps 4th attributes
//        Log.d("inotify","noOfSocialApps......." + noOfSocialApps);
//
//        int noOfCommunicationApps = applicationsHelper.commonCommunicationAppCount(); //no of communication app count
//        Log.d("inotify","noOfCommunicationApps......." + noOfCommunicationApps);
//
//
//        //get the probability of all the attributes
//        long allAppUsageProbability = (allAppsUsage *15)/100;
//        long socialAppUsageProbability = (socialAppUsage *15)/100;
//        long newAppsProbability = (newApps *15)/100;
//        long noOfSocialAppsprobability = (noOfSocialApps * 15)/100;
//        long noOfSocialAppsProbability = (noOfCommunicationApps *15)/100;
//        long noOfCommunicationAppsProbability = (noOfCommunicationApps * 15)/100;
//        long noOfCommunicationAppUsage = (communicationAppUsage * 15)/100;
//
//
//        Log.d("inotify","allAppUsageProbability......." + allAppUsageProbability);
//        Log.d("inotify","socialAppUsageProbability......." + socialAppUsageProbability);
//        Log.d("inotify","newAppsProbability......." + newAppsProbability);
//        Log.d("inotify","noOfSocialAppsProbability......." + noOfSocialAppsProbability);
//        Log.d("inotify","noOfSocialAppsProbability......." + noOfSocialAppsProbability);
//        Log.d("inotify","noOfCommunicationAppsProbability......." + noOfCommunicationAppsProbability);
//        Log.d("inotify","noOfCommunicationAppUsage......." + noOfCommunicationAppUsage);
//
//
//        long openness = (allAppUsageProbability + noOfCommunicationAppUsage + noOfCommunicationAppsProbability + socialAppUsageProbability + newAppsProbability + noOfSocialAppsprobability + noOfSocialAppsProbability);
//        Log.d("inotify","Openness......." + openness);
//
//        final TextView textViewToChange = findViewById(R.id.Op_attr_1);
//        final TextView textViewToChange2 = findViewById(R.id.Op_attr_2);
//        final TextView textViewToChange3 = findViewById(R.id.Op_attr_3);
//        final TextView textViewToChange4 = findViewById(R.id.Op_attr_4);
//        final TextView textViewToChange5 = findViewById(R.id.Op_attr_5);
//        final TextView textViewToChange6 = findViewById(R.id.Op_attr_6);
//
//        textViewToChange6.setText(""+allAppUsageProbability);
//        textViewToChange2.setText(""+socialAppUsageProbability);
//        textViewToChange.setText(""+newAppsProbability);
//        textViewToChange3.setText(""+noOfSocialAppsProbability);
//        textViewToChange4.setText(""+noOfCommunicationAppsProbability);
//        textViewToChange5.setText(""+noOfCommunicationAppUsage);
//
//        return openness;
//
//
//    }
        public long displayOpenness()
        {
           AppUsageHelper appUsageHelper = new AppUsageHelper(this);
            long todayAllAppUsage = appUsageHelper.appAllsUsageToday();
            long todaySocialAppUsage = appUsageHelper.socialAppsUsageToday();
            long todayCommunicationAppUsage = appUsageHelper.communicationAppsUsageToday();

            ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
            int NoofAppsToday = applicationsHelper.appCountGetToday();
            int NoofAppsAll = applicationsHelper.allAppCountAVG();

            int newApps = (NoofAppsAll - NoofAppsToday);

            int socialAppToday = applicationsHelper.commonSocialAppTodayCount();
            int communicationAppToday = applicationsHelper.commonCommunicationAppTodayCount();

            //probability
            long todayAllAppUsageProbability = (todayAllAppUsage * 16)/100;
            long todaySocialAppUsageProbability = (todaySocialAppUsage * 16)/100;
            long todayCommunicationAppUsageProbability = (todayCommunicationAppUsage * 16)/100;
            long newAppsProbability = (newApps * 16)/100;
            long socialAppTodayProbability = (socialAppToday * 16)/100;
            long communicationAppTodayProbability = (communicationAppToday * 16)/100;

            long Openness = (todayAllAppUsageProbability + todaySocialAppUsageProbability + todayCommunicationAppUsageProbability + newAppsProbability + socialAppTodayProbability + communicationAppTodayProbability)/100;

            Log.d("inotify","Openness..............."+ Openness);



        final TextView textViewToChange = findViewById(R.id.Op_attr_1);
        final TextView textViewToChange2 = findViewById(R.id.Op_attr_2);
        final TextView textViewToChange3 = findViewById(R.id.Op_attr_3);
        final TextView textViewToChange4 = findViewById(R.id.Op_attr_4);
        final TextView textViewToChange5 = findViewById(R.id.Op_attr_5);
        final TextView textViewToChange6 = findViewById(R.id.Op_attr_6);

        textViewToChange6.setText(""+todayAllAppUsageProbability);
        textViewToChange2.setText(""+todaySocialAppUsageProbability);
        textViewToChange.setText(""+newAppsProbability);
        textViewToChange3.setText(""+socialAppTodayProbability);
        textViewToChange4.setText(""+communicationAppTodayProbability);
        textViewToChange5.setText(""+todayCommunicationAppUsageProbability);


            Intent intent = new Intent(Openness.this, UserAttentivenessActivity.class);
            intent.putExtra("Openness", Openness);
            startActivity(intent);

            return Openness;


        }



    public void next_open(View view) {
        Intent intent = new Intent(this,Neuroticism.class);
        startActivity(intent);

    }

    public void back_open(View view) {
//        Intent intent = new Intent(this,Conscientiousness.class);
//        startActivity(intent);
        this.displayOpenness();

    }






}
