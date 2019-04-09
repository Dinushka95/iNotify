package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.NotificationDbHelper;
import com.example.inotify.models.NotificationModel;

import java.util.List;

public class NotificationHelper {

    private Context c1;

    public NotificationHelper(Context context) {
        c1 = context;
    }

    public boolean insert(NotificationModel NotificationModel) {
        return NotificationDbHelper.getInstance(c1).insert(NotificationModel);
    }

    public List<NotificationModel> allAppInfoGet() {
        return NotificationDbHelper.getInstance(c1).allNotificationInfoGet();
    }

    public List<NotificationModel> allPendingNotificationInfoGet() {
        return NotificationDbHelper.getInstance(c1).allPendingNotificationInfoGet();
    }

    public List<NotificationModel> allSmartNotificationInfoGet() {
        return NotificationDbHelper.getInstance(c1).allSmartNotificationInfoGet();
    }

}
