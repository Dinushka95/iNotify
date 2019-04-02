package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.helpers.ApplicationsHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonAppCountDbHelper extends  MainDbHelp {
    private Context c1;
    private static CommonAppCountDbHelper mInstance = null;

    public CommonAppCountDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public static CommonAppCountDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new CommonAppCountDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public boolean characteristicsInsert()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        ApplicationsHelper applicationsHelper = new ApplicationsHelper(c1);
        int gaming = applicationsHelper.commonGamingAppTodayCount();
        int social = applicationsHelper.commonSocialAppTodayCount();
        int musicVideo = applicationsHelper.commonMusicVideoAppTodayCount();
        int photograpy = applicationsHelper.commonPhotograpyAppTodayCount();
        int communication = applicationsHelper.commonCommunicationAppTodayCount();
        int personalize = applicationsHelper.commonPersonalizationAppTodayCount();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TbColNames.GAMING,gaming);
        contentValues.put(TbColNames.SOCIAL,social);
        contentValues.put(TbColNames.MUSICVIDEO,musicVideo);
        contentValues.put(TbColNames.PHOTOGRAPHY,photograpy);
        contentValues.put(TbColNames.COMMUNICATION,communication);
        contentValues.put(TbColNames.PERSONALIZATION,personalize);
       // contentValues.put(TbColNames.COMMUNICATION,communication);


        long result = db.insert(TbNames.COMMONAPPCOUNT_TABLE, null, contentValues);
        db.close();
        return true;
    }

    public int commonSocialAppAvg() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select AVG(TbColNames.SOCIAL) as COUNT from " + TbNames.COMMONAPPCOUNT_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("COUNT"));
            }
        }
        db.close();
        return 0;
    }

    public int commonGamingAppAvg() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select AVG(TbColNames.GAMING) as COUNT from " + TbNames.COMMONAPPCOUNT_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("COUNT"));
            }
        }
        db.close();
        return 0;
    }

    public int commonMusicVideoAppAvg() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select AVG(TbColNames.MUSICVIDEO) as COUNT from " + TbNames.COMMONAPPCOUNT_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("COUNT"));
            }
        }
        db.close();
        return 0;
    }

    public int commonPhotograpyAppAvg() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select AVG(TbColNames.PHOTOGRAPHY) as COUNT from " + TbNames.COMMONAPPCOUNT_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("COUNT"));
            }
        }
        db.close();
        return 0;
    }

    public int commonCommunicationAppAvg() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select AVG(TbColNames.COMMUNICATION) as COUNT from " + TbNames.COMMONAPPCOUNT_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("COUNT"));
            }
        }
        db.close();
        return 0;
    }

    public int commonPersonalizationAppAvg() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select AVG(TbColNames.PERSONALIZATION) as COUNT from " + TbNames.COMMONAPPCOUNT_TABLE , null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("COUNT"));
            }
        }
        db.close();
        return 0;
    }

}
