package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.util.ArrayList;

public class UserAttentivnessDbHelper extends MainDbHelp {

    public UserAttentivnessDbHelper(Context context) {
        super(context);
    }

    public boolean UserAttentivnessInsert(String id, String application, Double attentivnessvalue) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NID, id);
        contentValues.put(TbColNames.APPLICATION, application);
        contentValues.put(TbColNames.ATTENTIVNESSVALUE, attentivnessvalue);

        long result = db.insert(TbNames.USERATTENTIVNESS_TABLE, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else return true;
    }

    String[] ans = new String[30];

    public String[] View_Attentivness() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TbNames.USERATTENTIVNESS_TABLE, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ans[2] = cursor.getString(2);
                ans[3] = cursor.getString(3);
            }

        }

        return ans;


    }


//    public String[] TotalAttentivness(String Appname){
//        String[] list= new String[30];
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select *,sum(ATTENTIVNESSVALUE)  from " + TbNames.USERATTENTIVNESS_TABLE+" where APPLICATION =\"" + Appname + "\"", null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                list[1] = cursor.getString(0);
//                list[2] = cursor.getString(1);
//                list[3] = cursor.getString(2);
//                list[4] = cursor.getString(3);
//            }
//
//        }
//        return list;
//    }

    //Get the attentivness of a particulr notification
    public String getattentivness(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.USERATTENTIVNESS_TABLE + " where NID =\"" + id + "\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(3);
            }
            res.close();
        }
        return null;

    }

    public boolean tptalAttentivnessInsert(String Appname, double totalAttentivness, double totalattentivnesspercentage) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.APPLICATION, Appname);
        contentValues.put(TbColNames.TOTALATTENTIVNESS, totalAttentivness);
        contentValues.put(TbColNames.TOTALATTENTIVNESSPERCENTAGE, totalattentivnesspercentage);


        long result = db.insert(TbNames.ATTENTIVNESSPERAPP_TABLE, null, contentValues);
        db.close();
        return result != -1;

    }

    //Get the cumilative attentivness
    public String AttentivnessperAppGet(String Appname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE + " where APPLICATION =\"" + Appname + "\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(2);
            }
            res.close();
        }
        return null;


    }

    public boolean CheckAttentivness(String Appname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE + " where APPLICATION =\"" + Appname + "\"", null);
        if (res.getCount() > 0) {
            Log.d("a", "record exists");
            return true;
        }
        return false;

    }

    public boolean updateaattentivnessperApp(String Appname, double totalattentivness, double totalattentivnesspercentage) {
        Log.d("inotify(^_^) ", "tottal attentivness in update method " + totalattentivness);
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TbColNames.TOTALATTENTIVNESS, totalattentivness);
        contentValues.put(TbColNames.TOTALATTENTIVNESSPERCENTAGE, totalattentivnesspercentage);

        String where = "APPLICATION = ?";
        String[] whereargs = new String[]{String.valueOf(Appname)};
        long res = db.update(TbNames.ATTENTIVNESSPERAPP_TABLE, contentValues, where, whereargs);
        db.close();
        return res != -1;

    }

    public double CumilativeAttentivnessValueGet() {
        double cumilativeAttentivness = 0.0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select sum(" + TbColNames.TOTALATTENTIVNESS + " ) as Total from " + TbNames.ATTENTIVNESSPERAPP_TABLE, null);
        if (res.moveToFirst()) {
            cumilativeAttentivness = res.getDouble(res.getColumnIndex("Total"));
            Log.d("inotify(^_^", "cumilativeAttentivness =====" + cumilativeAttentivness);

        }
        return cumilativeAttentivness;
    }

    public int CountTotalAttentivnessGet() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*)" + "as count from " + TbNames.ATTENTIVNESSPERAPP_TABLE, null);
        res.moveToFirst();
        int count = res.getInt(res.getColumnIndex("count"));
        Log.d("inotify(^_^", "count" + count);

        res.close();
        return count;
    }

    public double calculateAverageAttentivness() {
        double cumilativeAttentivness = this.CumilativeAttentivnessValueGet();
        double countAttetivness = this.CountTotalAttentivnessGet();
        double attentivnessAverage = cumilativeAttentivness / countAttetivness;
        Log.d("inotify(^_^", "attentivnessAverage" + attentivnessAverage);

        return attentivnessAverage;
    }


    public double calculateTotalAttentivness(String id, String Appname) {
        double value = 0;

        // String[]  attentivness =  this.TotalAttentivness(Appname);
        // String Application = attentivness[3];
        // String totalAttentivness = attentivness[4];
        String attentivnessPerParticularNotification = this.getattentivness(id);

        //for freash values
        // double totalAttentivnessValue = Double.parseDouble(totalAttentivness);
        double attentivnessPerParticulrNotificationValue = Double.parseDouble(attentivnessPerParticularNotification);
        boolean attentibvnessexistemce = this.CheckAttentivness(Appname);
        Log.d("inotify(^_^", "attentivnessPerParticulrNotificationValue======== " + attentivnessPerParticulrNotificationValue);
        Log.d("inotify(^_^", "attentibvnessexistemce=====" + attentibvnessexistemce);

        //  Log.d("inotify(^_^" , "totalAttentivnessValue" + totalAttentivnessValue);
        // Log.d("inotify(^_^" , "atttentivnessperAppValue" + atttentivnessperAppValue);


        //true = exists
        if (attentibvnessexistemce) {
            Log.d("inotify(^_^)", "update");
            double currentToallAttentivnessValue = Double.parseDouble(this.AttentivnessperAppGet(Appname));
            double updatedAtttentivnessPerAppValue = currentToallAttentivnessValue + attentivnessPerParticulrNotificationValue;

            double attentivnessavg = this.calculateAverageAttentivness();
            double totalAttentivnessPercentage = (attentivnessPerParticulrNotificationValue / attentivnessavg) * 100;
            this.updateaattentivnessperApp(Appname, updatedAtttentivnessPerAppValue, totalAttentivnessPercentage);

            Log.d("inotify(^_^", "currentTotalAttentivness =====" + currentToallAttentivnessValue);
            Log.d("inotify(^_^", "updatedAtttentivnessPerAppValue ====== " + updatedAtttentivnessPerAppValue);
            Log.d("inotify(^_^)", "totalAttentivnessPercentage=========" + totalAttentivnessPercentage);


        } else {
            Log.d("inotify(^_^)", "Insert");
            double initialAttentivnessPerceantage = 100;
            this.tptalAttentivnessInsert(Appname, attentivnessPerParticulrNotificationValue, initialAttentivnessPerceantage);
            Log.d("inotify(^_^", "initialAttentivnessPerceantage ==== " + initialAttentivnessPerceantage);

        }


        return value;
    }


    public String[] AppNamesGet() {
        String AppList[];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE, null);

        if (res != null) {
            int cols = res.getColumnCount();
            AppList = new String[cols];
            while (res.moveToNext()) {
                for (int j = 0; j < cols; j++) {
                    AppList[1] = res.getString(1);
                    AppList[2] = res.getString(3);
                }

            }
            return AppList;
        }
        return null;

    }


