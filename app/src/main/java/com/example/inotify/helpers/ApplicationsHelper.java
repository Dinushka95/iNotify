package com.example.inotify.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.inotify.configs.TbNames;
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
        return ApplicationDbHelper.getInstance(c1).appInfoGet();
    }

    public boolean appInfoInsert(List<ApplicationInfoModel> appInfo) {

            return ApplicationDbHelper.getInstance(c1).appInfoInsert(appInfo);

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
        amtem.setPackageName(x.packageName);
        amtem.setAppCategory(x.packageName);
        amtem.setDate(date);
        Log.d("iNotify","date..................." + date);


            list.add(amtem);
    }

            appInfoInsert(list);




    }

    public int appCountGetToday()
    {
        return ApplicationDbHelper.getInstance(c1).appCountGetToday();
    }

    public int allAppCountAVG()
    {
        return ApplicationDbHelper.getInstance(c1).allAppCountGet();
    }

    public List<ApplicationInfoModel> mySocialAppTodayGet()
    {

        return ApplicationDbHelper.getInstance(c1).mySocialAppTodayGet();
    }

    public List<ApplicationInfoModel> mySocialAppAVGGet()
    {

        return ApplicationDbHelper.getInstance(c1).mySocialAppAllGet();
    }


    public List<ApplicationInfoModel> mySPhotograpyAppTodayGet()
    {

        return ApplicationDbHelper.getInstance(c1).myPhotograpyAppTodayGet();
    }

    public List<ApplicationInfoModel> mySPhotograpyAppAVGGet()
    {
        return ApplicationDbHelper.getInstance(c1).myPhotograpyAppAVGGet();
    }

    public List<ApplicationInfoModel> myPersonalizationAppTodayGet()
    {

        return ApplicationDbHelper.getInstance(c1).myPersonalizationAppTodayGet();
    }

    public List<ApplicationInfoModel> myPersonalizationAppAVGGet()
    {
        return ApplicationDbHelper.getInstance(c1).myPersonalizationAppAllGet();
    }

    public List<ApplicationInfoModel> myGamingAppTodayGet()
    {
        return ApplicationDbHelper.getInstance(c1).myGamingAppTodayGet();
    }

    public List<ApplicationInfoModel> myGamingAppAVGGet()
    {
        return ApplicationDbHelper.getInstance(c1).myGamingAppAllGet();
    }

    public List<ApplicationInfoModel> myCommunicationAppTodayGet()
    {
        return ApplicationDbHelper.getInstance(c1).myCommunicationAppGetToday();
    }

    public List<ApplicationInfoModel> myCommunicationAppAVGGet()
    {
        return ApplicationDbHelper.getInstance(c1).myCommunicationAppGetAll();
    }

    public List<ApplicationInfoModel> myMusicVideoAppTodayGet()
    {
        return ApplicationDbHelper.getInstance(c1).myMusicVideoAppTodayGet();
    }

    public List<ApplicationInfoModel> myMusicVideoAppAVGGet()
    {
        return ApplicationDbHelper.getInstance(c1).myMusicVideoAppAllGet();
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
        return ApplicationDbHelper.getInstance(c1).appGet(packageName);

    }

    public int socialAppCountToday()
    {
       return ApplicationDbHelper.getInstance(c1).socialAppCountTodayGet();
    }

    public int communicationAppCountToday()
    {
       return ApplicationDbHelper.getInstance(c1).communicationAppCountTodayGet();
    }

    public int gamingAppCountToday()
    {
        return ApplicationDbHelper.getInstance(c1).gamingAppCountTodayGet();
    }

    public int personalizationppCountToday()
    {
        return ApplicationDbHelper.getInstance(c1).PersonalizationppCountTodayGet();
    }


    public void  saveCurrentPhoneAppsOnAvailability()
    {
        if(! ApplicationDbHelper.getInstance(c1).cheackAvailability(TbNames.APPLICATIONS_TABLE))
        {
            this.saveCurrentPhoneApps();
        }

    }

    //packagename to application name
    // package to catergory
}
