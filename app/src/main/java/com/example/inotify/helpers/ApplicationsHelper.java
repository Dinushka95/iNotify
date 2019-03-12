package com.example.inotify.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.models.AppInfoModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;

public class ApplicationsHelper {

    private Context c1;

    public ApplicationsHelper(Context context)
    {
        c1=context;
    }

    public List<AppInfoModel> appAllGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.appInfoGet();
    }

    public boolean appInfoInsert(List<AppInfoModel> appInfo) {

            ApplicationDbHelper applicationDbHelper =new ApplicationDbHelper(c1);
            return applicationDbHelper.appInfoInsert(appInfo);


    }

    public void saveCurrentPhoneApps()
    {
        ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);
        List<AppInfoModel> listOfApps = applicationsHelper.appAllGet();


        final PackageManager pm = c1.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);


        List<AppInfoModel> list = new ArrayList<>();


        for (ApplicationInfo x : packages){
            AppInfoModel amtem = new AppInfoModel();
            Log.d("iNotify",pm.getApplicationLabel(x).toString());
            amtem.setAppName(pm.getApplicationLabel(x).toString());
            amtem.setPakageName(x.packageName);
            amtem.setAppCategory(x.packageName);

            list.add(amtem);
        }

        appInfoInsert(list);
        Log.d("inotify","app name list............"+ list);


    }

    public int appCountGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return (int) applicationdbHelper.appCountGet();
    }

    public List<AppInfoModel> mySocialAppGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.mySocialAppGet();
    }

    public List<AppInfoModel> myGamingAppGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myGamingAppGet();
    }

    public List<AppInfoModel> myCommunicationAppGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myCommunicationAppGet();
    }

    public List<AppInfoModel> myMusicVideoAppGet()
    {
        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myMusicVideoAppGet();
    }


    public int commonSocialAppCount()
    {

        List<AppInfoModel> mySocialApps= this.mySocialAppGet();

       TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<AppInfoModel> topSocialApps = topAppsHelper.topAppSocial();


        List<AppInfoModel> commonSocialApps = new ArrayList<>();
        for(AppInfoModel tmp1: topSocialApps) {
            for(AppInfoModel tmp2: mySocialApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    AppInfoModel appInfoModelT = new AppInfoModel();
                    appInfoModelT.setAppName(tmp1.getAppName());
                    commonSocialApps.add(appInfoModelT);}
            }
        }

//        for (AppInfoModel xx :commonSocialApps){
//
//            Log.d("inotify","CCCCCCCCCCCCCCCCCCCCCCCCCCCCC"+xx.getAppName());
//        }
        int myCommonAppCount=commonSocialApps.size();

        //Log.d("inotify","common social app count = " + myCommonAppCount);
        return myCommonAppCount;
    }

    public int commonGamingAppCount()
    {

        List<AppInfoModel> myGamingApps= this.mySocialAppGet();

        TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<AppInfoModel> topGamingApps = topAppsHelper.topAppSocial();


        List<AppInfoModel> commonGamingApps = new ArrayList<>();
        for(AppInfoModel tmp1: topGamingApps) {
            for(AppInfoModel tmp2: myGamingApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    AppInfoModel appInfoModelT = new AppInfoModel();
                    appInfoModelT.setAppName(tmp1.getAppName());
                    commonGamingApps.add(appInfoModelT);}
            }
        }

        int myCommonAppCountGaming=commonGamingApps.size();

        //Log.d("inotify","common social app count = " + myCommonAppCount);
        return myCommonAppCountGaming;
    }

    public int commonMusicVideoAppCount()
    {

        List<AppInfoModel> myMusicVideoApps= this.myMusicVideoAppGet();

        TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<AppInfoModel> topMusicVideoApps = topAppsHelper.topAppMusicVideo();


        List<AppInfoModel> commonMusicVideoApps = new ArrayList<>();
        for(AppInfoModel tmp1: topMusicVideoApps) {
            for(AppInfoModel tmp2: myMusicVideoApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    AppInfoModel appInfoModelT = new AppInfoModel();
                    appInfoModelT.setAppName(tmp1.getAppName());
                    commonMusicVideoApps.add(appInfoModelT);}
            }
        }

        int myCommonAppCountMusicVideo=commonMusicVideoApps.size();

        //Log.d("inotify","common social app count = " + myCommonAppCount);
        return myCommonAppCountMusicVideo;
    }

    public int commonCommunicationAppCount()
    {

        List<AppInfoModel> myCommunicationApps= this.myCommunicationAppGet();

        TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<AppInfoModel> topCommunicationApps = topAppsHelper.topAppCommunication();


        List<AppInfoModel> commonCommunicationApps = new ArrayList<>();
        for(AppInfoModel tmp1: topCommunicationApps) {
            for(AppInfoModel tmp2: myCommunicationApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    AppInfoModel appInfoModelT = new AppInfoModel();
                    appInfoModelT.setAppName(tmp1.getAppName());
                    commonCommunicationApps.add(appInfoModelT);}
            }
        }

        int myCommonAppCountGaming=commonCommunicationApps.size();

        //Log.d("inotify","common social app count = " + myCommonAppCount);
        return myCommonAppCountGaming;
    }


    public AppInfoModel appGet(String packageName)
    {
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(c1);
        return applicationDbHelper.appGet(packageName);

    }


    //packagename to application name
    // package to catergory
}
