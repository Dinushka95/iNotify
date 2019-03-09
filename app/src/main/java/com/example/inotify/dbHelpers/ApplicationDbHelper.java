package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.TopAppsHelper;
import com.example.inotify.models.AppInfoModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;

public class ApplicationDbHelper extends MainDbHelp {


    public static final String APPLICATION_ID = "applicationId";
    public static final String APPNAME = "appname";
    public static final String APPCATEGORY = "appcategory";
    public static final String APPPACKAGE = "APPPACKAGE";


    private  Context c1;
    public ApplicationDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public List<AppInfoModel> appInfoGet()
    {
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE, null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    //SNSModel snsModel = new SNSModel();
                    AppInfoModel appInfoModel = new AppInfoModel();
                    Log.d("inotify","mmmmmmmmmmmmmmmmmm");
                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPCATEGORY")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return listAppInfoModels;
        //Log.d("list of apps", listAppInfoModels)

    }
    public boolean appInfoInsert(List<AppInfoModel> appInfo) {



       // String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (AppInfoModel value : appInfo)
        {
            contentValues.put(APPNAME, value.getAppName());
            contentValues.put(APPPACKAGE, value.getPakageName());
            contentValues.put(APPCATEGORY, value.getAppCategory());
            db.insert(APPLICATIONS_TABLE, null, contentValues);
        }


        db.close();
        return true;
    }

    public long appCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE, null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("appCount"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public List<AppInfoModel> mySocialAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"social\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    //appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> myGamingAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"gaming\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    //appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> myMusicVideoAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"musicvideo\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    //appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public List<AppInfoModel> myCommunicationAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"communication\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    AppInfoModel appInfoModel = new AppInfoModel();

                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    //appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listAppInfoModels;
    }

    public boolean appCategoryCount()
    {
        ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);
        int social = applicationsHelper.commonSocialAppCount();
        int communication = applicationsHelper.commonCommunicationAppCount();
        int gaming = applicationsHelper.commonGamingAppCount();
        int musicVideo = applicationsHelper.commonMusicVideoAppCount();

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TbColNames.DATE,date);
        contentValues.put(TbColNames.SOCIALAPPCOUNT,social);
        contentValues.put(TbColNames.COMMUNICATIONAPPCOUNT,communication);
        contentValues.put(TbColNames.GAMINGAPPCOUNT,gaming);
        contentValues.put(TbColNames.MUSICVIDEOAPPCOUNT,musicVideo);
        contentValues.put(TbColNames.EDUCATIONAPPCOUNT,"");

        db.insert(TbNames.APPCOUNT_TABLE, null, contentValues);



        db.close();
        return true;

        // Log.d("inotify","Counts ss - " + social);

    }
}
