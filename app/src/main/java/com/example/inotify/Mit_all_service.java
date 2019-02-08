package com.example.inotify;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Mit_all_service extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {

        getAppsInformaion (this);
        getAppUsage(this);
        getContacts(this);
        getCallDuration(this);
        getCalenderEvent(this);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


    String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

    public void getAppsInformaion (Context context){


        //get a list of installed apps.
        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        int count = 0;

        /*for (ApplicationInfo packageInfo : packages) {
            String tem = "null";//pm.getLaunchIntentForPackage(packageInfo.packageName).toString();


        }*/
        count =packages.size();

        Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(this);
        mit_sqlLiteDbHelper.mit_appListcount_insert(String.valueOf(count));
        mit_sqlLiteDbHelper.close();

    }

    public void getAppUsage(Context context){

        // need app usage permission
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long start = calendar.getTimeInMillis();
        long end = System.currentTimeMillis();
        UsageStatsManager usageStatsManager = (UsageStatsManager)context.getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, start, end);

        long count =0;
      //  Log.v("inotify","uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        for (UsageStats stat : stats) {
            count = count +stat.getTotalTimeInForeground();


        }

        Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(this);
        mit_sqlLiteDbHelper.mit_appusagecount_insert(String.valueOf(count));
        mit_sqlLiteDbHelper.close();
    }


    public void getContacts(Context context){


        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        List<MyContact> myList = new ArrayList<>();

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);

                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        myList.add(new MyContact(name,phoneNo));

                        Collections.sort(myList, new Comparator<MyContact>() {
                            @Override
                            public int compare(MyContact o1, MyContact o2) {
                                return  o1.getName().compareTo(o2.getName());
                            }
                        });

                    }
                    pCur.close();
                }

            }
        }

        if(cur!=null){
            cur.close();
        }

        int count = myList.size();
        Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(this);
        mit_sqlLiteDbHelper.mit_contactCount_insert(String.valueOf(count));
        mit_sqlLiteDbHelper.close();

    }

    public void getCallDuration(Context context){

        Cursor cursor = context.getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int totalDuration=0;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                String dateTime = new SimpleDateFormat("yyyyMMddhhmmsss").format(new Date(Long.valueOf(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)))));
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));


                //  Log.d("inotify", number+duration+type+dateTime );
                totalDuration = totalDuration+Integer.valueOf(duration);
            }

        }

        Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(this);
        mit_sqlLiteDbHelper.mit_callduration_insert(String.valueOf(totalDuration));
        mit_sqlLiteDbHelper.close();
    }

    public void getCalenderEvent(Context context){

        Mit_CalenderEvent mit_calenderEvent = new Mit_CalenderEvent();
        String x = mit_calenderEvent.getcalanderEventCount(context);


        Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(context);
        mit_sqlLiteDbHelper.mit_calenderEventCount_insert(x);
        mit_sqlLiteDbHelper.close();
    }


    public void  getSocialMediaApps(Context context){


        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        int count =0;
      //  Log.v("inotify","pppppppppppppppppppp");

        for (ApplicationInfo applicationInfo : packages) {
            String x = applicationInfo.packageName.toString();
            if(x.equals("com.example.dinu.testd")){
                count = count++ ;
            }
        }

        Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(this);
        mit_sqlLiteDbHelper.mit_appusagecount_insert(String.valueOf(count));
        mit_sqlLiteDbHelper.close();

    }



}
