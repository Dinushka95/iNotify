package com.example.inotify.viewControllers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.inotify.helpers.NotificationHelper;
import com.example.inotify.models.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationHistoryLogic {

    public Context c1;

    public NotificationHistoryLogic(Context context) {
        c1= context;
    }

    public ArrayList<String> getNotificationList(){

        ArrayList<String> packageList = new ArrayList<>();
        //notification list from db class and filter them and put
        NotificationHelper notificationHelper = new NotificationHelper(c1);
        List<NotificationModel> notificationModelList = notificationHelper.allAppInfoGet();

        for (NotificationModel notificationModel:notificationModelList){
            packageList.add(notificationModel.getAppName());
        }
        return packageList;
    }
}
