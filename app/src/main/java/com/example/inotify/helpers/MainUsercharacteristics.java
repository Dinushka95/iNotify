package com.example.inotify.helpers;

import android.content.Context;
import android.util.Log;

import com.example.inotify.configs.AppUserConfigs;
import com.example.inotify.dbHelpers.UC_DbHelper;

import java.util.ArrayList;


public class MainUsercharacteristics {

    public String getUsercharacteristics(Context context) {

        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            Log.d("inotify", "Main-Usercharacteristics--Started---");
        }

        UC_DbHelper UC_DbHelper = new UC_DbHelper(context);

        long appListCountFixedvalue = 75; // move it to main entr point and make it a static variable

        // long appUsageCountAvg = UC_DbHelper.appUsageAverage_get();
        long appListCountAvg = UC_DbHelper.applistAverage_get();
        long contactCountAvg = UC_DbHelper.contactsAverage_get();
        long screenTimeCountAvg = UC_DbHelper.screentimeAverage_get();
        long calldurationCountAvg = UC_DbHelper.calldurationAverage_get();
        long calendarCountAvg = UC_DbHelper.calendereventAverage_get();
        long chargetimeAvg = UC_DbHelper.chargeAverage_get();
        long socialmediacAppCountAvg = UC_DbHelper.applistsocialmediaAverage_get();
        long appListAverageWithoutLast = UC_DbHelper.applistAverageWithoutLast_get();

//        long appListCountLast = UC_DbHelper.applistLast_get();
        long contactCountLast = UC_DbHelper.contactsLast_get();
        long screenTimeCountLast = UC_DbHelper.screentimeLast_get();
        long calldurationCountLast = UC_DbHelper.calldurationLast_get();
        long calendarCountLast = UC_DbHelper.calendereventLast_get();
        long chargetimeLast = UC_DbHelper.chargeLast_get();
        long socialmediacAppCountLast = UC_DbHelper.applistsocialmediaLast_get();

        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            // Log.d("inotify", "Main-Usercharacteristics--appUsageCountAvg---"+appUsageCountAvg);
            // Log.d("inotify", "Main-Usercharacteristics--appUsageCountLast---"+appUsageCountLast);
            Log.d("inotify", "Main-Usercharacteristics--appListCountAvg---" + appListCountAvg);
            // Log.d("inotify", "Main-Usercharacteristics--appListCountLast---"+appListCountLast);
            Log.d("inotify", "Main-Usercharacteristics--contactCountAvg---" + contactCountAvg);
            Log.d("inotify", "Main-Usercharacteristics--contactCountLast---" + contactCountLast);
            Log.d("inotify", "Main-Usercharacteristics--screenTimeCountAvg---" + screenTimeCountAvg);
            Log.d("inotify", "Main-Usercharacteristics--screenTimeCountLast---" + screenTimeCountLast);
            Log.d("inotify", "Main-Usercharacteristics--calldurationCountAvg---" + calldurationCountAvg);
            Log.d("inotify", "Main-Usercharacteristics--calldurationCountLast---" + calldurationCountLast);
            Log.d("inotify", "Main-Usercharacteristics--calendarCountAvg---" + calendarCountAvg);
            Log.d("inotify", "Main-Usercharacteristics--calendarCountLast---" + calendarCountLast);
            Log.d("inotify", "Main-Usercharacteristics--chargetimeAvg---" + chargetimeAvg);
            Log.d("inotify", "Main-Usercharacteristics--chargetimeLast---" + chargetimeLast);
            Log.d("inotify", "Main-Usercharacteristics--socialmediacAppCountAvg---" + socialmediacAppCountAvg);
            Log.d("inotify", "Main-Usercharacteristics--socialmediacAppCountLast---" + socialmediacAppCountLast);
            Log.d("inotify", "Main-Usercharacteristics--appListAverageWithoutLast---" + appListAverageWithoutLast);


        }

        String appUsageCountStatue = "error";
        String appListCountStatue = "error";
        String appListDownloadStatue = "error";
        String contactCountStatue = "error";
        String screenTimeCountStatue = "error";
        String calldurationCountStatue = "error";
        String calendarCountStatue = "error";
        String chargetimeStatue = "error";
        String socialmediacAppCountStatue = "error";


        if (contactCountLast > contactCountAvg) {
            contactCountStatue = "high";
        } else {
            contactCountStatue = "low";
        }
        if (screenTimeCountLast > screenTimeCountAvg) {
            screenTimeCountStatue = "high";
        } else {
            screenTimeCountStatue = "low";
        }
        if (calldurationCountLast > calldurationCountAvg) {
            calldurationCountStatue = "high";
        } else {
            calldurationCountStatue = "low";
        }
        if (calendarCountLast > calendarCountAvg) {
            calendarCountStatue = "high";
        } else {
            calendarCountStatue = "low";
        }
        if (chargetimeLast > chargetimeAvg) {
            chargetimeStatue = "high";
        } else {
            chargetimeStatue = "low";
        }
        if (socialmediacAppCountLast > socialmediacAppCountAvg) {
            socialmediacAppCountStatue = "high";
        } else {
            socialmediacAppCountStatue = "low";
        }


        //////////////////////////////////////////////////////////////////////////////////////////////
        // openness

        double opennessFinal = 0.0;

        int openness_WL1 = 0;
        int openness_WL2 = 0;
        int openness_WL3 = 0;

        if (appUsageCountStatue.equals("high")) {
            openness_WL1 = 20;
        } else {
            openness_WL1 = 0;
        }
        if (appListCountStatue.equals("high")) {
            openness_WL2 = 40;
        } else {
            openness_WL2 = 0;
        }
        if (appListDownloadStatue.equals("high")) {
            openness_WL3 = 40;
        } else {
            openness_WL3 = 0;
        }

        opennessFinal = (openness_WL1 + openness_WL2 + openness_WL3) / 100;

        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            Log.d("inotify", "Main-Usercharacteristics--opennessFinal---" + opennessFinal);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////
        // neuroticism

        double neuroticismFinal = 0.0;

        int neuroticism_WL1 = 0;
        int neuroticism_WL2 = 0;
        int neuroticism_WL3 = 0;

        if (appUsageCountStatue.equals("high")) {
            neuroticism_WL1 = 30;
        } else {
            neuroticism_WL1 = 0;
        }
        if (chargetimeStatue.equals("high")) {
            neuroticism_WL2 = 40;
        } else {
            neuroticism_WL2 = 0;
        }
        if (socialmediacAppCountStatue.equals("high")) {
            neuroticism_WL3 = 30;
        } else {
            neuroticism_WL3 = 0;
        }

        neuroticismFinal = (neuroticism_WL1 + neuroticism_WL2 + neuroticism_WL3) / 100;

        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            Log.d("inotify", "Main-Usercharacteristics--neuroticismFinal---" + neuroticismFinal);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////
        // extraversion

        double extraversionFinal = 0.0;

        int extraversion_WL1 = 0;
        int extraversion_WL2 = 0;
        int extraversion_WL3 = 0;
        int extraversion_WL4 = 0;
        int extraversion_WL5 = 0;

        if (appListDownloadStatue.equals("high")) {
            extraversion_WL1 = 10;
        } else {
            extraversion_WL1 = 0;
        }
        if (socialmediacAppCountStatue.equals("high")) {
            extraversion_WL2 = 30;
        } else {
            extraversion_WL2 = 0;
        }
        if (chargetimeStatue.equals("high")) {
            extraversion_WL3 = 10;
        } else {
            extraversion_WL3 = 0;
        }
        if (screenTimeCountStatue.equals("high")) {
            extraversion_WL4 = 20;
        } else {
            extraversion_WL4 = 0;
        }
        if (calldurationCountStatue.equals("high")) {
            extraversion_WL5 = 30;
        } else {
            extraversion_WL5 = 0;
        }

        extraversionFinal = (extraversion_WL1 + extraversion_WL2 + extraversion_WL3 + extraversion_WL4 + extraversion_WL5) / 100;

        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            Log.d("inotify", "Main-Usercharacteristics--extraversionFinal---" + extraversionFinal);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////
        // consientiousness

        double consientiousnessFinal = 0.0;

        int consientiousness_WL1 = 0;
        int consientiousness_WL2 = 0;
        int consientiousness_WL3 = 0;
        int consientiousness_WL4 = 0;

        if (calendarCountStatue.equals("high")) {
            consientiousness_WL1 = 30;
        } else {
            consientiousness_WL1 = 0;
        }
        if (chargetimeStatue.equals("high")) {
            consientiousness_WL2 = 30;
        } else {
            consientiousness_WL2 = 0;
        }
        if (calldurationCountStatue.equals("high")) {
            consientiousness_WL3 = 20;
        } else {
            consientiousness_WL3 = 0;
        }
        if (screenTimeCountStatue.equals("high")) {
            consientiousness_WL4 = 20;
        } else {
            consientiousness_WL4 = 0;
        }

        consientiousnessFinal = (consientiousness_WL1 + consientiousness_WL2 + consientiousness_WL3 + consientiousness_WL4) / 100;

        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            Log.d("inotify", "Main-Usercharacteristics--consientiousnessFinal---" + consientiousnessFinal);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////
        // areeableness

        double areeablenessFinal = 0.0;

        int areeableness_WL1 = 0;
        int areeableness_WL2 = 0;
        int areeableness_WL3 = 0;
        int areeableness_WL4 = 0;
        int areeableness_WL5 = 0;

        if (screenTimeCountStatue.equals("high")) {
            consientiousness_WL1 = 10;
        } else {
            consientiousness_WL1 = 0;
        }
        if (appListDownloadStatue.equals("high")) {
            consientiousness_WL2 = 20;
        } else {
            consientiousness_WL2 = 0;
        }
        if (appListCountStatue.equals("high")) {
            consientiousness_WL3 = 10;
        } else {
            consientiousness_WL3 = 0;
        }
        if (contactCountStatue.equals("high")) {
            consientiousness_WL4 = 30;
        } else {
            consientiousness_WL4 = 0;
        }
        if (calldurationCountStatue.equals("high")) {
            consientiousness_WL4 = 30;
        } else {
            consientiousness_WL4 = 0;
        }

        areeablenessFinal = (areeableness_WL1 + areeableness_WL2 + areeableness_WL3 + areeableness_WL4 + areeableness_WL5) / 100;
        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            Log.d("inotify", "Main-Usercharacteristics--areeablenessFinal---" + areeablenessFinal);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //save all sates to db
        ArrayList<Double> ad = new ArrayList<>();
        ad.add(opennessFinal);
        ad.add(neuroticismFinal);
        ad.add(extraversionFinal);
        ad.add(consientiousnessFinal);
        ad.add(areeablenessFinal);

        String x = "error";

        if (opennessFinal > neuroticismFinal) {
            if (opennessFinal > extraversionFinal) {
                if (opennessFinal > consientiousnessFinal) {
                    if (opennessFinal > areeablenessFinal) {
                        x = "openness";//"openness";
                    } else {
                        x = "oldtechnology";//"ageeableness";
                    }
                } else {
                    if (consientiousnessFinal > areeablenessFinal) {
                        x = "professional"; //"consientiousness";
                    } else {
                        x = "oldtechnology";//"ageeableness";
                    }
                }
            } else {
                if (consientiousnessFinal > areeablenessFinal) {
                    x = "professional"; //"consientiousness";
                } else {
                    x = "oldtechnology";//"ageeableness";
                }
            }
        } else {
            if (neuroticismFinal > extraversionFinal) {
                if (neuroticismFinal > consientiousnessFinal) {
                    if (neuroticismFinal > areeablenessFinal) {
                        x = "friendliness";//"neuroticism";
                    } else {
                        x = "oldtechnology";//"ageeableness";
                    }
                } else {
                    if (consientiousnessFinal > areeablenessFinal) {
                        x = "professional"; //"consientiousness";
                    } else {
                        x = "oldtechnology";//"ageeableness";
                    }
                }
            } else {
                if (consientiousnessFinal > areeablenessFinal) {
                    x = "professional"; //"consientiousness";
                } else {
                    x = "oldtechnology";//"ageeableness";
                }
            }


        }

        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            Log.d("inotify", "Main-Usercharacteristics--Final convertion from big 5 to app category---" + x);
        }

        if (AppUserConfigs.MainUsercharacteristics_DebuggerLogger) {
            Log.d("inotify", "Main-Usercharacteristics--Stop---");
        }

        return x;
    }

}
