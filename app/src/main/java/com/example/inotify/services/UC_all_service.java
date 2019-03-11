package com.example.inotify.services;

import android.Manifest;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

import com.example.inotify.models.ContactsModel;
import com.example.inotify.helpers.UC_CalenderEvent;
import com.example.inotify.dbHelpers.UC_DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UC_all_service extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {

        getAppsInformaion(this);
      //  getAppUsage(this);
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

    public void getAppsInformaion(Context context) {


        //get a list of installed apps.
        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        int count = 0;

        /*for (ApplicationInfo packageInfo : packages) {
            String tem = "null";//pm.getLaunchIntentForPackage(packageInfo.packageName).toString();


        }*/


        count = packages.size();

        UC_DbHelper UC_DbHelper = new UC_DbHelper(this);
        UC_DbHelper.appListcount_insert(String.valueOf(count));
        UC_DbHelper.close();


    }

   /* public void getAppUsage(Context context) {

// delete old code and and app use
        // need app usage permission
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long start = calendar.getTimeInMillis();
        long end = System.currentTimeMillis();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, start, end);

        long count = 0;
        //  Log.v("inotify","uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        for (UsageStats stat : stats) {
            count = count + stat.getTotalTimeInForeground();


        }

        UC_DbHelper UC_DbHelper = new UC_DbHelper(this);
        UC_DbHelper.appusagecount_insert(String.valueOf(count));
        UC_DbHelper.close();
    }
*/

    public void getContacts(Context context) {


        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        List<ContactsModel> myList = new ArrayList<>();

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
                        myList.add(new ContactsModel(name, phoneNo));

                        Collections.sort(myList, new Comparator<ContactsModel>() {
                            @Override
                            public int compare(ContactsModel o1, ContactsModel o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        });

                    }
                    pCur.close();
                }

            }
        }

        if (cur != null) {
            cur.close();
        }

        int count = myList.size();
        UC_DbHelper UC_DbHelper = new UC_DbHelper(this);
        UC_DbHelper.contactCount_insert(String.valueOf(count));
        UC_DbHelper.close();

    }

    public void getCallDuration(Context context) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = context.getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
            int totalDuration = 0;
            if ((cursor != null ? cursor.getCount() : 0) > 0) {
                while (cursor.moveToNext()) {

                    String dateTime = new SimpleDateFormat("yyyyMMddhhmmsss",Locale.getDefault()).format(new Date(Long.valueOf(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)))));
                    String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                    String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));


                    //  Log.d("inotify", number+duration+type+dateTime );
                    totalDuration = totalDuration + Integer.valueOf(duration);
                }
            }
            UC_DbHelper UC_DbHelper = new UC_DbHelper(this);
            UC_DbHelper.callduration_insert(String.valueOf(totalDuration));
            UC_DbHelper.close();
        }
    }

    public void getCalenderEvent(Context context) {

        UC_CalenderEvent UC_calenderEvent = new UC_CalenderEvent();
        String x = UC_calenderEvent.getcalanderEventCount(context);


        UC_DbHelper UC_DbHelper = new UC_DbHelper(context);
        UC_DbHelper.calenderEventCount_insert(x);
        UC_DbHelper.close();
    }


    public void getSocialMediaApps(Context context) {


        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        int count = 0;
        //  Log.v("inotify","pppppppppppppppppppp");

        for (ApplicationInfo applicationInfo : packages) {
            String x = applicationInfo.packageName.toString();
            if (x.equals("com.example.dinu.testd")) {
                count++;
            }
        }

        UC_DbHelper UC_DbHelper = new UC_DbHelper(this);
        //UC_DbHelper.appusagecount_insert(String.valueOf(count));
        UC_DbHelper.close();

    }
//
//    public List<String> commonSocialApps()
//    {
//        ApplicationsHelper applicationsHelper = new ApplicationsHelper(view.getContext());
//        List<AppInfoModel> mySocialApps= applicationsHelper.mySocialAppGet();
//
//        TopAppsHelper topAppsHelper = new TopAppsHelper(view.getContext());
//        List<AppInfoModel> topSocialApps = topAppsHelper.topAppSocial();
//
//
//
//
//        int correctCount=0, incorrectCount = 0;
////        List<String> list1MysocialApps = new ArrayList<String>(mySocialApps);
////
////        List<String> list2topSocialApps = new ArrayList<String>();
//
//        for(AppInfoModel tmp1: topSocialApps) {
//            for(AppInfoModel tmp2: mySocialApps) {
//                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
//                    List<AppInfoModel> commonSocial =
//                            Log.d("inotify","Social apps");
//                    for (AppInfoModel value : commonSocial)
//                    {
//                        Log.d("inotify",value.getAppName());
//                        Log.d("inotify","app info");
//                    }
//                    correctCount++;
//                } else {
//                    incorrectCount++;
//                }
//            }
//        }
//
//
//
//        return List<String>social ;
//    }
//
//

}
