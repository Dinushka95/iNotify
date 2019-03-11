package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.AppUsageDbHelper;
import com.example.inotify.models.AppUsageModel;

import java.util.List;

public class AppUsageHelper {

    private Context c1;

    public AppUsageHelper(Context context) {
        this.c1=context;
    }

    public boolean insert(List<AppUsageModel> appUsageModelList) {

        AppUsageDbHelper appUsageDbHelper = new AppUsageDbHelper(c1);
        return appUsageDbHelper.insert(appUsageModelList);
    }


   // public getsocialappsusage();
   // public gettotalsocialaAvG();
   // public getappusage();
   // public getAppUage();



}
