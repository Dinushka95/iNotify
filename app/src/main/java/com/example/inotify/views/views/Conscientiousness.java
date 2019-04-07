package com.example.inotify.views.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.AttributeCountHelper;
import com.example.inotify.helpers.CalenderEventHelper;
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.CommonAppCountHelper;

import org.w3c.dom.Attr;

public class Conscientiousness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conscientiousness);
    }


    public void next_con(View view) {
        Intent intent = new Intent(this,Openness.class);
        startActivity(intent);


    }

    public  void back_detail(View view)
    {
       this.displayConscientiousness();
    }

    public long displayConscientiousness()
    {
        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int todaySocialApp = applicationsHelper.commonSocialAppTodayCount();
        int todayMusicVideoApp = applicationsHelper.commonMusicVideoAppTodayCount();

        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long todaySocialAppUsage = appUsageHelper.socialAppsUsageToday();
        long todayMusicVideoAppUsage = appUsageHelper.musicvideoAppsUsageToday();

        CalenderEventHelper calenderEventHelper = new CalenderEventHelper(this);
        long calender = calenderEventHelper.getcalanderEventCount(this);

        ChargerHelper chargerHelper = new ChargerHelper(this);
        long charge = chargerHelper.powerOnCount();

        //Probability
        long todaySocialAppProbability = (todaySocialApp * 16)/100;
        long todayMusicVideoAppProbability = (todayMusicVideoApp * 16)/100;
        long todaySocialAppUsageProbability = (todaySocialAppUsage * 16)/100;
        long todayMusicVideoAppUsageProbability = (todayMusicVideoAppUsage * 16)/100;
        long calenderProbability = (calender * 16)/100;
        long chargeProbability = (charge * 16)/100;

        long conscientiousness = (calenderProbability - chargeProbability - todaySocialAppProbability -todayMusicVideoAppProbability - todaySocialAppUsageProbability - todayMusicVideoAppUsageProbability);

        Log.d("inotify","conscientiousness..................." + conscientiousness);

        final TextView textViewToChange = findViewById(R.id.Co_attr_1);
        final TextView textViewToChange2 = findViewById(R.id.Co_attr_2);
        final TextView textViewToChange3 = findViewById(R.id.Co_attr_3);
        final TextView textViewToChange4 = findViewById(R.id.Co_attr_4);
        final TextView textViewToChange5 = findViewById(R.id.Co_attr_5);
        final TextView textViewToChange6 = findViewById(R.id.Co_attr_6);

        textViewToChange6.setText(""+todayMusicVideoAppProbability);
        textViewToChange2.setText(""+todayMusicVideoAppUsageProbability);
        textViewToChange.setText(""+calenderProbability);
        textViewToChange3.setText(""+todaySocialAppProbability);
        textViewToChange4.setText(""+todaySocialAppUsageProbability);
        textViewToChange5.setText(""+chargeProbability);
        return conscientiousness;
    }

    public long displayConscientiousnessAVG()
    {
        CommonAppCountHelper commonAppCountHelper = new CommonAppCountHelper(this);
        long socialAppAvg = commonAppCountHelper.commonSocialAppAvg();
        int AVGMusicVideoApp = commonAppCountHelper.commonMusicVideoAppAvg();

        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long avgSocialAppUsage = appUsageHelper.socialAppsUsageAVG();
        long avgMusicVideoAppUsage = appUsageHelper.musicvideoAppsUsageAVG();

        CalenderEventHelper calenderEventHelper = new CalenderEventHelper(this);
        long calenderAVG = calenderEventHelper.getcalanderEventAVGCount();

        AttributeCountHelper attributeCountHelper = new AttributeCountHelper(this);
        long chargeAVG = attributeCountHelper.chargingCountAvgGet();

        //Probability
        long avgSocialAppProbability = (socialAppAvg * 16)/100;
        long avgMusicVideoAppProbability = (AVGMusicVideoApp * 16)/100;
        long avgSocialAppUsageProbability = (avgSocialAppUsage * 16)/100;
        long avgyMusicVideoAppUsageProbability = (avgMusicVideoAppUsage * 16)/100;
        long avgcalenderProbability = (calenderAVG * 16)/100;
        long avgchargeProbability = (chargeAVG * 16)/100;

        long conscientiousnessAVG = (avgcalenderProbability - avgchargeProbability - avgSocialAppProbability -avgMusicVideoAppProbability - avgSocialAppUsageProbability - avgyMusicVideoAppUsageProbability);

        Log.d("inotify","conscientiousness..................." + conscientiousnessAVG);


        return conscientiousnessAVG;
    }



}
