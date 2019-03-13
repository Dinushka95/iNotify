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

public class AppUsageDbHelper extends MainDbHelp {

    public AppUsageDbHelper(Context context) {
        super(context);
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


    public int appsUsageTodayGet(String appcategoriesConstants) {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select SUM(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where DATE = \""+date+"\" AND APPCATEGORY = \""+appcategoriesConstants+"\"", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("USAGETIME"));
            }
        }
        db.close();
        return 0;
    }

    public int appsUsageAvgGet(String appcategoriesConstants) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select SUM("+ TbColNames.USAGETIME +") as USAGETIME from " + TbNames.APPUSAGE_TABLE + " where  APPCATEGORY = \""+appcategoriesConstants+"\"", null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("USAGETIME"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg=total/count;
        }catch (Exception e){
            return 0;
        }

        return avg;
    }

    public int appAllUsageTodayGet() {
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

    public int appAllUsageAvgGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select SUM(UsageTime) as USAGETIME from " + TbNames.APPUSAGE_TABLE , null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("USAGETIME"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg=total/count;
        }catch (Exception e){
            return 0;
        }

        return avg;
    }


}
