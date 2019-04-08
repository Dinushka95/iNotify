package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.models.SNSModel;

import java.util.ArrayList;
import java.util.List;

public class SmartNotificationSystemHelper {

    private Context c1;

    public SmartNotificationSystemHelper(Context context) {
        c1=context;
    }

    public SNSModel convertSNSdataToNumberic(SNSModel snsModelData) {

        SNSModel snsModelNumeric = new SNSModel();

        String day = snsModelData.getDay();
        String cday = "";
        if (day.equals("Monday")) {
            cday = "1";
        }
        if (day.equals("Tuesday")) {
            cday = "2";
        }
        if (day.equals("Wednesday")) {
            cday = "3";
        }
        if (day.equals("Thursday")) {
            cday = "4";
        }
        if (day.equals("Friday")) {
            cday = "5";
        }
        if (day.equals("Saturday")) {
            cday = "6";
        }
        if (day.equals("Sunday")) {
            cday = "7";
        }
        snsModelNumeric.setDay(cday);

        snsModelNumeric.setTime(snsModelData.getTime());

        snsModelNumeric.setViewability(snsModelData.getViewability());

        snsModelNumeric.setAttentiviness(snsModelData.getAttentiviness());

        String userchaacteristics = snsModelData.getUserchacteristics();
        String cuserchaacteristics = "";
        if (userchaacteristics.equals("openness")) {
            cuserchaacteristics = "1";
        }
        if (userchaacteristics.equals("agreeableness")) {
            cuserchaacteristics = "2";
        }
        if (userchaacteristics.equals("neuroticism")) {
            cuserchaacteristics = "3";
        }
        if (userchaacteristics.equals("extraversion")) {
            cuserchaacteristics = "4";
        }
        if (userchaacteristics.equals("conscientiousness")) {
            cuserchaacteristics = "5";
        }
        snsModelNumeric.setUserchacteristics(cuserchaacteristics);

        String notificationtype = snsModelData.getNotificationtype();
        String cnotificationtype = "";
        if (notificationtype.equals("business")) {
            cnotificationtype = "1";
        }
        if (notificationtype.equals("communication")) {
            cnotificationtype = "2";
        }
        if (notificationtype.equals("dating")) {
            cnotificationtype = "3";
        }
        if (notificationtype.equals("education")) {
            cnotificationtype = "4";
        }
        if (notificationtype.equals("entertainment")) {
            cnotificationtype = "5";
        }
        if (notificationtype.equals("famailymusicvideo")) {
            cnotificationtype = "6";
        }
        if (notificationtype.equals("gaming")) {
            cnotificationtype = "7";
        }
        if (notificationtype.equals("healthandfitness")) {
            cnotificationtype = "8";
        }
        if (notificationtype.equals("librariesanddemo")) {
            cnotificationtype = "9";
        }
        if (notificationtype.equals("lifestyle")) {
            cnotificationtype = "10";
        }
        if (notificationtype.equals("musicandaudio")) {
            cnotificationtype = "11";
        }
        if (notificationtype.equals("personalization")) {
            cnotificationtype = "12";
        }
        if (notificationtype.equals("photograph")) {
            cnotificationtype = "13";
        }
        if (notificationtype.equals("productivity")) {
            cnotificationtype = "14";
        }
        if (notificationtype.equals("tools")) {
            cnotificationtype = "15";
        }
        if (notificationtype.equals("weather")) {
            cnotificationtype = "16";
        }
        if (notificationtype.equals("other")) {
            cnotificationtype = "17";
        }
        snsModelNumeric.setNotificationtype(cnotificationtype);

        String appname = snsModelData.getPackagename();
        String cid = String.valueOf(ApplicationDbHelper.getInstance(c1).getApplicationDetailsByPackName(appname).getId());
        if(cid==""||cid==null){cid="999";}
        snsModelNumeric.setPackagename(cid);

        if(snsModelData.getVtime()!=null){
            snsModelNumeric.setVtime(snsModelData.getVtime());
        }

        return snsModelNumeric;

    }

