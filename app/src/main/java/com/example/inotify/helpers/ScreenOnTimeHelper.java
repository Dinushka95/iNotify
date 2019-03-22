package com.example.inotify.helpers;

import android.app.ActivityManager;
import android.content.Context;
import android.net.TrafficStats;
import android.util.Log;

import com.example.inotify.dbHelpers.AppUsageDbHelper;
import com.example.inotify.dbHelpers.MainDbHelp;
import com.example.inotify.dbHelpers.ScreenOnTimeDbHelper;

import java.util.List;
import java.util.Locale;

import static android.content.Context.ACTIVITY_SERVICE;

public class ScreenOnTimeHelper extends MainDbHelp {
    private  Context c1;
    public ScreenOnTimeHelper(Context context) {
        super(context);
        this.c1=context;

    }

    public int ScreenOnTimeTodayGet() {
        ScreenOnTimeDbHelper screenOnTimeDbHelper = new ScreenOnTimeDbHelper(c1);
        Log.d("inotify","ScreenOnTimeTodayGet......" + screenOnTimeDbHelper.ScreenOnTimeCountTodayGet());
        return screenOnTimeDbHelper.ScreenOnTimeCountTodayGet();

    }




}
