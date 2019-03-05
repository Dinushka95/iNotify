package com.example.inotify.dbHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.content.Context.MODE_PRIVATE;


public class MainSqlliteOpenHelp extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AppInotify.db";

    SharedPreferences.Editor editor009;

    public static final String NV_NOTIFICATIONREMOVE_TABLE = "nv_notificationRemove_table";
    public static final String NV_NOTIFICATIONVIEWABILITY_TABLE = "nv_viewability_table";
    public static final String NV_ACTIVITY_TABLE = "nv_activity_table";
    public static final String NV_LOCATION_TABLE = "nv_location_table";

    public static final String UA_N_TABLE = "ua_N_table";
    public static final String UA_NI_TABLE = "ua_NI_table";
    public static final String UA_SCREENOFF_TABLE = "ua_screenoff_table";
    public static final String UA_SCREENON_TABLE = "ua_screenon_table";

    public static final String UC_CHARGE_TABLE = "uc_charge_table";
    public static final String UC_APPLISTCOUNT_TABLE = "uc_applistcount_table";
    public static final String UC_USAGECOUNT_TABLE = "uc_usagecount_table";
    public static final String UC_CONTACTCOUNT_TABLE = "uc_contactcount_table";
    public static final String UC_SCREENTIME_TABLE = "uc_screentime_table";
    public static final String UC_CALLDURATION_TABLE = "uc_callduration_table";
    public static final String UC_CALENDEREVENTCOUNT_TABLE = "uc_calendereventcount_table";
    public static final String UC_APPLISTSOCIALMEDIACOUNT_TABLE = "uc_applistsocialmediacount_table";

    public static final String SNS_TABLE = "sns_table";

    public static final String UC_TOPAPP_TABLE = "uc_topapp_table";

    public static final String APPLICATIONS_TABLE = "applications_table";


    public static final String SETTINGS_SNAVCTIVEAPPS_TABLE = "settings_snactiveapps_table";

    public MainSqlliteOpenHelp(Context context) {
        super(context, DATABASE_NAME, null, 1);
        editor009 = context.getSharedPreferences("lockscreen", MODE_PRIVATE).edit();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        editor009.putString("screen", "off");
        editor009.apply();


        // put all the  create tables in one place

        db.execSQL("create table " + NV_ACTIVITY_TABLE + " (ACTIVITY_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT,TYPE TEXT,CONFIDENCE TEXT)");
        db.execSQL("create table " + NV_LOCATION_TABLE + " (LOCATION_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT,LOG TEXT,LAT TEXT)");
        db.execSQL("create table " + NV_NOTIFICATIONREMOVE_TABLE + " (NOTIFICATIONREMOVE_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DAY TEXT,TIME TEXT)");
        db.execSQL("create table " + NV_NOTIFICATIONVIEWABILITY_TABLE + " (BUSYORNOT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DAY TEXT,TIME TEXT,ACTIVITY TEXT,LOCATION TEXT,BUSYORNOT TEXT)");

        db.execSQL("create table " + SNS_TABLE + " (SNS_ID INTEGER,SNS_DATE TEXT,SNS_DAY TEXT,SNS_TIME TEXT,SNS_BUSYORNOT TEXT," +
                "SNS_ATTENTIVINESS TEXT,SNS_USERCHAACTERISTICS TEXT,SNS_NOTIFICATIONTYPE TEXT,SNS_APPNAME TEXT,SNS_VTIME TEXT)");

        db.execSQL("create table " + UA_N_TABLE + " (N_ID INTEGER,N_APPNAME TEXT,N_DATETIME INTEGER)");
        db.execSQL("create table " + UA_NI_TABLE + " (NI_ID INTEGER PRIMARY KEY AUTOINCREMENT,NI_APPNAME TEXT,NI_VALUE INTEGER)");

        db.execSQL("create table " + UC_CHARGE_TABLE + " (CHARGE_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT)");
        db.execSQL("create table " + UC_APPLISTCOUNT_TABLE + " (APPLISTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + UC_USAGECOUNT_TABLE + " (USAGECOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + UC_CONTACTCOUNT_TABLE + " (CONTACTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + UC_SCREENTIME_TABLE + " (SCREENTIME_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIME TEXT)");
        db.execSQL("create table " + UC_CALLDURATION_TABLE + " (CALLDURATION_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIME TEXT)");
        db.execSQL("create table " + UC_CALENDEREVENTCOUNT_TABLE + " (CALENDEREVENTCOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");
        db.execSQL("create table " + UC_APPLISTSOCIALMEDIACOUNT_TABLE + " (APPLISTSOCIALMEDIACOUNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,COUNT TEXT)");

        db.execSQL("create table " + SETTINGS_SNAVCTIVEAPPS_TABLE + " (SNAVCTIVEAPPS_ID INTEGER PRIMARY KEY AUTOINCREMENT,APPNAME TEXT,STATUS TEXT)");

        db.execSQL("create table " + UA_SCREENOFF_TABLE + " (SCREENOFF_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIMEOFF TEXT)");
        db.execSQL("create table " + UA_SCREENON_TABLE + " (SCREENOFF_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIMEON TEXT)");

        db.execSQL("create table " + UC_TOPAPP_TABLE + " (TOPAPP_ID INTEGER PRIMARY KEY AUTOINCREMENT,APPNAME TEXT,APPCATEGORY TEXT,APPPACKAGE Text)");

        db.execSQL("create table " + APPLICATIONS_TABLE + " (APPLICATION_ID INTEGER PRIMARY KEY AUTOINCREMENT,APPNAME TEXT,APPCATEGORY TEXT,APPPACKAGE Text)");


        db.execSQL("insert into nv_location_table(LOCATION_ID,DATE,DAY,TIME,LOG,LAT)values(1,20190216,'Saturday',2345,80.9,78.8);");
        db.execSQL("insert into nv_location_table(LOCATION_ID,DATE,DAY,TIME,LOG,LAT)values(2,20190216,'Saturday',2345,80.9,78.8);");

        db.execSQL("insert into nv_activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(1,20190216,'Saturday',2345,1,100);");
        db.execSQL("insert into nv_activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(2,20190216,'Saturday',2345,2,100);");
        db.execSQL("insert into nv_activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(3,20190216,'Saturday',2345,3,100);");
        db.execSQL("insert into nv_activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(4,20190216,'Saturday',2345,4,100);");
        db.execSQL("insert into nv_activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(5,20190216,'Saturday',2345,5,100);");
        db.execSQL("insert into nv_activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(6,20190216,'Saturday',2345,6,100);");
        db.execSQL("insert into nv_activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(7,20190216,'Saturday',2345,7,100);");
        db.execSQL("insert into nv_activity_table(ACTIVITY_ID,DATE,DAY,TIME,TYPE,CONFIDENCE)values(8,20190216,'Saturday',2345,8,100);");


        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182419,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.example.dinu.testa',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182429,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.example.dinu.testb',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182439,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.example.dinu.testc',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182449,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.example.dinu.testd',8000);");
        db.execSQL("insert into sns_table(SNS_ID,SNS_DATE,SNS_DAY,SNS_TIME,SNS_BUSYORNOT,SNS_ATTENTIVINESS,SNS_USERCHAACTERISTICS,SNS_NOTIFICATIONTYPE,SNS_APPNAME,SNS_VTIME)values(20190217182459,20190216,'Saturday',0945,'NotBusy','high','oldtechnology','Mobile','com.google.android.apps.messaging',8000);");


        db.execSQL("insert into ua_NI_table(NI_ID,NI_APPNAME,NI_VALUE)values(1,'com.example.dinu.testa',1);");
        db.execSQL("insert into ua_NI_table(NI_ID,NI_APPNAME,NI_VALUE)values(2,'com.example.dinu.testb',1);");
        db.execSQL("insert into ua_NI_table(NI_ID,NI_APPNAME,NI_VALUE)values(3,'com.example.dinu.testc',1);");
        db.execSQL("insert into ua_NI_table(NI_ID,NI_APPNAME,NI_VALUE)values(4,'com.example.dinu.testd',1);");
        db.execSQL("insert into ua_NI_table(NI_ID,NI_APPNAME,NI_VALUE)values(5,'com.google.android.apps.messaging',1);");

        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('facebook','social','com.google.android.apps.facebook');");
        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('whatsapp','social','com.google.android.apps.whatsapp');");
        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('hungamamusic','musicvideo','com.google.android.apps.hungamamusic');");
        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('tinder','dating','com.google.android.apps.tinder');");
        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('badoo','dating','com.google.android.apps.badoo');");
        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('netflix','entertainment','com.google.android.apps.netflix');");
        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('coverfire','gaming','com.google.android.apps.coverfire');");
        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('edx','education','com.google.android.apps.edx');");
        db.execSQL("insert into UC_TOPAPP_TABLE(APPNAME,APPCATEGORY,APPPACKAGE)values('fruitninja','gaming','com.google.android.apps.fruitninja');");

        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('facebook','social','com.google.android.apps.facebook');");
        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('whatsapp','social','com.google.android.apps.whatsapp');");
        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('hungamamusic','musicvideo','com.google.android.apps.hungamamusic');");
        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('tinder','dating','com.google.android.apps.tinder');");
        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('badoo','dating','com.google.android.apps.badoo');");
        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('netflix','entertainment','com.google.android.apps.netflix');");
        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('coverfire','gaming','com.google.android.apps.coverfire');");
        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('edx','education','com.google.android.apps.edx');");
        db.execSQL("insert into applications_table(APPNAME,APPCATEGORY,APPPACKAGE)values('fruitninja','gaming','com.google.android.apps.fruitninja');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
