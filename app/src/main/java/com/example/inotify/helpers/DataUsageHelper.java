package com.example.inotify.helpers;

import android.annotation.SuppressLint;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.List;

public class DataUsageHelper {

    @SuppressLint("MissingPermission")
    private String getSubscriberId(Context context, int networkType) {
        if (ConnectivityManager.TYPE_MOBILE == networkType) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getSubscriberId();
        }
        return "";
    }

    private long getPackageRxBytesMobile(Context context, NetworkStatsManager networkStatsManager, int packageUid) {
        NetworkStats networkStats = null;
        networkStats = networkStatsManager.queryDetailsForUid(
                ConnectivityManager.TYPE_MOBILE,
                getSubscriberId(context, ConnectivityManager.TYPE_MOBILE),
                0,
                System.currentTimeMillis(),
                packageUid);
        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
        networkStats.getNextBucket(bucket);
        networkStats.getNextBucket(bucket);
        return bucket.getRxBytes();
    }

    private long getPackageTxBytesMobile(Context context,NetworkStatsManager networkStatsManager,int packageUid) {
        NetworkStats networkStats = null;
        networkStats = networkStatsManager.queryDetailsForUid(
                ConnectivityManager.TYPE_MOBILE,
                getSubscriberId(context, ConnectivityManager.TYPE_MOBILE),
                0,
                System.currentTimeMillis(),
                packageUid);
        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
        networkStats.getNextBucket(bucket);
        return bucket.getTxBytes();
    }

    private long getPackageRxBytesWifi(NetworkStatsManager networkStatsManager,int packageUid) {
        NetworkStats networkStats = null;
        networkStats = networkStatsManager.queryDetailsForUid(
                ConnectivityManager.TYPE_WIFI,
                "",
                0,
                System.currentTimeMillis(),
                packageUid);
        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
        networkStats.getNextBucket(bucket);
        return bucket.getRxBytes();
    }

    private long getPackageTxBytesWifi(NetworkStatsManager networkStatsManager,int packageUid) {
        NetworkStats networkStats = null;
        networkStats = networkStatsManager.queryDetailsForUid(
                ConnectivityManager.TYPE_WIFI,
                "",
                0,
                System.currentTimeMillis(),
                packageUid);
        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
        networkStats.getNextBucket(bucket);
        return bucket.getTxBytes();
    }
//TODO - implement proper helper methods go get data usage individual apps
    private void printAllDataUsage(Context context) {



        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NetworkStatsManager networkStatsManager = (NetworkStatsManager) context.getApplicationContext().getSystemService(Context.NETWORK_STATS_SERVICE);
            for (ApplicationInfo packageInfo : packages) {
                Log.d("inotify-verN", String.valueOf(packageInfo.uid));
                Log.d("inotify-verN", String.valueOf(packageInfo.name));
                Log.d("inotify-verN", String.valueOf(packageInfo.packageName));
                Log.d("inotify-verN", String.valueOf(getPackageRxBytesMobile(context, networkStatsManager, packageInfo.uid)));
            }
        }else{

            for (ApplicationInfo packageInfo : packages) {
                Log.d("inotify", String.valueOf(packageInfo.uid));
                Log.d("inotify", String.valueOf(packageInfo.name));
                Log.d("inotify", String.valueOf(packageInfo.packageName));
                Log.d("inotify", String.valueOf(TrafficStats.getUidRxBytes(packageInfo.uid)));
                Log.d("inotify", String.valueOf(TrafficStats.getUidTxBytes(packageInfo.uid)));
            }

        }



    }

    public void getTotalDataUsag(){

        long durx = TrafficStats.getMobileRxBytes()/1024;
        long dutx = TrafficStats.getTotalRxBytes()/1024;


    }

}
