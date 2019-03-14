package com.example.inotify.helpers;

import android.content.Context;
import android.util.Log;

import com.example.inotify.configs.AppCategoriesConstants;

public class EquationHelper {
    private Context c1;

    public EquationHelper(Context context)
    {
        c1=context;
    }

    AppUsageHelper appUsageHelper = new AppUsageHelper(c1);
    AppCategoriesConstants appCategoriesConstants = new AppCategoriesConstants();
    ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);


    public double GetOpenness()
    {
        double openness = 0.0;
        int todaySocialAppUsage = appUsageHelper.appsUsageTodayGet(AppCategoriesConstants.BUSINESS);
        int socialAppUsageAvg = appUsageHelper.appsUsageAvgGet(AppCategoriesConstants.SOCIAL);
        int allAppUsageAvg = appUsageHelper.appAllUsageAvgGet();
        int allAppUsageToday = appUsageHelper.appAllUsageTodayGet();
        int noOfAppstoday = applicationsHelper.appCountGet();
        int allAppsUsage = (allAppUsageToday - allAppUsageAvg);



       // int noOfDownloadedApps = (int) (noOfAppstoday - avgApps);
        int socialAppUsage = (todaySocialAppUsage - socialAppUsageAvg);
        int appUsage = (allAppUsageToday - allAppUsageAvg);
        openness = ((allAppsUsage + socialAppUsage + appUsage + noOfAppstoday)/3) * (1/100);
        Log.d("inotify","Openness............." + openness);
        return openness;
    }





}
