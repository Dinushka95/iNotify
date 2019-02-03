package com.example.inotify;

import android.content.Context;

import java.util.ArrayList;

public class BusyOrNotPredict {

    public String GetNewPrediction(Context context,String activity,String location){

        ArrayList<String> bon = new ArrayList();

        SqlLiteDbHelper sqlLiteDbHelper = new SqlLiteDbHelper(context);
        sqlLiteDbHelper.pra_BusyOrNotPredict_Get(activity,location);

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
