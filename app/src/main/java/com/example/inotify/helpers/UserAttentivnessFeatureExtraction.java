package com.example.inotify.helpers;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAttentivnessFeatureExtraction {

    private Context c1;

    public UserAttentivnessFeatureExtraction(Context context)
    {
        c1=context;
    }

    public double UserAttentivnessFeatureExtraction( String screenstatus, String RingerMode, String Viewtime, String RecivedTime, String Sequence, int notificationTotal )
    {
        double a = 0;
        double silent  = 99,general = 99 , vibrate = 99,sequenceImportnacegiven  = 99,sequenceImportnaceotGiven = 99 , IsDelayed  = 99, IsnotDelayed = 99 , ScreenOn = 99 ,Screenoff = 99;

        double sequenceAVG = notificationTotal/2.0;
        double sequence = Double.parseDouble(Sequence);

        //check Ringer Mode
        if(RingerMode.equals("genaral"))
        {
            general=1;
            silent =0;
            vibrate=0;
        }
        else if(RingerMode.equals("silent"))
        {
            general=0;
            silent =1;
            vibrate=0;
        }
        else if(RingerMode.equals("vibrate"))
        {
            general=0;
            silent =0;
            vibrate=1;
        }
       //Check sequence
        if(sequenceAVG > sequence)
        {
            sequenceImportnacegiven = 1;
            sequenceImportnaceotGiven =0;
        }
        else
        {
            sequenceImportnacegiven = 0;
            sequenceImportnaceotGiven =1;
        }


        Date timeviwed= new Date();
        Date  timeRecived = new Date();


        //Convert the string value to date time
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        try{

            timeviwed =(Date) dateFormat.parse(Viewtime);
            timeRecived =(Date) dateFormat.parse(RecivedTime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }


        Log.d("inotify(^_^)" ,"time viwed  " +timeviwed);
        Log.d("inotify(^_^)" ,"time recived " +timeRecived);

        long delay = timeviwed.getTime() - timeRecived.getTime();
        // long diffMinutes = diff / (60 * 1000) % 60;
        long delayinminute = delay/60000 % 60;
        Date delaydiff = new Date(delay);


        Log.d("inotify(^_^)" , "delay=============" +delay );
        Log.d("inotify(^_^)" , "delaydiff=============" +delayinminute );
        //Check Delay
        if(delayinminute <= 10)
        {
            IsDelayed =0;
            IsnotDelayed =0;
        }
        else
        {
            IsDelayed =0;
            IsnotDelayed =1;
        }

        //Check Screen Off
        if(screenstatus.equals("on"))
        {
            ScreenOn= 1;
            Screenoff =0;
        }
        else
        {
            ScreenOn = 0;
            Screenoff =1;
        }

        Log.d ("inotify" , "features   ====================== screenstatus, String RingerMode, String Viewtime, String RecivedTime, String Sequence, int notificationTotal" );

        Log.d("inotify " ,"feature exteaction ==== " + silent + " , " +general+  " , " + vibrate + " , " + sequenceImportnacegiven+ " , " +sequenceImportnaceotGiven+ " , " + IsDelayed+ " , " +IsnotDelayed+ " , " + ScreenOn+ " , " +Screenoff);

        return a;

    }


    public Double IdentifyRule( String screenstatus, String RingerMode, String Viewtime, String RecivedTime, String Sequence, int notificationTotal )
    {

        double sequenceAVG = notificationTotal/2.0;
        double notificationSequence = Double.parseDouble(Sequence);


        Date timeviwed= new Date();
        Date  timeRecived = new Date();


        //Convert the string value to date time
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        try{

            timeviwed =(Date) dateFormat.parse(Viewtime);
            timeRecived =(Date) dateFormat.parse(RecivedTime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }


        Log.d("inotify(^_^)" ,"time viwed  " +timeviwed);
        Log.d("inotify(^_^)" ,"time recived " +timeRecived);

        long delay = timeviwed.getTime() - timeRecived.getTime();
        // long diffMinutes = diff / (60 * 1000) % 60;
        long delayinminute = delay/60000 % 60;
        Date delaydiff = new Date(delay);


        Log.d("inotify(^_^)" , "delay=============" +delay );
        Log.d("inotify(^_^)" , "delaydiff=============" +delayinminute );

        double RuleNo = 0;

        if((RingerMode.equals("normal")) && ( notificationSequence > sequenceAVG) && (delay <=10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 1;

        }
        else if((RingerMode.equals("normal")) && ( notificationSequence > sequenceAVG) && (delay <=10) && (screenstatus.equals("on"))  )
        {

            RuleNo = 2;
        }
        else if((RingerMode.equals("normal")) && ( notificationSequence > sequenceAVG) && (delay >10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 3;

        }
        else if((RingerMode.equals("normal")) && ( notificationSequence > sequenceAVG) && (delay >10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 4;

        }
        else if((RingerMode.equals("normal")) && ( notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 5;

        }
        else if((RingerMode.equals("normal")) && ( notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 6;

        }
        else if((RingerMode.equals("normal")) && ( notificationSequence <= sequenceAVG) && (delay >10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 7;

        }
        else if((RingerMode.equals("normal")) && ( notificationSequence <= sequenceAVG) && (delay  >10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 8;

        }


//*********************************************************Silent /////////////////////////////////////////////////////////////////////////////////


        if((RingerMode.equals("silent")) && ( notificationSequence > sequenceAVG) && (delay <=10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 9;

        }
        else if((RingerMode.equals("silent")) && ( notificationSequence > sequenceAVG) && (delay <=10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 10;

        }
        else if((RingerMode.equals("silent")) && ( notificationSequence > sequenceAVG) && (delay >10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 11;

        }
        else if((RingerMode.equals("silent")) && ( notificationSequence > sequenceAVG) && (delay >10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 12;

        }
        else if((RingerMode.equals("silent")) && ( notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 13;

        }
        else if((RingerMode.equals("silent")) && ( notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 14;

        }
        else if((RingerMode.equals("silent")) && ( notificationSequence <= sequenceAVG) && (delay >10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 15;

        }
        else if((RingerMode.equals("silent")) && ( notificationSequence <= sequenceAVG) && (delay  >10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 16;

        }




        //*********************************************************Vibrate /////////////////////////////////////////////////////////////////////////////////


        if((RingerMode.equals("vibrate")) && ( notificationSequence > sequenceAVG) && (delay <=10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 17;

        }
        else if((RingerMode.equals("vibrate")) && ( notificationSequence > sequenceAVG) && (delay <=10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 18;

        }
        else if((RingerMode.equals("vibrate")) && ( notificationSequence > sequenceAVG) && (delay >10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 19;

        }
        else if((RingerMode.equals("vibrate")) && ( notificationSequence > sequenceAVG) && (delay >10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 20;

        }
        else if((RingerMode.equals("vibrate")) && ( notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 21;

        }
        else if((RingerMode.equals("vibrate")) && ( notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 22;

        }
        else if((RingerMode.equals("vibrate")) && ( notificationSequence <= sequenceAVG) && (delay >10) && (screenstatus.equals("off"))  )
        {
            RuleNo = 23;

        }
        else if((RingerMode.equals("vibrate")) && ( notificationSequence <= sequenceAVG) && (delay  >10) && (screenstatus.equals("on"))  )
        {
            RuleNo = 24;

        }
        else {
            Log.d("inotify " , "error");

        }

       /// attentValue = (0.113*ringerModeWeight*RingerModeValue) + (0.1190*ScreeStatusValue* screenStatusWeight) + (0.3539*timeDelayWeight*TimeDelayValue) + (0.1936 * sequenceWeight*notificationSequence);


        //*******************************************************************************************************//

        return RuleNo;


    }

}
