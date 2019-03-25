package com.example.inotify.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.models.ApplicationInfoModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        final PackageManager pm = c1.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);


        List<ApplicationInfoModel> list = new ArrayList<>();


        for (ApplicationInfo x : packages){
        ApplicationInfoModel amtem = new ApplicationInfoModel();
        Log.d("iNotify",pm.getApplicationLabel(x).toString());
        amtem.setAppName(pm.getApplicationLabel(x).toString());
        amtem.setPakageName(x.packageName);
        amtem.setAppCategory(x.packageName);
        amtem.setDate(date);
        Log.d("iNotify","date..................." + date);


            list.add(amtem);
    }

        appInfoInsert(list);



    }

    public int appCountGetToday()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        Log.d("inotify","app count------" + (int) applicationdbHelper.appCountGetToday());
        return (int) applicationdbHelper.appCountGetToday();
    }

    public int allAppCountAVG()
    {
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(c1);
        applicationDbHelper.allAppCountGet();
        return applicationDbHelper.allAppCountGet();
    }

    public List<ApplicationInfoModel> mySocialAppTodayGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.mySocialAppTodayGet();
    }

    public List<ApplicationInfoModel> mySocialAppAVGGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.mySocialAppAllGet();
    }


    public List<ApplicationInfoModel> mySPhotograpyAppTodayGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myPhotograpyAppTodayGet();
    }

    public List<ApplicationInfoModel> mySPhotograpyAppAVGGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myPhotograpyAppAVGGet();
    }

    public List<ApplicationInfoModel> myPersonalizationAppTodayGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myPersonalizationAppTodayGet();
    }

    public List<ApplicationInfoModel> myPersonalizationAppAVGGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myPersonalizationAppAllGet();
    }

    public List<ApplicationInfoModel> myGamingAppTodayGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myGamingAppTodayGet();
    }

    public List<ApplicationInfoModel> myGamingAppAVGGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myGamingAppAllGet();
    }

    public List<ApplicationInfoModel> myCommunicationAppTodayGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myCommunicationAppGetToday();
    }

    public List<ApplicationInfoModel> myCommunicationAppAVGGet()
    {

        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myCommunicationAppGetAll();
    }

    public List<ApplicationInfoModel> myMusicVideoAppTodayGet()
    {
        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myMusicVideoAppTodayGet();
    }

    public List<ApplicationInfoModel> myMusicVideoAppAVGGet()
    {
        ApplicationDbHelper applicationdbHelper = new ApplicationDbHelper(c1);
        return applicationdbHelper.myMusicVideoAppAllGet();
    }


    public int commonSocialAppTodayCount()
    {

        List<ApplicationInfoModel> mySocialApps= this.mySocialAppTodayGet();

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

        return myCommonAppCount;
    }

    public int commonPersonalizationAppTodayCount()
    {

        List<ApplicationInfoModel> myPersonalizationApps= this.myPersonalizationAppTodayGet();

        TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<ApplicationInfoModel> topPersonalizationApps = topAppsHelper.topAppPersonalization();


        List<ApplicationInfoModel> commonPersonalizationApps = new ArrayList<>();
        for(ApplicationInfoModel tmp1: topPersonalizationApps) {
            for(ApplicationInfoModel tmp2: myPersonalizationApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    ApplicationInfoModel applicationInfoModelT = new ApplicationInfoModel();
                    applicationInfoModelT.setAppName(tmp1.getAppName());
                    commonPersonalizationApps.add(applicationInfoModelT);}
            }
        }

//        for (ApplicationInfoModel xx :commonSocialApps){
//
//            Log.d("inotify","CCCCCCCCCCCCCCCCCCCCCCCCCCCCC"+xx.getAppName());
//        }
        int myCommonAppCount=commonPersonalizationApps.size();

        Log.d("inotify","common social app count = " + myCommonAppCount);
        return myCommonAppCount;
    }


    public int commonPhotograpyAppTodayCount()
    {

        List<ApplicationInfoModel> myPhotographyApps= this.mySPhotograpyAppTodayGet();
        TopAppsHelper topAppsHelper = new TopAppsHelper(c1);
        List<ApplicationInfoModel> topPhotograpyApps = topAppsHelper.topAppPhotograpy();


        List<ApplicationInfoModel> commonPhotograpyApps = new ArrayList<>();
        for(ApplicationInfoModel tmp1: topPhotograpyApps) {
            for(ApplicationInfoModel tmp2: myPhotographyApps) {
                if(tmp1.getAppName().compareTo(tmp2.getAppName()) == 0) {
                    ApplicationInfoModel applicationInfoModelT = new ApplicationInfoModel();
                    applicationInfoModelT.setAppName(tmp1.getAppName());
                    commonPhotograpyApps.add(applicationInfoModelT);}
            }
        }

//        for (ApplicationInfoModel xx :commonSocialApps){
//
//            Log.d("inotify","CCCCCCCCCCCCCCCCCCCCCCCCCCCCC"+xx.getAppName());
//        }
        int myCommonAppCount=commonPhotograpyApps.size();

        return myCommonAppCount;
    }


    public int commonGamingAppTodayCount()
    {

        List<ApplicationInfoModel> myGamingApps= this.myGamingAppTodayGet();

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

        return myCommonAppCountGaming;
    }

    public int commonMusicVideoAppTodayCount()
    {

        List<ApplicationInfoModel> myMusicVideoApps= this.myMusicVideoAppTodayGet();

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

    public int commonCommunicationAppTodayCount()
    {

        List<ApplicationInfoModel> myCommunicationApps= this.myCommunicationAppTodayGet();

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

    public int socialAppCountToday()
    {
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(c1);
        applicationDbHelper.socialAppCountTodayGet();
        Log.d("inotify","social app count" +  applicationDbHelper.socialAppCountTodayGet());
        return applicationDbHelper.socialAppCountTodayGet();
    }

    public int communicationAppCountToday()
    {
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(c1);
        applicationDbHelper.communicationAppCountTodayGet();
        return applicationDbHelper.communicationAppCountTodayGet();
    }

    public int gamingAppCountToday()
    {
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(c1);
        applicationDbHelper.gamingAppCountTodayGet();
        return applicationDbHelper.gamingAppCountTodayGet();
    }

    public int personalizationppCountToday()
    {
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(c1);
        applicationDbHelper.PersonalizationppCountTodayGet();
        return applicationDbHelper.PersonalizationppCountTodayGet();
    }

    //packagename to application name
    // package to catergory
}
