package com.example.inotify.helpers;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class FeedbackYesIntent extends IntentService {

    public FeedbackYesIntent() {
        super("FeedbackYesIntent");
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        Log.d("inotify", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" );
        // TODO-- get user feedback. if this notification is important

    }
}
