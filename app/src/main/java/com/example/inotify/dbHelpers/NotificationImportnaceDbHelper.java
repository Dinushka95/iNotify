package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationImportnaceDbHelper extends MainDbHelp{

    public NotificationImportnaceDbHelper(Context context) {
        super(context);
    }

    public boolean NotificationImportnaceInsert(String id , String applicationname  , int sequencevalue){
          //  String id = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.getDefault()).format(new Date());

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TbColNames.NOTIFICATIONIID , id);
            contentValues.put(TbColNames.APPLICATIONNAME , applicationname);
            contentValues.put(TbColNames.SEQUENCEVALUE ,sequencevalue);

        Log.d("iNotify" , "SEQUENCEVALUE " + sequencevalue );

            long result = db.insert(TbNames.NOTIFICATIONIMPORTANCE_TABLE ,null , contentValues);
            db.close();
            if(result == -1)
            {
                return true;
            }
            else
                return false;

        }

        public String NotificationImportnaceGet(String id){
            String importanceValue = new String();
            SQLiteDatabase db = this.getReadableDatabase();
           // String id = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

            Cursor res = db.rawQuery("Select *,SEQUENCEVALUE from "+ TbNames.NOTIFICATIONIMPORTANCE_TABLE + "  where NOTIFICATIONIID =\"" + id + "\"", null );
            if(res != null)
            {
                if(res.moveToFirst()){
                    return res.getString(3);
                    //return res.getString(res.getColumnIndex(TbColNames.SEQUENCEVALUE));

                }
                res.close();
            }
            return null;
        }


}
