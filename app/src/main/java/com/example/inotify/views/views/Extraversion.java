package com.example.inotify.views.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.CallUsageDbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.AttributeCountHelper;
import com.example.inotify.helpers.CalenderEventHelper;
import com.example.inotify.helpers.CallUsageHelper;
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.CommonAppCountHelper;
import com.example.inotify.helpers.ContactsHelper;

public class Extraversion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extraversion);
    }
    public void back_extra(View view) {
//        Intent intent = new Intent(this, Agreeableness.class);
//        startActivity(intent);
        this.displayExtraversion();

    }
//
        public long displayExtraversion()
        {
            AppUsageHelper appUsageHelper = new AppUsageHelper(this);
            long todayGamingAppUsage = appUsageHelper.gamingAppsUsageToday();
            long todaySocialAppUsage = appUsageHelper.socialAppsUsageToday();

            ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
            int todaySocialApp = applicationsHelper.commonSocialAppTodayCount();
            int todayGamingApp = applicationsHelper.commonGamingAppTodayCount();

            ContactsHelper contactsHelper = new ContactsHelper(this);
            int todayContacts = contactsHelper.getContacts(this);//have to implement to get newly added contacts
            //int allContacts = contactsHelper.getContacts();

            int NoofAppsToday = applicationsHelper.appCountGetToday();
            int NoofAppsAll = applicationsHelper.allAppCountAVG();

            int newApps = (NoofAppsAll - NoofAppsToday);

            CallUsageHelper callUsageHelper = new CallUsageHelper(this);
            long todayCallDuraion = Long.valueOf(callUsageHelper.getTodayTotalCallDuration());
            Log.d("inotify","todayCallDuraion......." + todayCallDuraion);

            //probbility
            long todayGamingAppUsageProbability = (todayGamingAppUsage * 14)/100;
            long todaySocialAppUsageProbability = (todaySocialAppUsage * 14)/100;
            long todaySocialAppProbability = (todaySocialApp * 14)/100;
            long todayGamingAppProbability = (todayGamingApp * 14)/100;
            long todayContactsProbability = (todayContacts * 14)/100;
            Log.d("inotify","todayContactsProbability..........." + todayContactsProbability);

            long newAppsProbability = (newApps * 14)/100;
            long todayCallDuraionProbability = (todayCallDuraion * 14)/100;


            //check again this equation
            long extraversion = (todayContactsProbability + newAppsProbability - todayGamingAppProbability - todayGamingAppUsageProbability + todayCallDuraionProbability + todaySocialAppUsageProbability + todaySocialAppProbability)/10;
            Log.d("inotify","extraversion..........." + extraversion);

            final TextView textViewToChange = findViewById(R.id.Ex_attr_1);
            final TextView textViewToChange2 = findViewById(R.id.Ex_attr_2);
            final TextView textViewToChange3 = findViewById(R.id.Ex_attr_3);
            final TextView textViewToChange4 = findViewById(R.id.Ex_attr_4);
            final TextView textViewToChange5 = findViewById(R.id.Ex_attr_5);
            final TextView textViewToChange6 = findViewById(R.id.Ex_attr_6);
            final TextView textViewToChange7 = findViewById(R.id.Ex_attr_7);


            textViewToChange.setText(""+todayCallDuraionProbability);
            textViewToChange2.setText(""+todayGamingAppProbability);
            textViewToChange3.setText(""+todayGamingAppUsageProbability);
            textViewToChange4.setText(""+todayContactsProbability);
            textViewToChange5.setText(""+todaySocialAppProbability);
            textViewToChange6.setText(""+todaySocialAppUsageProbability);
            textViewToChange7.setText(""+newAppsProbability);


            return extraversion;
        }

    public long displayExtraversionAVG()
    {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long avgAllAppUsage = appUsageHelper.appAllsUsageAVG();
        long avgSocialAppUsage = appUsageHelper.socialAppsUsageAVG();

        CommonAppCountHelper commonAppCountHelper = new CommonAppCountHelper(this);
        long socialAppAvg = commonAppCountHelper.commonSocialAppAvg();
        int gamingAppAvg = commonAppCountHelper.commonGamingAppAvg();

        AttributeCountHelper attributeCountHelper = new AttributeCountHelper(this);
        long avgContacts = attributeCountHelper.ContactsAvgGet();//have to implement to get newly added contacts
        //int allContacts = contactsHelper.getContacts();

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);

        int NoofAppsAll = applicationsHelper.allAppCountAVG();

        CallUsageHelper callUsageHelper = new CallUsageHelper(this);
        long avgCallDuraion = Long.valueOf(callUsageHelper.getTotalCallDurationAvg());


        //probbility
        long avgGamingAppUsageProbability = (avgAllAppUsage * 14)/100;
        long avgSocialAppUsageProbability = (avgSocialAppUsage * 14)/100;
        long avgSocialAppProbability = (socialAppAvg * 14)/100;
        long avgGamingAppProbability = (gamingAppAvg * 14)/100;
        long avgContactsProbability = (avgContacts * 14)/100;


        long newAppsProbability = (NoofAppsAll * 14)/100;
        long avgCallDuraionProbability = (avgCallDuraion * 14)/100;


        //check again this equation
        long extraversionAVG = (avgContactsProbability + newAppsProbability - avgGamingAppProbability - avgGamingAppUsageProbability + avgCallDuraionProbability + avgSocialAppUsageProbability + avgSocialAppProbability)/10;
        Log.d("inotify","extraversion..........." + extraversionAVG);




        return extraversionAVG;
    }


}
