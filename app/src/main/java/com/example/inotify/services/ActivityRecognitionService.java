package com.example.inotify.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.inotify.dbHelpers.NotificationViewabilityDbHelper;
import com.google.android.gms.location.ActivityRecognitionResult;

public class ActivityRecognitionService extends IntentService {

    public ActivityRecognitionService() {
        super("ActivityRecognitionService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        String type = String.valueOf(result.getMostProbableActivity().getType());
        String confidance = String.valueOf(result.getMostProbableActivity().getConfidence());
      //  Log.d("inotifyLog", "Activity recognition ==" + type + confidance);
        NotificationViewabilityDbHelper praSqlLiteDbHelper = new NotificationViewabilityDbHelper(this);
        praSqlLiteDbHelper.activity_insert(type,confidance);
        praSqlLiteDbHelper.close();


        String sptype="";

        if (type.equals("0")) {sptype="V";}
        if (type.equals("3")) {sptype="S";}
        if (type.equals("5")) {sptype="T";}
        if (type.equals("7")) {sptype="W";}
        if (type.equals("8")) {sptype="R";}

        SharedPreferences.Editor editor = this.getSharedPreferences("activityrecognition", MODE_PRIVATE).edit();
        editor.putString("activity", sptype);
        editor.apply();

    }
}
