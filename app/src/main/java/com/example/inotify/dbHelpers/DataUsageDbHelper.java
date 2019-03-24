package com.example.inotify.dbHelpers;

import android.app.ActivityManager;
import android.content.Context;
import android.net.TrafficStats;
import android.util.Log;

import java.util.List;
import java.util.Locale;

public class DataUsageDbHelper extends MainDbHelp {
    public Context c1;

    public DataUsageDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

//    void networkUsage(Context context) {
//
//        ActivityManager manager = (ActivityManager) context.getSystemService(Answer);
//        List<ActivityManager.RunningAppProcessInfo> runningApps = manager.getRunningAppProcesses();
//        for (ActivityManager.RunningAppProcessInfo runningApp : runningApps) {
//            long received = TrafficStats.getUidRxBytes(runningApp.uid);
//            long sent = TrafficStats.getUidTxBytes(runningApp.uid);
//            Log.d("inotify", String.format(Locale.getDefault(),
//                    "uid: %1d - name: %s: Sent = %1d, Rcvd = %1d", runningApp.uid, runningApp.processName, sent, received));
//        }
//    }
}
