package com.example.inotify.services;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.CharacteristicFinalDbHelper;
import com.example.inotify.dbHelpers.TopAppDbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.AttributeCountHelper;
import com.example.inotify.helpers.CalenderEventHelper;
import com.example.inotify.helpers.CallUsageHelper;
import com.example.inotify.helpers.CommonAppCountHelper;
import com.example.inotify.helpers.ContactsHelper;
import com.example.inotify.helpers.DataUsageHelper;
import com.example.inotify.helpers.ScreenOnTimeHelper;
import com.example.inotify.helpers.TopAppsHelper;

public class UserCharacteristics_service extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {

        //get all applications in the phone
        ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
        applicationsHelper.saveCurrentPhoneAppsOnAvailability();

        //get top apps from play store that is firebase
        // and save to phone topapps db
        if (!TopAppDbHelper.getInstance(getApplicationContext()).cheackAvailability(TbNames.TOPAPPS_TABLE)) {
            TopAppsHelper topAppsHelper =new TopAppsHelper(this);
            topAppsHelper.readData(topAppModel -> {
                TopAppDbHelper topAppDbHelper =  new TopAppDbHelper(getApplicationContext());
                topAppDbHelper.insert(topAppModel);
            });
        }

        // updates application table catergory col -- runs every time
        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
        applicationDbHelper.appCategoryUpdate(applicationDbHelper.getAppPackage());

        AppUsageHelper appUsageHelper = new AppUsageHelper(this);
        appUsageHelper.saveTodaysAppUsagesOnAvailability();

        ContactsHelper contactsHelper = new ContactsHelper(this);
        contactsHelper.ContactsCountInsertOnAvailability();

        CalenderEventHelper calenderEventHelper = new CalenderEventHelper(this);
        calenderEventHelper.calenderEventCount_insertOnAvailability();

        CallUsageHelper callUsageHelper = new CallUsageHelper(this);
        callUsageHelper.saveTodayTotalCallDurationToDbOnAvailability();

        DataUsageHelper dataUsageHelper = new DataUsageHelper(this);
        dataUsageHelper.getTotalDataUsagAvailability();

        ScreenOnTimeHelper screenOnTimeHelper = new ScreenOnTimeHelper(this);
        screenOnTimeHelper.screenOnTimeTodayGet();

        AttributeCountHelper attributeCountHelper = new AttributeCountHelper(this);
        attributeCountHelper.atrributeCountInsertOnAvailability();

        CommonAppCountHelper commonAppCountHelper = new CommonAppCountHelper(this);
        commonAppCountHelper.characteristicsInsertOnAvailability();


        if(! CharacteristicFinalDbHelper.getInstance(this).cheackAvailability(TbNames.CHARACTERISTICSFINAL_TABLE))
        {
            CharacteristicFinalDbHelper characteristicFinalDbHelper = new CharacteristicFinalDbHelper(getApplicationContext());
            characteristicFinalDbHelper.characteristicsInsert();
        }


        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;


    }


}
