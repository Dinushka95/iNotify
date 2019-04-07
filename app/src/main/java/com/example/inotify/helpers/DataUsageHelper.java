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

import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.DataUsageDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataUsageHelper {

    private Context c1;

    public DataUsageHelper(Context context) {
        this.c1 = context;
    }

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

            }
        }else{



        }



    }

    public void getTotalDataUsag(){
        long durx = TrafficStats.getMobileRxBytes()/1024;
        long dutx = TrafficStats.getTotalRxBytes()/1024;

        long totalamount =durx+dutx;
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        DataUsageDbHelper.getInstance(c1).insertTotoalDataUsage(date, String.valueOf(totalamount));

    }


    public void  getTotalDataUsagAvailability()
    {
        if(! DataUsageDbHelper.getInstance(c1).cheackAvailability(TbNames.CHARGER_TABLE))
        {
            this.getTotalDataUsag();
        }

    }



}
