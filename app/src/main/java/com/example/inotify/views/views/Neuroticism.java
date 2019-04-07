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
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.CommonAppCountHelper;
import com.example.inotify.helpers.ContactsHelper;

public class Neuroticism extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuroticism);
    }

    public void neuro_next(View view) {
        Intent intent = new Intent(this, Agreeablenesss.class);
        startActivity(intent);

    }

    public void neuro_back(View view) {
//        Intent intent = new Intent(this, Openness.class);
//        startActivity(intent);
        this.neuroticismDisplay();

    }

    public long neuroticismDisplayAVG() {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long avgPhotograpyAppUsage = appUsageHelper.photograpyAppsUsageAVG();
        long avgSocialAppUsage = appUsageHelper.socialAppsUsageAVG();

        CommonAppCountHelper commonAppCountHelper = new CommonAppCountHelper(this);
        long socialAppAvg = commonAppCountHelper.commonSocialAppAvg();
        int avgPhotograpyApps = commonAppCountHelper.commonPhotograpyAppAvg();

        long avgAllAppUsage = appUsageHelper.appAllsUsageAVG();

        AttributeCountHelper attributeCountHelper = new AttributeCountHelper(this);
        long chargeAVG = attributeCountHelper.chargingCountAvgGet();

        //Probability
        long avgPhotograpyAppUsageProbability = (avgPhotograpyAppUsage * 16) / 100;
        long avgSocialAppUsageProability = (avgSocialAppUsage * 16) / 100;
        long avgtodayPhotograpyAppsProbability = (avgPhotograpyApps * 16) / 100;
        long avgSocialappsProbability = (socialAppAvg * 16) / 100;
        long avgAllAppUsageProbability = (avgAllAppUsage * 16) / 100;
        long avgchargeProbability = (chargeAVG * 16) / 100;

        long neuroticismAVG = (avgPhotograpyAppUsageProbability + avgtodayPhotograpyAppsProbability - avgSocialAppUsageProability - avgSocialappsProbability + avgAllAppUsageProbability + avgchargeProbability) / 10;

        Log.d("inotify", "neuroticism..................." + neuroticismAVG);


        return neuroticismAVG;
    }

    public long neuroticismDisplay() {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long todayPhotograpyAppUsage = appUsageHelper.photograpyAppsUsageToday();
        long todaySocialAppUsage = appUsageHelper.socialAppsUsageToday();

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int todayPhotograpyApps = applicationsHelper.commonPhotograpyAppTodayCount();
        int todaySocialapps = applicationsHelper.commonSocialAppTodayCount();

        long todayAllAppUsage = appUsageHelper.appAllsUsageToday();

        ChargerHelper chargerHelper = new ChargerHelper(this);
//        long charge = chargerHelper.powerOnCount();

        //Probability
        long todayPhotograpyAppUsageProbability = (todayPhotograpyAppUsage * 16) / 100;
        long todaySocialAppUsageProability = (todaySocialAppUsage * 16) / 100;
        long todayPhotograpyAppsProbability = (todayPhotograpyApps * 16) / 100;
        long todaySocialappsProbability = (todaySocialapps * 16) / 100;
        long todayAllAppUsageProbability = (todayAllAppUsage * 16) / 100;
  //      long chargeProbability = (charge * 16) / 100;

    //    long neuroticism = (todayPhotograpyAppUsageProbability + todayPhotograpyAppsProbability - todaySocialAppUsageProability - todaySocialappsProbability + todayAllAppUsageProbability + chargeProbability) / 10;
            long neuroticism = (todayPhotograpyAppUsageProbability + todayPhotograpyAppsProbability - todaySocialAppUsageProability - todaySocialappsProbability + todayAllAppUsageProbability ) / 10;

 //       Log.d("inotify", "neuroticism..................." + neuroticism);

/*        final TextView textViewToChange = findViewById(R.id.N_attr_1);
        final TextView textViewToChange2 = findViewById(R.id.N_attr_2);
        final TextView textViewToChange3 = findViewById(R.id.N_attr_3);
        final TextView textViewToChange4 = findViewById(R.id.N_attr_4);
        final TextView textViewToChange5 = findViewById(R.id.N_attr_5);
        final TextView textViewToChange6 = findViewById(R.id.N_attr_6);

        textViewToChange.setText("" + todayPhotograpyAppsProbability);
        textViewToChange2.setText("" + todayPhotograpyAppUsageProbability);
        textViewToChange3.setText("" + todaySocialappsProbability);
        textViewToChange4.setText("" + todaySocialAppUsageProability);
        textViewToChange5.setText("" + todayAllAppUsageProbability);
        textViewToChange6.setText("" + chargeProbability);*/


        return neuroticism;
    }


}
