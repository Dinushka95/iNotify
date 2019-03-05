package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.configs.TableColumnNames.DATE;
import static com.example.inotify.configs.TableColumnNames.TIMEON;
import static com.example.inotify.configs.TableNames.UA_SCREENON_TABLE;

public class ScreenOnDbHelper extends MainSqlliteOpenHelp{



    public ScreenOnDbHelper(Context context) {
        super(context);
    }
    //Insert to screenOn table

    public boolean ScreenOnInsert(){
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date())),
        (Integer.valueOf(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date())) - 1),
        Integer.valueOf(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date())));
        String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE , date);
        contentValues.put(TIMEON , time);

        long result = db.insert(UA_SCREENON_TABLE ,null,contentValues);
        db.close();
        if(result ==-10){
            return false;
        }
        else
            return true;


    }

}
