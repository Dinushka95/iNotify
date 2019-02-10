package com.example.inotify;

import android.content.Context;

import java.util.ArrayList;

public class MainUsercharacteristics {

    public String getUsercharacteristics(Context context){

        Mit_SqlLiteDbHelper mit_sqlLiteDbHelper = new Mit_SqlLiteDbHelper(context);

        long appListCountFixedvalue =75;

        long appUsageCountAvg =mit_sqlLiteDbHelper.mit_appUsageAverage_get();
        long appListCountAvg =mit_sqlLiteDbHelper.mit_applistAverage_get();
        long contactCountAvg =mit_sqlLiteDbHelper.mit_contactsAverage_get();
        long screenTimeCountAvg =mit_sqlLiteDbHelper.mit_screentimeAverage_get();
        long calldurationCountAvg =mit_sqlLiteDbHelper.mit_calldurationAverage_get();
        long calendarCountAvg =mit_sqlLiteDbHelper.mit_calendereventAverage_get();
        long chargetimeAvg =mit_sqlLiteDbHelper.mit_chargeAverage_get();
        long socialmediacAppCountAvg =mit_sqlLiteDbHelper.mit_applistsocialmediaAverage_get();


        long appUsageCountLast =mit_sqlLiteDbHelper.mit_appUsageLast_get();
        long appListCountLast =mit_sqlLiteDbHelper.mit_applistLast_get();
        long contactCountLast =mit_sqlLiteDbHelper.mit_contactsLast_get();
        long screenTimeCountLast =mit_sqlLiteDbHelper.mit_screentimeLast_get();
        long calldurationCountLast =mit_sqlLiteDbHelper.mit_calldurationLast_get();
        long calendarCountLast =mit_sqlLiteDbHelper.mit_calendereventLast_get();
        long chargetimeLast =mit_sqlLiteDbHelper.mit_chargeLast_get();
        long socialmediacAppCountLast =mit_sqlLiteDbHelper.mit_applistsocialmediaLast_get();


        String appUsageCountStatue ="error";
        String appListCountStatue ="error";
        String appListDownloadStatue ="error";
        String contactCountStatue ="error";
        String screenTimeCountStatue ="error";
        String calldurationCountStatue ="error";
        String calendarCountStatue ="error";
        String chargetimeStatue ="error";
        String socialmediacAppCountStatue ="error";


        if(appUsageCountLast>appUsageCountAvg){appUsageCountStatue="high";}else{appUsageCountStatue="low";}
        if(appListCountLast>appListCountFixedvalue){appListCountStatue="high";}else{appListCountStatue="low";}
        if(appListCountLast>appListCountAvg){appListDownloadStatue="high";}else{appListDownloadStatue="low";}
        if(contactCountLast>contactCountAvg){contactCountStatue="high";}else{contactCountStatue="low";}
        if(screenTimeCountLast>screenTimeCountAvg){screenTimeCountStatue="high";}else{screenTimeCountStatue="low";}
        if(calldurationCountLast>calldurationCountAvg){calldurationCountStatue="high";}else{calldurationCountStatue="low";}
        if(calendarCountLast>calendarCountAvg){calendarCountStatue="high";}else{calendarCountStatue="low";}
        if(chargetimeLast>chargetimeAvg){chargetimeStatue="high";}else{chargetimeStatue="low";}
        if(socialmediacAppCountLast>socialmediacAppCountAvg){socialmediacAppCountStatue="high";}else{socialmediacAppCountStatue="low";}




        //////////////////////////////////////////////////////////////////////////////////////////////
        // openness

        double opennessFinal=0.0;

        int openness_WL1=0;
        int openness_WL2=0;
        int openness_WL3=0;

        if(appUsageCountStatue.equals("high")){openness_WL1=20;}else{openness_WL1=0;}
        if(appListCountStatue.equals("high")){openness_WL2=40;}else{openness_WL2=0;}
        if(appListDownloadStatue.equals("high")){openness_WL3=40;}else{openness_WL3=0;}

        opennessFinal=(openness_WL1+openness_WL2+openness_WL3)/100;


        //////////////////////////////////////////////////////////////////////////////////////////////
        // neuroticism

        double neuroticismFinal=0.0;

        int neuroticism_WL1=0;
        int neuroticism_WL2=0;
        int neuroticism_WL3=0;

        if(appUsageCountStatue.equals("high")){neuroticism_WL1=30;}else{neuroticism_WL1=0;}
        if(chargetimeStatue.equals("high")){neuroticism_WL2=40;}else{neuroticism_WL2=0;}
        if(socialmediacAppCountStatue.equals("high")){neuroticism_WL3=30;}else{neuroticism_WL3=0;}

        neuroticismFinal=(neuroticism_WL1+neuroticism_WL2+neuroticism_WL3)/100;

        //////////////////////////////////////////////////////////////////////////////////////////////
        // extraversion

        double extraversionFinal=0.0;

        int extraversion_WL1=0;
        int extraversion_WL2=0;
        int extraversion_WL3=0;
        int extraversion_WL4=0;
        int extraversion_WL5=0;

        if(appListDownloadStatue.equals("high")){extraversion_WL1=10;}else{extraversion_WL1=0;}
        if(socialmediacAppCountStatue.equals("high")){extraversion_WL2=30;}else{extraversion_WL2=0;}
        if(chargetimeStatue.equals("high")){extraversion_WL3=10;}else{extraversion_WL3=0;}
        if(screenTimeCountStatue.equals("high")){extraversion_WL4=20;}else{extraversion_WL4=0;}
        if(calldurationCountStatue.equals("high")){extraversion_WL5=30;}else{extraversion_WL5=0;}

        extraversionFinal=(extraversion_WL1+extraversion_WL2+extraversion_WL3+extraversion_WL4+extraversion_WL5)/100;

        //////////////////////////////////////////////////////////////////////////////////////////////
        // consientiousness

        double consientiousnessFinal=0.0;

        int consientiousness_WL1=0;
        int consientiousness_WL2=0;
        int consientiousness_WL3=0;
        int consientiousness_WL4=0;

        if(calendarCountStatue.equals("high")){consientiousness_WL1=30;}else{consientiousness_WL1=0;}
        if(chargetimeStatue.equals("high")){consientiousness_WL2=30;}else{consientiousness_WL2=0;}
        if(calldurationCountStatue.equals("high")){consientiousness_WL3=20;}else{consientiousness_WL3=0;}
        if(screenTimeCountStatue.equals("high")){consientiousness_WL4=20;}else{consientiousness_WL4=0;}

        consientiousnessFinal=(consientiousness_WL1+consientiousness_WL2+consientiousness_WL3+consientiousness_WL4)/100;

        //////////////////////////////////////////////////////////////////////////////////////////////
        // areeableness

        double areeablenessFinal=0.0;

        int areeableness_WL1=0;
        int areeableness_WL2=0;
        int areeableness_WL3=0;
        int areeableness_WL4=0;
        int areeableness_WL5=0;

        if(screenTimeCountStatue.equals("high")){consientiousness_WL1=10;}else{consientiousness_WL1=0;}
        if(appListDownloadStatue.equals("high")){consientiousness_WL2=20;}else{consientiousness_WL2=0;}
        if(appListCountStatue.equals("high")){consientiousness_WL3=10;}else{consientiousness_WL3=0;}
        if(contactCountStatue.equals("high")){consientiousness_WL4=30;}else{consientiousness_WL4=0;}
        if(calldurationCountStatue.equals("high")){consientiousness_WL4=30;}else{consientiousness_WL4=0;}

        areeablenessFinal=(areeableness_WL1+areeableness_WL2+areeableness_WL3+areeableness_WL4+areeableness_WL5)/100;

        //save all sates to db
        ArrayList<Double> ad = new ArrayList<>();
        ad.add(opennessFinal);
        ad.add(neuroticismFinal);
        ad.add(extraversionFinal);
        ad.add(consientiousnessFinal);
        ad.add(areeablenessFinal);

        String x = "error";

        if(opennessFinal>neuroticismFinal){
            if(opennessFinal>extraversionFinal){
                if(opennessFinal>consientiousnessFinal){
                    if(opennessFinal>areeablenessFinal){
                        x="openness";//"openness";
                    }else{
                        x="oldtechnology";//"ageeableness";
                    }
                }else{
                    if(consientiousnessFinal>areeablenessFinal){
                        x="professional"; //"consientiousness";
                    }else{
                        x="oldtechnology";//"ageeableness";
                    }
                }
            } else {
                if(extraversionFinal>consientiousnessFinal){
                    if(extraversionFinal>areeablenessFinal){
                        x="social"; //"extraversion";
                    }else{
                        x="oldtechnology";//"ageeableness";
                    }
                }else{
                    if(consientiousnessFinal>areeablenessFinal){
                        x="professional"; //"consientiousness";
                    }else{
                        x="oldtechnology";//"ageeableness";
                    }
                }
            }
        }else {
            if(neuroticismFinal>extraversionFinal){
                if(neuroticismFinal>consientiousnessFinal){
                    if(neuroticismFinal>areeablenessFinal){
                        x="friendliness";//"neuroticism";
                    }else{
                        x="oldtechnology";//"ageeableness";
                    }
                }else{
                    if(consientiousnessFinal>areeablenessFinal){
                        x="professional"; //"consientiousness";
                    }else{
                        x="oldtechnology";//"ageeableness";
                    }
                }
            } else {
                if(extraversionFinal>consientiousnessFinal){
                    if(extraversionFinal>areeablenessFinal){
                        x="social"; //"extraversion";
                    }else{
                        x="oldtechnology";//"ageeableness";
                    }
                }else{
                    if(consientiousnessFinal>areeablenessFinal){
                        x="professional"; //"consientiousness";
                    }else{
                        x="oldtechnology";//"ageeableness";
                    }
                }
            }


        }
        //social-Extraversion
        //professional-Conscientiousness
        //friendliness - 	Neuroticism
        //gaming - openess
        // oldtechnology - agreeableness


        return x;
    }

}
