package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.AppCategoriesConstants;
import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.models.ApplicationInfoModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;

public class ApplicationDbHelper extends MainDbHelp {



    private  Context c1;
    public ApplicationDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public List<ApplicationInfoModel> appInfoGet()
    {
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE, null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    //SNSModel snsModel = new SNSModel();
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    Log.d("inotify","mmmmmmmmmmmmmmmmmm");
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPakageName( res.getString(res.getColumnIndex(TbColNames.APPPACKAGE)));
                    applicationInfoModel.setPakageName( res.getString(res.getColumnIndex(TbColNames.APPCATEGORY)));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return listApplicationInfoModels;
        //Log.d("list of apps", listApplicationInfoModels)

    }

    public ApplicationInfoModel appGet(String packageName)
    {
        ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " WHERE "+TbColNames.APPPACKAGE +" = \""+packageName+"\"", null);
        if (res != null) {

            if (res.moveToFirst()) {

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPakageName( res.getString(res.getColumnIndex(TbColNames.APPPACKAGE)));
                    applicationInfoModel.setAppCategory( res.getString(res.getColumnIndex(TbColNames.APPCATEGORY)));

            }

        }

        return applicationInfoModel;


    }

    public boolean appInfoInsert(List<ApplicationInfoModel> appInfo) {



       // String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (ApplicationInfoModel value : appInfo)
        {
            contentValues.put(TbColNames.APPNAME, value.getAppName());
            contentValues.put(TbColNames.APPPACKAGE, value.getPakageName());
            contentValues.put(TbColNames.APPCATEGORY, value.getAppCategory());

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

    public List<ApplicationInfoModel> mySocialAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"social\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myGamingAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"gaming\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myMusicVideoAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"musicvideo\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myCommunicationAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.APPLICATIONS_TABLE + " where APPCATEGORY = \"communication\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public void updateCategory()
    {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues newValues = new ContentValues();

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.COMMUNICATION);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.skype.raider\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.truecaller\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.mms.service\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.facebook.orca\t\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.mms\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.google.android.gm\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.WEATHER);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.android.totemweather\"", null);


        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.BUSINESS);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.mobisystems.office\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.iconnect\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.MUSICVIDEO);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.google.android.youtube\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.mediacenter\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.android.FMRadio\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.BestPhotoEditor.HappyBirthdayVideoMaker\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.videoeditor\"", null);

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.MUSICANDAUDIO);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.soundrecorder\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.PHOTOGRAPY);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.camera\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.motionservice\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.LIBRARIESANDDEMO);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.google.android.ext.services\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"org.simalliance.openmobileapi.service\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.TOOLS);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.android.launcher\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.android.CotaDecompressService\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.htmlviewer\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.google.android.googlequicksearchbox\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.hidisk\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.bluetooth\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.providers.media\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.android.chr\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.android.hsf\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.google.android.ext.shared\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.autoinstallapkfrommcc\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.wallpapercropper\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.android.FloatTasks\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.appmarket\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.externalstorage\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.providers.downloads\"", null);

        //if this download app usage is high then
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.phoneservice\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.screenrecorder\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.securitymgr\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.google.android.configupdater\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.KoBackup\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.defcontainer\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.providers.downloads.ui\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.vending\"", null);














        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.SOCIAL);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.whatsapp\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.providers.telephony\"", null);

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.PRODUCTIVITY);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.internetaudioservice\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.providers.calendar\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.google.android.onetimeinitializer\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.android.documentsui\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.nuance.swype.emui\"", null);

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.SOCIAL);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"lk.bhasha.helakuru\"", null);






        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.HEALTHANDFITNESS);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.health\"", null);

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.PERSONALIZATION);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.APPPACKAGE + " = \"com.huawei.android.thememanager\"", null);













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
    public int appsCountAvgGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select SUM("+ TbColNames.APPNAME +") as APPCOUNTAVG from " + TbNames.APPLICATIONS_TABLE , null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("APPCOUNTAVG"));
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

    public List<ApplicationInfoModel> myPhotograpyAppGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"photograpy\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }


    public int socialAppCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"social\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int photograpyAppCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"photograpy\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }



    public int communicationAppCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"communication\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int gamingAppCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"gaming\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }
}
