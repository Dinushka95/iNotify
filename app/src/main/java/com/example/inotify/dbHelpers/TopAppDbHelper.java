package com.example.inotify.dbHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.models.AppInfoModel;

import java.util.ArrayList;
import java.util.List;

public class TopAppDbHelper extends MainSqlliteOpenHelp {

    public static final String TOPAPP_ID = "topapp_id";
    public static final String APPNAME = "appname";
    public static final String APPCATEGORY = "appcategory";

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
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"social\"", null);
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

    public String topAppCommunicationGet() {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"communication\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }

    public String topAppGamingGet() {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"gaming\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }

    public String topAppDatingGet() {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"dating\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }

    public String topAppEducationGet() {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"education\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }

    public String topAppEntertainmentGet() {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"entertainment\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }

    public String topAppProductivityGet() {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"productivity\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }

    public String topAppToolGet() {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"tool\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }

    public String topAppBusinessGet() {
        //Log.d("cdap", " ---NValueGet--");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TOPAPP_TABLE + " where APPCATEGORY = \"business\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(1);
            }
            res.close();
        }
        return "null";
    }


}
