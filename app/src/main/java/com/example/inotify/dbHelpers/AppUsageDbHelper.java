package com.example.inotify.dbHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.AppUsageModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppUsageDbHelper extends MainDbHelp {

    public AppUsageDbHelper(Context context) {
        super(context);
    }

    public boolean insert(List<AppUsageModel> appUsageModelList) {

        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        String sql = "INSERT INTO " + TbNames.APPUSAGE_TABLE + "(DATE,TIME,PACKAGENAME,APPNAME,APPCATEGORY,USAGETIME) VALUES (?,?,?);";
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();
        try {
            for (AppUsageModel appUsageModel : appUsageModelList) {
                statement.clearBindings();
                statement.bindString(1,date);
                statement.bindString(2,time);
                statement.bindString(3,appUsageModel.getAppName());
                statement.bindString(4,appUsageModel.getAppCategory());
                statement.bindString(5, appUsageModel.getAppCategory());
                statement.bindString(6,appUsageModel.getAppUsageId());
                statement.execute();
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }



}
