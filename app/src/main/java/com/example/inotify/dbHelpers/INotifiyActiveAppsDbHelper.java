package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.NotificationModel;

import java.util.ArrayList;
import java.util.List;


public class INotifiyActiveAppsDbHelper extends MainDbHelp {

    public INotifiyActiveAppsDbHelper(Context context) {
        super(context);
    }

    public Boolean saveAppSetting(String appName) {

        if (checkNull()) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res0 = db.rawQuery("select * from settings_snactiveapps_table where APPNAME=\"" + appName + "\"", null);

            if (res0 != null) {
                if (res0.getCount() > 0) {
                    // if app name  contain in db. then get current and change value
                    String results = res0.getString(res0.getColumnIndex("STATUS"));
                    res0.close();
                    db.close();
                    if (results.equals("true")) {
                        // change to false
                        SQLiteDatabase db1 = this.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("STATUS", "false");
                        db1.update(TbNames.INOTIFYACTIVEAPPS_TABLE, contentValues, "APPNAME = ? ", new String[]{appName});
                        db1.close();
                        return true;
                    } else if (results.equals("true")) {
                        // change to true
                        SQLiteDatabase db2 = this.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("STATUS", "true");
                        db2.update(TbNames.INOTIFYACTIVEAPPS_TABLE, contentValues, "APPNAME = ? ", new String[]{appName});
                        db2.close();
                        return true;
                    } else {
                        // if db contains other than true or false
                        return false;
                    }
                } else {
                    // no app recode in db. 1st time adding data into db so create a new recode and make true by defalut
                    SQLiteDatabase db3 = this.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("APPNAME", appName);
                    contentValues.put("STATUS", "true");
                    long result1 = db3.insert(TbNames.INOTIFYACTIVEAPPS_TABLE, null, contentValues);
                    db3.close();

                    res0.close();
                    db.close();
                    if (result1 == -1)
                        return false;
                    else
                        return true;
                }
            }
        }
        return false;
    }

    public Boolean checkNull() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res0 = db.rawQuery("select * from settings_snactiveapps_table", null);

        if (res0 != null) {
            if (res0.getCount() > 0) {
                res0.close();
                return true;
            }
        }
        res0.close();
        return false;
    }

    public List<String> getINotifyActiveApps(){
        List<String> stringList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.INOTIFYACTIVEAPPS_TABLE+" WHERE status = \"true\"" , null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {
                    stringList.add(res.getString(res.getColumnIndex(TbColNames.PACKAGENAME)));
                } while (res.moveToNext());
            }
            res.close();
        }
        return stringList;
    }
}
