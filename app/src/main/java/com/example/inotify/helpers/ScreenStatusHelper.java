package com.example.inotify.helpers;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenStatusHelper extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


    }

    //Implement the function to check the screen status when a notification is delivered
    public boolean isPhoneLockedOrNot(Context context){
        boolean isPhoneLock =false;
        if(context != null){
            KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if(myKM != null && myKM.isKeyguardLocked()){
                isPhoneLock =true;
                //Log.d("inotify" , "screen type " +isPhoneLock );
            }
        }
        return isPhoneLock;
    }
}
