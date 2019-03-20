package com.example.inotify.dbHelpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.ApplicationInfoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;


public class TopAppDbHelper extends MainDbHelp {

    public TopAppDbHelper(Context context) {
        super(context);
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));


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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));


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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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
                    applicationInfoModel.setPakageName(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
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


}
