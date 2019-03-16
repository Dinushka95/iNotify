package com.example.inotify.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.models.ApplicationInfoModel;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsHelper {

    private Context c1;

    public ApplicationsHelper(Context context)
    {
        c1=context;
    }

    public List<ApplicationInfoModel> appAllGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.appInfoGet();
    }

    public boolean appInfoInsert(List<ApplicationInfoModel> appInfo) {

            ApplicationDbHelper applicationDbHelper =new ApplicationDbHelper(c1);
            return applicationDbHelper.appInfoInsert(appInfo);


    }

    public void saveCurrentPhoneApps()
    {
        ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);
        List<ApplicationInfoModel> listOfApps = applicationsHelper.appAllGet();


        final PackageManager pm = c1.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);


        List<ApplicationInfoModel> list = new ArrayList<>();


        for (ApplicationInfo x : packages){
            ApplicationInfoModel amtem = new ApplicationInfoModel();
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
        Log.d("inotify","app count------" + (int) applicationdbHelper.appCountGet());
        return (int) applicationdbHelper.appCountGet();
    }

    public List<ApplicationInfoModel> mySocialAppGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.mySocialAppGet();
    }

    public List<ApplicationInfoModel> myGamingAppGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myGamingAppGet();
    }

    public List<ApplicationInfoModel> myCommunicationAppGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myCommunicationAppGet();
    }

    public List<ApplicationInfoModel> myMusicVideoAppGet()
    {
        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myMusicVideoAppGet();
    }


    public int commonSocialAppCount()
    {

        List<ApplicationInfoModel> mySocialApps= this.mySocialAppGet();

       TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<ApplicationInfoModel> topSocialApps = topAppsHelper.topAppSocial();


        List<ApplicationInfoModel> commonSocialApps = new ArrayList<>();
        for(ApplicationInfoModel tmp1: topSocialApps) {
            for(ApplicationInfoModel tmp2: mySocialApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    ApplicationInfoModel applicationInfoModelT = new ApplicationInfoModel();
                    applicationInfoModelT.setAppName(tmp1.getAppName());
                    commonSocialApps.add(applicationInfoModelT);}
            }
        }

//        for (ApplicationInfoModel xx :commonSocialApps){
//
//            Log.d("inotify","CCCCCCCCCCCCCCCCCCCCCCCCCCCCC"+xx.getAppName());
//        }
        int myCommonAppCount=commonSocialApps.size();

        Log.d("inotify","common social app count = " + myCommonAppCount);
        return myCommonAppCount;
    }

    public int commonGamingAppCount()
    {

        List<ApplicationInfoModel> myGamingApps= this.mySocialAppGet();

        TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<ApplicationInfoModel> topGamingApps = topAppsHelper.topAppSocial();


        List<ApplicationInfoModel> commonGamingApps = new ArrayList<>();
        for(ApplicationInfoModel tmp1: topGamingApps) {
            for(ApplicationInfoModel tmp2: myGamingApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    ApplicationInfoModel applicationInfoModelT = new ApplicationInfoModel();
                    applicationInfoModelT.setAppName(tmp1.getAppName());
                    commonGamingApps.add(applicationInfoModelT);}
            }
        }

        int myCommonAppCountGaming=commonGamingApps.size();

        Log.d("inotify","common social app count = " + myCommonAppCountGaming);
        return myCommonAppCountGaming;
    }

    public int commonMusicVideoAppCount()
    {

        List<ApplicationInfoModel> myMusicVideoApps= this.myMusicVideoAppGet();

        TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<ApplicationInfoModel> topMusicVideoApps = topAppsHelper.topAppMusicVideo();


        List<ApplicationInfoModel> commonMusicVideoApps = new ArrayList<>();
        for(ApplicationInfoModel tmp1: topMusicVideoApps) {
            for(ApplicationInfoModel tmp2: myMusicVideoApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    ApplicationInfoModel applicationInfoModelT = new ApplicationInfoModel();
                    applicationInfoModelT.setAppName(tmp1.getAppName());
                    commonMusicVideoApps.add(applicationInfoModelT);}
            }
        }

        int myCommonAppCountMusicVideo=commonMusicVideoApps.size();

        Log.d("inotify","common social app count = " + myCommonAppCountMusicVideo);
        return myCommonAppCountMusicVideo;
    }

    public int commonCommunicationAppCount()
    {

        List<ApplicationInfoModel> myCommunicationApps= this.myCommunicationAppGet();

        TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<ApplicationInfoModel> topCommunicationApps = topAppsHelper.topAppCommunication();


        List<ApplicationInfoModel> commonCommunicationApps = new ArrayList<>();
        for(ApplicationInfoModel tmp1: topCommunicationApps) {
            for(ApplicationInfoModel tmp2: myCommunicationApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    ApplicationInfoModel applicationInfoModelT = new ApplicationInfoModel();
                    applicationInfoModelT.setAppName(tmp1.getAppName());
                    commonCommunicationApps.add(applicationInfoModelT);}
            }
        }

        int myCommonAppCountGaming=commonCommunicationApps.size();

        //Log.d("inotify","common social app count = " + myCommonAppCount);
        return myCommonAppCountGaming;
    }


    public ApplicationInfoModel appGet(String packageName)
    {
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(c1);
        return applicationDbHelper.appGet(packageName);

    }


    //packagename to application name
    // package to catergory
}
