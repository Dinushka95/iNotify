package com.example.inotify;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MainAttentiviness {

    public String getFinalAttentiviness(Context context,String appName){

        String ringerMode = new RingerMode().getRingerMode(context);
        SharedPreferences prefs = context.getSharedPreferences("lockscreen", MODE_PRIVATE);
        String isScreenOn = prefs.getString("screen", null);

        Cha_SqlLiteDbHelper cha_sqlLiteDbHelper = new Cha_SqlLiteDbHelper(context);

        int total_important_value = cha_sqlLiteDbHelper.NIRgetTotalvalue();

        int AppINV = cha_sqlLiteDbHelper.NIRgetValueNo(appName);

        double W_r=0;
        double W_sl=0;
        double W_Ar=0;

        if (ringerMode.equals("normal")){W_r=0.33333333;}
        if (ringerMode.equals("vibrate")){W_r=0.6666666;}
        if (ringerMode.equals("silent")){W_r=1;}


        if (ringerMode.equals("on")){W_sl=0.5;}
        if (ringerMode.equals("off")){W_sl=0.5;}

        double currentp = AppINV/total_important_value;

        double finalvalue = currentp*W_sl*W_r;

        if (finalvalue>0 && finalvalue<.3){return "low";}
        if (finalvalue>.3 && finalvalue<.6){return "medium";}
        if (finalvalue>.6 && finalvalue<1){return "high";}


        return "error";

    }

}
