package com.example.inotify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class Mit_Charge extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
        {
            Log.v("Power in", "Charging ");
            Mit_SqlLiteDbHelper mit_sqlLiteDbHelper =new Mit_SqlLiteDbHelper(context);
            mit_sqlLiteDbHelper.mit_charge_insert();
            mit_sqlLiteDbHelper.close();
        }
    }
}
