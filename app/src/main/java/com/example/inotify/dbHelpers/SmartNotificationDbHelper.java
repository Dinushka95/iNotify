package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.inotify.configs.TbColNames;
import com.example.inotify.helpers.SmartNotificationSystemHelper;
import com.example.inotify.models.SNSModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.configs.TbColNames.SNS_APPNAME;
import static com.example.inotify.configs.TbColNames.SNS_ATTENTIVINESS;
import static com.example.inotify.configs.TbColNames.SNS_BUSYORNOT;
import static com.example.inotify.configs.TbColNames.SNS_DATE;
import static com.example.inotify.configs.TbColNames.SNS_DAY;
import static com.example.inotify.configs.TbColNames.SNS_ID;
import static com.example.inotify.configs.TbColNames.SNS_NOTIFICATIONTYPE;
import static com.example.inotify.configs.TbColNames.SNS_TIME;
import static com.example.inotify.configs.TbColNames.SNS_USERCHAACTERISTICS;
import static com.example.inotify.configs.TbColNames.SNS_VTIME;
import static com.example.inotify.configs.TbNames.SNS_TABLE;


public class SmartNotificationDbHelper extends MainDbHelp {

    private static SmartNotificationDbHelper mInstance = null;
    private  Context c1;

    public SmartNotificationDbHelper(Context context) {

        super(context);
        c1=context;
    }
    public static SmartNotificationDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new SmartNotificationDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public boolean saveData(String id, String busyornot, String attentiviness, String usercharacteristics, String notificationtype, String appname) {


        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
                (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
                Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SNS_ID, id);
        contentValues.put(SNS_DATE, date);
        contentValues.put(SNS_DAY, day);
        contentValues.put(SNS_TIME, time);
        contentValues.put(SNS_BUSYORNOT, busyornot);
        contentValues.put(SNS_ATTENTIVINESS, attentiviness);
        contentValues.put(SNS_USERCHAACTERISTICS, usercharacteristics);
        contentValues.put(SNS_NOTIFICATIONTYPE, notificationtype);
        contentValues.put(SNS_APPNAME, appname);

        long result = db.insert(SNS_TABLE, null, contentValues);
        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }

    public void updateData(String id, String vtime) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues newValues = new ContentValues();
        newValues.put(SNS_VTIME, vtime);

        db.update(SNS_TABLE, newValues, "sns_id=" + id, null);

    }

    public ArrayList<SNSModel> getALL() {

        ArrayList<SNSModel> Al = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SNS_TABLE, null);

        if (res != null) {

            if (res.moveToFirst()) {
                do {

                    SNSModel snsModel = new SNSModel();
                    snsModel.setDay(res.getString(res.getColumnIndex(TbColNames.SNS_DAY)));
                    snsModel.setTime(res.getString(res.getColumnIndex(TbColNames.SNS_TIME)));
                    snsModel.setViewability(res.getString(res.getColumnIndex(TbColNames.SNS_BUSYORNOT)));
                    snsModel.setAttentiviness(res.getString(res.getColumnIndex(TbColNames.SNS_ATTENTIVINESS)));
                    snsModel.setUserchacteristics(res.getString(res.getColumnIndex(TbColNames.SNS_USERCHAACTERISTICS)));
                    snsModel.setNotificationtype(res.getString(res.getColumnIndex(TbColNames.SNS_NOTIFICATIONTYPE)));
                    snsModel.setPackagename(res.getString(res.getColumnIndex(TbColNames.SNS_APPNAME)));
                    snsModel.setVtime(res.getString(res.getColumnIndex(TbColNames.SNS_VTIME)));

                    SmartNotificationSystemHelper smartNotificationSystemHelper = new SmartNotificationSystemHelper(c1);
                    Al.add(smartNotificationSystemHelper.convertSNSdataToNumberic(snsModel));

                } while (res.moveToNext());
            }
            res.close();
        }
        return Al;

    }


}
