package com.example.inotify.dbHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CalenderEventDbHelper extends MainDbHelp  {


    private  Context c1;
    public CalenderEventDbHelper(Context context) {
        super(context);
        this.c1=context;
    }


    public long CalenderEventAVGGet()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //avgappp
        Cursor res = db.rawQuery("select SUM("+ TbColNames.COUNT +") as COUNT from " + TbNames.UC_CALENDEREVENTCOUNT_TABLE , null);
        int total = 0;
        int count = 0;
        int avg;
        if (res != null) {
            if ((res.moveToFirst())){
                do {
                    total=total+ res.getInt(res.getColumnIndex("COUNT"));
                    count++;
                } while (res.moveToNext());
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        try {
            avg=total/count;
        }catch (Exception e){
            return 0;
        }
        Log.d("inotify","calenderEventAVG......" + avg);

        return avg;
    }

    public boolean checkIfExist()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddHHmm", Locale.getDefault());
        Date currentDate = new Date();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TbNames.UC_CALENDEREVENTCOUNT_TABLE + " WHERE "+TbColNames.DATE +" = \""+currentDate+"\"", null);
        if(res == null)
        {
            Log.d("inotify","checkIfExist........."+res );
            return true;
        }
        else
        {
            Log.d("inotify","checkIfExist........."+res );
            return false;
        }
    }
}
