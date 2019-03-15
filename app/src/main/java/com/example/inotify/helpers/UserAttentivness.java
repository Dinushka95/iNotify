package com.example.inotify.helpers;

import android.util.Log;

import com.example.inotify.services.MyNotificationListenerService;

public class UserAttentivness  {


    //write a method to check in which table does the record exisits (screen on tabele or screen off table)

    public double calculateAttentivness(String id, String screenstatus, String RingerMode, String Viewtime, String RecivedTime, String Sequence, int notificationTotal) {
        double attentiivness = 0;
        Log.d("inotifyC ", "Attentivness id  " + id);
        String Id = id;

        double Attentivnes = 0.0;
        int seqenceAvg = (notificationTotal) / 2;
        int notificationSequence = Integer.parseInt(Sequence);
        int timeViewed = Integer.parseInt(Viewtime);
        int timeRecived = Integer.parseInt(RecivedTime);
        int delay = timeViewed - timeRecived;
        double RMWeight = 0;
        double STweight = 0;
        double delayWeight = 0;
        double seqenceWeight = Double.parseDouble(Sequence);
        double seqencemp = 0;


        Log.d("inotifyx", "RingerMode " + RingerMode);
        Log.d("inotifyx", "RecivedTime " + RecivedTime);
        Log.d("inotifyx", "Viewtime " + Viewtime);
        Log.d("inotifyx", "screenstatus " + screenstatus);
        Log.d("inotifyx", "Sequence " + Sequence);
        Log.d("inotifyx", "notificationTotal " + notificationTotal);

        // if(notificationSequence <= seqenceAvg )
        if (RingerMode.equals("normal")) {
            RMWeight = 0.3333;
            Log.d("inotifyx", "RingerMode is normal" + RMWeight);

            if (notificationSequence > seqenceAvg) {
                seqencemp = 1;
                Log.d("inotifyx", "seqencemp is " + seqencemp);
                if (delay <= 10) {
                    delayWeight = 1;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off") ) {
                        STweight = 1;

                        Log.d("notify", "hjkhjkkhjkh");
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "1" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);


                    } else {
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "2" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    }
                } else {
                    delayWeight = 1;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        //locked , importnace not given , seqebncy High
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "3" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);


                    } else {
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "4" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                        //unlocked , importnace not given , seqebncy High
                    }
                }
            } else {
                seqencemp = 0;
                Log.d("inotifyx", "seqencemp is " + seqencemp);
                if (delay <= 10) {
                    delayWeight = 1;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "5" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "6" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    }
                } else {
                    delayWeight = 0;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "7" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "8" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);


                    }
                }

            }

        } else if (RingerMode.equals("vibrate")) {
            RMWeight = 0.6666;
            Log.d("inotifyx", "RingerMode is Vibrate" + RMWeight);
            if (notificationSequence > seqenceAvg) {
                seqencemp = 1;
                Log.d("inotifyx", "seqencemp is " + seqencemp);
                if (delay <= 100000) {
                    delayWeight = 1;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "9" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "10" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    }
                } else {
                    delayWeight = 0;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        //locked , importnace not given , seqebncy High
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "11" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        //unlocked , importnace not given , seqebncy High
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "12" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);


                    }
                }
            } else {
                seqencemp = 0;
                Log.d("inotifyx", "seqencemp is " + seqencemp);
                if (delay < 100000) {
                    delayWeight = 1;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        //locked
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "13" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "14" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    }
                } else {
                    delayWeight = 0;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        //locked
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "15" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "16" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    }
                }

            }
        } else if (RingerMode.equals("silent")) {
            RMWeight = 1;
            Log.d("inotifyx", "RingerMode is Silent" + RMWeight);
            if (notificationSequence <= seqenceAvg) {
                seqencemp = 1;
                Log.d("inotifyx", "seqencemp is " + seqencemp);
                if (delay <= 100000) {
                    delayWeight = 1;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        //locked , importnace given , seqebncy low
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "17" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "18" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    }
                } else {
                    delayWeight = 0;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        //locked , importnace not given , seqebncy High
                        STweight = 0;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "19" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        //unlocked , importnace not given , seqebncy High
                        Log.d("notify12 ", "20" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                    }
                }
            } else {
                seqencemp = 0;
                Log.d("inotifyx", "seqencemp is " + seqencemp);
                if (delay < 100000) {
                    delayWeight = 1;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        //locked
                        STweight = 1;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "21" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "22" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    }
                } else {
                    delayWeight = 0;
                    Log.d("inotifyx", "delayWeight is " + delayWeight);
                    if (screenstatus.equals("off")) {
                        //locked
                        STweight = 0;
                        Log.d("inotifyx", "STweight is off " + STweight);
                        Log.d("notify12 ", "23" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;
                        Log.d("inotifyx", "STweight is on " + STweight);
                        Log.d("notify12 ", "24" + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);

                    }
                }

            }
         //   Log.d("notify12 ", "RMWeight ,STweight,delayWeight,seqencemp,seqenceWeight " + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);



//            double rm = 0.1133;
//            double st = 0.1190;
//            double dw = 0.3539;
//            double seq = 0.1936;
//
//
//            Log.d("inotifyC", " RM " + rm);
//            Log.d("inotifyC", " RM " + st);
//            Log.d("inotifyC", " RM " + dw);
//            Log.d("inotifyC", " RM " + seq);

            Log.d("notify ", "RMWeight ,STweight,delayWeight,seqencemp,seqenceWeight " + RMWeight + "," + STweight + "," + delayWeight + "," + seqencemp + "," + seqenceWeight);



        }
         Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);
        Log.d("inotifyC", "Attentiivness final value = " + Attentivnes);

        return Attentivnes;

    }
}

