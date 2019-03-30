package com.example.inotify.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;

import com.example.inotify.dbHelpers.CallUsageDbHelper;
import com.example.inotify.models.CallLogModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CallUsageHelper {
    private Context thisContex;

    public CallUsageHelper(Context context) {
        this.thisContex = context;
    }


    public String  getTodayTotalCallDurationFromPhone(Context context) {
        int totalDuration = 0;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            String fromDate = String.valueOf(calendar.getTimeInMillis());
            String toDate = String.valueOf(System.currentTimeMillis());
            String[] whereValue = {fromDate,toDate};

            Cursor cursor = context.getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, android.provider.CallLog.Calls.DATE+" BETWEEN ? AND ?", whereValue, null);

            if ((cursor != null ? cursor.getCount() : 0) > 0) {
                while (cursor.moveToNext()) {
                    String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
                    totalDuration = totalDuration + Integer.valueOf(duration);
                }
            }
        }
        return String.valueOf(totalDuration);
    }

    public List<CallLogModel> getTodayCallListFromPhone(Context context) {

        List<CallLogModel>callLogModelList = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Uri callUri = Uri.parse("content://call_log/calls");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        String fromDate = String.valueOf(calendar.getTimeInMillis());
        String toDate = String.valueOf(System.currentTimeMillis());
        String[] whereValue = {fromDate,toDate};

        Cursor cur = context.getApplicationContext().getContentResolver().query(callUri, null, android.provider.CallLog.Calls.DATE+" BETWEEN ? AND ?", whereValue, strOrder);

        //Cursor cur = cr.query(callUri, null, android.provider.CallLog.Calls.DATE+" >= ?", whereValue, strOrder);
        // loop through cursor
        while (cur.moveToNext()) {

            CallLogModel callLogModel = new CallLogModel();

            String callNumber = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.NUMBER));

            String callName = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME));

            String callDate = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.DATE));
            SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMMyyyy");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmm");
            String dateString = dateFormatter.format(new Date(Long.parseLong(callDate)).getDate());
            String timeString = timeFormatter.format(new Date(Long.parseLong(callDate)).getTime());

            String callType = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.TYPE));
            String dir=null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }

           // String isCallNew = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.NEW));
            String duration = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.DURATION));

            callLogModel.setDate(dateString);
            callLogModel.setTime(timeString);
            callLogModel.setCallType(dir);
            callLogModel.setNumber(callNumber);
            callLogModel.setName(callName);
            callLogModel.setDuration(duration);

            callLogModelList.add(callLogModel);
        }

        return callLogModelList;
    }


    public String getTodayTotalCallDuration()
    {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        return CallUsageDbHelper.getInstance(thisContex).getTotalCallDuration(date);
    }

    public String getTotalCallDurationAvg()
    {
        return CallUsageDbHelper.getInstance(thisContex).getCallTotalCallDurationAvg();
    }

    public void saveTodayTotalCallDurationToDb(){
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        CallUsageDbHelper.getInstance(thisContex).insertTodayTotalCallDuration(date,getTodayTotalCallDurationFromPhone(thisContex));
    }


}
