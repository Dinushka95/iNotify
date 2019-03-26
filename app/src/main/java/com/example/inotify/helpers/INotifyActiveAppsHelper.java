package com.example.inotify.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.inotify.dbHelpers.INotifiyActiveAppsDbHelper;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.NotificationModel;

import java.util.List;

public class INotifyActiveAppsHelper {

    private Context c1;

    public INotifyActiveAppsHelper(Context context) {
    c1=context;
    }

    public List<String> getINotifyActiveApps(){
        INotifiyActiveAppsDbHelper iNotifiyActiveAppsDbHelper = new INotifiyActiveAppsDbHelper(c1);
        return  iNotifiyActiveAppsDbHelper.getINotifyActiveAppsOnly();
    }

    public List<ApplicationInfoModel> getAllINotifyActiveApps(){
        INotifiyActiveAppsDbHelper iNotifiyActiveAppsDbHelper = new INotifiyActiveAppsDbHelper(c1);
        return  iNotifiyActiveAppsDbHelper.getALLINotifyActiveApps();
    }

    public boolean setNewActiveApp(String packageName){

        INotifiyActiveAppsDbHelper iNotifiyActiveAppsDbHelper = new INotifiyActiveAppsDbHelper(c1);

        if(iNotifiyActiveAppsDbHelper.packNameisExisCheck(packageName)){
            return true;
        }else {

            PackageManager pm = c1.getPackageManager();

            String appName = "";
            try {
                if (pm != null) {
                    ApplicationInfo app = c1.getPackageManager().getApplicationInfo(packageName, 0);
                    appName = (String) pm.getApplicationLabel(app);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            return iNotifiyActiveAppsDbHelper.insertNewActiveApp(packageName, appName);
        }

    }

}
