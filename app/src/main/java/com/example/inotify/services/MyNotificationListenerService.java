package com.example.inotify.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.NV_DbHelper;
import com.example.inotify.dbHelpers.NotificationDbHelper;
import com.example.inotify.dbHelpers.NotificationImportnaceDbHelper;
import com.example.inotify.dbHelpers.RingerModeDbHelper;
import com.example.inotify.dbHelpers.ScreenStatusDbHelper;
import com.example.inotify.helpers.FeedbackYesIntent;
import com.example.inotify.helpers.NotificationHelper;
import com.example.inotify.helpers.RingerModeHelper;
import com.example.inotify.helpers.ScreenStatusHelper;
import com.example.inotify.helpers.UserAttentivness;
import com.example.inotify.models.NotificationModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MyNotificationListenerService extends NotificationListenerService {

    String id = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.getDefault()).format(new Date());
    String TimeRecieved = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());


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

        Log.d("inotify", "Main-MyNotificationListenerService--preLoop-Ticker---" + ticker);

        Log.d("inotify", "Main-MyNotificationListenerService--packageName---" + sbn.getPackageName());

        if ((sbn.getPackageName().equals("com.example.dinu.testa") || sbn.getPackageName().equals("com.example.dinu.testb") || sbn.getPackageName().equals("com.example.dinu.testc") || sbn.getPackageName().equals("com.example.dinu.testd") || sbn.getPackageName().equals("com.example.myapplication") || sbn.getPackageName().equals("com.whatsapp") || sbn.getPackageName().equals("com.facebook.orca") || sbn.getPackageName().equals("com.google.android.apps.messaging"))) {


            pack = sbn.getPackageName();
            nid = sbn.getId();

            Bundle extras = sbn.getNotification().extras;
            title = extras.getString("android.title");
            text = extras.getCharSequence("android.text").toString();

            Log.d("inotify", "Main-MyNotificationListenerService--nid---" + nid);
            Log.d("inotify", "Main-MyNotificationListenerService--title---" + title);
            Log.d("inotify", "Main-MyNotificationListenerService--text---" + text);

            //remove notification
            cancelNotification(sbn.getKey());
            Log.d("inotify", "Main-MyNotificationListenerService--Cancel original Notification---");

            Intent LaunchIntent = null;
            PackageManager pm = this.getPackageManager();
            String apppack = pack;


            //Test
            NV_DbHelper pratest = new NV_DbHelper(this);
            pratest.probability_insert();
            pratest.close();

            //call the isPhoneLowckedOrNot method here
            ScreenStatusHelper screenStatusHelper = new ScreenStatusHelper();
            Boolean screenstatus = screenStatusHelper.isPhoneLockedOrNot(this);
            Log.d("inotifyC ", "ScreenStatus On Notification recive" + screenstatus);
            if (screenstatus == false) {
                //Save to screen on table
                ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(this);
                // String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                screenStatusDbHelper.ScreenOnInsert();
                screenStatusDbHelper.close();
                Log.d("inotifyC", "SCreen status Saved");
            } else {
                //String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(this);
                screenStatusDbHelper.ScreenOffInsert();
                screenStatusDbHelper.close();
                Log.d("inotifyC", "SCreen off status Saved");


            }

            //Get the ringer Mode
            RingerModeHelper ringermodeHelper = new RingerModeHelper();
            String RingerMode = ringermodeHelper.getRingerMode(this);
            Log.d("inotifyC ", "RingerMode On Notification recive" + RingerMode);

            //Save ringer Mode to the table
            RingerModeDbHelper ringerModeDbHelper = new RingerModeDbHelper(this);
            Log.d("inotifyC ", "RingerMode" + RingerMode + "," + id);

            ringerModeDbHelper.RMinsert(id, RingerMode);
            ringerModeDbHelper.close();
            Log.d("inotifyC ", " ringer mode Record Saved");


            boolean sendornotsend;
            sendornotsend = true;

            if (sendornotsend) {


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


                // feedbackYes
                Intent feedbackYes = new Intent(this, FeedbackYesIntent.class);
                PendingIntent pFeedbackYes = PendingIntent.getService(this, 1, feedbackYes, PendingIntent.FLAG_ONE_SHOT);

                // feedbackNo
                Intent feedbackNo = new Intent(this, FeedbackYesIntent.class);
                PendingIntent pFeedbackNo = PendingIntent.getService(this, 1, feedbackNo, PendingIntent.FLAG_ONE_SHOT);

                NotificationManager notifManager = null;
                final int NOTIFY_ID = 0;
                Intent intent;
                PendingIntent pendingIntent;
                NotificationCompat.Builder builder;

                String Sendtime = "";

                if (notifManager == null) {
                    notifManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = notifManager.getNotificationChannel(id);
                    if (mChannel == null) {
                        mChannel = new NotificationChannel(id, title, importance);
                        mChannel.enableVibration(true);
                        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                        notifManager.createNotificationChannel(mChannel);
                    }
                    builder = new NotificationCompat.Builder(this, id);
                    intent = LaunchIntent;
                    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
                    builder.setContentTitle(title + "-iNotify")                            // required
                            .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                            .setContentText(text) // required
                            .setDefaults(Notification.DEFAULT_ALL).setAutoCancel(true).addAction(R.drawable.common_google_signin_btn_icon_light, "Yes", pFeedbackYes).addAction(R.drawable.common_google_signin_btn_icon_light, "No", pFeedbackNo).setContentIntent(pIntent).setTicker(id).setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    Sendtime = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
                } else {
                    builder = new NotificationCompat.Builder(this, id);
                    intent = LaunchIntent;
                    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
                    builder.setContentTitle(title + "-iNotify")                            // required
                            .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                            .setContentText(text) // required
                            .setDefaults(Notification.DEFAULT_ALL).setAutoCancel(true).addAction(R.drawable.common_google_signin_btn_icon_light, "Yes", pFeedbackYes).addAction(R.drawable.common_google_signin_btn_icon_light, "No", pFeedbackNo).setContentIntent(pIntent).setTicker(id).setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400}).setPriority(Notification.PRIORITY_HIGH);
                    Sendtime = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
                }
                Notification notification = builder.build();
                notifManager.notify(NOTIFY_ID, notification);


                //PRASHAN
                // Notification insert

                String nid = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.getDefault()).format(new Date());
                Log.d("Notification_insert", "onNotificationPosted: Notification ID");

                String Date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

                //               String TimeRecieved = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
                String appName1 = "null";
                final String packageName1 = sbn.getPackageName();
                PackageManager packageManager = getApplicationContext().getPackageManager();
                try {
                    appName1 = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName1, PackageManager.GET_META_DATA));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                String datetime = Date;
                String timeRecevied = TimeRecieved;
                String timeSent = Sendtime;
                String timeViewed = "";
                String appName = appName1;
                String packageName = sbn.getPackageName();

                NotificationHelper notificationHelper = new NotificationHelper(getBaseContext());
                notificationHelper.insert(new NotificationModel(id, datetime, timeRecevied, timeSent, timeViewed, appName, packageName, "1"));


            }
            Log.d("inotify", "Main-MyNotificationListenerService--un smart notification--stop----");
        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

        Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved---start");


        Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--input notification name-" + sbn.getPackageName());

        String ticker = sbn.getNotification().tickerText.toString();
        Log.d("Notification ticker", "onNotificationRemoved: " + ticker);


        String Viewtime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        //String id = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.getDefault()).format(new Date());


        int totalnotificationinlist = 0;

        StatusBarNotification[] notificationManager1 = getActiveNotifications();

        for (StatusBarNotification notification : notificationManager1) {

            Log.d("inotify", "Total notifications initially " + totalnotificationinlist);
            totalnotificationinlist = totalnotificationinlist + 1;

        }
        Log.d("inotifyC", "totalnotificationinlist Importnace table " + totalnotificationinlist);

        NotificationDbHelper notificationDbHelper = new NotificationDbHelper(this);
        //String PackageName = notificationDbHelper.AppnameGet(sbn.getNotification().tickerText.toString());
        String PackageName = notificationDbHelper.AppnameGet(id);
        Log.d("inotifyC", "PackaheName Importnace table " + PackageName);

        NotificationImportnaceDbHelper notificationImportnaceDbHelper = new NotificationImportnaceDbHelper(this);
        notificationImportnaceDbHelper.NotificationImportnaceInsert(id, PackageName, totalnotificationinlist);

        Log.d("inotifyC", "Savedto notification Importnace table pname, totnotif ,ticker " + PackageName + "," + totalnotificationinlist + "," + ticker);
        //  Log.d("inotifyC" , "Savedto notification Importnace table ticker "+ticker );

        notificationImportnaceDbHelper.close();

        // String timerecived  = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
        Log.d("inotify", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + id);
        Log.d("inotify ", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + TimeRecieved);


        int notificationTotal = totalnotificationinlist;
        String notificationRecivedTime = notificationDbHelper.recivedTimeGet(id);
        String notificationViwedTime = notificationDbHelper.viewTimeGet(id);
        Log.d("inotifyC", "Viwed Time " + notificationViwedTime);
        Log.d("inotifyC", "Viwed Time " + id);

        NotificationImportnaceDbHelper notificationImportnaceDbHelper1 = new NotificationImportnaceDbHelper(this);
        String Seqence = notificationImportnaceDbHelper.NotificationImportnaceGet(id);
        Log.d("inotifyC", "Seqence Time " + Seqence);


        RingerModeDbHelper ringerModeDbHelperHelper = new RingerModeDbHelper(this);
        String RingerMode = ringerModeDbHelperHelper.RingerModeGet(id);
        Log.d("inotifyC", "RingerMode " + RingerMode);

        ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(this);
        String tablename = screenStatusDbHelper.checkAvaulability(id);
        String screenStatus;
        if (tablename == "UA_SCREENON_TABLE") {
            String ScreenOnStatus = screenStatusDbHelper.ScreenOnStatusGet();
            screenStatus = "on";
        } else {
            String ScreenOffStstus = screenStatusDbHelper.ScreenOffStatusGet();
            screenStatus = "off";
        }
        Log.d("attentiv", notificationRecivedTime + " ," + notificationViwedTime + "," + RingerMode + "," + screenStatus + "," + Seqence + "," + notificationTotal);

        UserAttentivness userAttentivness = new UserAttentivness();
        double attentivnessvalue = 0.0;
        attentivnessvalue = userAttentivness.calculateAttentivness(id, screenStatus, RingerMode, notificationViwedTime, notificationRecivedTime, Seqence, notificationTotal);
        Log.d("inotifyC", "attentivness per notification" + attentivnessvalue);


        NotificationDbHelper notificationDbHelper2 = new NotificationDbHelper(this);
        String application = notificationDbHelper2.AppnameGet(id);
        Log.d("inotifyC ", "Attentivness appname" + application);


        Log.d("inotifyC ", "Attentivness successfully inserted" + id + "," + application + ", " + attentivnessvalue);


        Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved---stop");


    }
}
