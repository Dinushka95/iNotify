package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.ScreenOnTimeHelper;
import com.example.inotify.views.views.Extraversion;
import com.example.inotify.views.views.Openness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CharacteristicFinalDbHelper extends MainDbHelp {
    private  Context c1;
    public CharacteristicFinalDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public boolean charcateristicsInser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Openness openness = new Openness();
        //long characterOpenness = openness.displayOpenness();
        Extraversion extraversion = new Extraversion();
        //long characterExtravertion = extraversion.displayExtraversion();
        //Log.d("inotify","characterssssssssssssss" + characterOpenness);

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);
        int appCount = applicationsHelper.appCountGetToday();
        ScreenOnTimeHelper screenOnTimeHelper = new ScreenOnTimeHelper(c1);
        int screenOnTimeCount = screenOnTimeHelper.ScreenOnTimeTodayGet();
        ChargerHelper chargerHelper = new ChargerHelper(c1);
        int chageCount = chargerHelper.powerOnCount();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.APPCOUNT,appCount);
        contentValues.put(TbColNames.SCREENONTIMECOUNT,screenOnTimeCount);
        contentValues.put(TbColNames.CHARGINGCOUNT,chageCount);
        contentValues.put(TbColNames.DATE,date);

        long result = db.insert(TbNames.ATTRIBUTECOUNT_TABLE, null, contentValues);
        db.close();
        return true;
    }

}
