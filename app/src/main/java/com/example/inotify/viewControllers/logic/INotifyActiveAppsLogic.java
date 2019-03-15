package com.example.inotify.viewControllers.logic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.inotify.models.ApplicationInfoModel;

import java.util.ArrayList;
import java.util.List;

public class INotifyActiveAppsLogic {

    public Context context;

    public INotifyActiveAppsLogic(Context c1) {
        context = c1;
    }

    public List<ApplicationInfoModel> getApplicationList() {

        List<ApplicationInfoModel> packageList = new ArrayList<>();
        //get a list of installed apps.
        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {
            ApplicationInfoModel applicationInfoModel = new ApplicationInfoModel();
            String x = applicationInfo.packageName.toString();
            applicationInfoModel.setPakageName(x);
            packageList.add(applicationInfoModel);
        }
        // need to filter the apps to a the right apps because lot of unwanted stuff are  coming
        // only display apps which are capable of sending notifications

        // or only enable for apps which send notifications
        return packageList;
    }

    public void createTable() {

    }

    public ArrayList<String> getVales() {
        return null;
    }

    public boolean nullcheck() {
        return false;
    }

    public void updateValue(String key, String value) {

    }


    // check if db tb null
    // all objects create
    // tb null check
    // get all values list
    // default all off
    // turn the values in table to on
    // display object


}
