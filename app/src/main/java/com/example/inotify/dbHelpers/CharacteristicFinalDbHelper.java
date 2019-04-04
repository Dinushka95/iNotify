package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.views.views.Agreeablenesss;
import com.example.inotify.views.views.Conscientiousness;
import com.example.inotify.views.views.Extraversion;
import com.example.inotify.views.views.Neuroticism;
import com.example.inotify.views.views.Openness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CharacteristicFinalDbHelper extends MainDbHelp {
    private  Context c1;
    public CharacteristicFinalDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

    public boolean characteristicsInsert()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        Openness openness = new Openness(c1);
        long openness_chara= openness.displayOpenness();

        Conscientiousness conscientiousness = new Conscientiousness();
        long conscientiousness_chara = conscientiousness.displayConscientiousness();

        Agreeablenesss agreeablenesss = new Agreeablenesss();
        long agreeableness_chara = agreeablenesss.DisplayAgreeableness();

        Neuroticism neuroticism = new Neuroticism();
        long neuroticism_chara = neuroticism.neuroticismDisplay();

        Extraversion extraversion = new Extraversion();
        long extraversion_chara = extraversion.displayExtraversion();



        ContentValues contentValues = new ContentValues();

        contentValues.put(TbColNames.OPENNESS,openness_chara);
        contentValues.put(TbColNames.CONSCIENTIOUSNESS,conscientiousness_chara);
        contentValues.put(TbColNames.AGREEABLENESS,agreeableness_chara);
        contentValues.put(TbColNames.NEUROTICISM,neuroticism_chara);
        contentValues.put(TbColNames.EXTRAVERSION,extraversion_chara);

        long result = db.insert(TbNames.CHARACTERISTICSFINAL_TABLE, null, contentValues);
        db.close();
        return true;
    }


}
