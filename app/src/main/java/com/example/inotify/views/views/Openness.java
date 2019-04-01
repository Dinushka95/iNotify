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

        public void checkAttributeOpenness()
        {
            AppUsageHelper appUsageHelper = new AppUsageHelper(this);
             long todayAppUsage = appUsageHelper.appAllsUsageToday();
             long avgAppUsage = appUsageHelper.appAllsUsageAVG();
             if(todayAppUsage > avgAppUsage)
             {
                 Log.d("inotify","Your AppUsage is high on today than the other days");
                 String appUsage = "high";
             }
             else if(todayAppUsage == avgAppUsage)
             {
                 Log.d("inotify","Your AppUsage is normal");
                 String appUsage = "normal";
             }
             else
             {
                 Log.d("inotify","Your AppUsage is low on today than the other days");
                 String appUsage = "low";
             }

            long socilAppUsgeToday =  appUsageHelper.socialAppsUsageToday();
             long socialAppUsageAVG = appUsageHelper.socialAppsUsageAVG();
            if(socilAppUsgeToday > socialAppUsageAVG)
            {
                Log.d("inotify","Your soailAppUsage is high on today than the other days");
                String soailAppUsage = "high";
            }
            else if(todayAppUsage == avgAppUsage)
            {
                Log.d("inotify","Your soailAppUsage is normal");
                String soailAppUsage = "normal";
            }
            else
            {
                Log.d("inotify","Your soailAppUsage is low on today than the other days");
                String soailAppUsage = "low";
            }

            long communicationAppUsgeToday =  appUsageHelper.communicationAppsUsageToday();
            long communicationAppUsageAVG = appUsageHelper.communicationAppsUsageAVG();
            if(communicationAppUsgeToday > communicationAppUsageAVG)
            {
                Log.d("inotify","Your communicationAppUsage is high on today than the other days");
                String communicationAppUsage = "high";
            }
            else if(todayAppUsage == avgAppUsage)
            {
                Log.d("inotify","Your communicationAppUsage is normal");
                String communicationAppUsage = "normal";
            }
            else
            {
                Log.d("inotify","Your communicationAppUsage is low on today than the other days");
                String communicationAppUsage = "low";
            }

            ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
            int NoofAppsToday = applicationsHelper.appCountGetToday();
            int NoofAppsAllAVG = applicationsHelper.allAppCountAVG();

            if(NoofAppsToday > NoofAppsAllAVG)
            {
                Log.d("inotify","Your Number of apps is high on today than the other days");
                String noOfApps = "high";
            }
            else if(todayAppUsage == avgAppUsage)
            {
                Log.d("inotify","Your Number of apps is normal");
                String noOfApps = "normal";
            }
            else
            {
                Log.d("inotify","Your Number of apps is low on today than the other days");
                String noOfApps = "low";
            }

            int socilAppCountToday = applicationsHelper.commonSocialAppTodayCount();


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

        public void onClick(View v) {
           // number = 5; //this is an integer
            long openness1 = this.displayOpenness();
            Intent in = new Intent(Openness.this, UsercharacteristicsActivity.class);
            in.putExtra("name of your value (eg. adapter_int)", openness1);
            startActivity(in);
        }







}
