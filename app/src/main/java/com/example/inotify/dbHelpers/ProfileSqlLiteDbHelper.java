package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.models.ProfileModel;

import static com.example.inotify.configs.TbColNames.AGE;
import static com.example.inotify.configs.TbColNames.DATE;
import static com.example.inotify.configs.TbColNames.EMAIL;
import static com.example.inotify.configs.TbColNames.GENDER;
import static com.example.inotify.configs.TbColNames.NAME;
import static com.example.inotify.configs.TbColNames.OCCUPATION;
import static com.example.inotify.configs.TbColNames.PHONE;
import static com.example.inotify.configs.TbColNames.PROFILE_ID;
import static com.example.inotify.configs.TbNames.PROFILE_TABLE;

public class ProfileSqlLiteDbHelper extends MainSqlliteOpenHelp {

    public ProfileSqlLiteDbHelper(Context context) {
        super(context);
    }

    public boolean insert(ProfileModel profileModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, profileModel.getDATE());
        contentValues.put(NAME, profileModel.getNAME());
        contentValues.put(AGE, profileModel.getAGE());
        contentValues.put(GENDER, profileModel.getGENDER());
        contentValues.put(OCCUPATION, profileModel.getOCCUPATION());
        contentValues.put(EMAIL, profileModel.getEMAIL());
        contentValues.put(PHONE, profileModel.getPHONE());

        long result = db.insert(PROFILE_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean profileisExisCheck() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + PROFILE_TABLE , null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                db.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public ProfileModel get() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + PROFILE_TABLE , null);
        db.close();

        ProfileModel profileModel = new ProfileModel();

        if (res != null) {
            if (res.moveToFirst()) {
                profileModel.setPROFILE_ID(res.getString(res.getColumnIndex(PROFILE_ID)));
                profileModel.setDATE(res.getString(res.getColumnIndex(DATE)));
                profileModel.setNAME(res.getString(res.getColumnIndex(NAME)));
                profileModel.setAGE(res.getString(res.getColumnIndex(AGE)));
                profileModel.setGENDER(res.getString(res.getColumnIndex(GENDER)));
                profileModel.setOCCUPATION(res.getString(res.getColumnIndex(OCCUPATION)));
                profileModel.setEMAIL(res.getString(res.getColumnIndex(EMAIL)));
                profileModel.setPHONE(res.getString(res.getColumnIndex(PHONE)));
                return profileModel;
            }
            res.close();
        }
        return null;
    }
}