//    public String displayData1() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE, null);
//
//        StringBuilder sb = new StringBuilder();
//        while (res.moveToNext()) {
//            String Appname = res.getString(1);
//            String AttentivnessValue = res.getString(2);
//            String AttenotvnessPercenatge = res.getString(3);
//            // sb.append(Appname).append(";").append(AttentivnessValue).append(";").append(AttenotvnessPercenatge).append(";").append("_");
//        }
//        Log.d("inotifyCCC", sb.toString());
//        System.out.flush();
//        return sb.toString();
//
//    }

    //String[] ans = new String[200];
    ArrayList<String> ansarraylist = new ArrayList<>();

    public ArrayList displayData() {
        ansarraylist.add("");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select application ,totalattentivnesspercentage  from " + TbNames.ATTENTIVNESSPERAPP_TABLE , null);

        if (cursor != null) {
            int count2 = 1;
            while (cursor.moveToNext()) {
                for (int count = 0; count < 2; count++) {
                    if (cursor.getString(count) == null) {
                        ansarraylist.add("");
                    } else
                        ansarraylist.add(cursor.getString(count));
                    count2 = count2 + 1;
                }
            }
           ansarraylist.set(0, Integer.toString(count2));
            Log.d("inotifyCC" , "inotify456789 " +ansarraylist );
        }

        return ansarraylist;
    }

        //Check the appLunch event
//   func application (application:UIApplication , didReciveRemoteNotification UserInfo: [NSObject :AnyObject])
//    {
//        if ( application.applicationState == UIApplicationState.Inactive || application.applicationState == UIApplicationState.Background ){
//            print("opened from a push notification when the app was on background");
//        }else{
//            print("opened from a push notification when the app was on foreground");
//        }
//    }

}