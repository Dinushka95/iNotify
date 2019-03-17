package com.example.inotify.helpers;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.inotify.dbHelpers.ScreenStatusDbHelper;

public class ScreenStatusHelper extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(context);
            screenStatusDbHelper.screenOnInsert();
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(context);
            screenStatusDbHelper.screenOffInsert();
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
}
