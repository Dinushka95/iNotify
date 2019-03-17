package com.example.inotify.views.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.AppCategoriesConstants;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.CalenderEventDbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.UC_CalenderEvent;


public class UsercharacteristicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_characteristics);
    }


    public void displayOpenness()
    {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        appUsageHelper.appAllUsageAvgGet();
        appUsageHelper.appAllUsageTodayGet();
        appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
        appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL);

        appUsageHelper.saveTodaysAppUsage();

        int allAppsUsage = ( appUsageHelper.appAllUsageTodayGet() - appUsageHelper.appAllUsageAvgGet());
        Log.d("inotify","allAppUsage------------" + allAppsUsage);

        int socialAppUsage = (appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.SOCIAL) - appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL));
        Log.d("inotify","socialAppUsage------------" + socialAppUsage);

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        int NoofApps = applicationsHelper.appCountGet();
        Log.d("inotify","AppCount---" + NoofApps);

        int Openness = ((allAppsUsage + socialAppUsage + NoofApps)/300);
        Log.d("inotify","Openness------" + Openness);

        final TextView textViewToChange = findViewById(R.id.allAppUsageText);
        final TextView textViewToChange2 = findViewById(R.id.socialAppusageText);
        final TextView textViewToChange3 = findViewById(R.id.NoOfAPpsText);
        final TextView textViewToChange4 = findViewById(R.id.openness);

        textViewToChange.setText(""+allAppsUsage);
        textViewToChange2.setText(""+socialAppUsage);
        textViewToChange3.setText(""+NoofApps);
        textViewToChange4.setText(""+Openness);
    }


    public void DisplayOpenness(View view) {
        this.displayOpenness();


    }

    public void test1(View view) {
        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
//        appUsageHelper.saveTodaysAppUsage();




    }

    public void test2(View view) {

          ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
         // applicationsHelper.saveCurrentPhoneApps();

        applicationsHelper.appCountGet();

//        List<AppInfoModel> apps  =  new ArrayList<AppInfoModel>();
//        applicationsHelper.appInfoInsert(apps);

        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
       // applicationDbHelper.updateCategory();

        UC_CalenderEvent uc_calenderEvent = new UC_CalenderEvent();
        uc_calenderEvent.getcalanderEventCount(this);
        Log.d("inotify","aaaaaaaaaaaaaaaa");

        CalenderEventDbHelper calenderEventDbHelper = new CalenderEventDbHelper(this);
        calenderEventDbHelper.checkIfExist();



    }

}
