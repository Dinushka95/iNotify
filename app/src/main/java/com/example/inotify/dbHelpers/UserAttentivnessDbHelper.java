package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserAttentivnessDbHelper extends MainDbHelp {
    private static UserAttentivnessDbHelper mInstance = null;

    public UserAttentivnessDbHelper(Context context) {
        super(context);
    }

    public static UserAttentivnessDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new UserAttentivnessDbHelper(context.getApplicationContext());
        }
        return mInstance;
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


    public boolean InsertAttentivnessPerNotificationOnTime(String notificationid , String time , String application , double attentivnessperhour)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.NOTIFICATIONID,notificationid);
        contentValues.put(TbColNames.TIME ,time);
        contentValues.put(TbColNames.APPLICATION ,application);
        contentValues.put(TbColNames.ATTENTIVNESSPERHOUR , attentivnessperhour);

        long result = db.insert(TbNames.ATTENTIVNESSPERNOTIFICATIONONTIME_TABLE , null,contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else
            return true;


    }





    ArrayList<String> ansarraylist = new ArrayList<>();
    ArrayList<String> ansarraylist2 = new ArrayList<>();
    public ArrayList displayNotificationID (){
        ansarraylist.add(" ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id   from " + TbNames.USERATTENTIVNESS_TABLE , null);

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

        }

        return ansarraylist;
    }


    public ArrayList displayAttentivness (){
        ansarraylist2.add(" ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select attentivnessvalue   from " + TbNames.USERATTENTIVNESS_TABLE  , null);

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

        }

        return ansarraylist2;
    }


    ArrayList<String> ansarraylistLineChartApp = new ArrayList<>();
    ArrayList<String> ansarraylistLineChartTime = new ArrayList<>();
    ArrayList<String> ansarraylistLineChartAttentivness = new ArrayList<>();


    public ArrayList displayAttentivnessLineChartTime(String application) {
        ansarraylistLineChartTime.add(" ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select time   from " + TbNames.ATTENTIVNESSPERNOTIFICATIONONTIME_TABLE + " where " + TbColNames.APPLICATION + " =\"" + application +"\"", null);

        if (cursor != null) {
            int count2 = 1;
            while (cursor.moveToNext()) {
                for (int count = 0; count < 1; count++) {
                    if (cursor.getString(count) == null) {
                        ansarraylistLineChartTime.add("");
                    } else
                        ansarraylistLineChartTime.add(cursor.getString(count));
                    count2 = count2 + 1;
                }
            }
            ansarraylistLineChartTime.set(0, Integer.toString(count2));

        }

        return ansarraylistLineChartTime;
    }


    public ArrayList displayAttentivnessLineChartAttentivness(String application) {
        ansarraylistLineChartAttentivness.add(" ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select attentivnessperhour   from " + TbNames.ATTENTIVNESSPERNOTIFICATIONONTIME_TABLE + " where " + TbColNames.APPLICATION + " = \"" + application +"\"", null);

        if (cursor != null) {
            int count2 = 1;
            while (cursor.moveToNext()) {
                for (int count = 0; count < 1; count++) {
                    if (cursor.getString(count) == null) {
                        ansarraylistLineChartAttentivness.add("");
                    } else
                        ansarraylistLineChartAttentivness.add(cursor.getString(count));
                    count2 = count2 + 1;
                }
            }
            ansarraylistLineChartAttentivness.set(0, Integer.toString(count2));

        }

        return ansarraylistLineChartAttentivness;
    }

    public ArrayList displayAttentivnessLineChartApp(){

        ansarraylistLineChartApp.add(" ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select application  from " + TbNames.ATTENTIVNESSPERNOTIFICATIONONTIME_TABLE  , null);

        if (cursor != null) {
            int count2 = 1;
            while (cursor.moveToNext()) {
                for (int count = 0; count < 1; count++) {
                    if (cursor.getString(count) == null) {
                        ansarraylistLineChartApp.add("");
                    } else
                        ansarraylistLineChartApp.add(cursor.getString(count));
                    count2 = count2 + 1;
                }
            }
            ansarraylistLineChartApp.set(0, Integer.toString(count2));

        }

        return ansarraylistLineChartApp;
    }

    ArrayList<String> arraylistLineChartAttentPerApp = new ArrayList<>();
      public  ArrayList DisplayAppInDropDown(){
          arraylistLineChartAttentPerApp.add(" ");


          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor = db.rawQuery("select distinct(application) from " + TbNames.ATTENTIVNESSPERNOTIFICATIONONTIME_TABLE, null);

              if (cursor != null) {
                  int count2 = 1;
                  while (cursor.moveToNext()) {
                      for (int count = 0; count < 1; count++) {
                          if (cursor.getString(count) == null) {
                              arraylistLineChartAttentPerApp.add("");
                          } else
                              arraylistLineChartAttentPerApp.add(cursor.getString(count));
                          count2 = count2 + 1;
                      }
                  }
                  arraylistLineChartAttentPerApp.set(0, Integer.toString(count2));

              }


          return arraylistLineChartAttentPerApp;
      }



    ArrayList<String>  time =  new ArrayList<>();
      public ArrayList<String> AssignTimeSlots(String datetime)
      {
         time.add(" ");


          for(int i=0 ;i< datetime.length()/2 ;i++)
          {
              time.add(datetime.substring(i*2));


          }

          return time;
      }

      public ArrayList<String> asd(String dateTime)
      {

         ArrayList<String> a = this.AssignTimeSlots(dateTime);


return a;

      }
}