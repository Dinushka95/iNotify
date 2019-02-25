package com.example.inotify.notificationViewability;

import android.content.Context;

import java.util.ArrayList;

public class MainBusyOrNotPredict {

    public String GetNewPrediction(Context context,String activity,String location){

        ArrayList<String> bon = new ArrayList();

        NV_SqlLiteDbHelper praSqlLiteDbHelper = new NV_SqlLiteDbHelper(context);
        praSqlLiteDbHelper.busyOrNotPredict_Get(activity,location);
        praSqlLiteDbHelper.close();

        int busyCount = 0;
        int notbusyCount = 0;
        int totalCount = bon.size();

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
