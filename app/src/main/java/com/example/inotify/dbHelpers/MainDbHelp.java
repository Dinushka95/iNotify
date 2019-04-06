package com.example.inotify.dbHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.util.ArrayList;

import static com.example.inotify.configs.AppUserConfigs.DATABASE_NAME;


public class MainDbHelp extends SQLiteOpenHelper {

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

        db.execSQL("create table " + TbNames.SNS_TABLE + " ("+TbColNames.SNS_ID+","+TbColNames.SNS_DATE+","+TbColNames.SNS_DAY+","+TbColNames.SNS_TIME+","+TbColNames.SNS_BUSYORNOT+","+TbColNames.SNS_ATTENTIVINESS+","+TbColNames.SNS_USERCHAACTERISTICS +","+TbColNames.SNS_NOTIFICATIONTYPE+","+TbColNames.SNS_APPNAME+","+TbColNames.SNS_VTIME+")");

        db.execSQL("create table " + TbNames.N_TABLE + " (N_ID INTEGER,N_APPNAME TEXT,N_DATETIME INTEGER)");
        db.execSQL("create table " + TbNames.RINGERMODE_TABLE + "(RM_ID INTEGER PRIMARY KEY AUTOINCREMENT,RM_NOTIFICATIONID TEXT,RM_DAY TEXT,RM_DATE TEXT , RM_TIME TEXT  ,RM_RINGERMODE TEXT)");
        db.execSQL("create table " + TbNames.NOTIFICATIONIMPORTANCE_TABLE + "(NOTIFICATION_IMPORTANCE_ID  INTEGER PRIMARY KEY AUTOINCREMENT , NOTIFICATIONIID TEXT , APPLICATIONNAME TEXT , SEQUENCEVALUE TEXT)");
        db.execSQL("create table " + TbNames.USERATTENTIVNESS_TABLE + "(ID INTEGER PRIMARY KEY , NID TEXT , APPLICATION TEXT , ATTENTIVNESSVALUE DOUBLE) ");
        db.execSQL("create table " + TbNames.ATTENTIVNESSPERAPP_TABLE + "(ID INTEGER PRIMARY KEY , APPLICATION  TEXT , TOTALATTENTIVNESS TEXT , TOTALATTENTIVNESSPERCENTAGE TEXT)");

