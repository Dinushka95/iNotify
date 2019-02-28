package com.example.inotify.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.inotify.dbHelpers.UC_SqlLiteDbHelper;

public class UC_Charge extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
        {
            Log.v("Power in", "Charging ");
            UC_SqlLiteDbHelper UC_sqlLiteDbHelper =new UC_SqlLiteDbHelper(context);
            UC_sqlLiteDbHelper.mit_charge_insert();
            UC_sqlLiteDbHelper.close();
        }
    }
}
