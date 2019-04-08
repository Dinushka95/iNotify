package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anychart.chart.common.dataentry.DataEntry;
import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.util.ArrayList;
import java.util.List;

public class AttentivnessPerAppDbHelper extends MainDbHelp{

    private static AttentivnessPerAppDbHelper mInstance = null;


    private Context c1;
    public AttentivnessPerAppDbHelper(Context context) {
        super(context);
        c1= context;

    }

    public static  AttentivnessPerAppDbHelper getInstance(Context context)
    {
        if(mInstance ==null){
            mInstance = new AttentivnessPerAppDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }





    //Insert method for AttentivnessPerApp table
    public boolean totalAttentivnessPerAppInsert(String packageName, double totalAttentivness, double totalattentivnesspercentage) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.APPLICATION, packageName);
        contentValues.put(TbColNames.TOTALATTENTIVNESS, totalAttentivness);
        contentValues.put(TbColNames.TOTALATTENTIVNESSPERCENTAGE, totalattentivnesspercentage);


        long result = db.insert(TbNames.ATTENTIVNESSPERAPP_TABLE, null, contentValues);
        db.close();
        return result != -1;

    }

    //Update method for AttentivnessPerApp table
    public boolean updateaAttentivnessPerApp(String packageName, double totalattentivness, double totalattentivnesspercentage) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TbColNames.TOTALATTENTIVNESS, totalattentivness);
        contentValues.put(TbColNames.TOTALATTENTIVNESSPERCENTAGE, totalattentivnesspercentage);

        String where = "APPLICATION = ?";
        String[] whereargs = new String[]{String.valueOf(packageName)};
        long res = db.update(TbNames.ATTENTIVNESSPERAPP_TABLE, contentValues, where, whereargs);
        db.close();
        return res != -1;

    }

    //Check whather a particular record(Appname) exists in the table or not
    public boolean CheckAttentivness(String packageName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE + " where APPLICATION =\"" + packageName + "\"", null);
        if (res.getCount() > 0) {
            Log.d("a", "record exists");
            return true;
        }
        return false;

    }

//Check wheather theare are records in AttentivnessPerApp table or not
   public int CheckExistance()
    {
        Log.d("inotify " , " inotify =================================Check Exixtance"  );
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*)" + "as count from " + TbNames.USERATTENTIVNESS_TABLE, null);
        Log.d("inotify " , " inotify =================================NoOfRowa" + res);
        res.moveToFirst();
        int count = res.getInt(res.getColumnIndex("count"));

        res.close();
        return count;


    }

    //Get the  attentivness value for a particulr app in attentivnessPerApp table
    public String AttentivnessperAppGet(String packageName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE + " where APPLICATION =\"" + packageName + "\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                return res.getString(2);
            }
            res.close();
        }
        return null;


    }

    //Get the cumilative attentivness Value for all the apps in the table
    public double CumilativeAttentivnessValueGet() {
        double cumilativeAttentivness = 0.0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select sum(" + TbColNames.TOTALATTENTIVNESS + " ) as Total from " + TbNames.ATTENTIVNESSPERAPP_TABLE, null);
        if (res.moveToFirst()) {
            cumilativeAttentivness = res.getDouble(res.getColumnIndex("Total"));

        }
        return cumilativeAttentivness;
    }



