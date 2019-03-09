package com.example.inotify.broadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.inotify.dbHelpers.UC_DbHelper;
import com.example.inotify.helpers.ChargerHelper;

public class ChargerBR extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
        {
            ChargerHelper chargerHelper = new ChargerHelper(context);
            chargerHelper.powerOninsert();
        }
    }
}
