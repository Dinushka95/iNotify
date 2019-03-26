package com.example.inotify.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.models.CharacteristicsFinalModel;
import com.example.inotify.views.views.Agreeablenesss;
import com.example.inotify.views.views.Conscientiousness;
import com.example.inotify.views.views.Extraversion;
import com.example.inotify.views.views.Neuroticism;
import com.example.inotify.views.views.Openness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.inotify.configs.TbNames.CHARACTERISTICSfINAL_TABLE;

public class CharacteristicFinalDbHelper extends MainDbHelp {
    private  Context c1;
    public CharacteristicFinalDbHelper(Context context) {
        super(context);
        this.c1=context;
    }

//    public boolean characterFinalInsert()
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
//
//        Openness openness = new Openness();
//        long opennessFinal = openness.displayOpenness();
//        Log.d("inotify","opennessFinal.........." + opennessFinal);
//
//        Conscientiousness conscientiousness = new Conscientiousness();
//        long conscientiousnessFinal = conscientiousness.displayConscientiousness();
//        Log.d("inotify","conscientiousnessFinal.........." + conscientiousnessFinal);
//
//
//        Extraversion extraversion = new Extraversion();
//        long extraversionFinal = extraversion.displayExtraversion();
//        Log.d("inotify","extraversionFinal.........." + extraversionFinal);
//
//
//        Neuroticism neuroticism = new Neuroticism();
//        long neuroticismFinal = neuroticism.neuroticismDisplay();
//        Log.d("inotify","neuroticismFinal.........." + neuroticismFinal);
//
//
//        Agreeablenesss agreeablenesss = new Agreeablenesss();
//        long AgreeablenessFinal = agreeablenesss.DisplayAgreeableness();
//        Log.d("inotify","AgreeablenessFinal.........." + AgreeablenessFinal);
//
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(TbColNames.OPENNESS,opennessFinal);
//        contentValues.put(TbColNames.CONSCIENTIOUSNESS,conscientiousnessFinal);
//        contentValues.put(TbColNames.EXTRAVERSION,extraversionFinal);
//        contentValues.put(TbColNames.NEUROTICISM,neuroticismFinal);
//        contentValues.put(TbColNames.AGREEABLENESS,AgreeablenessFinal);
//        contentValues.put(TbColNames.DATE,date);
//
//
//        long result = db.insert(TbNames.CHARACTERISTICSfINAL_TABLE, null, contentValues);
//        db.close();
//        return true;
//    }
//
//    public void xxx()
//    {
//        Openness openness = new Openness();
//        long opennessFinal = openness.displayOpenness();
//        Log.d("inotify","opennessFinal.........." + opennessFinal);
//
//        Conscientiousness conscientiousness = new Conscientiousness();
//        long conscientiousnessFinal = conscientiousness.displayConscientiousness();
//        Log.d("inotify","conscientiousnessFinal.........." + conscientiousnessFinal);
//
//
//        Extraversion extraversion = new Extraversion();
//        long extraversionFinal = extraversion.displayExtraversion();
//        Log.d("inotify","extraversionFinal.........." + extraversionFinal);
//
//
//        Neuroticism neuroticism = new Neuroticism();
//        long neuroticismFinal = neuroticism.neuroticismDisplay();
//        Log.d("inotify","neuroticismFinal.........." + neuroticismFinal);
//
//
//        Agreeablenesss agreeablenesss = new Agreeablenesss();
//        long AgreeablenessFinal = agreeablenesss.DisplayAgreeableness();
//        Log.d("inotify","AgreeablenessFinal.........." + AgreeablenessFinal);
//    }
//
//    public CharacteristicsFinalModel characterGet(String date)
//    {
//        CharacteristicsFinalModel characteristicsFinalModel = new CharacteristicsFinalModel();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from " + CHARACTERISTICSfINAL_TABLE + " WHERE "+TbColNames.DATE +" = \""+date+"\"", null);
//        if (res != null) {
//
//            if (res.moveToFirst()) {
//
//                characteristicsFinalModel.setOpenness( res.getString(res.getColumnIndex(TbColNames.OPENNESS)));
//                characteristicsFinalModel.setConscientiousness( res.getString(res.getColumnIndex(TbColNames.CONSCIENTIOUSNESS)));
//                characteristicsFinalModel.setExtraversion( res.getString(res.getColumnIndex(TbColNames.EXTRAVERSION)));
//                characteristicsFinalModel.setNeuroticism( res.getString(res.getColumnIndex(TbColNames.NEUROTICISM)));
//                characteristicsFinalModel.setAgreeableness( res.getString(res.getColumnIndex(TbColNames.AGREEABLENESS)));
//
//            }
//
//        }
//
//        return characteristicsFinalModel;
//
//
//    }
}
