package com.example.inotify.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.inotify.dbHelpers.INotifiyActiveAppsDbHelper;
import com.example.inotify.models.ApplicationInfoModel;

import java.util.List;

public class INotifyActiveAppsHelper {

    private Context c1;

    public INotifyActiveAppsHelper(Context context) {
    c1=context;
    }

    public List<String> getINotifyActiveApps(){
        return  INotifiyActiveAppsDbHelper.getInstance(c1).getINotifyActiveAppsOnly();
    }

    public boolean isExisINotifyActiveApps(String packName){
        return  INotifiyActiveAppsDbHelper.getInstance(c1).packNameisExisCheck(packName);
    }

    public List<ApplicationInfoModel> getAllINotifyActiveApps(){
        return  INotifiyActiveAppsDbHelper.getInstance(c1).getALLINotifyActiveApps();
    }

    public boolean setNewActiveApp(String packageName){

        if(INotifiyActiveAppsDbHelper.getInstance(c1).packNameisExisCheck(packageName)){
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

            return INotifiyActiveAppsDbHelper.getInstance(c1).insertNewActiveApp(packageName, appName);
        }

    }

    public boolean changeState(String packageName,Boolean newState){

        String value="";
        if (newState){
            value="true";
        }else{
            value="false";
        }
        return INotifiyActiveAppsDbHelper.getInstance(c1).update(packageName,value);
    }

}
