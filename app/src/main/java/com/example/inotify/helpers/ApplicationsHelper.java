package com.example.inotify.helpers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.TopAppDbHelper;
import com.example.inotify.dbHelpers.UC_SqlLiteDbHelper;
import com.example.inotify.models.AppInfoModel;

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
}
