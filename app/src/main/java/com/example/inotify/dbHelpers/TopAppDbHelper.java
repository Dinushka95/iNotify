package com.example.inotify.dbHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbNames;
import com.example.inotify.models.AppInfoModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.inotify.configs.TbNames.TOPAPP_TABLE;

public class TopAppDbHelper extends MainSqlliteOpenHelp {

    public static final String TOPAPP_ID = "topapp_id";
    public static final String APPNAME = "appname";
    public static final String APPCATEGORY = "appcategory";
    public static final String APPPACKAGE = "apppackage";


    public TopAppDbHelper(Context context) {
        super(context);
    }

    //No of social media apps -- If available top apps or not
    //No of gaming apps -- If available top apps or not
    //No of music and video apps -- If available top apps or not
    //No of chatting apps -- If available top apps or not
    //check with pre define top apps

//app categories - social/Messaging/

    public List<AppInfoModel> topAppSocilGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.TOPAPP_TABLE + " where APPCATEGORY = \"social\"", null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    //SNS_SNSModel snsModel = new SNS_SNSModel();
                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return listAppInfoModels;
    }

    public List<AppInfoModel> topAppCommunicationGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"communication\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> topAppGamingGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"gaming\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> topAppDatingGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"dating\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> topAppEducationGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"education\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> topAppEntertainmentGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"entertainment\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> topAppProductivityGet() {
        //Log.d("cdap", " ---NValueGet--");

        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"productivity\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> topAppToolGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"tool\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> topAppBusinessGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"business\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }


}
