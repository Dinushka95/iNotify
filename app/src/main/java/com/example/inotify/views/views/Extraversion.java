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
import com.example.inotify.helpers.CallUsageHelper;
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
//    public long displayExtraversion()
//    {
//        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
//        long socialAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
//        long socialAPpUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);
//
//        long gamingAPpUsageAVG = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.GAMING);
//        long gamingAPpUsageToday = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.GAMING);
//
//        long socialAppUsage = (socialAPpUsageAVG - socialAPpUsageToday)/socialAPpUsageAVG; //social app usage 2nd attribute
//        Log.d("inotify","socialAppUsage......." + socialAppUsage);
//
//        long gamingAppUsage = (gamingAPpUsageAVG - gamingAPpUsageToday)/gamingAPpUsageAVG; //social app usage 2nd attribute
//        Log.d("inotify","gamingAppUsage......." + gamingAppUsage);
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
//        long socialAppUsageProbability = (socialAppUsage *16)/100;//----1st attribute
//
//        long gamingAppUsageProbability = (gamingAppUsage * 16)/100;//---5th attribute
//
//        int socialAppCount = applicationsHelper.commonSocialAppCount(); //think again have to get the avg also
//
//        long socialAppCountProbability = (socialAppCount * 16)/100;//----2nd attribute
//        Log.d("inotify","socialAppCountProbability......." + socialAppCountProbability);
//
//        int gamingAppCount = applicationsHelper.commonGamingAppCount();
//
//        long gamingAppCountProbability = (gamingAppCount * 16)/100; //----3rd attribute
//        Log.d("inotify","gamingAppCountProbability......." + gamingAppCountProbability);
//
//        long newAppsProbability = (newApps * 16)/100;//----6th attribute
//
//
//        CallUsageHelper callDurationHelper = new CallUsageHelper(this);
//        long callDurationToday = callDurationHelper.getCallDuraionAVGToday();
//        long callDurationAVG = callDurationHelper.getCallDurationAVG();
//
//        long callDuration = (callDurationAVG - callDurationToday)/callDurationAVG;
//
//        long callDurationProbability = (callDuration * 16)/100;//----4th attribute
//        Log.d("inotify","callDurationProbability......." + callDurationProbability);
//
//
//        ContactsHelper contactsHelper = new ContactsHelper(this);
//        int todayContacts = contactsHelper.getcontactToday();
//
//        AttributeCountHelper attributeCountHelper = new AttributeCountHelper(this);
//        long contactAVG =  attributeCountHelper.ContactsAvgGet();
//
//        long contacts = (contactAVG - todayContacts)/contactAVG;
//        long contactProbability = (contacts *16)/100;//----5th attribute
//        Log.d("inotify","contactProbability......." + contactProbability);
//
//
//
//        long extraversion = ( callDurationProbability + newAppsProbability + contactProbability - gamingAppCountProbability - gamingAppUsageProbability - socialAppCountProbability - socialAppUsageProbability);
//        Log.d("inotify","extraversion////////////////" + extraversion);
//
//        final TextView textViewToChange = findViewById(R.id.Ex_attr_1);
//        final TextView textViewToChange2 = findViewById(R.id.Ex_attr_2);
//        final TextView textViewToChange3 = findViewById(R.id.Ex_attr_3);
//        final TextView textViewToChange4 = findViewById(R.id.Ex_attr_4);
//        final TextView textViewToChange5 = findViewById(R.id.Ex_attr_5);
//        final TextView textViewToChange6 = findViewById(R.id.Ex_attr_6);
//        final TextView textViewToChange7 = findViewById(R.id.Ex_attr_7);
//
//
//        textViewToChange.setText(""+callDurationProbability);
//        textViewToChange2.setText(""+socialAppCountProbability);
//        textViewToChange3.setText(""+newAppsProbability);
//        textViewToChange4.setText(""+contactProbability);
//        textViewToChange5.setText(""+gamingAppCountProbability);
//        textViewToChange5.setText(""+gamingAppUsageProbability);
//        textViewToChange5.setText(""+socialAppUsageProbability);
//
//
//        return extraversion;
//
//
//    }

        public long displayExtraversion()
        {
            AppUsageHelper appUsageHelper = new AppUsageHelper(this);
            long todayGamingAppUsage = appUsageHelper.gamingAppsUsageToday();
            long todaySocialAppUsage = appUsageHelper.socialAppsUsageToday();

            ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
            int todaySocialApp = applicationsHelper.commonSocialAppTodayCount();
            int todayGamingApp = applicationsHelper.commonGamingAppTodayCount();

            ContactsHelper contactsHelper = new ContactsHelper(this);
            int todayContacts = contactsHelper.getcontactToday();//have to implement to get newly added contacts
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
}
