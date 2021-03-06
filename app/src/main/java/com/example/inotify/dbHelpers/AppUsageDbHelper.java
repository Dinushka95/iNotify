package com.example.inotify.dbHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.AppUsageModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;

public class AppUsageDbHelper extends MainDbHelp {

    private static AppUsageDbHelper mInstance = null;


    public AppUsageDbHelper(Context context) {
        super(context);
    }

    public static AppUsageDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new AppUsageDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public boolean insert(List<AppUsageModel> appUsageModelList) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());
        String sql = "INSERT INTO " + TbNames.APPUSAGE_TABLE + "(DATE,TIME,PACKAGENAME,APPNAME,APPCATEGORY,USAGETIME) VALUES (?,?,?,?,?,?);";
        SQLiteDatabase db = this.getWritableDatabase();

        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();
        try {
            for (AppUsageModel appUsageModel : appUsageModelList) {

                if (appUsageModel != null) {
                    statement.clearBindings();
                    statement.bindString(1, date);
                    statement.bindString(2, time);
                    String packageName = appUsageModel.getPackageName();
                    if (packageName==null) {
                        packageName = "";
                    }
                    String appName = appUsageModel.getAppName();
                    if (appName==null) {
                        appName = "null";
                    }
                    String appCategory = appUsageModel.getAppCategory();
                    if (appCategory==null) {
                        appCategory = "null";
                    }
                    statement.bindString(3, packageName);
                    statement.bindString(4, appName);
                    statement.bindString(5, appCategory);
                    statement.bindString(6, appUsageModel.getUsageTime());
                    statement.execute();
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
            return true;
        } catch (Exception e) {
            Log.d("inotify", "Db Transaction Error------" + e.toString());
            return false;
        }
    }


    public int appAllsUsageToday () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select SUM(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where DATE = \""+date+"\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int appAllsUsageAVG () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select SUM(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int communicationAppsUsageToday () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select (UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where DATE = \""+date+"\" AND APPCATEGORY = \"COMMUNICATION\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int gamingAppsUsageToday () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select (UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where DATE = \""+date+"\" AND APPCATEGORY = \"GAMING\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int photograpyAppsUsageToday () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select (UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where DATE = \""+date+"\" AND APPCATEGORY = \"PHOTOGRAPY\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int personalizationAppsUsageToday () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select (UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where DATE = \""+date+"\" AND APPCATEGORY = \"PERSONALIZATION\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int musicvideoAppsUsageToday () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select (UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where DATE = \""+date+"\" AND APPCATEGORY = \"MUSIC&AUDIO\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int socialAppsUsageToday () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select (UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where DATE = \""+date+"\" AND APPCATEGORY = \"SOCIAL\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int communicationAppsUsageAVG () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select AVG(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"COMMUNICATION\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }


    public int personalizationAppsUsageAVG () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select AVG(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"PERSONALIZATION\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int gamingAppsUsageAVG () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select AVG(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"GAMING\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int photograpyAppsUsageAVG () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select AVG(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"PHOTOGRAPY\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int socialAppsUsageAVG () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select AVG(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"SOCIAL\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int musicvideoAppsUsageAVG () {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select AVG(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"MUSIC&AUDIO\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int allUsageTimeColumCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(UsageTime) as USAGETIMECOUNT from "+ TbNames.APPUSAGE_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("USAGETIMECOUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int socialUsageTimeColumCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(UsageTime) as USAGETIMECOUNT from "+ TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"SOCIAL\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("USAGETIMECOUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int musicvideoUsageTimeColumCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(UsageTime) as USAGETIMECOUNT from "+ TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"MUSIC&AUDIO\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("USAGETIMECOUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }



    public int communicationUsageTimeColumCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(UsageTime) as USAGETIMECOUNT from "+ TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"COMMUNICATION\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("USAGETIMECOUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int personalizationUsageTimeColumCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(UsageTime) as USAGETIMECOUNT from "+ TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"PERSONALIZATION\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("USAGETIMECOUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int gamingUsageTimeColumCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(UsageTime) as USAGETIMECOUNT from "+ TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"GAMING\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("USAGETIMECOUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int photograpyUsageTimeColumCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(UsageTime) as USAGETIMECOUNT from "+ TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \"PHOTOGRAPY\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("USAGETIMECOUNT"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public boolean cheackAvailability(String TableName ) {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableName + " where DATE =\"" + date + "\"", null);
        if (res.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
