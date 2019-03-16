package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.TopAppDbHelper;
import com.example.inotify.models.ApplicationInfoModel;

import java.util.List;

public class TopAppsHelper {

    private Context c1;
    public TopAppsHelper(Context context) {
        c1=context;
    }
//No of social media apps -- If available top apps or not
    //No of gaming apps -- If available top apps or not
    //No of music and video apps -- If available top apps or not
    //No of chatting apps -- If available top apps or not
    //check with pre define top apps


public List<ApplicationInfoModel> topAppSocial()
{
    TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
    return topAppDbHelper.topAppSocilGet();
}

    public List<ApplicationInfoModel> topAppCommunication()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppCommunicationGet();
    }

    public List<ApplicationInfoModel> topAppGaming()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppGamingGet();
    }

    public List<ApplicationInfoModel> topAppBusiness()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppBusinessGet();
    }

    public List<ApplicationInfoModel> topAppDating()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppDatingGet();
    }

    public List<ApplicationInfoModel> topAppEntertainment()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppEntertainmentGet();
    }

    public List<ApplicationInfoModel> topAppProductivity()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppProductivityGet();
    }

    public List<ApplicationInfoModel> topAppTools()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppToolGet();
    }

    public List<ApplicationInfoModel> topAppMusicVideo()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppMusicVideoGet();
    }

    public int appCountGet()
    {

        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return (int) topAppDbHelper.SocialAppCountGet();
    }



}
