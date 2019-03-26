package com.example.inotify.dbHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ContactsDbHelper extends MainDbHelp {
    public Context c1;

    public ContactsDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public int ContactsTodayGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        Cursor res = db.rawQuery("select SUM(COUNT) as COUNT from " + TbNames.CONTACTCOUNT_TABLE + " where DATE = \""+date+"\" ", null);
        if (res != null) {
            if ((res.moveToFirst())){

                db.close();
                return res.getInt(res.getColumnIndex("COUNT"));
            }
        }
        db.close();
        return 0;
    }

//    public int ContactsAvgGet() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select SUM("+ TbColNames.COUNT +") as COUNT from " + TbNames.CONTACTCOUNT_TABLE , null);
//        int total = 0;
//        int count = 0;
//        int avg;
//        if (res != null) {
//            if ((res.moveToFirst())){
//                do {
//                    total=total+ res.getInt(res.getColumnIndex("COUNT"));
//                    count++;
//                } while (res.moveToNext());
//            }
//        }
//        Objects.requireNonNull(res).close();
//        db.close();
//
//        try {
//            avg=total/count;
//        }catch (Exception e){
//            return 0;
//        }
//
//        return avg;
//    }


}
