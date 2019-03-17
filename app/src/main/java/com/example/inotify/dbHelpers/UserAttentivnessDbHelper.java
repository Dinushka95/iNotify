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
                //ans[1] = cursor.getString(0);
                ans[2] = cursor.getString(2);
                ans[3] = cursor.getString(3);

                //  Log.d("cursor", "View_Attentivness " + cursor.getString(1));

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
                //  Log.d("cursor", "View_Attentivness " + cursor.getString(1));

            }

        }
        return list;
    }
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

    public boolean tptalAttentivnessInsert(String Appname , double totalAttentivness)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.APPLICATION ,Appname);
        contentValues.put(TbColNames.TOTALATTENTIVNESS ,totalAttentivness);

        long result = db.insert(TbNames.ATTENTIVNESSPERAPP_TABLE , null,contentValues);
        db.close();
        return result != -1;

    }

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
            //Record exists
            //update the record
           return true;
        }
//insert the record
        return false;

    }

    public boolean updateaattentivnessperApp(String Appname , double totalattentivness)
    {
        Log.d("inotify(^_^) " , "tottal attentivness in update method " + totalattentivness );
        SQLiteDatabase db =this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TbColNames.TOTALATTENTIVNESS ,totalattentivness);

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
        String attentivnessPerNotification = this.getattentivness(id);

        //for freash values
        double totalAttentivnessValue = Double.parseDouble(totalAttentivness);
        double attentivnessPerNotificationValue = Double.parseDouble(attentivnessPerNotification);




        boolean attentibvnessexistemce = this.CheckAttentivness(Appname);




        Log.d("inotify(^_^" , "attentivnessPerNotificationValue" + attentivnessPerNotificationValue);
        Log.d("inotify(^_^" , "totalAttentivnessValue" + totalAttentivnessValue);
       // Log.d("inotify(^_^" , "atttentivnessperAppValue" + atttentivnessperAppValue);

        Log.d("inotify(^_^" , "attentibvnessexistemce" + attentibvnessexistemce);

//        this.tptalAttentivnessInsert(Appname,atttentivnessperAppValue);

        //true = exists
        if(attentibvnessexistemce)
        {
            String currentAttentivness = this.AttentivnessperAppGet(Appname);
            double currentAttentivnessValue = Double.parseDouble(currentAttentivness);
            double updatedAtttentivnessPerAppValue = currentAttentivnessValue + attentivnessPerNotificationValue;

            Log.d("inotify(^_^" , "currentAttentivness" + currentAttentivness);
            Log.d("inotify(^_^" , "updatedAtttentivnessPerAppValue" + updatedAtttentivnessPerAppValue);
            //update.
            this.updateaattentivnessperApp(Appname , updatedAtttentivnessPerAppValue);
            Log.d("inotify(^_^)" , "update");


        }
        else
        {
            double atttentivnessperAppValue = totalAttentivnessValue + attentivnessPerNotificationValue;
            String attenivnessPerApp = Double.toString(atttentivnessperAppValue);
            this.tptalAttentivnessInsert(Appname,atttentivnessperAppValue);
            Log.d("inotify(^_^)" , "Insert");

            Log.d("inotify(^_^" , "attenivnessPerApp" + attenivnessPerApp);
        }


    return value;
    }



}