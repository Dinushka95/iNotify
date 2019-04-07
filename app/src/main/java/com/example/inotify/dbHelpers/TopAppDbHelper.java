package com.example.inotify.dbHelpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.TopAppModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;


public class TopAppDbHelper extends MainDbHelp {
    private static TopAppDbHelper mInstance = null;


    public TopAppDbHelper(Context context) {
        super(context);
    }

    public static TopAppDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new TopAppDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }



    public List<ApplicationInfoModel> topAppSocilGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " +TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"social\"", null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    //SNSModel snsModel = new SNSModel();
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return listApplicationInfoModels;
    }


    public List<ApplicationInfoModel> topAppPhotograpyGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " +TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"photograpy\"", null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    //SNSModel snsModel = new SNSModel();
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> topAppPersonalizationGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " +TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"personalization\"", null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    //SNSModel snsModel = new SNSModel();
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return listApplicationInfoModels;
    }


    public List<ApplicationInfoModel> topAppCommunicationGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"communication\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
            db.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> topAppGamingGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"gaming\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        db.close();
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> topAppDatingGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"dating\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> topAppEducationGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"education\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> topAppEntertainmentGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"entertainment\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> topAppProductivityGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"productivity\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> topAppToolGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"tool\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> topAppBusinessGet() {
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"business\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }


    public List<ApplicationInfoModel> topAppMusicVideoGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where APPCATEGORY = \"musicvideo\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName(res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }


    public long SocialAppCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as socialAppCount from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"social\"", null);
        if (res != null) {
            if ((res.moveToFirst())) {
                return res.getLong(res.getColumnIndex("socialappcount"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();
        return 0;
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    public boolean insert(List<TopAppModel> topAppModelList) {


        String sql = "INSERT INTO " + TbNames.TOPAPPS_TABLE + "("+TbColNames.DATE+","+TbColNames.DATECREATED+","+TbColNames.APPCOLLECTION+","+TbColNames.APPCATEGORY+","+TbColNames.APPNAME+","+TbColNames.PACKAGENAME+","+TbColNames.RANK+") VALUES (?,?,?,?,?,?,?);";
        SQLiteDatabase db = this.getWritableDatabase();

        SQLiteStatement statement = db.compileStatement(sql);
        String datenow = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        db.beginTransaction();
        try {
            for (TopAppModel topAppModel : topAppModelList) {

                if (topAppModel != null) {
                    statement.clearBindings();
                    statement.bindString(1, datenow);
                    statement.bindString(2, topAppModel.getDate());
                    statement.bindString(3, topAppModel.getCollection());
                    statement.bindString(4, topAppModel.getCategory());
                    statement.bindString(5, topAppModel.getApplicationName());
                    statement.bindString(6, topAppModel.getPackageName());
                    statement.bindString(7, String.valueOf(topAppModel.getRank()));

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
    public boolean cheackAvailability(String TableName ) {

        //TODO- change scapper and this to the sasme standard
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
