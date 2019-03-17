package com.example.inotify.services;

import android.Manifest;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

import com.example.inotify.dbHelpers.UserCharacteristics_DbHelper;
import com.example.inotify.helpers.CalenderEventHelper;
import com.example.inotify.models.ContactsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserCharacteristics_service extends JobService {

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

        count = packages.size();

        UserCharacteristics_DbHelper UserCharacteristics_DbHelper = new UserCharacteristics_DbHelper(this);
        UserCharacteristics_DbHelper.appListcount_insert(String.valueOf(count));
        UserCharacteristics_DbHelper.close();

    }

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
        UserCharacteristics_DbHelper UserCharacteristics_DbHelper = new UserCharacteristics_DbHelper(this);
        UserCharacteristics_DbHelper.contactCount_insert(String.valueOf(count));
        UserCharacteristics_DbHelper.close();

    }

    public void getCallDuration(Context context) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = context.getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
            int totalDuration = 0;
            if ((cursor != null ? cursor.getCount() : 0) > 0) {
                while (cursor.moveToNext()) {

                    String dateTime = new SimpleDateFormat("yyyyMMddhhmmsss", Locale.getDefault()).format(new Date(Long.valueOf(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)))));
                    String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                    String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));


                    //  Log.d("inotify", number+duration+type+dateTime );
                    totalDuration = totalDuration + Integer.valueOf(duration);
                }
            }
            UserCharacteristics_DbHelper UserCharacteristics_DbHelper = new UserCharacteristics_DbHelper(this);
            UserCharacteristics_DbHelper.callduration_insert(String.valueOf(totalDuration));
            UserCharacteristics_DbHelper.close();
        }
    }

    public void getCalenderEvent(Context context) {

        CalenderEventHelper calenderEvent = new CalenderEventHelper();
        String x = calenderEvent.getcalanderEventCount(context);


        UserCharacteristics_DbHelper UserCharacteristics_DbHelper = new UserCharacteristics_DbHelper(context);
        UserCharacteristics_DbHelper.calenderEventCount_insert(x);
        UserCharacteristics_DbHelper.close();
    }


    public void getSocialMediaApps(Context context) {


        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        int count = 0;

        for (ApplicationInfo applicationInfo : packages) {
            String x = applicationInfo.packageName.toString();
            if (x.equals("com.example.dinu.testd")) {
                count++;
            }
        }

        UserCharacteristics_DbHelper UserCharacteristics_DbHelper = new UserCharacteristics_DbHelper(this);
        //UserCharacteristics_DbHelper.appusagecount_insert(String.valueOf(count));
        UserCharacteristics_DbHelper.close();
    }

}
