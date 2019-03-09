package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.models.AppInfoModel;

import java.util.ArrayList;
import java.util.List;

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

    public void appInfoInsert(List<AppInfoModel> appInfo) {

            ApplicationDbHelper applicationDbHelper =new ApplicationDbHelper(c1);
            applicationDbHelper.appInfoInsert(appInfo);
            applicationDbHelper.close();

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
}
