package com.example.inotify.helpers;

import android.app.ActivityManager;
import android.content.Context;
import android.net.TrafficStats;
import android.util.Log;

import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.AppUsageDbHelper;
import com.example.inotify.dbHelpers.ContactsDbHelper;
import com.example.inotify.dbHelpers.DataUsageDbHelper;
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

    public int screenOnTimeTodayGet() {

        if(! ScreenOnTimeDbHelper.getInstance(c1).cheackAvailability(TbNames.CHARGER_TABLE))
        {
            return ScreenOnTimeDbHelper.getInstance(c1).ScreenOnTimeCountTodayGet();
        }
        return 0;
    }


}
