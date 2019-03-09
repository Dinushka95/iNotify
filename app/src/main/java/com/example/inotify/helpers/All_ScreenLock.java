package com.example.inotify.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.inotify.dbHelpers.UA_DbHelper;
import com.example.inotify.dbHelpers.UC_DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class All_ScreenLock extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.v("xxxxxxxxxxxxxxxxx", intent.getDataString());
        if (Objects.equals(intent.getAction(), Intent.ACTION_SCREEN_OFF))
        {
           // Log.v("Screen mode", "Screen is in off State");

            //chaya
            SharedPreferences.Editor editor = context.getSharedPreferences("lockscreen", MODE_PRIVATE).edit();
            editor.putString("screen", "off");
            editor.apply();
            // add db entry to save data time of screen off
            UA_DbHelper ua_DbHelper = new UA_DbHelper(context);


            //mitha part
            String timenow = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());

            SharedPreferences prefs = context.getSharedPreferences("lockscreen", MODE_PRIVATE);
            String timeoff = prefs.getString("time", null);


            SimpleDateFormat format = new SimpleDateFormat("HHmmss",Locale.getDefault());
            Date date1 = null;
            try {
                date1 = format.parse(timeoff);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date2 = null;
            try {
                date2 = format.parse(timenow);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long difference = (date2 != null ? date2.getTime() : 0) - (date1 != null ? date1.getTime() : 0);
            System.out.println(difference/1000);

            UC_DbHelper UC_DbHelper = new UC_DbHelper(context);
            UC_DbHelper.screentime_insert(String.valueOf(difference));

        }


        if (Objects.equals(intent.getAction(), Intent.ACTION_SCREEN_ON))
        {
          //  Log.v("Screen mode","Screen is in on State" );
            SharedPreferences.Editor editor = context.getSharedPreferences("lockscreen", MODE_PRIVATE).edit();
            editor.putString("screen", "on");
            // add db entry to save data time of screen on
            UA_DbHelper ua_DbHelper = new UA_DbHelper(context);
            ua_DbHelper.screenOnInsert();
            String timenow = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
            editor.putString("time",timenow );
            editor.apply();
        }
        }

}
