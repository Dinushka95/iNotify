package com.example.inotify.dbHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.util.ArrayList;


public class MainDbHelp extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AppInotify.db";

    private SharedPreferences.Editor editor009;

    private Context c1;

    public MainDbHelp(Context context) {
        super(context, DATABASE_NAME, null, 1);
        c1=context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        // put all the  create tables in one place

        db.execSQL("create table " + TbNames.ACTIVITY_TABLE + " (ACTIVITY_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT,TYPE TEXT,CONFIDENCE TEXT)");
        db.execSQL("create table " + TbNames.LOCATION_TABLE + " (LOCATION_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT,LOG TEXT,LAT TEXT)");
        db.execSQL("create table " + TbNames.NOTIFICATIONREMOVE_TABLE + " (NOTIFICATIONREMOVE_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT)");
        db.execSQL("create table " + TbNames.NOTIFICATIONVIEWABILITY_TABLE + " (BUSYORNOT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DAY TEXT,TIME TEXT,ACTIVITY TEXT,LOCATION TEXT,BUSYORNOT TEXT)");
        db.execSQL("create table " + TbNames.PROBABILITY_TABLE + " (PROBABILITY_ID TEXT PRIMARY KEY,DAY TEXT,TIME TEXT,ACTIVITY TEXT,VIEWOR INTEGER,NOTOR INTEGER, PROBABILITY DOUBLE)");
        db.execSQL("create table " + TbNames.PROBABILITYQUERYMON_TABLE + " (TIME_SLOT TEXT PRIMARY KEY,VIEWORSUM INTEGER,NOTORSUM INTEGER, PROBABILITYFINAL DOUBLE)");
        db.execSQL("create table " + TbNames.PROBABILITYQUERYTUE_TABLE + " (TIME_SLOT TEXT PRIMARY KEY,VIEWORSUM INTEGER,NOTORSUM INTEGER, PROBABILITYFINAL DOUBLE)");
        db.execSQL("create table " + TbNames.PROBABILITYQUERYWED_TABLE + " (TIME_SLOT TEXT PRIMARY KEY,VIEWORSUM INTEGER,NOTORSUM INTEGER, PROBABILITYFINAL DOUBLE)");
        db.execSQL("create table " + TbNames.PROBABILITYQUERYTHU_TABLE + " (TIME_SLOT TEXT PRIMARY KEY,VIEWORSUM INTEGER,NOTORSUM INTEGER, PROBABILITYFINAL DOUBLE)");
        db.execSQL("create table " + TbNames.PROBABILITYQUERYFRI_TABLE + " (TIME_SLOT TEXT PRIMARY KEY,VIEWORSUM INTEGER,NOTORSUM INTEGER, PROBABILITYFINAL DOUBLE)");
        db.execSQL("create table " + TbNames.PROBABILITYQUERYSAT_TABLE + " (TIME_SLOT TEXT PRIMARY KEY,VIEWORSUM INTEGER,NOTORSUM INTEGER, PROBABILITYFINAL DOUBLE)");
        db.execSQL("create table " + TbNames.PROBABILITYQUERYSUN_TABLE + " (TIME_SLOT TEXT PRIMARY KEY,VIEWORSUM INTEGER,NOTORSUM INTEGER, PROBABILITYFINAL DOUBLE)");

        db.execSQL("create table " + TbNames.SNS_TABLE + " (SNS_ID INTEGER,SNS_DATE TEXT,SNS_DAY TEXT,SNS_TIME TEXT,SNS_BUSYORNOT TEXT," +
                "SNS_ATTENTIVINESS TEXT,SNS_USERCHAACTERISTICS TEXT,SNS_NOTIFICATIONTYPE TEXT,SNS_APPNAME TEXT,SNS_VTIME TEXT)");

        db.execSQL("create table " + TbNames.N_TABLE + " (N_ID INTEGER,N_APPNAME TEXT,N_DATETIME INTEGER)");
        db.execSQL("create table " + TbNames.RINGERMODE_TABLE + "(RM_ID INTEGER PRIMARY KEY AUTOINCREMENT,RM_NOTIFICATIONID TEXT,RM_DAY TEXT,RM_DATE TEXT , RM_TIME TEXT  ,RM_RINGERMODE TEXT)");
        db.execSQL("create table " + TbNames.NOTIFICATIONIMPORTANCE_TABLE + "(NOTIFICATION_IMPORTANCE_ID  INTEGER PRIMARY KEY AUTOINCREMENT , NOTIFICATIONIID TEXT , APPLICATIONNAME TEXT , SEQUENCEVALUE TEXT)");
        db.execSQL("create table " + TbNames.USERATTENTIVNESS_TABLE + "(ID INTEGER PRIMARY KEY , NID TEXT , APPLICATION TEXT , ATTENTIVNESSVALUE DOUBLE) ");
        db.execSQL("create table " + TbNames.ATTENTIVNESSPERAPP_TABLE + "(ID INTEGER PRIMARY KEY , APPLICATION  TEXT , TOTALATTENTIVNESS TEXT , TOTALATTENTIVNESSPERCENTAGE TEXT)");

        db.execSQL("create table " + TbNames.CHARGER_TABLE + " (CHARGERID INTEGER PRIMARY KEY AUTOINCREMENT, POWERONDATE TEXT,POWERONTIME TEXT,POWEROFFDATE TEXT,POWEROFFTIME TEXT,DATE TEXT)");
        db.execSQL("create table " + TbNames.APPLISTCOUNT_TABLE + " (APPLISTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + TbNames.CONTACTCOUNT_TABLE + " (CONTACTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + TbNames.SCREENTIME_TABLE + " (SCREENTIME_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIME TEXT)");
        db.execSQL("create table " + TbNames.CALENDEREVENTCOUNT_TABLE + " (CALENDEREVENTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + TbNames.APPLISTSOCIALMEDIACOUNT_TABLE + " (APPLISTSOCIALMEDIACOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");

        db.execSQL("create table " + TbNames.INOTIFYACTIVEAPPS_TABLE + " ("+TbColNames.INOTIFYACTIVEACTIVEAPPS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.PACKAGENAME+" TEXT,"+TbColNames.STATUS+" TEXT)");

        db.execSQL("create table " + TbNames.SCREENSTATUS_TABLE + " ("+TbColNames.SCREENSTATUS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.NOTIFICATIONID+" TEXT,"+TbColNames.DATE+" TEXT,"+TbColNames.TIMEON+" TEXT,"+TbColNames.TIMEOFF+" TEXT)");

        db.execSQL("create table " + TbNames.TOPAPPS_TABLE + " ("+TbColNames.TOPAPP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.APPNAME+" TEXT,"+TbColNames.APPCATEGORY+" TEXT,"+TbColNames.PACKAGENAME+" Text, "+TbColNames.DATE+" TEXT, "+TbColNames.APPCOLLECTION+" TEXT,"+TbColNames.RANK+" TEXT)");

        db.execSQL("create table " + TbNames.APPLICATIONS_TABLE + " ("+TbColNames.APPLICATION_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.APPNAME+" TEXT,"+TbColNames.APPCATEGORY+" TEXT,"+TbColNames.PACKAGENAME+" Text,"+TbColNames.DATE+" TEXT)");

        db.execSQL("create table " + TbNames.NOTIFICATION_TABLE + " ("+TbColNames.NOTIFICATIONID+" INTEGER,"+TbColNames.DATE+" TEXT,"+TbColNames.TIMERECEVIED+" TEXT,"+TbColNames.TIMESENT+" TEXT,"+TbColNames.TIMEVIEW+" TEXT,"+TbColNames.APPNAME+" TEXT,"+TbColNames.PACKAGENAME+" TEXT,"+TbColNames.SMARTNOTIFICATION+" TEXT)");

        db.execSQL("create table " + TbNames.PROFILE_TABLE + " ("+TbColNames.PROFILE_ID+" INTEGER,"+TbColNames.DATE+" TEXT,"+TbColNames.NAME+" TEXT,"+TbColNames.AGE+" TEXT,"+TbColNames.GENDER+" TEXT,"+TbColNames.OCCUPATION+" TEXT,"+TbColNames.EMAIL+" TEXT,"+TbColNames.PHONE+" TEXT)");

        db.execSQL("create table " + TbNames.APPCOUNT_TABLE + " ("+TbColNames.APPCOUNT_ID+" INTEGER,"+TbColNames.DATE+" TEXT,"+TbColNames.SOCIALAPPCOUNT+" TEXT,"+TbColNames.GAMINGAPPCOUNT+" TEXT,"+TbColNames.EDUCATIONAPPCOUNT+" TEXT,"+TbColNames.DATINGAPPCOUNT+" TEXT,"+TbColNames.MUSICVIDEOAPPCOUNT+" TEXT,"+TbColNames.COMMUNICATIONAPPCOUNT+" TEXT)");

        db.execSQL("create table " + TbNames.APPUSAGE_TABLE + " ("+TbColNames.APPUSAGEID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.TIME+" TEXT,"+TbColNames.PACKAGENAME+" TEXT,"+TbColNames.APPNAME+" TEXT,"+TbColNames.APPCATEGORY+" TEXT,"+TbColNames.USAGETIME+" TEXT)");

        db.execSQL("create table " + TbNames.TOPAPPSCOUNT_TABLE + " ("+TbColNames.TOPAPPCOUNT_ID+" INTEGER,"+TbColNames.DATE+" TEXT,"+TbColNames.SOCIALAPPCOUNT+" TEXT,"+TbColNames.GAMINGAPPCOUNT+" TEXT,"+TbColNames.EDUCATIONAPPCOUNT+" TEXT,"+TbColNames.DATINGAPPCOUNT+" TEXT,"+TbColNames.MUSICVIDEOAPPCOUNT+" TEXT,"+TbColNames.COMMUNICATIONAPPCOUNT+" TEXT)");

        db.execSQL("create table " + TbNames.CALLDURATION_TABLE + "("+TbColNames.CALLDURATION_ID+"INTEGER,"+TbColNames.DATE+" TEXT,"+TbColNames.TIME+" TEXT)");

        db.execSQL("create table " + TbNames.ATTRIBUTECOUNT_TABLE + " ("+TbColNames.ATTRIBUTECOUNT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.APPCOUNT+" TEXT,"+TbColNames.SCREENONTIMECOUNT+" TEXT,"+TbColNames.CHARGINGCOUNT+" TEXT,"+TbColNames.CONTACTCOUNT+" TEXT)");























        db.execSQL("insert into location_table(LOCATION_ID,DATE,DAY,TIME,LOG,LAT)values(1,20190216,'Saturday',2345,80.9,78.8);");
        db.execSQL("insert into location_table(LOCATION_ID,DATE,DAY,TIME,LOG,LAT)values(2,20190216,'Saturday',2345,80.9,78.8);");

        db.execSQL("insert into activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(1,20190216,'Saturday',2345,1,100);");
        db.execSQL("insert into activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(2,20190216,'Saturday',2345,2,100);");
        db.execSQL("insert into activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(3,20190216,'Saturday',2345,3,100);");
        db.execSQL("insert into activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(4,20190216,'Saturday',2345,4,100);");
        db.execSQL("insert into activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(5,20190216,'Saturday',2345,5,100);");
        db.execSQL("insert into activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(6,20190216,'Saturday',2345,6,100);");
        db.execSQL("insert into activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(7,20190216,'Saturday',2345,7,100);");
        db.execSQL("insert into activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(8,20190216,'Saturday',2345,8,100);");


        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182419,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.example.dinu.testa',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182429,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.example.dinu.testb',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182439,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.example.dinu.testc',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182449,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.example.dinu.testd',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182459,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.google.android.apps.messaging',8000);");


        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('facebook','social','com.google.android.apps.facebook');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('whatsapp','social','com.google.android.apps.whatsapp');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('hungamamusic','musicvideo','com.google.android.apps.hungamamusic');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('tinder','dating','com.google.android.apps.tinder');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('badoo','dating','com.google.android.apps.badoo');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('netflix','entertainment','com.google.android.apps.netflix');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('coverfire','gaming','com.google.android.apps.coverfire');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('edx','education','com.google.android.apps.edx');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" ( APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('fruitninja','gaming','com.google.android.apps.fruitninja');");
        db.execSQL("insert into "+ TbNames.TOPAPPS_TABLE+" (APPNAME,APPCATEGORY,"+TbColNames.PACKAGENAME+")values('call','communication','com.google.android.apps.call');");



        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('facebook','social','com.google.android.apps.facebook');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('whatsapp','social','com.google.android.apps.whatsapp');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('hungamamusic','musicvideo','com.google.android.apps.hungamamusic');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('tinder','dating','com.google.android.apps.tinder');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('badoo','dating','com.google.android.apps.badoo');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('netflix','entertainment','com.google.android.apps.netflix');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('coverfire','gaming','com.google.android.apps.coverfire');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('edx','education','com.google.android.apps.edx');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('fruitninja','gaming','com.google.android.apps.fruitninja');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('clashofclans','gaming','com.google.android.apps.clashofclans');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('game2','gaming','com.google.android.apps.game2');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('Message','communication','com.google.android.apps.Message');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('chatMe','communication','com.google.android.apps.chatMe');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('call','communication','com.google.android.apps.call');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('telephony','communication','com.android.providers.telephony');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('googlequicksearchbox','communication','com.google.android.googlequicksearchbox');");
        db.execSQL("insert into "+ TbNames.APPLICATIONS_TABLE +" (APPNAME,APPCATEGORY,PACKAGENAME)values('calendar','communication','com.android.providers.calendar');");
        //TODO-- please implement temperately insert statements or Api to get application categories


        db.execSQL("insert into ringermode_table(RM_ID ,RM_NOTIFICATIONID ,RM_DAY ,RM_DATE  , RM_TIME   ,RM_RINGERMODE )values(1 ,20190304155042 ,20190304 ,'Monday',1550 ,'normal'  );");


        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190216,5,6,2,1,3,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190217,5,6,2,2,3,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190218,6,5,2,3,4,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190219,6,6,4,3,4,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190220,7,6,4,3,3,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190221,8,7,4,3,3,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190222,8,7,5,3,2,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190223,8,7,3,3,2,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190224,9,8,4,3,2,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190225,9,8,5,3,4,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190226,9,9,5,3,4,1);");
        db.execSQL("insert into "+TbNames.TOPAPPSCOUNT_TABLE+"(DATE,SOCIALAPPCOUNT,GAMINGAPPCOUNT,MUSICVIDEOAPPCOUNT,COMMUNICATIONAPPCOUNT,EDUCATIONAPPCOUNT,DATINGAPPCOUNT)values(20190227,9,6,5,3,4,1);");


        //PROBABILITYQUERY_TABLE

        NotificationViewabilityDbHelper timeS = new NotificationViewabilityDbHelper(c1);
        ArrayList <String> TimeSlots = timeS.genarateTimeSlots();
        for(int i = 0 ; i < 144 ; i++  ){
            db.execSQL("insert into PROBABILITYQUERYMON_TABLE(TIME_SLOT)values('"+ TimeSlots.get(i)+"');");
            db.execSQL("insert into PROBABILITYQUERYTUE_TABLE(TIME_SLOT)values('"+ TimeSlots.get(i)+"');");
            db.execSQL("insert into PROBABILITYQUERYWED_TABLE(TIME_SLOT)values('"+ TimeSlots.get(i)+"');");
            db.execSQL("insert into PROBABILITYQUERYTHU_TABLE(TIME_SLOT)values('"+ TimeSlots.get(i)+"');");
            db.execSQL("insert into PROBABILITYQUERYFRI_TABLE(TIME_SLOT)values('"+ TimeSlots.get(i)+"');");
            db.execSQL("insert into PROBABILITYQUERYSAT_TABLE(TIME_SLOT)values('"+ TimeSlots.get(i)+"');");
            db.execSQL("insert into PROBABILITYQUERYSUN_TABLE(TIME_SLOT)values('"+ TimeSlots.get(i)+"');");

        }
        //Log.d("DBoncreate", "onCreate: " +TimeSlots.get(0));




       //db.execSQL("insert into PROBABILITYQUERY_TABLE(TIME_SLOT,VIEWORSUM,NOTORSUM,PROBABILITYFINAL)values(timeslot,0,0,0);");
    }
/*
    important code
    private void bulkInsertOneHundredRecords() {
        String sql = "INSERT INTO "+ SAMPLE_TABLE_NAME +" VALUES (?,?,?);";
        SQLiteStatement statement = sampleDB.compileStatement(sql);
        sampleDB.beginTransaction();
        for (int i = 0; i<100; i++) {
            statement.clearBindings();
            statement.bindLong(1, i);
            statement.bindLong(2, i);
            statement.bindLong(3, i*i);
            statement.execute();
        }
        sampleDB.setTransactionSuccessful();
        sampleDB.endTransaction();
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
