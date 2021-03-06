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

    private static ApplicationDbHelper mInstance = null;
    private  Context c1;

    public ApplicationDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public static ApplicationDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new ApplicationDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public List<ApplicationInfoModel> appInfoGet()
    {
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE, null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    applicationInfoModel.setPackageName( res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                    applicationInfoModel.setPackageName( res.getString(res.getColumnIndex(TbColNames.APPCATEGORY)));
                    applicationInfoModel.setDate( res.getString(res.getColumnIndex(TbColNames.DATE)));

                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public ApplicationInfoModel getApplicationDetailsByPackName(String packageName)
    {
        ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " WHERE "+TbColNames.PACKAGENAME +" = \""+packageName+"\"", null);
        if (res != null) {

            if (res.moveToFirst()) {
                applicationInfoModel.setId( res.getInt(res.getColumnIndex(TbColNames.APPLICATION_ID)));
                applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                applicationInfoModel.setPackageName( res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                applicationInfoModel.setAppCategory( res.getString(res.getColumnIndex(TbColNames.APPCATEGORY)));
                applicationInfoModel.setDate( res.getString(res.getColumnIndex(TbColNames.DATE)));
            }
        }
        return applicationInfoModel;
    }

    public ApplicationInfoModel getApplicationDetailsById(String id)
    {
        ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " WHERE "+TbColNames.APPLICATION_ID +" = \""+id+"\"", null);
        if (res != null) {

            if (res.moveToFirst()) {
                applicationInfoModel.setId( res.getInt(res.getColumnIndex(TbColNames.APPLICATION_ID)));
                applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                applicationInfoModel.setPackageName( res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                applicationInfoModel.setAppCategory( res.getString(res.getColumnIndex(TbColNames.APPCATEGORY)));
                applicationInfoModel.setDate( res.getString(res.getColumnIndex(TbColNames.DATE)));
            }
        }

        return applicationInfoModel;
    }

    public boolean appInfoInsert(List<ApplicationInfoModel> appInfo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (ApplicationInfoModel value : appInfo)
        {
            contentValues.put(TbColNames.APPNAME, value.getAppName());
            contentValues.put(TbColNames.PACKAGENAME, value.getPackageName());
            contentValues.put(TbColNames.APPCATEGORY, value.getAppCategory());
            contentValues.put(TbColNames.DATE, value.getDate());

            db.insert(APPLICATIONS_TABLE, null, contentValues);
        }

        db.close();
        return true;
    }

    public int appCountGetToday() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where DATE = \""+date+"\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("appCount"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int allAppCountGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex("appCount"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public long allAppCountAllGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE  , null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getLong(res.getColumnIndex("appCount"));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public List<ApplicationInfoModel> mySocialAppTodayGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"SOCIAL\" AND DATE = \"date\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> mySocialAppAllGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"SOCIAL\" ", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myPersonalizationAppTodayGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"PERSONALIZATION\" AND DATE = \"date\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myPersonalizationAppAllGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"PERSONALIZATION\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myGamingAppTodayGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"GAMING\" AND DATE = \"date\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myGamingAppAllGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"GAMING\" ", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myMusicVideoAppTodayGet() {

        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"MUSIC&AUDIO\" AND DATE = \"date\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myMusicVideoAppAllGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"MUSIC&AUDIO\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myCommunicationAppGetToday() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.APPLICATIONS_TABLE + " where APPCATEGORY = \"COMMUNICATION\" AND DATE = \"date\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPackageName( res.getString(res.getColumnIndex("APPPACKAGE")));

                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myCommunicationAppGetAll() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.APPLICATIONS_TABLE + " where APPCATEGORY = \"COMMUNICATION\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPackageName( res.getString(res.getColumnIndex("APPPACKAGE")));


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
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.skype.raider\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.truecaller\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.mms.service\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.facebook.orca\t\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.mms\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.google.android.gm\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.WEATHER);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.android.totemweather\"", null);


        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.BUSINESS);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.mobisystems.office\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.iconnect\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.MUSICVIDEO);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.google.android.youtube\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.mediacenter\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.android.FMRadio\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.BestPhotoEditor.HappyBirthdayVideoMaker\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.videoeditor\"", null);

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.MUSICANDAUDIO);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.soundrecorder\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.PHOTOGRAPY);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.camera\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.motionservice\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.LIBRARIESANDDEMO);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.google.android.ext.services\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"org.simalliance.openmobileapi.service\"", null);



        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.TOOLS);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.android.launcher\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.android.CotaDecompressService\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.htmlviewer\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.google.android.googlequicksearchbox\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.hidisk\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.bluetooth\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.providers.media\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.android.chr\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.android.hsf\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.google.android.ext.shared\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.autoinstallapkfrommcc\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.wallpapercropper\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.android.FloatTasks\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.appmarket\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.externalstorage\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.providers.downloads\"", null);

        //if this download app usage is high then
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.phoneservice\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.screenrecorder\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.securitymgr\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.google.android.configupdater\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.KoBackup\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.defcontainer\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.providers.downloads.ui\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.vending\"", null);














        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.SOCIAL);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.whatsapp\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.providers.telephony\"", null);

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.PRODUCTIVITY);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.internetaudioservice\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.providers.calendar\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.google.android.onetimeinitializer\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.android.documentsui\"", null);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.nuance.swype.emui\"", null);

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.SOCIAL);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"lk.bhasha.helakuru\"", null);






        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.HEALTHANDFITNESS);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.health\"", null);

        newValues.put(TbColNames.APPCATEGORY, AppCategoriesConstants.PERSONALIZATION);
        db.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME + " = \"com.huawei.android.thememanager\"", null);













    }

    public boolean appCategoryCount()
    {
        ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);
        int social = applicationsHelper.commonSocialAppTodayCount();
        int communication = applicationsHelper.commonCommunicationAppTodayCount();
        int gaming = applicationsHelper.commonGamingAppTodayCount();
        int musicVideo = applicationsHelper.commonMusicVideoAppTodayCount();

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
        Cursor res = db.rawQuery("select ("+ TbColNames.APPNAME +") as APPCOUNTAVG from " + TbNames.APPLICATIONS_TABLE , null);
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

    public ArrayList<String> getAppPackage() {
        ArrayList<String> pacNamearraylist = new ArrayList<>();
        pacNamearraylist.add("");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select "+ TbColNames.PACKAGENAME +" from " + TbNames.APPLICATIONS_TABLE , null);
        if (cursor != null) {
            int count =1;
            while (cursor.moveToNext()) {
                    pacNamearraylist.add(cursor.getString(0));
                    count = count + 1 ;
            }
            pacNamearraylist.set(0,Integer.toString(count-1));
        }
        return pacNamearraylist;
    }

    public boolean appCategoryUpdate(ArrayList<String> apacNamearraylist){

        for (String packageName :apacNamearraylist) {
            SQLiteDatabase db = this.getReadableDatabase();
            String x="";
            Cursor res = db.rawQuery("select * from " + TbNames.TOPAPPS_TABLE + " where packageName = \"" + packageName + "\" ", null);
            if (res != null) {
                if ((res.moveToFirst())) {
                    db.close();
                    x = res.getString(res.getColumnIndex(TbColNames.APPCATEGORY));
                }
            }
            db.close();
            if(x==null||x.equals("")){x="null";}
            SQLiteDatabase db1 = this.getWritableDatabase();
            ContentValues newValues = new ContentValues();
            newValues.put(TbColNames.APPCATEGORY, x);
            db1.update(TbNames.APPLICATIONS_TABLE, newValues, TbColNames.PACKAGENAME+"=\"" + packageName+"\"", null);
            db1.close();

        }
        return true;
    }


    public List<ApplicationInfoModel> myPhotograpyAppTodayGet() {
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"PHOTOGRAPY\" AND DATE = \"date\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPackageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }

    public List<ApplicationInfoModel> myPhotograpyAppAVGGet() {
        //Log.d("cdap", " ---NValueGet--");
        List<ApplicationInfoModel> listApplicationInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + APPLICATIONS_TABLE + " where APPCATEGORY = \"PHOTOGRAPY\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();

                    applicationInfoModel.setAppName( res.getString(res.getColumnIndex(TbColNames.APPNAME)));
                    //applicationInfoModel.setPackageName( res.getString(res.getColumnIndex("APPPACKAGE")));


                    listApplicationInfoModels.add(applicationInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }
        return listApplicationInfoModels;
    }
    public int socialAppCountTodayGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"SOCIAL\" AND DATE = \"date\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int socialAppCountAVGGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"SOCIAL\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int photograpyAppCountTodayGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"PHOTOGRAPY\" AND DATE =\"date\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int photograpyAppCountAVGGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"PHOTOGRAPY\" ", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }



    public int communicationAppCountTodayGet() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"COMMUNICATION\"AND Date = \"date\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int communicationAppCountAVGGet() {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"COMMUNICATION\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int gamingAppCountTodayGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"GAMING\" AND Date = \"date\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int gamingAppCountAVGGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"GAMING\" ", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int PersonalizationppCountTodayGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"PERSONALIZATION\" AND Date = \"date\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public int PersonalizationppCountAVGGet() {

        //same for charging above need correction
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE + " where APPCATEGORY = \"PERSONALIZATION\"", null);
        if (res != null) {
            if ((res.moveToFirst())){
                return res.getInt(res.getColumnIndex(TbColNames.APPNAME));
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return 0;
    }

    public boolean cheackAvailability(String TableName )
    {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableName +" where DATE =\"" + date + "\"", null);

      if(res.getCount()>0)
      {
          return true;
      }
      else
      {
          return false;
      }
   }

}
