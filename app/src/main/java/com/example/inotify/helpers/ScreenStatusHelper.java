package com.example.inotify.helpers;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.inotify.dbHelpers.ScreenStatusDbHelper;

public class ScreenStatusHelper extends BroadcastReceiver {
    private Context c1;
    private BroadcastReceiver mReceiver;
    private IntentFilter intentFilter;
    @Override
    public void onReceive(Context context, Intent intent) {
        c1 =context;
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            ScreenStatusDbHelper.getInstance(c1).screenOnInsert();
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(context);
            ScreenStatusDbHelper.getInstance(c1).screenOffInsert();
        }
    }

    //Implement the function to check the screen status when a notification is delivered
    // this needs to be tested
    public boolean isPhoneLockedOrNot(Context context) {
        boolean isPhoneLock = false;
        if (context != null) {
            KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if (myKM != null && myKM.isKeyguardLocked()) {
                isPhoneLock = true;
            }
        }
        return isPhoneLock;
    }
    public void start(Context context){
        // has to test because screen off  doen't work proprly with manifest file. need to do it in code

        intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new ScreenStatusHelper();
        context.getApplicationContext().registerReceiver(mReceiver, intentFilter);
    }
}
