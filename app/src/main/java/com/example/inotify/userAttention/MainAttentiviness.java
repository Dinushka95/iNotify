package com.example.inotify.userAttention;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.inotify.userAttention.UA_SqlLiteDbHelper;
import com.example.inotify.userAttention.UA_RingerMode;

import static android.content.Context.MODE_PRIVATE;
import static com.example.inotify.MainActivity.MainAttentiviness_DebuggerLogger;

public class MainAttentiviness {

    public String getFinalAttentiviness(Context context,String appName){

        if(MainAttentiviness_DebuggerLogger) {
            Log.d("inotify", "Main-Attentiviness--Started---");
        }

        String ringerMode = new UA_RingerMode().getRingerMode(context);
        SharedPreferences prefs = context.getSharedPreferences("lockscreen", MODE_PRIVATE);
        String isScreenOn = prefs.getString("screen", null);

        UA_SqlLiteDbHelper attention_sqlLiteDbHelper = new UA_SqlLiteDbHelper(context);

        int total_important_value = attention_sqlLiteDbHelper.NIRgetTotalvalue();

        int AppINV = attention_sqlLiteDbHelper.NIgetValue(appName);

        double W_r=0;
        double W_sl=0;
        double W_Ar=0;

        if (ringerMode.equals("normal")){W_r=0.33333333;}
        if (ringerMode.equals("vibrate")){W_r=0.6666666;}
        if (ringerMode.equals("silent")){W_r=1;}


        if (isScreenOn.equals("on")){W_sl=0.5;}
        if (isScreenOn.equals("off")){W_sl=0.5;}

        if(MainAttentiviness_DebuggerLogger) {
        Log.d("inotify", "MainAttentiviness--ringerMode---"+ringerMode );
        Log.d("inotify", "MainAttentiviness--isScreenOn---"+isScreenOn );
        Log.d("inotify", "MainAttentiviness--total_important_value from previous data---"+total_important_value );
        Log.d("inotify", "MainAttentiviness--AppINV-application important---"+AppINV );
        }

        double currentp = (float)AppINV/(float)total_important_value;

        if(MainAttentiviness_DebuggerLogger) {
            Log.d("inotify", "MainAttentiviness--current probability of importance of notification---" + currentp);
        }
        double finalvalue = currentp*W_sl*W_r;
        if(MainAttentiviness_DebuggerLogger) {
            Log.d("inotify", "MainAttentiviness-- final value ---" + finalvalue);
        }

        String finalTemValue="error";
        if (finalvalue>0 && finalvalue<.3){finalTemValue = "low";}
        if (finalvalue>.3 && finalvalue<.6){finalTemValue = "medium";}
        if (finalvalue>.6 && finalvalue<1){finalTemValue = "high";}

        if(MainAttentiviness_DebuggerLogger) {
            Log.d("inotify", "Main-Attentiviness--final value ---" + finalTemValue);
            Log.d("inotify", "Main-Attentiviness--stop---");
        }
        return finalTemValue;

    }

}
