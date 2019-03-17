package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
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

public class ProfileDbHelper extends MainDbHelp {

    public ProfileDbHelper(Context context) {
        super(context);
    }

    public boolean insert(ProfileModel profileModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, profileModel.getDate());
        contentValues.put(NAME, profileModel.getName());
        contentValues.put(AGE, profileModel.getAge());
        contentValues.put(GENDER, profileModel.getGender());
        contentValues.put(OCCUPATION, profileModel.getOccupation());
        contentValues.put(EMAIL, profileModel.getEmail());
        contentValues.put(PHONE, profileModel.getPhone());

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

        ProfileModel profileModel = new ProfileModel();

        if (res != null) {
            if (res.moveToFirst()) {
                profileModel.setProfile_id(res.getString(res.getColumnIndex(TbColNames.PROFILE_ID)));
                profileModel.setDate(res.getString(res.getColumnIndex(TbColNames.DATE)));
                profileModel.setName(res.getString(res.getColumnIndex(TbColNames.NAME)));
                profileModel.setAge(res.getString(res.getColumnIndex(TbColNames.AGE)));
                profileModel.setGender(res.getString(res.getColumnIndex(TbColNames.GENDER)));
                profileModel.setOccupation(res.getString(res.getColumnIndex(TbColNames.OCCUPATION)));
                profileModel.setEmail(res.getString(res.getColumnIndex(TbColNames.EMAIL)));
                profileModel.setPhone(res.getString(res.getColumnIndex(TbColNames.PHONE)));
                return profileModel;
            }
            res.close();
        }

        db.close();
        return null;
    }
}
