package com.example.inotify.helpers;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class FeedbackNoIntent extends IntentService {

    public FeedbackNoIntent() {
        super("FeedbackNoIntent");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
