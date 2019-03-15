package com.example.inotify.helpers;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;

import com.example.inotify.dbHelpers.AppUsageDbHelper;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.AppUsageModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppUsageHelper {

    private Context c1;

    public AppUsageHelper(Context context) {
        this.c1 = context;
    }

    public boolean insert(List<AppUsageModel> appUsageModelList) {

        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);

        return appUsageDbHelper.insert(appUsageModelList);
    }

    public boolean saveTodaysAppUsage() {

        AppUsageHelper appUsageHelper = new AppUsageHelper(c1);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long start = calendar.getTimeInMillis();
        long end = System.currentTimeMillis();
        UsageStatsManager usageStatsManager = (UsageStatsManager) c1.getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, start, end);

        List<AppUsageModel> appUsageModelList = new ArrayList<>();

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);

        for (UsageStats stat : stats) {
            AppUsageModel appUsageModel = new AppUsageModel();

            ApplicationInfoModel applicationInfoModel = applicationsHelper.appGet(stat.getPackageName());
            appUsageModel.setDate(date);
            appUsageModel.setTime(time);
            appUsageModel.setPackageName(stat.getPackageName());
            appUsageModel.setAppCategory(applicationInfoModel.getAppCategory());
            appUsageModel.setAppName(applicationInfoModel.getAppName());
            appUsageModel.setUsageTime(String.valueOf((stat.getTotalTimeInForeground() / 1000)));

            appUsageModelList.add(appUsageModel);
        }
        return appUsageHelper.insert(appUsageModelList);
    }


    public int appsUsageTodayGet(String appcategoriesConstants) {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        Log.d("inotify","appsUsageTodayGet......." + appUsageDbHelper.appsUsageTodayGet(appcategoriesConstants));
        return appUsageDbHelper.appsUsageTodayGet(appcategoriesConstants);
    }

    public int appsUsageAvgGet(String appcategoriesConstants) {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        Log.d("inotify","appsUsageAvgGet......." + appUsageDbHelper.appsUsageAvgGet(appcategoriesConstants));

        return appUsageDbHelper.appsUsageAvgGet(appcategoriesConstants);
    }

    public int appAllUsageTodayGet() {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        Log.d("inotify","appAllUsageTodayGet......." + appUsageDbHelper.appAllUsageTodayGet());

        return appUsageDbHelper.appAllUsageTodayGet();
    }

    public int appAllUsageAvgGet() {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        Log.d("inotify","appAllUsageAvgGet......." + appUsageDbHelper.appAllUsageAvgGet());

        return appUsageDbHelper.appAllUsageAvgGet();
    }


}
