package com.example.inotify.setting;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsInformation {

    public Context context;

    public ApplicationsInformation(Context c1) {
        context=c1;
    }

    public ArrayList<String> getApplicationList(){

        //get a list of installed apps.
        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        return null;
    }
}
