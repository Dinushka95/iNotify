package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.TopAppDbHelper;
import com.example.inotify.models.AppInfoModel;

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


public List<AppInfoModel> topAppSocial()
{
    TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
    return topAppDbHelper.topAppSocilGet();
}



}
