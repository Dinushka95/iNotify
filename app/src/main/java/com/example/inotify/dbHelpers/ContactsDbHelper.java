package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.ContactsHelper;
import com.example.inotify.helpers.ScreenOnTimeHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ContactsDbHelper extends MainDbHelp {
    public Context c1;
    private static ContactsDbHelper mInstance = null;


    public ContactsDbHelper(Context context) {
        super(context);
        this.c1=context;
    }
    public static ContactsDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new ContactsDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public boolean ContactsCountInsert()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        ContactsHelper contactsHelper = new ContactsHelper(c1);
        int count = contactsHelper.getContacts(c1);


        ContentValues contentValues = new ContentValues();

        contentValues.put(TbColNames.CONTACTCOUNT,count);
        contentValues.put(TbColNames.DATE,date);

        long result = db.insert(TbNames.CONTACTCOUNT_TABLE, null, contentValues);
        db.close();
        return true;
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
