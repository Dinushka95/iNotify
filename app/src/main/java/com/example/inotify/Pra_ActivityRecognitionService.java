package com.example.inotify;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Pra_ActivityRecognitionService extends IntentService {

    public Pra_ActivityRecognitionService() {
        super("Pra_ActivityRecognitionService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        String type = String.valueOf(result.getMostProbableActivity().getType());
        String confidance = String.valueOf(result.getMostProbableActivity().getConfidence());
      //  Log.d("inotifyLog", "Activity recognition ==" + type + confidance);
        Pra_SqlLiteDbHelper praSqlLiteDbHelper = new Pra_SqlLiteDbHelper(this);
        praSqlLiteDbHelper.pra_activity_insert(type,confidance);
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
