package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.ProfileDbHelper;
import com.example.inotify.models.ProfileModel;

public class ProfileHelper {

    private Context c1;

    public ProfileHelper(Context context) {
        c1 = context;
    }

    public boolean insert(ProfileModel profileModel) {
        ProfileDbHelper profileDbHelper = new ProfileDbHelper(c1);
        return ProfileDbHelper.getInstance(c1).insert(profileModel);
    }

    public ProfileModel get() {
        ProfileDbHelper profileDbHelper = new ProfileDbHelper(c1);
        return ProfileDbHelper.getInstance(c1).get();

    }

    public boolean profileisExisCheck() {
        ProfileDbHelper profileDbHelper = new ProfileDbHelper(c1);
        return ProfileDbHelper.getInstance(c1).profileisExisCheck();
    }
}
