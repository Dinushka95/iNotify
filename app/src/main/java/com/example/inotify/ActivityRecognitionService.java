package com.example.inotify;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

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
        Log.d("inotifyLog", "Activity recognition ==" + type + confidance);
        Pra_SqlLiteDbHelper praSqlLiteDbHelper = new Pra_SqlLiteDbHelper(this);
        praSqlLiteDbHelper.pra_activity_insert(type,confidance);
        praSqlLiteDbHelper.close();


    }
}
