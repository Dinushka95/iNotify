package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.NotificationViewabilityDbHelper;

import java.util.ArrayList;

public class MainNotificationViewability {

    public String GetNewPrediction(Context context,String activity,String location){

        ArrayList<java.lang.String> bon = new ArrayList();

        NotificationViewabilityDbHelper praSqlLiteDbHelper = new NotificationViewabilityDbHelper(context);
        praSqlLiteDbHelper.busyOrNotPredict_Get(activity,location);
        praSqlLiteDbHelper.close();

        int busyCount = 0;
        int notbusyCount = 0;
        //int totalCount = bon.size();

        for (String x:bon) {
            if (x.equals("BUSY")){
                busyCount++;
            }
            if (x.equals("NotBusy")){
                notbusyCount++;
            }
        }

        if(busyCount>notbusyCount){
            return "Busy";
        }
        else {
            return "NotBusy";
        }
    }
}
