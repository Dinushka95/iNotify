package com.example.inotify;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class MainAttentiviness {

    public String getFinalAttentiviness(Context context,String appName){

        String ringerMode = new Cha_RingerMode().getRingerMode(context);
        SharedPreferences prefs = context.getSharedPreferences("lockscreen", MODE_PRIVATE);
        String isScreenOn = prefs.getString("screen", null);

        Cha_SqlLiteDbHelper cha_sqlLiteDbHelper = new Cha_SqlLiteDbHelper(context);

        int total_important_value = cha_sqlLiteDbHelper.NIRgetTotalvalue();

        int AppINV = cha_sqlLiteDbHelper.NIgetValue(appName);

        double W_r=0;
        double W_sl=0;
        double W_Ar=0;

        if (ringerMode.equals("normal")){W_r=0.33333333;}
        if (ringerMode.equals("vibrate")){W_r=0.6666666;}
        if (ringerMode.equals("silent")){W_r=1;}


        if (isScreenOn.equals("on")){W_sl=0.5;}
        if (isScreenOn.equals("off")){W_sl=0.5;}

        Log.d("inotify", "attentiviness-MainAttentiviness--ringerMode---"+ringerMode );
        Log.d("inotify", "attentiviness-MainAttentiviness--isScreenOn---"+isScreenOn );
        Log.d("inotify", "attentiviness-MainAttentiviness--total_important_value---"+total_important_value );
        Log.d("inotify", "attentiviness-MainAttentiviness--AppINV---"+AppINV );

        double currentp = (float)AppINV/(float)total_important_value;
        Log.d("inotify", "attentiviness-MainAttentiviness--currentp---"+currentp );

        double finalvalue = currentp*W_sl*W_r;
        Log.d("inotify", "attentiviness-MainAttentiviness--finalvalue---"+finalvalue );
        if (finalvalue>0 && finalvalue<.3){return "low";}
        if (finalvalue>.3 && finalvalue<.6){return "medium";}
        if (finalvalue>.6 && finalvalue<1){return "high";}

        return "error";

    }

}
