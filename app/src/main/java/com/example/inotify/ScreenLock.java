package com.example.inotify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class ScreenLock extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
        {
            Log.v("Screen mode", "Screen is in off State");
            SharedPreferences.Editor editor = context.getSharedPreferences("lockscreen", MODE_PRIVATE).edit();
            editor.putString("screen", "off");
            editor.apply();

        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
        {
            Log.v("Screen mode","Screen is in on State" );
            SharedPreferences.Editor editor = context.getSharedPreferences("lockscreen", MODE_PRIVATE).edit();
            editor.putString("screen", "on");
            editor.apply();
        }
    }
}
