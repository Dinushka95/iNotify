package com.example.inotify.viewControllers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class NotificationHistoryLogic {

    public Context context;

    public NotificationHistoryLogic(Context c1) {
        context=c1;
    }

    public ArrayList<String> getNotificationList(){

        ArrayList<String> packageList = new ArrayList<>();
        //notification list from db class and filter them and put

        packageList.add("nitification 1");
        packageList.add("nitification 2");
        packageList.add("nitification 3");
        packageList.add("nitification 4");


        return packageList;
    }
}
