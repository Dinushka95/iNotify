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




//    public int appsUsageAvgGet() {
//        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
//        Log.d("inotify","appsUsageAvgGet......." + appUsageDbHelper.appAllsUsageAVG());
//
//        return appUsageDbHelper.appAllsUsageAVG();
//    }
//
//    public int appAllUsageTodayGet() {
//        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
//        Log.d("inotify","appAllUsageTodayGet......." + appUsageDbHelper.appAllsUsageToday());
//
//        return appUsageDbHelper.appAllsUsageToday();
//    }
//
//    public int socialAppUsageTodayGet() {
//        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
//        Log.d("inotify","appAllUsageTodayGet......." + appUsageDbHelper.socialAppsUsageToday());
//
//        return appUsageDbHelper.socialAppsUsageToday();
//    }
//
//    public int socialAppUsageAVG() {
//        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
//        Log.d("inotify","appAllUsageTodayGet......." + appUsageDbHelper.socialAppsUsageAVG());
//
//        return appUsageDbHelper.socialAppsUsageAVG();
//    }

    public long appAllsUsageToday()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.appAllsUsageToday();

    }

    public long appAllsUsageAVG()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.appAllsUsageAVG();

    }

    public long socialAppsUsageToday()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.socialAppsUsageToday();

    }

    public long communicationAppsUsageAVG()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.communicationAppsUsageAVG();

    }

    public long personalizationAppsUsageAVG()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.personalizationAppsUsageAVG();

    }

    public long gamingAppsUsageAVG()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.gamingAppsUsageAVG();

    }

    public long photograpyAppsUsageAVG()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.photograpyAppsUsageAVG();

    }

    public int socialUsageTimeColumCountGet()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.socialUsageTimeColumCountGet();

    }

    public int communicationUsageTimeColumCountGet()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.communicationUsageTimeColumCountGet();

    }

    public int personalizationUsageTimeColumCountGet()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.personalizationUsageTimeColumCountGet();

    }

    public int gamingUsageTimeColumCountGet()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.gamingUsageTimeColumCountGet();

    }

    public int communicationAppsUsageToday()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.communicationAppsUsageToday();

    }

    public int gamingAppsUsageToday()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.gamingAppsUsageToday();

    }

    public int photograpyAppsUsageToday()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.photograpyAppsUsageToday();

    }

    public int personalizationAppsUsageToday()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.personalizationAppsUsageToday();

    }

    public int musicvideoAppsUsageToday()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.musicvideoAppsUsageToday();

    }

    public int musicvideoUsageTimeColumCountGet()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.musicvideoUsageTimeColumCountGet();

    }

    public int musicvideoAppsUsageAVG()
    {
        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.musicvideoAppsUsageAVG();

    }




}
