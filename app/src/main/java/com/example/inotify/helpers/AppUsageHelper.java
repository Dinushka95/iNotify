package com.example.inotify.helpers;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;

import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.AppUsageDbHelper;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
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

        return AppUsageDbHelper.getInstance(c1).insert(appUsageModelList);
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

        return AppUsageDbHelper.getInstance(c1).insert(appUsageModelList);

    }


    public void  saveTodaysAppUsagesOnAvailability()
    {
        if(! AppUsageDbHelper.getInstance(c1).cheackAvailability(TbNames.APPUSAGE_TABLE))
        {
            this.saveTodaysAppUsage();
        }

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
        return AppUsageDbHelper.getInstance(c1).appAllsUsageToday();

    }

    public long appAllsUsageAVG()
    {
        return AppUsageDbHelper.getInstance(c1).appAllsUsageAVG();

    }

    public long socialAppsUsageToday()
    {
        return AppUsageDbHelper.getInstance(c1).socialAppsUsageToday();
    }

    public long socialAppsUsageAVG()
    {
        return AppUsageDbHelper.getInstance(c1).socialAppsUsageToday();
    }

    public long communicationAppsUsageAVG()
    {
        return AppUsageDbHelper.getInstance(c1).communicationAppsUsageAVG();

    }

    public long personalizationAppsUsageAVG()
    {
        return AppUsageDbHelper.getInstance(c1).personalizationAppsUsageAVG();

    }

    public long gamingAppsUsageAVG()
    {
        return AppUsageDbHelper.getInstance(c1).gamingAppsUsageAVG();

    }

    public long photograpyAppsUsageAVG()
    {
        return AppUsageDbHelper.getInstance(c1).photograpyAppsUsageAVG();

    }

    public int socialUsageTimeColumCountGet()
    {
        return AppUsageDbHelper.getInstance(c1).socialUsageTimeColumCountGet();

    }

    public int communicationUsageTimeColumCountGet()
    {
        return AppUsageDbHelper.getInstance(c1).communicationUsageTimeColumCountGet();

    }

    public int personalizationUsageTimeColumCountGet()
    {
        return AppUsageDbHelper.getInstance(c1).personalizationUsageTimeColumCountGet();

    }

    public int gamingUsageTimeColumCountGet()
    {
        return AppUsageDbHelper.getInstance(c1).gamingUsageTimeColumCountGet();

    }

    public int communicationAppsUsageToday()
    {
        return AppUsageDbHelper.getInstance(c1).communicationAppsUsageToday();

    }

    public int gamingAppsUsageToday()
    {
        return AppUsageDbHelper.getInstance(c1).gamingAppsUsageToday();

    }

    public int photograpyAppsUsageToday()
    {
        return AppUsageDbHelper.getInstance(c1).photograpyAppsUsageToday();

    }

    public int personalizationAppsUsageToday()
    {
        return AppUsageDbHelper.getInstance(c1).personalizationAppsUsageToday();

    }

    public int musicvideoAppsUsageToday()
    {
        return AppUsageDbHelper.getInstance(c1).musicvideoAppsUsageToday();

    }

    public int musicvideoUsageTimeColumCountGet()
    {
        return AppUsageDbHelper.getInstance(c1).musicvideoUsageTimeColumCountGet();

    }

    public int musicvideoAppsUsageAVG()
    {
        return AppUsageDbHelper.getInstance(c1).musicvideoAppsUsageAVG();

    }




}
