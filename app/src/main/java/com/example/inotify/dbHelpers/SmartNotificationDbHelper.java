package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


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

    public SmartNotificationDbHelper(Context context) {

        super(context);
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

                    String day = res.getString(res.getColumnIndex("SNS_DAY"));
                    String cday = "";
                    if (day.equals("Monday")) {
                        cday = "1";
                    }
                    if (day.equals("Tuesday")) {
                        cday = "2";
                    }
                    if (day.equals("Wednesday")) {
                        cday = "3";
                    }
                    if (day.equals("Thursday")) {
                        cday = "4";
                    }
                    if (day.equals("Friday")) {
                        cday = "5";
                    }
                    if (day.equals("Saturday")) {
                        cday = "6";
                    }
                    if (day.equals("Sunday")) {
                        cday = "7";
                    }
                    snsModel.setDay(cday);

                    snsModel.setTime(res.getString(3));

                    String busyornot = res.getString(4);
                    String cbusyornot = "";
                    if (busyornot.equals("Busy")) {
                        cbusyornot = "1";
                    }
                    if (busyornot.equals("NotBusy")) {
                        cbusyornot = "1";
                    }
                    snsModel.setViewability(cbusyornot);

                    String attentiviness = res.getString(5);
                    snsModel.setAttentiviness(attentiviness);

                    String userchaacteristics = res.getString(6);
                    String cuserchaacteristics = "";
                    if (userchaacteristics.equals("social")) {
                        cuserchaacteristics = "1";
                    }
                    if (userchaacteristics.equals("professional")) {
                        cuserchaacteristics = "2";
                    }
                    if (userchaacteristics.equals("friendliness")) {
                        cuserchaacteristics = "3";
                    }
                    if (userchaacteristics.equals("gaming")) {
                        cuserchaacteristics = "4";
                    }
                    if (userchaacteristics.equals("oldtechnology")) {
                        cuserchaacteristics = "5";
                    }
                    snsModel.setUserchacteristics(cuserchaacteristics);

                    String notificationtype = res.getString(7);
                    String cnotificationtype = "";
                    if (notificationtype.equals("Mobile")) {
                        cnotificationtype = "1";
                    }
                    if (notificationtype.equals("mobile")) {
                        cnotificationtype = "1";
                    }
                    snsModel.setNotificationtype(cnotificationtype);

                    String appname = res.getString(8);
                    String cappname = "";
                    if (appname.equals("com.example.dinu.testa")) {
                        cappname = "1";
                    }
                    if (appname.equals("com.example.dinu.testb")) {
                        cappname = "2";
                    }
                    if (appname.equals("com.example.dinu.testc")) {
                        cappname = "3";
                    }
                    if (appname.equals("com.example.dinu.testd")) {
                        cappname = "4";
                    }
                    if (appname.equals("com.google.android.apps.messaging")) {
                        cappname = "5";
                    }
                    snsModel.setPackagename(cappname);


                    String vtime = res.getString(9);
                    snsModel.setVtime(vtime);

                    Al.add(snsModel);

                } while (res.moveToNext());
            }
            res.close();
        }
        return Al;

    }


}
