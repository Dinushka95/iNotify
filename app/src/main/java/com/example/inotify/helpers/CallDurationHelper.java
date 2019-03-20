package com.example.inotify.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.inotify.dbHelpers.CallDurationDbHelper;
import com.example.inotify.dbHelpers.UserCharacteristics_DbHelper;

public class CallDurationHelper {
    private Context c1;

    public CallDurationHelper(Context context) {
        this.c1 = context;
    }


    public void getCallDuration(Context context) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = context.getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
            int totalDuration = 0;
            if ((cursor != null ? cursor.getCount() : 0) > 0) {
                while (cursor.moveToNext()) {

                    // String dateTime = new SimpleDateFormat("yyyyMMddhhmmsss", Locale.getDefault()).format(new Date(Long.valueOf(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)))));
                    // String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                    String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));


                    // Log.d("inotify", number+duration+type+dateTime );
                    totalDuration = totalDuration + Integer.valueOf(duration);


                }
            }
            Log.d("inotify","total call duration" + totalDuration);
            UserCharacteristics_DbHelper UserCharacteristics_DbHelper = new UserCharacteristics_DbHelper(context);
            UserCharacteristics_DbHelper.callduration_insert(String.valueOf(totalDuration));
            UserCharacteristics_DbHelper.close();
        }

    }

    public long getCallDuraionAVGToday()
    {
        CallDurationDbHelper callDurationDbHelper = new CallDurationDbHelper(c1);
        callDurationDbHelper.callDurationTodayGet();
        Log.d("inotify","call duration today----" + callDurationDbHelper.callDurationTodayGet());

        return callDurationDbHelper.callDurationTodayGet();
    }

    public long getCallDurationAVG()
    {
        CallDurationDbHelper callDurationDbHelper = new CallDurationDbHelper(c1);
        callDurationDbHelper.callDurationAvgGet();
        Log.d("inotify","call duration AVG----" + callDurationDbHelper.callDurationAvgGet());
        return callDurationDbHelper.callDurationAvgGet();

    }

}
