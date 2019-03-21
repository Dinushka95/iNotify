package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

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


    public String[] TotalAttentivness(String Appname){
       String[] list= new String[30];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select *,sum(ATTENTIVNESSVALUE)  from " + TbNames.USERATTENTIVNESS_TABLE+" where APPLICATION =\"" + Appname + "\"", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                list[1] = cursor.getString(0);
                list[2] = cursor.getString(1);
                list[3] = cursor.getString(2);
                list[4] = cursor.getString(3);
            }

        }
        return list;
    }

    //Get the attentivness of a particulr notification
    public String getattentivness(String id )
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.USERATTENTIVNESS_TABLE +" where NID =\"" + id + "\"", null);
        if(res != null)
        {
            if(res.moveToFirst())
            {
                return res.getString(3);
            }
            res.close();
        }
       return null;

    }

    public boolean tptalAttentivnessInsert(String Appname , double totalAttentivness ,double totalattentivnesspercentage)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.APPLICATION ,Appname);
        contentValues.put(TbColNames.TOTALATTENTIVNESS ,totalAttentivness);
        contentValues.put(TbColNames.TOTALATTENTIVNESSPERCENTAGE,totalattentivnesspercentage);


        long result = db.insert(TbNames.ATTENTIVNESSPERAPP_TABLE , null,contentValues);
        db.close();
        return result != -1;

    }

    //Get the cumilative attentivness
    public String AttentivnessperAppGet(String Appname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE +" where APPLICATION =\"" + Appname + "\"", null);
        if(res != null)
        {
            if(res.moveToFirst())
            {
                return res.getString(2);
            }
            res.close();
        }
        return null;


    }
    public  boolean CheckAttentivness(String Appname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE +" where APPLICATION =\"" + Appname + "\"", null);
        if(res.getCount()>0)
        {
            Log.d("a", "record exists");
            return true;
        }
        return false;

    }

    public boolean updateaattentivnessperApp(String Appname , double totalattentivness ,double totalattentivnesspercentage)
    {
        Log.d("inotify(^_^) " , "tottal attentivness in update method " + totalattentivness );
        SQLiteDatabase db =this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TbColNames.TOTALATTENTIVNESS ,totalattentivness);
        contentValues.put(TbColNames.TOTALATTENTIVNESSPERCENTAGE ,totalattentivnesspercentage);

        String where = "APPLICATION = ?";
        String[] whereargs = new String[] {String.valueOf(Appname)};
        long res = db.update(TbNames.ATTENTIVNESSPERAPP_TABLE , contentValues , where , whereargs );
        db.close();
        return res != -1;

    }

    public double calculateTotalAttentivness(String id,String Appname)
    {
        double value =0;

        String[]  attentivness =  this.TotalAttentivness(Appname);
        String Application = attentivness[3];
        String totalAttentivness = attentivness[4];
        String attentivnessPerParticularNotification = this.getattentivness(id);

        //for freash values
        double totalAttentivnessValue = Double.parseDouble(totalAttentivness);
        double attentivnessPerParticulrNotificationValue = Double.parseDouble(attentivnessPerParticularNotification);
        boolean attentibvnessexistemce = this.CheckAttentivness(Appname);




        Log.d("inotify(^_^" , "attentivnessPerParticulrNotificationValue " + attentivnessPerParticulrNotificationValue);
        Log.d("inotify(^_^" , "totalAttentivnessValue" + totalAttentivnessValue);
       // Log.d("inotify(^_^" , "atttentivnessperAppValue" + atttentivnessperAppValue);

        Log.d("inotify(^_^" , "attentibvnessexistemce" + attentibvnessexistemce);


        //true = exists
        if(attentibvnessexistemce)
        {
            String currentTotalAttentivness = this.AttentivnessperAppGet(Appname);
            double currentToallAttentivnessValue = Double.parseDouble(currentTotalAttentivness);
            double updatedAtttentivnessPerAppValue = currentToallAttentivnessValue + attentivnessPerParticulrNotificationValue;

            double attentivnessavg = this.calculateAverageAttentivness();

            double totalAttentivnessPercentage = (attentivnessPerParticulrNotificationValue/attentivnessavg)*100;


            Log.d("inotify(^_^" , "currentAttentivness" + currentToallAttentivnessValue);
            Log.d("inotify(^_^" , "updatedAtttentivnessPerAppValue" + updatedAtttentivnessPerAppValue);
            Log.d("inotify(^_^)","totalAttentivnessPercentage"+ totalAttentivnessPercentage);
            //update.
            this.updateaattentivnessperApp(Appname , updatedAtttentivnessPerAppValue , totalAttentivnessPercentage);
            Log.d("inotify(^_^)" , "update");


        }
        else
        {
            //double atttentivnessperAppValue = totalAttentivnessValue + attentivnessPerParticulrNotificationValue;
            double atttentivnessperAppValue = attentivnessPerParticulrNotificationValue;
            String attenivnessPerApp = Double.toString(atttentivnessperAppValue);
            double initialAtentivnessPercentage = 100;

            this.tptalAttentivnessInsert(Appname,atttentivnessperAppValue,initialAtentivnessPercentage);
            Log.d("inotify(^_^)" , "Insert");
            Log.d("inotify(^_^" , "attenivnessPerApp" + attenivnessPerApp);
        }


    return value;
    }


    public  String[] AppNamesGet(){
        String AppList[];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE , null);

        if(res!=null)
        {
            int cols = res.getColumnCount();
            AppList = new String[cols];
            while(res.moveToNext()){
                for(int j=0; j <cols ;j++ )
                {
                    AppList[j] = res.getString(1);
                }

            }
            return AppList;
        }
       return null;

    }


    public double CumilativeAttentivnessValueGet()
    {
        double  cumilativeAttentivness= 0.0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=db.rawQuery("select sum("+TbColNames.TOTALATTENTIVNESS+" ) as Total from "+ TbNames.ATTENTIVNESSPERAPP_TABLE,null);
        if(res.moveToFirst())
        {
            cumilativeAttentivness =res.getDouble(res.getColumnIndex("Total"));
            Log.d("inotify(^_^","cumilativeAttentivness"+cumilativeAttentivness);

        }
        return cumilativeAttentivness;
    }

    public int CountTotalAttentivnessGet()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*)" + "as count from " + TbNames.ATTENTIVNESSPERAPP_TABLE ,null);
        res.moveToFirst();
        int count = res.getInt(res.getColumnIndex("count"));
        Log.d("inotify(^_^","count"+count);

        res.close();
        return count;
    }

    public double calculateAverageAttentivness()
    {
        double cumilativeAttentivness = this.CumilativeAttentivnessValueGet();
        int countAttetivness = this.CountTotalAttentivnessGet();
        double attentivnessAverage = cumilativeAttentivness/countAttetivness;
        Log.d("inotify(^_^","attentivnessAverage"+attentivnessAverage);

        return attentivnessAverage;
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