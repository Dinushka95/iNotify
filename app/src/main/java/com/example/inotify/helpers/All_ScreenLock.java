package com.example.inotify.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.inotify.dbHelpers.UC_SqlLiteDbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class All_ScreenLock extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.v("xxxxxxxxxxxxxxxxx", intent.getDataString());
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
        {
           // Log.v("Screen mode", "Screen is in off State");

            //chaya
            SharedPreferences.Editor editor = context.getSharedPreferences("lockscreen", MODE_PRIVATE).edit();
            editor.putString("screen", "off");
            editor.apply();

            //mitha part
            String timenow = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());

            SharedPreferences prefs = context.getSharedPreferences("lockscreen", MODE_PRIVATE);
            String timeoff = prefs.getString("time", null);


            SimpleDateFormat format = new SimpleDateFormat("HHmmss");
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

            long difference = date2.getTime() - date1.getTime();
            System.out.println(difference/1000);

            UC_SqlLiteDbHelper UC_sqlLiteDbHelper = new UC_SqlLiteDbHelper(context);
            UC_sqlLiteDbHelper.screentime_insert(String.valueOf(difference));




        }


        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
        {
          //  Log.v("Screen mode","Screen is in on State" );
            SharedPreferences.Editor editor = context.getSharedPreferences("lockscreen", MODE_PRIVATE).edit();
            editor.putString("screen", "on");
            String timenow = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
            editor.putString("time",timenow );
            editor.apply();
        }

    }


}
