package com.example.inotify.helpers;

import android.content.Context;
import android.service.notification.StatusBarNotification;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationImportnaceHelper {

    public String AssignSequenceValue(StatusBarNotification[] statusBarNotifications, StatusBarNotification sbn) {
        String Removethis = " ";
        if (sbn.getPackageName().equals("com.example.inotify")) {
            String ticker = sbn.getNotification().tickerText.toString();

            String Viewtime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

            int totalnotificationlist = 0;
            for (StatusBarNotification notification : statusBarNotifications) {
                if (notification.getPackageName().equals("com.example.inotify")) {
                    totalnotificationlist = totalnotificationlist + 1;
                }
            }
        }
        return Removethis;
    }

}
