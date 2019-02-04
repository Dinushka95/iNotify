package com.example.inotify;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyNotificationListenerService extends NotificationListenerService {

    String pack = "";
    int nid;
    //String ticker = "";
    String title = "";
    String text = "";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        String ticker = "";
        if (sbn.getNotification().tickerText != null) {
            ticker = sbn.getNotification().tickerText.toString();
        }
        Log.d("cdap", " --MyNotificationListenerService--preLoop-Ticker--" + ticker);
        if ((sbn.getPackageName().equals("com.example.dinu.testa") ||
                sbn.getPackageName().equals("com.example.dinu.testb") ||
                sbn.getPackageName().equals("com.example.dinu.testc") ||
                sbn.getPackageName().equals("com.example.dinu.testd"))) {


            pack = sbn.getPackageName();
            nid = sbn.getId();

            Bundle extras = sbn.getNotification().extras;
            title = extras.getString("android.title");
            text = extras.getCharSequence("android.text").toString();

            //remove notification
            cancelNotification(sbn.getKey());

            Intent LaunchIntent = null;
            PackageManager pm = this.getPackageManager();
            String apppack = pack;
//==========================================================================================================================


            //////////////////////////////////////////////////////////////////////////////////////////////////









            if (true) {
                //send notification
                String name = "";
                try {
                    if (pm != null) {
                        ApplicationInfo app = this.getPackageManager().getApplicationInfo(apppack, 0);
                        name = (String) pm.getApplicationLabel(app);
                        LaunchIntent = pm.getLaunchIntentForPackage(apppack);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = LaunchIntent;
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

                String dateTime = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault()).format(new Date());

                NotificationCompat.Builder mBuilder = new NotificationCompat
                        .Builder(this, "inotifyapp")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(title + "-iNotify")
                        .setContentText(text)
                        .setTicker(dateTime)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pIntent);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(nid, mBuilder.build());

                Cha_SqlLiteDbHelper chaSqlLiteDbHelper = new Cha_SqlLiteDbHelper(this);
                chaSqlLiteDbHelper.Ninsert(Long.valueOf(dateTime) ,sbn.getPackageName(), Long.valueOf(dateTime));

            }
            Log.d("cdap", " --MyNotificationListenerService--run smart notification--stop----");
        }



    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

        Log.d("cdap", " --onNotificationRemoved---start" );


        //chaya
        if (sbn.getPackageName().equals("com.example.inotify")) {
            Log.d("cdap", " --onNotificationRemoved---" + sbn.getPackageName());

            int totalnotificationinlist = 0;

            StatusBarNotification[] notificationManager1 = getActiveNotifications();
            for (StatusBarNotification notification : notificationManager1) {
                Log.d("cdap", " ---onNotificationRemoved--------"+notification.getPackageName());
                if(notification.getPackageName().equals("com.example.inotify")){
                    totalnotificationinlist = totalnotificationinlist +1;
                }
            }
            Log.d("cdap", " ---onNotificationRemoved--------total notifications-----" + totalnotificationinlist);
            Cha_SqlLiteDbHelper chaSqlLiteDbHelper = new Cha_SqlLiteDbHelper(this);
            String packageName = chaSqlLiteDbHelper.NgetValue(sbn.getNotification().tickerText.toString());
            chaSqlLiteDbHelper.NIupdate(packageName, totalnotificationinlist);

        }


        // for prashan
        Pra_SqlLiteDbHelper praSqlLiteDbHelper = new Pra_SqlLiteDbHelper(this);
        praSqlLiteDbHelper.pra_notificationRemove_insert();
    }
}
