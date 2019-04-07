package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.CharacteristicsFinalModel;
import com.example.inotify.views.views.Agreeablenesss;
import com.example.inotify.views.views.Conscientiousness;
import com.example.inotify.views.views.Extraversion;
import com.example.inotify.views.views.Neuroticism;
import com.example.inotify.views.views.Openness;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.inotify.configs.TbNames.APPLICATIONS_TABLE;
import static com.example.inotify.configs.TbNames.CHARACTERISTICSFINAL_TABLE;

public class CharacteristicFinalDbHelper extends MainDbHelp {

    private Context c1;
    private static CharacteristicFinalDbHelper mInstance = null;

    public CharacteristicFinalDbHelper(Context context) {
        super(context);
        this.c1 = context;
    }

    public static CharacteristicFinalDbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new CharacteristicFinalDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public boolean characteristicsInsert() {

        SQLiteDatabase db = this.getWritableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Openness openness = new Openness();
        long openness_chara = openness.displayOpenness(c1);

        Conscientiousness conscientiousness = new Conscientiousness();
        long conscientiousness_chara = conscientiousness.displayConscientiousness();

        Agreeablenesss agreeablenesss = new Agreeablenesss();
        long agreeableness_chara = agreeablenesss.DisplayAgreeableness();

        Neuroticism neuroticism = new Neuroticism();
        long neuroticism_chara = neuroticism.neuroticismDisplay();

        Extraversion extraversion = new Extraversion();
        long extraversion_chara = extraversion.displayExtraversion();


        String datenow = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbColNames.DATE, datenow);
        contentValues.put(TbColNames.OPENNESS, openness_chara);
        contentValues.put(TbColNames.CONSCIENTIOUSNESS, conscientiousness_chara);
        contentValues.put(TbColNames.AGREEABLENESS, agreeableness_chara);
        contentValues.put(TbColNames.NEUROTICISM, neuroticism_chara);
        contentValues.put(TbColNames.EXTRAVERSION, extraversion_chara);

        long result = db.insert(TbNames.CHARACTERISTICSFINAL_TABLE, null, contentValues);
        db.close();
        return true;
    }


    public String getFinalUserCharacteries() {
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Cursor res = db.rawQuery("select * from "+TbNames.CHARACTERISTICSFINAL_TABLE+" ORDER BY DATE DESC " , null);
        if (res != null) {
            if ((res.moveToFirst())){

               String x="";

                double openness = res.getDouble(res.getColumnIndex(TbColNames.OPENNESS));
                double conscienous = res.getDouble(res.getColumnIndex(TbColNames.CONSCIENTIOUSNESS));
                double extraversion = res.getDouble(res.getColumnIndex(TbColNames.EXTRAVERSION));
                double neuroticsm = res.getDouble(res.getColumnIndex(TbColNames.NEUROTICISM));
                double agreeableness = res.getDouble(res.getColumnIndex(TbColNames.AGREEABLENESS));

                if(openness>conscienous){
                    if(openness>extraversion){
                        if(openness>neuroticsm){
                            if(openness>agreeableness){ x="openness"; }else{x="agreeableness"; }
                        }else{ if(neuroticsm>agreeableness){ x="neuroticism"; }else{x="agreeableness"; } }
                    }else{ if(extraversion>neuroticsm){ if(extraversion>agreeableness){ x="extraversion"; }else{ x="agreeableness"; }
                        }else{ if(neuroticsm>agreeableness){x="neuroticism"; }else{ x="agreeableness"; }
                        }
                    }
                }else{
                    if(conscienous>extraversion){if(conscienous>neuroticsm){ if(conscienous>agreeableness){ x="conscientiousness"; }else{ x="agreeableness"; }
                        }else{ if(neuroticsm>agreeableness){ x="neuroticism"; }else{ x="agreeableness"; } }
                    }else{if(extraversion>neuroticsm){ if(extraversion>agreeableness){ x="extraversion"; }else{ x="agreeableness"; }
                        }else{ if(neuroticsm>agreeableness){x="neuroticism"; }else{ x="agreeableness"; } }
                    }
                }

                return x;
            }
        }
        Objects.requireNonNull(res).close();
        db.close();

        return "";
    }


    public boolean cheackAvailability(String TableName) {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableName + " where DATE =\"" + date + "\"", null);

        if (res.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<CharacteristicsFinalModel> CharacteristicsGet() {
        List<CharacteristicsFinalModel> ListCharacteristicsFinalModel = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CHARACTERISTICSFINAL_TABLE + " where  DATE = \"date\"", null);
        if (res != null) {
            if (res.moveToFirst()) {
                do {

                    CharacteristicsFinalModel characteristicsFinalModel = new CharacteristicsFinalModel();

                    characteristicsFinalModel.setOpenness( res.getString(res.getColumnIndex(TbColNames.OPENNESS)));
                    characteristicsFinalModel.setConscientiousness( res.getString(res.getColumnIndex(TbColNames.CONSCIENTIOUSNESS)));
                    characteristicsFinalModel.setExtraversion( res.getString(res.getColumnIndex(TbColNames.EXTRAVERSION)));
                    characteristicsFinalModel.setNeuroticism( res.getString(res.getColumnIndex(TbColNames.NEUROTICISM)));
                    characteristicsFinalModel.setAgreeableness( res.getString(res.getColumnIndex(TbColNames.AGREEABLENESS)));



                    ListCharacteristicsFinalModel.add(characteristicsFinalModel);
                } while (res.moveToNext());
            }
            res.close();
        }

        return ListCharacteristicsFinalModel;
    }


}