        db.execSQL("create table " + TbNames.CHARGER_TABLE + " (CHARGERID INTEGER PRIMARY KEY AUTOINCREMENT, POWERONDATE TEXT,POWERONTIME TEXT,POWEROFFDATE TEXT,POWEROFFTIME TEXT,DATE TEXT)");
        db.execSQL("create table " + TbNames.APPLISTCOUNT_TABLE + " (APPLISTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + TbNames.CONTACTCOUNT_TABLE + " ("+TbColNames.CONTACTCOUNT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.CONTACTCOUNT+" TEXT)");
        db.execSQL("create table " + TbNames.SCREENTIME_TABLE + " (SCREENTIME_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIME TEXT)");
        db.execSQL("create table " + TbNames.CALENDEREVENTCOUNT_TABLE + " (CALENDEREVENTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + TbNames.APPLISTSOCIALMEDIACOUNT_TABLE + " (APPLISTSOCIALMEDIACOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");

        db.execSQL("create table " + TbNames.INOTIFYACTIVEAPPS_TABLE + " ("+TbColNames.INOTIFYACTIVEACTIVEAPPS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.PACKAGENAME+" TEXT,"+TbColNames.APPNAME+" Text," +TbColNames.STATUS+" TEXT)");

        db.execSQL("create table " + TbNames.SCREENSTATUS_TABLE + " ("+TbColNames.SCREENSTATUS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.NOTIFICATIONID+" TEXT,"+TbColNames.DATE+" TEXT,"+TbColNames.TIMEON+" TEXT,"+TbColNames.TIMEOFF+" TEXT)");

        db.execSQL("create table " + TbNames.TOPAPPS_TABLE + " ("+TbColNames.TOPAPP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+","+TbColNames.APPNAME+" TEXT,"+TbColNames.APPCATEGORY+" TEXT,"+TbColNames.PACKAGENAME+" Text, "+TbColNames.DATECREATED+" TEXT, "+TbColNames.APPCOLLECTION+" TEXT,"+TbColNames.RANK+" TEXT)");

        db.execSQL("create table " + TbNames.APPLICATIONS_TABLE + " ("+TbColNames.APPLICATION_ID+"  INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.APPNAME+" TEXT,"+TbColNames.APPCATEGORY+" TEXT,"+TbColNames.PACKAGENAME+" Text)");

        db.execSQL("create table " + TbNames.NOTIFICATION_TABLE + " ("+TbColNames.NOTIFICATIONID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.TIMERECEVIED+" TEXT,"+TbColNames.TIMESENT+" TEXT,"+TbColNames.TIMEVIEW+" TEXT,"+TbColNames.APPNAME+" TEXT,"+TbColNames.PACKAGENAME+" TEXT,"+TbColNames.TITLE+" TEXT,"+TbColNames.CONTENT+" TEXT,"+TbColNames.SMARTNOTIFICATION+" TEXT)");

        db.execSQL("create table " + TbNames.PROFILE_TABLE + " ("+TbColNames.PROFILE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.NAME+" TEXT,"+TbColNames.AGE+" TEXT,"+TbColNames.GENDER+" TEXT,"+TbColNames.OCCUPATION+" TEXT,"+TbColNames.EMAIL+" TEXT,"+TbColNames.PHONE+" TEXT)");

        db.execSQL("create table " + TbNames.APPCOUNT_TABLE + " ("+TbColNames.APPCOUNT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.SOCIALAPPCOUNT+" TEXT,"+TbColNames.GAMINGAPPCOUNT+" TEXT,"+TbColNames.EDUCATIONAPPCOUNT+" TEXT,"+TbColNames.DATINGAPPCOUNT+" TEXT,"+TbColNames.MUSICVIDEOAPPCOUNT+" TEXT,"+TbColNames.COMMUNICATIONAPPCOUNT+" TEXT)");

        db.execSQL("create table " + TbNames.APPUSAGE_TABLE + " ("+TbColNames.APPUSAGEID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.TIME+" TEXT,"+TbColNames.PACKAGENAME+" TEXT,"+TbColNames.APPNAME+" TEXT,"+TbColNames.APPCATEGORY+" TEXT,"+TbColNames.USAGETIME+" TEXT)");

        db.execSQL("create table " + TbNames.TOPAPPSCOUNT_TABLE + " ("+TbColNames.TOPAPPCOUNT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.SOCIALAPPCOUNT+" TEXT,"+TbColNames.GAMINGAPPCOUNT+" TEXT,"+TbColNames.EDUCATIONAPPCOUNT+" TEXT,"+TbColNames.DATINGAPPCOUNT+" TEXT,"+TbColNames.MUSICVIDEOAPPCOUNT+" TEXT,"+TbColNames.COMMUNICATIONAPPCOUNT+" TEXT)");

        db.execSQL("create table " + TbNames.CALLDURATION_TABLE + "("+TbColNames.CALLDURATION_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.DURATION+" TEXT)");

        db.execSQL("create table " + TbNames.ATTRIBUTECOUNT_TABLE + " ("+TbColNames.ATTRIBUTECOUNT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.APPCOUNT+" TEXT,"+TbColNames.SCREENONTIMECOUNT+" TEXT,"+TbColNames.CHARGINGCOUNT+" TEXT,"+TbColNames.CONTACTCOUNT+" TEXT)");

        db.execSQL("create table " + TbNames.OPENNESS_TABLE + " ("+TbColNames.OPENNESS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.NEWLYAPPS+" TEXT,"+TbColNames.ALLAPPUSAGE+" TEXT,"+TbColNames.SOCIALAPPUSAGE+" TEXT,"+TbColNames.NOOFSOCIALAPPS+" TEXT,"+TbColNames.NOOFCOMMUNICTIONAPPS+" TEXT,"+TbColNames.COMMUNICATIONAPPUSAGE+" TEXT)");

        db.execSQL("create table " + TbNames.CONSCIENTIOUSNESS_TABLE + " ("+TbColNames.CONSCIENTIOUSNESS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.MUSICVIDEOAPPUSAGE+" TEXT,"+TbColNames.MUSICVIDEOAPP+" TEXT,"+TbColNames.PHOTOGRAPYAPPUSAGE+" TEXT,"+TbColNames.PHOTOGRAPYAPP+" TEXT,"+TbColNames.CALENDEREVENT+" TEXT,"+TbColNames.CHARGE+" TEXT)");

        db.execSQL("create table " + TbNames.CHARACTERISTICSFINAL_TABLE + " ("+TbColNames.CHARACTERISTICSfINAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.OPENNESS+" TEXT,"+TbColNames.CONSCIENTIOUSNESS+" TEXT,"+TbColNames.EXTRAVERSION+" TEXT,"+TbColNames.NEUROTICISM+" TEXT,"+TbColNames.AGREEABLENESS+" TEXT)");

        db.execSQL("create table " + TbNames.DATAUSAGE_TABLE + " ("+TbColNames.DATAUSAGE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.AMOUNT+" TEXT)");

        db.execSQL("create table " + TbNames.COMMONAPPCOUNT_TABLE + " ("+TbColNames.COMMONAPPCOUNT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TbColNames.DATE+" TEXT,"+TbColNames.SOCIAL+" TEXT,"+TbColNames.GAMING+" TEXT,"+TbColNames.PHOTOGRAPHY+" TEXT,"+TbColNames.PERSONALIZATION+" TEXT,"+TbColNames.COMMUNICATION+" TEXT,"+TbColNames.TOOLS+" TEXT,"+TbColNames.MUSICVIDEO+" TEXT)");





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


        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182419,20190216,'Saturday',0945,'0.5','0.5','oldtechnology','dating','com.example.dinu.testa',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182429,20190216,'Saturday',0945,'0.5','0.5','oldtechnology','dating','com.example.dinu.testb',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182439,20190216,'Saturday',0945,'0.5','0.5','oldtechnology','dating','com.example.dinu.testc',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182449,20190216,'Saturday',0945,'0.5','0.5','oldtechnology','dating','com.example.dinu.testd',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182459,20190216,'Saturday',0945,'0.5','0.5','oldtechnology','dating','com.example.dinu.testc',8000);");





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
