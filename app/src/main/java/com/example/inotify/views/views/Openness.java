package com.example.inotify.views.views;

import android.content.Context;
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
import com.example.inotify.helpers.CommonAppCountHelper;
import com.google.android.gms.common.internal.service.Common;

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


    public long displayOpenness(Context context) {
        AppUsageHelper appUsageHelper = new AppUsageHelper(context);
        long todayAllAppUsage = appUsageHelper.appAllsUsageToday();
        long todaySocialAppUsage = appUsageHelper.socialAppsUsageToday();
        long todayCommunicationAppUsage = appUsageHelper.communicationAppsUsageToday();

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int NoofAppsToday = applicationsHelper.appCountGetToday();
        int NoofAppsAll = applicationsHelper.allAppCountAVG();

        int newApps = (NoofAppsAll - NoofAppsToday);
        Log.d("inotify", "newApps" + newApps);

        int socialAppToday = applicationsHelper.commonSocialAppTodayCount();
        int communicationAppToday = applicationsHelper.commonCommunicationAppTodayCount();

        //probability
        long todayAllAppUsageProbability = (todayAllAppUsage * 16) / 100;
        long todaySocialAppUsageProbability = (todaySocialAppUsage * 16) / 100;
        long todayCommunicationAppUsageProbability = (todayCommunicationAppUsage * 16) / 100;
        long newAppsProbability = (newApps * 16) / 100;
        long socialAppTodayProbability = (socialAppToday * 16) / 100;
        long communicationAppTodayProbability = (communicationAppToday * 16) / 100;

        long Openness = (todayAllAppUsageProbability + todaySocialAppUsageProbability + todayCommunicationAppUsageProbability + newAppsProbability + socialAppTodayProbability + communicationAppTodayProbability) / 100;

        Log.d("inotify", "Openness..............." + Openness);

        //TODO implement intent with data
        /*final TextView textViewToChange = findViewById(R.id.Op_attr_1);
        final TextView textViewToChange2 = findViewById(R.id.Op_attr_2);
        final TextView textViewToChange3 = findViewById(R.id.Op_attr_3);
        final TextView textViewToChange4 = findViewById(R.id.Op_attr_4);
        final TextView textViewToChange5 = findViewById(R.id.Op_attr_5);
        final TextView textViewToChange6 = findViewById(R.id.Op_attr_6);

        textViewToChange6.setText("" + todayAllAppUsageProbability);
        textViewToChange2.setText("" + todaySocialAppUsageProbability);
        textViewToChange.setText("" + newAppsProbability);
        textViewToChange3.setText("" + socialAppTodayProbability);
        textViewToChange4.setText("" + communicationAppTodayProbability);
        textViewToChange5.setText("" + todayCommunicationAppUsageProbability);*/


/*
        Intent intent = new Intent(Openness.this, UserAttentivenessActivity.class);
        intent.putExtra("Openness", Openness);
        startActivity(intent);

        Log.d("inotify", "Openness--------" + Openness);
*/

        return Openness;


    }

    public long displayOpennessAVG() {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long avgAllAppUsage = appUsageHelper.appAllsUsageAVG();
        long avgSocialAppUsage = appUsageHelper.socialAppsUsageAVG();
        long avgCommunicationAppUsage = appUsageHelper.communicationAppsUsageAVG();

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        // int NoofAppsToday = applicationsHelper.appCountGetToday();
        int NoofAppsAll = applicationsHelper.allAppCountAVG();

        // int newApps = (NoofAppsAll - NoofAppsToday);

        CommonAppCountHelper commonAppCountHelper = new CommonAppCountHelper(this);
        long socialAppAvg = commonAppCountHelper.commonSocialAppAvg();
        int communicationAppAvg = commonAppCountHelper.commonCommunicationAppAvg();

        //probability
        long avgAllAppUsageProbability = (avgAllAppUsage * 16) / 100;
        long avgSocialAppUsageProbability = (avgSocialAppUsage * 16) / 100;
        long avgCommunicationAppUsageProbability = (avgCommunicationAppUsage * 16) / 100;
        long newAppsProbability = (NoofAppsAll * 16) / 100;
        long socialAppTodayProbability = (socialAppAvg * 16) / 100;
        long communicationAppTodayProbability = (communicationAppAvg * 16) / 100;

        long OpennessAVG = (avgAllAppUsageProbability + avgSocialAppUsageProbability + avgCommunicationAppUsageProbability + newAppsProbability + socialAppTodayProbability + communicationAppTodayProbability) / 100;


//
//
//        Intent intent = new Intent(Openness.this, UserAttentivenessActivity.class);
//        intent.putExtra("Openness", Openness);
//        startActivity(intent);
//
        Log.d("inotify", "Openness--------" + OpennessAVG);

        return OpennessAVG;


    }


    public void next_open(View view) {
        Intent intent = new Intent(this, Neuroticism.class);
        startActivity(intent);

    }

    public void back_open(View view) {
//        Intent intent = new Intent(this,Conscientiousness.class);
//        startActivity(intent);
        this.displayOpenness(view.getContext());

    }

    public void onClick(View v) {
        // number = 5; //this is an integer
        long openness1 = this.displayOpenness(v.getContext());
        Intent in = new Intent(Openness.this, UsercharacteristicsActivity.class);
        in.putExtra("name of your value (eg. adapter_int)", openness1);
        startActivity(in);
    }


}
