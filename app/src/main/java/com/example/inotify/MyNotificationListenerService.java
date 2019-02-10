package com.example.inotify;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.MainActivity.accuracy;
import static com.example.inotify.MainActivity.home_Lat;
import static com.example.inotify.MainActivity.home_Log;
import static com.example.inotify.MainActivity.work_Lat;
import static com.example.inotify.MainActivity.work_Log;

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

        Log.d("inotify", "Main-MyNotificationListenerService--preLoop-Ticker---"+ticker );

        if ((sbn.getPackageName().equals("com.example.dinu.testa") ||
                sbn.getPackageName().equals("com.example.dinu.testb") ||
                sbn.getPackageName().equals("com.example.dinu.testc") ||
                sbn.getPackageName().equals("com.example.dinu.testd"))) {


            pack = sbn.getPackageName();
            nid = sbn.getId();

            Bundle extras = sbn.getNotification().extras;
            title = extras.getString("android.title");
            text = extras.getCharSequence("android.text").toString();

            Log.d("inotify", "Main-MyNotificationListenerService--package name---"+pack);
            Log.d("inotify", "Main-MyNotificationListenerService--nid---"+nid);
            Log.d("inotify", "Main-MyNotificationListenerService--title---"+title);
            Log.d("inotify", "Main-MyNotificationListenerService--text---"+text);

            //remove notification
            cancelNotification(sbn.getKey());
            Log.d("inotify", "Main-MyNotificationListenerService--Cancel original Notification---");

            Intent LaunchIntent = null;
            PackageManager pm = this.getPackageManager();
            String apppack = pack;

//==========================================================================================================================
            //////////////////////////////////////////////////////////////////////////////////////////////////
            // prashan


            SharedPreferences prefs = this.getSharedPreferences("activityrecognition", MODE_PRIVATE);
            String currentactivity=prefs.getString("activity", null);

            Pra_SqlLiteDbHelper praSqlLiteDbHelper1 = new Pra_SqlLiteDbHelper(this);
            ArrayList<Double> loc= praSqlLiteDbHelper1.pra_location_get();
            praSqlLiteDbHelper1.close();

            double log=loc.get(0);
            double lat = loc.get(1);
            double distanceHome = Math.hypot(log - home_Log, lat-home_Lat);
            double distanceWork = Math.hypot(log - work_Log, lat-work_Lat);

            String currentlocation="unknown";

            if(distanceHome<accuracy){
                currentlocation="home";
            }
            if(distanceWork<accuracy){
                currentlocation="work";
            }

            MainBusyOrNotPredict mainBusyOrNotPredict =new MainBusyOrNotPredict();
            String busyornot = mainBusyOrNotPredict.GetNewPrediction(this,currentactivity,currentlocation);

            Log.d("inotify", "Main-MyNotificationListenerService--currentactivity---"+currentactivity );
            Log.d("inotify", "Main-MyNotificationListenerService--currentlocation---"+currentlocation );

            /////////////////////////////////////////////////////////////////////////////////////////////////
            // chaya
            MainAttentiviness mainAttentiviness = new MainAttentiviness();
            String attentiviness = mainAttentiviness.getFinalAttentiviness(this,apppack);
            //////////////////////////////////////////////////////////////////////////////////////////////////
            // mitha
            MainUsercharacteristics mainUsercharacteristics = new MainUsercharacteristics();
            String userCharacteistics = mainUsercharacteristics.getUsercharacteristics(this);
            //////////////////////////////////////////////////////////////////////////////////////////////////
            Log.d("inotify", "Main-MyNotificationListenerService--FinalOutput-BusyOrNotPredict---"+busyornot );
            Log.d("inotify", "Main-MyNotificationListenerService--FinalOutput-Attentiviness---"+attentiviness );
            Log.d("inotify", "Main-MyNotificationListenerService--FinalOutput-Usercharacteristics---"+userCharacteistics );
            /////////////////////////////////////////////////////////////////////////////////////////////////

            /////////////////////////////////////////////////////////////////////////////////////////////////
            // dinu
            // run ML prediction function and get predicted notification view time
            Din_SNSModel snsModel = new Din_SNSModel();

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                    (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                    Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
            String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

            String cday = "";
            if (day.equals("Monday")) {
                cday = "1";
            }
            if (day.equals("Tuesday")) {
                cday = "2";
            }
            if (day.equals("Wednesday")) {
                cday = "3";
            }
            if (day.equals("Thursday")) {
                cday = "4";
            }
            if (day.equals("Friday")) {
                cday = "5";
            }
            if (day.equals("Saturday")) {
                cday = "6";
            }
            if (day.equals("Sunday")) {
                cday = "7";
            }
            snsModel.setDay(cday);

            snsModel.setTime( new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date()));


            String cbusyornot = "";
            if (busyornot.equals("Busy")) {
                cbusyornot = "1";
            }
            if (busyornot.equals("NotBusy")) {
                cbusyornot = "1";
            }
            snsModel.setBusyornot(cbusyornot);


            String cattentiviness = "";
            if (attentiviness.equals("low")) {
                cattentiviness = "1";
            }
            if (attentiviness.equals("medium")) {
                cattentiviness = "2";
            }
            if (attentiviness.equals("high")) {
                cattentiviness = "3";
            }
            snsModel.setAttentiviness(cattentiviness);

            String userchaacteristics = userCharacteistics;
            String cuserchaacteristics = "";
            if (userchaacteristics.equals("social")) {
                cuserchaacteristics = "1";
            }
            if (userchaacteristics.equals("professional")) {
                cuserchaacteristics = "2";
            }
            if (userchaacteristics.equals("friendliness")) {
                cuserchaacteristics = "3";
            }
            if (userchaacteristics.equals("gaming")) {
                cuserchaacteristics = "4";
            }
            if (userchaacteristics.equals("oldtechnology")) {
                cuserchaacteristics = "5";
            }
            snsModel.setUserchaacteristics(cuserchaacteristics);

            String notificationtype = "Mobile";
            String cnotificationtype = "";
            if (notificationtype.equals("Mobile")) {
                cnotificationtype = "1";
            }
            snsModel.setNotificationtype(cnotificationtype);

            String appname =apppack;
            String cappname = "";
            if (appname.equals("com.example.dinu.testa")) {
                cappname = "1";
            }
            if (appname.equals("com.example.dinu.testb")) {
                cappname = "2";
            }
            if (appname.equals("com.example.dinu.testc")) {
                cappname = "3";
            }
            if (appname.equals("com.example.dinu.testd")) {
                cappname = "4";
            }
            snsModel.setAppname(cappname);

            MainSmartNotificationSystem mainSmartNotificationSystem = new MainSmartNotificationSystem(this,snsModel);

            String vtimes = mainSmartNotificationSystem.getPrediction();
            String tem1 =vtimes.replaceAll("[\\[\\](){}]","");
            Log.d("inotify", "Main-MyNotificationListenerService--FinalOutput-SmartNotificationSystem-predicted time---"+tem1 );
            double vtimed = Double.valueOf(tem1);
            Log.d("inotify", "Main-MyNotificationListenerService--FinalOutput-SmartNotificationSystem-predicted time---"+vtimed );

            //////////////////////////////////////////////////////////////////////////////////////////////////

           // get the old data
           // get current settting
           // update the json
            // api request
            // read respone

 /*         if(respone>acurracy){
                not sent get delay;
            }else {
                semd notification
            }
*/
            boolean sendornotsend;
            if (vtimed<10){sendornotsend = true;}
            else {sendornotsend = false;
            // run delay function
            }




            if (sendornotsend) {

                //dave to db
                Din_SqlLiteDbHelper din_sqlLiteDbHelper = new Din_SqlLiteDbHelper(this);
                String id = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                din_sqlLiteDbHelper.saveData(id,busyornot,attentiviness,userCharacteistics,"mobile",apppack);
                din_sqlLiteDbHelper.close();


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

               // String dateTime = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault()).format(new Date());

                NotificationCompat.Builder mBuilder = new NotificationCompat
                        .Builder(this, "inotifyapp")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(title + "-iNotify")
                        .setContentText(text)
                        .setTicker(id)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pIntent);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(nid, mBuilder.build());

                Cha_SqlLiteDbHelper chaSqlLiteDbHelper = new Cha_SqlLiteDbHelper(this);
                chaSqlLiteDbHelper.Ninsert(Long.valueOf(id) ,sbn.getPackageName(), Long.valueOf(id));
                chaSqlLiteDbHelper.close();

            }
            Log.d("inotify", "Main-MyNotificationListenerService--un smart notification--stop----" );

        }



    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

        Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved---start" );


        //chaya
        if (sbn.getPackageName().equals("com.example.inotify")) {

            Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved--input notification name-"+ sbn.getPackageName() );

            int totalnotificationinlist = 0;

            StatusBarNotification[] notificationManager1 = getActiveNotifications();
            for (StatusBarNotification notification : notificationManager1) {
               // Log.d("cdap", " ---onNotificationRemoved--------"+notification.getPackageName());
                if(notification.getPackageName().equals("com.example.inotify")){
                    totalnotificationinlist = totalnotificationinlist +1;
                }
            }
         //   Log.d("cdap", " ---onNotificationRemoved--------total notifications-----" + totalnotificationinlist);
            Cha_SqlLiteDbHelper chaSqlLiteDbHelper = new Cha_SqlLiteDbHelper(this);
            String packageName = chaSqlLiteDbHelper.NgetValue(sbn.getNotification().tickerText.toString());
            chaSqlLiteDbHelper.NIupdate(packageName, totalnotificationinlist);
            chaSqlLiteDbHelper.close();


            //dinu
            String oldtime = sbn.getNotification().tickerText.toString();
            String newtime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
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

            Din_SqlLiteDbHelper din_sqlLiteDbHelper = new Din_SqlLiteDbHelper(this);
            din_sqlLiteDbHelper.updateData(oldtime,String.valueOf(difference));
            din_sqlLiteDbHelper.close();

        }


        // for prashan
        Pra_SqlLiteDbHelper praSqlLiteDbHelper = new Pra_SqlLiteDbHelper(this);
        praSqlLiteDbHelper.pra_notificationRemove_insert();
        praSqlLiteDbHelper.close();




        Log.d("inotify", "Main-MyNotificationListenerService----onNotificationRemoved---stop" );
    }
}
