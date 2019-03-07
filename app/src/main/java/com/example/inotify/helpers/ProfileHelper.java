package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.ProfileSqlLiteDbHelper;
import com.example.inotify.models.ProfileModel;

public class ProfileHelper {

    private Context c1;

    public ProfileHelper(Context context) {
        c1=context;
    }

    public boolean insert(ProfileModel profileModel){
        ProfileSqlLiteDbHelper profileSqlLiteDbHelper = new ProfileSqlLiteDbHelper(c1);
        return profileSqlLiteDbHelper.insert(profileModel);
    }

    public ProfileModel get(){

        ProfileSqlLiteDbHelper profileSqlLiteDbHelper = new ProfileSqlLiteDbHelper(c1);
        return profileSqlLiteDbHelper.get();

    }
    public boolean profileisExisCheck(){
        ProfileSqlLiteDbHelper profileSqlLiteDbHelper = new ProfileSqlLiteDbHelper(c1);
        return profileSqlLiteDbHelper.profileisExisCheck();
    }
}
