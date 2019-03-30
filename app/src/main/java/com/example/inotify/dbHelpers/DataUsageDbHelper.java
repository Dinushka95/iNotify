package com.example.inotify.dbHelpers;

import android.app.ActivityManager;
import android.content.Context;
import android.net.TrafficStats;
import android.util.Log;

import java.util.List;
import java.util.Locale;

import static android.content.Context.ACTIVITY_SERVICE;

public class DataUsageDbHelper extends MainDbHelp {
    public Context c1;

    public DataUsageDbHelper(Context context) {
        super(context);
        this.c1=context;
    }


}
