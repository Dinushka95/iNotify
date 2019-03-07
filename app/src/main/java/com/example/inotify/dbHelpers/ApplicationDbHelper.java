package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbNames;
import com.example.inotify.models.AppInfoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApplicationDbHelper extends MainSqlliteOpenHelp{


    public static final String APPLICATION_ID = "applicationId";
    public static final String APPNAME = "appname";
    public static final String APPCATEGORY = "appcategory";
    public static final String APPPACKAGE = "APPPACKAGE";


    public ApplicationDbHelper(Context context) {
        super(context);
    }

    public List<AppInfoModel> appInfoGet()
    {
        List<AppInfoModel> listAppInfoModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.APPLICATIONS_TABLE, null);
        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    //SNS_SNSModel snsModel = new SNS_SNSModel();
                    AppInfoModel appInfoModel = new AppInfoModel();
                    Log.d("inotify","mmmmmmmmmmmmmmmmmm");
                    appInfoModel.setAppName( res.getString(res.getColumnIndex("APPNAME")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPPACKAGE")));
                    appInfoModel.setPakageName( res.getString(res.getColumnIndex("APPCATEGORY")));


                    listAppInfoModels.add(appInfoModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return listAppInfoModels;
        //Log.d("list of apps", listAppInfoModels)

    }
    public boolean appInfoInsert(List<AppInfoModel> appInfo) {



       // String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (AppInfoModel value : appInfo)
        {
            contentValues.put(APPNAME, value.getAppName());
            contentValues.put(APPPACKAGE, value.getPakageName());
            contentValues.put(APPCATEGORY, value.getAppCategory());
            db.insert(TbNames.APPLICATIONS_TABLE, null, contentValues);
        }


        db.close();
        return true;
    }

//    public long appCountGet() {
//
//        //same for charging above need correction
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select count(APPNAME) as appCount from "+APPLICATIONS_TABLE, null);
//        if (res != null) {
//            if ((res.moveToFirst())){
//                return res.getLong(res.getColumnIndex("appCount"));
//            }
//        }
//        Objects.requireNonNull(res).close();
//        db.close();
//
//        return 0;
//    }

}
