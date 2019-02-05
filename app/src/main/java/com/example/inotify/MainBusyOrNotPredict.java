package com.example.inotify;

import android.content.Context;

import java.util.ArrayList;

public class MainBusyOrNotPredict {

    public String GetNewPrediction(Context context,String activity,String location){

        ArrayList<String> bon = new ArrayList();

        Pra_SqlLiteDbHelper praSqlLiteDbHelper = new Pra_SqlLiteDbHelper(context);
        praSqlLiteDbHelper.pra_BusyOrNotPredict_Get(activity,location);
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
