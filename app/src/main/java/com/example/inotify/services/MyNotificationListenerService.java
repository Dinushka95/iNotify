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

import com.annimon.stream.Stream;
import com.example.inotify.R;
import com.example.inotify.dbHelpers.AttentivnessPerAppDbHelper;
import com.example.inotify.dbHelpers.NotificationViewabilityDbHelper;
import com.example.inotify.dbHelpers.NotificationDbHelper;
import com.example.inotify.dbHelpers.NotificationImportnaceDbHelper;
import com.example.inotify.dbHelpers.RingerModeDbHelper;
import com.example.inotify.dbHelpers.ScreenStatusDbHelper;
import com.example.inotify.dbHelpers.SmartNotificationDbHelper;
import com.example.inotify.dbHelpers.UserAttentivnessDbHelper;
import com.example.inotify.helpers.FeedbackYesIntent;
import com.example.inotify.helpers.INotifyActiveAppsHelper;
import com.example.inotify.helpers.MainSmartNotificationSystem;
import com.example.inotify.helpers.NotificationHelper;
import com.example.inotify.helpers.RingerModeHelper;
import com.example.inotify.helpers.ScreenStatusHelper;
import com.example.inotify.helpers.MainUserAttentivness;
import com.example.inotify.models.NotificationModel;
import com.example.inotify.models.SNSModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class MyNotificationListenerService extends NotificationListenerService {



    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        String timeSent = "";
        String appPackageName = "";
        String appName = "";
        int nid;
        String ticker = "";
        String title = "";
        String text = "";
        double accuracy = 0.0;
        boolean iNotifyActiveApp = false;
        boolean sendornotsend = false;

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String id = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.getDefault()).format(new Date());
        String timeRecieved = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());


        Intent LaunchIntent = null;
        PackageManager pm = null;

        if (sbn.getNotification().tickerText != null) {
            ticker = sbn.getNotification().tickerText.toString();
        }

        Log.d("inotify", "Main-MyNotificationListenerService--preLoop-Ticker---" + ticker);
        Log.d("inotify", "Main-MyNotificationListenerService--packageName---" + sbn.getPackageName());


        INotifyActiveAppsHelper iNotifyActiveAppsHelperbool = new INotifyActiveAppsHelper(this);
        //check is exist
        boolean iNotifyActiveAppsExis = iNotifyActiveAppsHelperbool.isExisINotifyActiveApps(sbn.getPackageName());

        if(!iNotifyActiveAppsExis&&!sbn.getPackageName().equals("com.example.inotify")){
            INotifyActiveAppsHelper iNotifyActiveAppsHelper1 = new INotifyActiveAppsHelper(this);
            // put the app in a actvity notification list
            iNotifyActiveAppsHelper1.setNewActiveApp(sbn.getPackageName());
        }

        INotifyActiveAppsHelper iNotifyActiveAppsHelper = new INotifyActiveAppsHelper(this);
        //get all active notifications
        List<String> iNotifyActiveAppsList = iNotifyActiveAppsHelper.getINotifyActiveApps();


        // check if the notification you got is in the inotify active list
        iNotifyActiveApp = Stream.of(iNotifyActiveAppsList).anyMatch(v -> v.equals(sbn.getPackageName()));

        if (iNotifyActiveApp) {


            appPackageName = sbn.getPackageName();
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

            //insert a row to probability table when receive a notification
            NotificationViewabilityDbHelper pratest = new NotificationViewabilityDbHelper(this);
            pratest.probability_insert(id, 0);
            pratest.close();

            //Chaya
            //call the isPhoneLowckedOrNot method here
            ScreenStatusHelper screenStatusHelper = new ScreenStatusHelper();
            boolean screenstatus = screenStatusHelper.isPhoneLockedOrNot(this);
            Log.d("inotifyC ", "ScreenStatus On Notification recive" + screenstatus);
            if (!screenstatus) {
                //Save to screen on table
                ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(this);
                screenStatusDbHelper.screenOnWithNotificationInsert(id);
                screenStatusDbHelper.close();
                Log.d("inotifyC", "SCreen status Saved");
            } else {
                ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(this);
                screenStatusDbHelper.screenOffWithNotificationInsert(id);
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


            // Smart Notifaction
            Log.d("inotify", "Main-MyNotificationListenerService--SmartNotificationSystem---" );
            NotificationViewabilityDbHelper notificationViewabilityDbHelper = new NotificationViewabilityDbHelper(this);
            String viewbillityProbability = String.valueOf(notificationViewabilityDbHelper.GetViewability(Calendar.getInstance().getTime()));
            Log.e("inotify", "Main-MyNotificationListenerService--SmartNotificationSystem---viewbillityProbability---"+viewbillityProbability );

            AttentivnessPerAppDbHelper attentivnessPerAppDbHelper = new AttentivnessPerAppDbHelper(this);
            String attentivenessPerApp = attentivnessPerAppDbHelper.AttentivnessperAppGet(appPackageName);
            Log.e("inotify", "Main-MyNotificationListenerService--SmartNotificationSystem---attentivenessPerApp---"+attentivenessPerApp );


            //     SNSModel snsModel = new SNSModel(date,timeRecieved,viewbillityProbability,attentivenessPerApp,"null","Mobile",appName);
/*            SNSModel snsModel = new SNSModel(date,timeRecieved,"","","null","null",appName);
            MainSmartNotificationSystem mainSmartNotificationSystem = new MainSmartNotificationSystem(this,snsModel);
            String vtimes =mainSmartNotificationSystem.getPrediction();

            String tem1 =vtimes.replaceAll("[\\[\\](){}]","");
            double predictionTime = Double.valueOf(tem1);
            Log.d("inotify", "Main-MyNotificationListenerService--FinalOutput-SmartNotificationSystem-predicted time---"+predictionTime );


            //TODO- get accuracy
            accuracy=30;
            if (predictionTime<accuracy) {
                sendornotsend = true;
            }*/
            sendornotsend = true;
            if (sendornotsend) {
                //send notification

                pm = this.getPackageManager();
                // get the original app's notifications intent
                try {
                    if (pm != null) {
                        ApplicationInfo app = this.getPackageManager().getApplicationInfo(appPackageName, 0);
                        appName = (String) pm.getApplicationLabel(app);
                        LaunchIntent = pm.getLaunchIntentForPackage(appPackageName);
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

                //start creating a new notification
                NotificationManager notifManager = null;
                final int NOTIFY_ID = 0;
                Intent intent;
                PendingIntent pendingIntent;
                NotificationCompat.Builder builder;


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
//                            .addExtras(extrasid)
                            .setDefaults(Notification.DEFAULT_ALL).setAutoCancel(true).addAction(R.drawable.common_google_signin_btn_icon_light, "Yes", pFeedbackYes).addAction(R.drawable.common_google_signin_btn_icon_light, "No", pFeedbackNo).setContentIntent(pIntent).setTicker(id).setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    timeSent = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());


                } else {
                    builder = new NotificationCompat.Builder(this, appName);
                    intent = LaunchIntent;
                    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
                    builder.setContentTitle(title + "-iNotify")
                            .setSmallIcon(android.R.drawable.ic_popup_reminder)
                            .setContentText(text) // required
                            .setTicker(id)
                            .setDefaults(Notification.DEFAULT_ALL).setAutoCancel(true).addAction(R.drawable.common_google_signin_btn_icon_light, "Yes", pFeedbackYes).addAction(R.drawable.common_google_signin_btn_icon_light, "No", pFeedbackNo).setContentIntent(pIntent).setTicker(id).setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400}).setPriority(Notification.PRIORITY_HIGH);
                    timeSent = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());
                }
                Random ranvValue = new Random();
                int n = ranvValue.nextInt(99);
                Notification notification = builder.build();
                notifManager.notify(n, notification);


                //PRASHAN
                // Notification insert
                Log.d("inotify", "Main-MyNotificationListenerService--onNotificationPosted: Notification  inserted to notificcation table");
                NotificationHelper notificationHelper = new NotificationHelper(getBaseContext());
                notificationHelper.insert(new NotificationModel(id, date, timeRecieved, timeSent, "", appName, appPackageName,title,text, "1"));


                //Smart Notification
              //  SmartNotificationDbHelper smartNotificationDbHelper = new SmartNotificationDbHelper(this);
              //  smartNotificationDbHelper.saveData(id,viewbillityProbability,"","null","Mobile",appName);

            }
        }
        Log.d("inotify", "Main-MyNotificationListenerService--smart notification--stop----");
    }


    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--input notification name-" + sbn.getPackageName());
        if (sbn.getPackageName().equals("com.example.inotify")) {
            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved---start");

            String ticker = sbn.getNotification().tickerText.toString();
            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--ticker" + ticker);

            String time = new SimpleDateFormat("HHmmssSS", Locale.getDefault()).format(new Date());

            NotificationDbHelper notificationDbHelper1 = new NotificationDbHelper(this);
            notificationDbHelper1.updateNotificationViewTime(ticker, time);
            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--Updated Succcessfully updateNotificationViewTime ==" + ticker + " " + time);
            notificationDbHelper1.close();

            NotificationViewabilityDbHelper notificationViewabilityDbHelper = new NotificationViewabilityDbHelper(this);
            notificationViewabilityDbHelper.probability_Update(ticker);
            notificationViewabilityDbHelper.close();


            int totalnotificationinlist = 0;
            StatusBarNotification[] notificationManager1 = getActiveNotifications();

            for (StatusBarNotification notification : notificationManager1) {
                if (sbn.getPackageName().equals("com.example.inotify")) {
                    totalnotificationinlist = totalnotificationinlist + 1;

                }
            }
            int notificationTotal = totalnotificationinlist;

            NotificationDbHelper notificationDbHelper = new NotificationDbHelper(this);
            String Appname = notificationDbHelper.AppnameGet(ticker);
            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--Appname " +Appname);
            String notificationRecivedTime = notificationDbHelper.recivedTimeGet(ticker);
            String notificationViwedTime = notificationDbHelper.viewTimeGet(ticker);
            notificationDbHelper.close();

            NotificationImportnaceDbHelper notificationImportnaceDbHelper = new NotificationImportnaceDbHelper(this);
            notificationImportnaceDbHelper.NotificationImportnaceInsert(ticker, Appname, totalnotificationinlist);
            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--Notification importnace table saved");
            String Seqence = notificationImportnaceDbHelper.NotificationImportnaceGet(ticker);

            RingerModeDbHelper ringerModeDbHelper = new RingerModeDbHelper(this);
            String Ringermode = ringerModeDbHelper.RingerModeGet(ticker);

            ScreenStatusDbHelper screenStatusDbHelper = new ScreenStatusDbHelper(this);
            String a = screenStatusDbHelper.checkScreenOnAdaptability(ticker);
            String b = screenStatusDbHelper.checkScreenOffAdaptability(ticker);

            String screenStatus1 = screenStatusDbHelper.checkAvaulability(ticker);
            Log.d("inotify", "screenStatus1" + screenStatus1);

            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved-- Data to clculate attentivness = " + ticker + " " + Ringermode + " " + screenStatus1 + " " + notificationViwedTime + " " + notificationRecivedTime + " " + Seqence + " " + notificationTotal);
            MainUserAttentivness mainUserAttentivness = new MainUserAttentivness(this);
            //double attentivnessvalue = mainUserAttentivness.calculateAttentivness(ticker, screenStatus1, Ringermode, notificationViwedTime, notificationRecivedTime, Seqence, notificationTotal);
          ///(^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^) (^_^)(^_^) (^_^) (^_^) (^_^) (^_^) (^_^)
            double attentivnessvalue = mainUserAttentivness.CalcAtten(ticker,Appname,screenStatus1,Ringermode,notificationViwedTime,notificationRecivedTime,Seqence,notificationTotal);



            UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(this);
            userAttentivnessDbHelper.UserAttentivnessInsert(ticker, Appname, attentivnessvalue);
            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--Attentivness inserted successfully  " + ticker + "  " + Appname + "  " + attentivnessvalue);


            AttentivnessPerAppDbHelper attentivnessPerAppDbHelper= new AttentivnessPerAppDbHelper(this);
            attentivnessPerAppDbHelper.calculateTotalAttentivness(ticker, Appname);
            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--Total Attentivness succcessfully added");

            //Smart Notification update with time
/*            String oldtime = sbn.getNotification().tickerText.toString();
            String newtime = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.getDefault()).format(new Date());

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
            Date date1 = null;
            try {
                date1 = format.parse(oldtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date2 = null;
            try {
                date2 = format.parse(newtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long difference = date2.getTime() - date1.getTime();

            SmartNotificationDbHelper smartNotificationDbHelper = new SmartNotificationDbHelper(this);
            smartNotificationDbHelper.updateData(ticker,String.valueOf(difference));*/

            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved---stop");
        }


    }
}





