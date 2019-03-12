package com.example.inotify.helpers;

import android.util.Log;

import com.example.inotify.dbHelpers.NotificationDbHelper;
import com.example.inotify.dbHelpers.ScreenStatusDbHelper;
import com.example.inotify.services.MyNotificationListenerService;

public class UserAttentivness extends MyNotificationListenerService {





    //write a method to check in which table does the record exisits (screen on tabele or screen off table)

    public double calculateAttentivness(String id, String screenstatus ,String RingerMode , String Viewtime , String RecivedTime , String Sequence,int notificationTotal )
    {
        double attentiivness = 0;
//        get the total number of notifications in the notification bar
//        devide by two and categorise
        int seqenceAvg  = notificationTotal/2;
        int notificationSequence =   Integer.parseInt(Sequence);

        int timeViewed = Integer.parseInt(Viewtime);
        int timeRecived = Integer.parseInt(RecivedTime);
        int delay = timeViewed- timeRecived;
        double RMWeight =0;
        double STweight = 0;
        double delayWeight = 0;
        double seqenceWeight =  Double.parseDouble(Sequence);
        double seqencemp =0;
       // if(notificationSequence <= seqenceAvg )
        if(RingerMode == "General")
        {
            RMWeight =0.3333;
            if (notificationSequence > seqenceAvg)
            {
                seqencemp = 1;
                if(delay <=10)
                {
                    delayWeight = 1;
                    if(screenstatus =="off")
                    {
                        STweight =1;
                    }
                    else
                    {
                        STweight =0;
                    }
                }
                else
                {
                    delayWeight = 1;
                    if(screenstatus =="off")
                    {
                        //locked , importnace not given , seqebncy High
                        STweight =1;

                    }
                    else
                    {
                        STweight =0;
                        //unlocked , importnace not given , seqebncy High
                    }
                }
            }
            else
            {
                seqencemp = 0;
                if(delay <=10)
                {
                    delayWeight = 1;
                    if(screenstatus =="off")
                    {
                        STweight = 1;
                    }
                    else
                    {
                        STweight = 0;
                    }
                }
                else
                {
                    delayWeight = 0;
                    if(screenstatus =="locked")
                    {
                        STweight = 1;
                    }
                    else
                    {
                        STweight = 0;
                    }
                }

            }

        }
        else if(RingerMode == "Vibrate")
        {
            RMWeight = 0.6666;
            if (notificationSequence > seqenceAvg)
            {
                seqencemp =1;
                if(delay <=10)
                {
                    delayWeight=1;
                    if(screenstatus =="off")
                        STweight =1;
                    else
                    {
                        STweight =0;
                    }
                }
                else
                {
                    delayWeight=0;
                    if(screenstatus =="off")
                    {
                        //locked , importnace not given , seqebncy High
                        STweight =1;
                    }
                    else
                    {
                        //unlocked , importnace not given , seqebncy High
                        STweight =0;

                    }
                }
            }
            else
            {    seqencemp = 0;
                if(delay <10)
                {
                    delayWeight= 1;
                    if(screenstatus =="off")
                    {
                        //locked
                        STweight = 1;
                    }
                    else
                    {
                        //unlocked
                        STweight=0;
                    }
                }
                else
                {
                    delayWeight = 0;
                    if(screenstatus =="off")
                    {
                        //locked
                        STweight= 1;
                    }
                    else
                    {
                        //unlocked
                        STweight =0;
                    }
                }

            }
        }

        else if( RingerMode == "Silent")
        {
            RMWeight = 1;
            if (notificationSequence <= seqenceAvg)
            {
                seqencemp = 1;
                if(delay <=10)
                {
                    delayWeight =1;
                    if(screenstatus =="off")
                    {
                        //locked , importnace given , seqebncy low
                        STweight =1;
                    }
                    else
                    {
                        //unlocked
                        STweight =0;
                    }
                }
                else
                {
                    delayWeight =0;
                    if(screenstatus =="off")
                    {
                        //locked , importnace not given , seqebncy High
                        STweight =0;
                    }
                    else
                    {
                        //unlocked , importnace not given , seqebncy High
                        STweight =0;
                    }
                }
            }
            else
            {   seqencemp=0;
                if(delay <10)
                {
                    delayWeight = 1;
                    if(screenstatus =="off")
                    {
                        //locked
                        STweight = 1;
                    }
                    else
                    {
                        //unlocked
                        STweight = 0;
                    }
                }
                else
                {
                    delayWeight = 0;
                    if(screenstatus =="off")
                    {
                        //locked
                        STweight = 0;
                    }
                    else
                    {
                        //unlocked
                        STweight =0;
                    }
                }

            }
        }

        double Attentivnes = (0.113* RMWeight*0.5) + (0.1190 *STweight*0.5) + (0.3539*delayWeight*0.5) + (0.1936* seqencemp *seqenceWeight);
        Log.d("inotifyC" ,"Attentiivness final value = " +Attentivnes );



        return attentiivness;
    }
}