    public SNSModel convertNumbericToSNSdata(SNSModel snsModelNumeric) {

        SNSModel snsModelData = new SNSModel();

        String day = snsModelNumeric.getDay();
        String cday = "";
        if (day.equals("1")) {
            cday = "Monday";
        }
        if (day.equals("2")) {
            cday = "Tuesday";
        }
        if (day.equals("3")) {
            cday = "Wednesday";
        }
        if (day.equals("4")) {
            cday = "Thursday";
        }
        if (day.equals("5")) {
            cday = "Friday";
        }
        if (day.equals("6")) {
            cday = "Saturday";
        }
        if (day.equals("7")) {
            cday = "Sunday";
        }
        snsModelData.setDay(cday);

        snsModelData.setTime(snsModelNumeric.getTime());

        snsModelData.setViewability(snsModelNumeric.getViewability());

        snsModelData.setAttentiviness(snsModelNumeric.getAttentiviness());

        String userchaacteristics = snsModelNumeric.getUserchacteristics();
        String cuserchaacteristics = "";
        if (userchaacteristics.equals("1")) {
            cuserchaacteristics = "openness";
        }
        if (userchaacteristics.equals("2")) {
            cuserchaacteristics = "agreeableness";
        }
        if (userchaacteristics.equals("3")) {
            cuserchaacteristics = "neuroticism";
        }
        if (userchaacteristics.equals("4")) {
            cuserchaacteristics = "extraversion";
        }
        if (userchaacteristics.equals("5")) {
            cuserchaacteristics = "conscientiousness";
        }
        snsModelData.setUserchacteristics(cuserchaacteristics);

        String notificationtype = snsModelNumeric.getNotificationtype();
        String cnotificationtype = "";
        if (notificationtype.equals("1")) {
            cnotificationtype = "business";
        }
        if (notificationtype.equals("2")) {
            cnotificationtype = "communication";
        }
        if (notificationtype.equals("3")) {
            cnotificationtype = "dating";
        }
        if (notificationtype.equals("4")) {
            cnotificationtype = "education";
        }
        if (notificationtype.equals("5")) {
            cnotificationtype = "entertainment";
        }
        if (notificationtype.equals("6")) {
            cnotificationtype = "famailymusicvideo";
        }
        if (notificationtype.equals("7")) {
            cnotificationtype = "gaming";
        }
        if (notificationtype.equals("8")) {
            cnotificationtype = "healthandfitness";
        }
        if (notificationtype.equals("9")) {
            cnotificationtype = "librariesanddemo";
        }
        if (notificationtype.equals("10")) {
            cnotificationtype = "lifestyle";
        }
        if (notificationtype.equals("11")) {
            cnotificationtype = "musicandaudio";
        }
        if (notificationtype.equals("12")) {
            cnotificationtype = "personalization";
        }
        if (notificationtype.equals("13")) {
            cnotificationtype = "photograph";
        }
        if (notificationtype.equals("14")) {
            cnotificationtype = "productivity";
        }
        if (notificationtype.equals("15")) {
            cnotificationtype = "tools";
        }
        if (notificationtype.equals("16")) {
            cnotificationtype = "weather";
        }
        if (notificationtype.equals("17")) {
            cnotificationtype = "other";
        }
        snsModelData.setNotificationtype(cnotificationtype);

        String id = snsModelNumeric.getPackagename();
        String cappname = String.valueOf(ApplicationDbHelper.getInstance(c1).getApplicationDetailsById(id).getPackageName());
        snsModelData.setPackagename(cappname);

        if(!snsModelNumeric.getVtime().isEmpty()){
            snsModelData.setVtime(snsModelNumeric.getVtime());
        }

        return snsModelData;
    }

    public List<SNSModel> convertSNSdataToNumberic(List<SNSModel> snsModelListData) {

        List<SNSModel>snsModelListNumeric = new ArrayList<>();
        for(SNSModel x:snsModelListData){
            snsModelListNumeric.add(convertSNSdataToNumberic(x));
        }
        return snsModelListNumeric;
    }

    public List<SNSModel> convertNumbericToSNSdata(List<SNSModel> snsModelListNumeric) {

        List<SNSModel>snsModelListData = new ArrayList<>();
        for(SNSModel x:snsModelListNumeric){
            snsModelListData.add(convertNumbericToSNSdata(x));
        }
        return snsModelListData;
    }




}