//Calculate the total attentivnessvalue except for a given app
    public double totalattentivnessSumGet(String packageName) {

        double CumilativeAttentivnessforAll = this.CumilativeAttentivnessValueGet();
        double attentivnessPerApp = Double.parseDouble(this.AttentivnessperAppGet(packageName));
        double cumilativeAttentivness = CumilativeAttentivnessforAll - attentivnessPerApp;
        Log.d("inotify " , "attentivness1234567890-" +cumilativeAttentivness);
        return cumilativeAttentivness;

    }


    public double calculateTotalAttentivness(String id, String PackageName) {
        double value =0;

        UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(c1);
        double attentivnessPerParticulrNotification = Double.parseDouble(userAttentivnessDbHelper.getattentivness(id));
        boolean attentivnessExistence = this.CheckAttentivness(PackageName);
        Log.d("inotify " , "AttentivnessPerApp ================== Attentivnessexixtence================= " +attentivnessExistence);

        if(attentivnessExistence)
        {
            Log.d("inotify ", "AttentivnessPerApp ================== Update The AttentivnessPerApp_Table================= ");

            double CurrentTotalAtttentivnessValue = Double.parseDouble(this.AttentivnessperAppGet(PackageName));
            double UpdatedAttentivnessPerAppValue = CurrentTotalAtttentivnessValue + attentivnessPerParticulrNotification;
            double cumilativeAttentivness = this.totalattentivnessSumGet(PackageName);
            double attentivnessPercenatge = (UpdatedAttentivnessPerAppValue / (UpdatedAttentivnessPerAppValue + cumilativeAttentivness)) * 100;

            this.updateaAttentivnessPerApp(PackageName, UpdatedAttentivnessPerAppValue, attentivnessPercenatge);

            Log.d("inotify ", "AttentivnessPerApp =====Update The AttentivnessPerApp_Table =====PackageName " +PackageName);
            Log.d("inotify ", "AttentivnessPerApp =====Update The AttentivnessPerApp_Table =====UpdatedAttentivnessPerAppValue " +UpdatedAttentivnessPerAppValue);
            Log.d("inotify ", "AttentivnessPerApp =====Update The AttentivnessPerApp_Table =====attentivnessPercenatge " +attentivnessPercenatge);
            Log.d("inotify ", "AttentivnessPerApp =====Update The AttentivnessPerApp_Table =====UpdatedAttentivnessPerAppValue " +UpdatedAttentivnessPerAppValue);
            Log.d("inotify ", "AttentivnessPerApp =====Update The AttentivnessPerApp_Table =====cumilativeAttentivness " +cumilativeAttentivness);







               /* ArrayList<String> arrayList1 = this.GetAllData();
                for (int i = 0; i < arrayList1.lastIndexOf(arrayList1); i++) {
                    //check whather the app name is in the table if so go for the normal method else go with the

                    double cumattentivness = this.totalattentivnessSumGet(arrayList1.get(1));

                    if(arrayList1.get(1) == PackageName)
                    {
                        Log.d("inotify ", "AttentivnessPerApp ================== Update The AttentivnessPerApp_Table ================= " +arrayList1.get(1));
                        this.updateaAttentivnessPerApp(PackageName, UpdatedAttentivnessPerAppValue, attentivnessPercenatge);
                        double attentivnessPercenatge = (UpdatedAttentivnessPerAppValue / (UpdatedAttentivnessPerAppValue + cumilativeAttentivness)) * 100;
                    }
                    else{
                        double attentivnessPerc = (cumattentivness / (UpdatedAttentivnessPerAppValue + cumilativeAttentivness)) * 100;
                        this.updateaAttentivnessPerApp(PackageName, cumattentivness, attentivnessPerc);
                        Log.d("inotify ", "AttentivnessPerApp ================== Update The AttentivnessPerApp_Table ================= " +arrayList1.get(1));


                    }

            }*/



        }
        else
        {
            Log.d("inotify " , "AttentivnessPerApp ================== Insert to  The AttentivnessPerApp_Table================= " );
            this.totalAttentivnessPerAppInsert(PackageName,attentivnessPerParticulrNotification,100);

        }
            return value;
    }

        //Get the attentivness  percentage value for a particulr app in the table
    public String getAttentivenessPerApp(String packageName) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("Select * from " + TbNames.ATTENTIVNESSPERAPP_TABLE + "  where "+TbColNames.APPLICATION+" =\"" + packageName + "\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                // return res.getString(res.getColumnIndex(TbColNames.TOTALATTENTIVNESSPERCENTAGE));
                return res.getString(3);

            }
            res.close();
        }
        return null;
    }

    //Display the table data
    ArrayList<String> ansarraylist = new ArrayList<>();
    ArrayList<String> ansarraylist2 = new ArrayList<>();


    public ArrayList displayApplication() {
        ansarraylist.add(" ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select application   from " + TbNames.ATTENTIVNESSPERAPP_TABLE , null);

        if (cursor != null) {
            int count2 = 1;
            while (cursor.moveToNext()) {
                for (int count = 0; count < 1; count++) {
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



    public ArrayList displayAttentivnessPercenatge() {
        ansarraylist2.add(" ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select totalattentivnesspercentage   from " + TbNames.ATTENTIVNESSPERAPP_TABLE , null);

        if (cursor != null) {
            int count2 = 1;
            while (cursor.moveToNext()) {
                for (int count = 0; count < 1; count++) {
                    if (cursor.getString(count) == null) {
                        ansarraylist2.add("");
                    } else
                        ansarraylist2.add(cursor.getString(count));
                    count2 = count2 + 1;
                }
            }
            ansarraylist2.set(0, Integer.toString(count2));
            Log.d("inotifyCC" , "inotify456789 " +ansarraylist2 );
        }

        return ansarraylist2;
    }

    public ArrayList displayData() {
        ansarraylist2.add(" ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select application ,totalattentivnesspercentage   from " + TbNames.ATTENTIVNESSPERAPP_TABLE , null);

        if (cursor != null) {
            int count2 = 1;
            while (cursor.moveToNext()) {
                for (int count = 0; count < 2; count++) {
                    if (cursor.getString(count) == null) {
                        ansarraylist2.add("");
                    } else
                        ansarraylist2.add(cursor.getString(count));
                    count2 = count2 + 1;
                }
            }
            ansarraylist2.set(0, Integer.toString(count2));
            Log.d("inotifyCC" , "inotify456789 " +ansarraylist2 );
        }

        return ansarraylist2;
    }



    public ArrayList GetAllData() {
        ansarraylist.add("");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select application ,totalattentivness,totalattentivnesspercentage  from " + TbNames.ATTENTIVNESSPERAPP_TABLE , null);

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

}
