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
import com.example.inotify.helpers.ContactsHelper;
import com.example.inotify.helpers.ScreenOnTimeHelper;

public class Agreeablenesss extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreeablenesss);
    }

    public void next_agree(View view) {
        Intent intent = new Intent(this, Extraversion.class);
        startActivity(intent);

    }

    public void back_agree(View view) {
//        Intent intent = new Intent(this, Neuroticism.class);
//        startActivity(intent);
        this.DisplayAgreeableness();// view aka

    }

    public long DisplayAgreeableness()//view aka ain kala
    {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        long todayPersonalizationAppUsage = appUsageHelper.personalizationAppsUsageToday();

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int todayPersonalizationApp = applicationsHelper.commonPersonalizationAppTodayCount();

        int todayAppCount = applicationsHelper.appCountGetToday();

        ContactsHelper contactsHelper = new ContactsHelper(this);
        int todayContcts =contactsHelper.getcontactToday();

        ScreenOnTimeHelper screenOnTimeHelper = new ScreenOnTimeHelper(this);
        int todayScreenOnTime = screenOnTimeHelper.ScreenOnTimeTodayGet();

        //Probability
        long todayPersonalizationAppUsageProbability = (todayPersonalizationAppUsage * 20)/100;
        long todayPersonalizationAppProbability = (todayPersonalizationApp * 20)/100;
        long todayAppCountProbability = (todayAppCount * 20)/100;
        long todayContctsProbability = (todayContcts * 20)/100;
        long todayScreenOnTimeProbability = (todayScreenOnTime * 20)/100;

        long agreeableness = (todayAppCountProbability + todayContctsProbability + todayScreenOnTimeProbability - todayPersonalizationAppUsageProbability -todayPersonalizationAppProbability);

        Log.d("inotify","agreeableness--------------"+ agreeableness);

        final TextView textViewToChange = findViewById(R.id.Ag_attr_1);
        final TextView textViewToChange2 = findViewById(R.id.Ag_attr_2);
        final TextView textViewToChange3 = findViewById(R.id.Ag_attr_3);
        final TextView textViewToChange4 = findViewById(R.id.Ag_attr_4);
        final TextView textViewToChange5 = findViewById(R.id.Ag_attr_5);
        //final TextView textViewToChange6 = view.findViewById(R.id.Ag_attr_6);

        textViewToChange.setText(""+todayPersonalizationAppProbability);
        textViewToChange2.setText(""+todayPersonalizationAppUsageProbability);
        textViewToChange3.setText(""+todayScreenOnTimeProbability);
        textViewToChange4.setText(""+todayAppCountProbability);
        textViewToChange5.setText(""+todayContctsProbability);
        //textViewToChange5.setText(""+todayCommunicationAppUsageProbability);

        return agreeableness;

    }
}
