package com.example.inotify.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.inotify.dbHelpers.AttentivnessPerAppDbHelper;
import com.example.inotify.dbHelpers.NotificationDbHelper;
import com.example.inotify.dbHelpers.RingerModeDbHelper;
import com.example.inotify.dbHelpers.ScreenStatusDbHelper;
import com.example.inotify.dbHelpers.UserAttentivnessDbHelper;
import com.example.inotify.services.MyNotificationListenerService;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class MainUserAttentivness {


    private Context c1;

    public MainUserAttentivness(Context context) {
        c1 = context;
    }

    //write a method to check in which table does the record exisits (screen on tabele or screen off table)

    public double calculateAttentivness(String id, String screenstatus, String RingerMode, String Viewtime, String RecivedTime, String Sequence, int notificationTotal) {
        double attentiivness = 0;

        String Id = id;

        double Attentivnes = 0.0;
        int seqenceAvg = (notificationTotal) / 2;
        int notificationSequence = Integer.parseInt(Sequence);
//        int timeViewed = Integer.parseInt(Viewtime);

        Date timeviwed = new Date();
        Date timeRecived = new Date();


        //Convert the string value to date time
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        try {

            timeviwed = (Date) dateFormat.parse(Viewtime);
            timeRecived = (Date) dateFormat.parse(RecivedTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        long delay = timeviwed.getTime() - timeRecived.getTime();
        // long diffMinutes = diff / (60 * 1000) % 60;
        long delayinminute = delay / 60000 % 60;
        Date delaydiff = new Date(delay);


        //     int timeRecived = Integer.parseInt(RecivedTime);

        double RMWeight = 0;
        double STweight = 0;
        double delayWeight = 0;
        double seqenceWeight = Double.parseDouble(Sequence);
        double seqencemp = 0;




        // if(notificationSequence <= seqenceAvg )
        if (RingerMode.equals("normal")) {
            RMWeight = 0.3333;


            if (notificationSequence > seqenceAvg) {
                seqencemp = 1;

                if (delay <= 10) {
                    delayWeight = 1;

                    if (screenstatus.equals("off")) {
                        STweight = 1;


                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);


                    } else {
                        STweight = 0;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                } else {
                    delayWeight = 1;

                    if (screenstatus.equals("off")) {
                        //locked , importnace not given , seqebncy High
                        STweight = 1;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);


                    } else {
                        STweight = 0;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                        //unlocked , importnace not given , seqebncy High
                    }
                }
            } else {
                seqencemp = 0;
                if (delay <= 10) {
                    delayWeight = 1;
                    if (screenstatus.equals("off")) {
                        STweight = 1;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        STweight = 0;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                } else {
                    delayWeight = 0;
                    if (screenstatus.equals("off")) {
                        STweight = 1;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        STweight = 0;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);


                    }
                }

            }

        } else if (RingerMode.equals("vibrate")) {
            RMWeight = 0.6666;
            if (notificationSequence > seqenceAvg) {
                seqencemp = 1;
                if (delay <= 100000) {
                    delayWeight = 1;
                    if (screenstatus.equals("off")) {
                        STweight = 1;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        STweight = 0;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                } else {
                    delayWeight = 0;
                    if (screenstatus.equals("off")) {
                        //locked , importnace not given , seqebncy High
                        STweight = 1;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        //unlocked , importnace not given , seqebncy High
                        STweight = 0;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);


                    }
                }
            } else {
                seqencemp = 0;
                if (delay < 100000) {
                    delayWeight = 1;
                    if (screenstatus.equals("off")) {
                        //locked
                        STweight = 1;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                } else {
                    delayWeight = 0;
                    if (screenstatus.equals("off")) {
                        //locked
                        STweight = 1;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                }

            }
        } else if (RingerMode.equals("silent")) {
            RMWeight = 1;

            if (notificationSequence <= seqenceAvg) {
                seqencemp = 1;

                if (delay <= 100000) {
                    delayWeight = 1;

                    if (screenstatus.equals("off")) {
                        //locked , importnace given , seqebncy low
                        STweight = 1;
                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                } else {
                    delayWeight = 0;

                    if (screenstatus.equals("off")) {
                        //locked , importnace not given , seqebncy High
                        STweight = 0;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        //unlocked , importnace not given , seqebncy High

                        STweight = 0;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                }
            } else {
                seqencemp = 0;

                if (delay < 100000) {
                    delayWeight = 1;

                    if (screenstatus.equals("off")) {
                        //locked
                        STweight = 1;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                } else {
                    delayWeight = 0;

                    if (screenstatus.equals("off")) {
                        //locked
                        STweight = 0;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    } else {
                        //unlocked
                        STweight = 0;

                        Attentivnes = (0.113 * RMWeight * 0.3333) + (0.1190 * STweight * 0.5) + (0.3539 * delayWeight * 0.5) + (0.1936 * seqencemp * seqenceWeight);

                    }
                }

            }



        }
        return Attentivnes;
    }


    public double CalcAtten(String id, String PackageName, String screenstatus, String RingerMode, String Viewtime, String RecivedTime, String Sequence, int notificationTotal) {
        double attentValue = 0;
        double Ringermode = 0.0;
        double ScreenStatus = 0.0;
        double AppImportnace = 0.0;
        double TimeDelay = 0.0;


        double ringerModeWeight = 0.0;
        double sequenceWeight = 0.0;
        double screenStatusWeight = 0.0;
        double appImportnaceWight = 0.0;
        double timeDelayWeight = 0.0;


        AttentivnessPerAppDbHelper attentivnessPerAppDbHelper = new AttentivnessPerAppDbHelper(c1);

        int AppExistence = AttentivnessPerAppDbHelper.getInstance(c1).CheckExistance();
        //attentivnessPerAppDbHelper.CheckExistance();

        if (AppExistence > 0) {
            double screenStatusRecordCountPerMode = 0.0;
            //update method


            //get ringer Mode Weighting
            double recordcount = RingerModeDbHelper.getInstance(c1).RecordCount();
            double recordCountPerMode = RingerModeDbHelper.getInstance(c1).RecordCountPerMode(RingerMode);
            Ringermode = recordCountPerMode / recordcount;


            double screenStatusRecordCount = ScreenStatusDbHelper.getInstance(c1).screenStatusRecordCount();
            if (screenstatus.equals("on")) {
                screenStatusRecordCountPerMode = ScreenStatusDbHelper.getInstance(c1).screenOnStatusRecordCountPerMode();
            } else {
                screenStatusRecordCountPerMode = ScreenStatusDbHelper.getInstance(c1).screenOffStatusRecordCountPerMode();
            }
            ScreenStatus = screenStatusRecordCountPerMode / screenStatusRecordCount;

            AppImportnace = 0.5;
            TimeDelay = 0.5;


            attentValue = this.attention(id, PackageName, screenstatus, RingerMode, Viewtime, RecivedTime, Sequence, notificationTotal, Ringermode, ScreenStatus, AppImportnace, TimeDelay);

        } else {
            //Insert method

            Ringermode = 0.3333;
            ScreenStatus = 0.5;
            AppImportnace = 0.5;
            TimeDelay = 0.5;

            attentValue = this.attention(id, PackageName, screenstatus, RingerMode, Viewtime, RecivedTime, Sequence, notificationTotal, Ringermode, ScreenStatus, AppImportnace, TimeDelay);

        }

        return attentValue;
    }


    public double attention(String id, String PackageName, String screenstatus, String RingerMode, String Viewtime, String RecivedTime, String Sequence, int notificationTotal, double RingerModeValue, double ScreeStatusValue, double AppImportnaceValue, double TimeDelayValue) {

        double attentValue = 0.0;


        double ringerModeWeight = 0.0;
        double sequenceWeight = 0.0;
        double screenStatusWeight = 0.0;
        double appImportnaceWight = 0.0;
        double timeDelayWeight = 0.0;

        double sequenceAVG = (notificationTotal) / 2.0;
        int notificationSequence = Integer.parseInt(Sequence);

        Date timeviwed = new Date();
        Date timeRecived = new Date();


        //Convert the string value to date time
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        try {

            timeviwed = (Date) dateFormat.parse(Viewtime);
            timeRecived = (Date) dateFormat.parse(RecivedTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }




        long delay = timeviwed.getTime() - timeRecived.getTime();
        // long diffMinutes = diff / (60 * 1000) % 60;
        long delayinminute = delay / 60000 % 60;
        Date delaydiff = new Date(delay);




        if ((RingerMode.equals("normal")) && (notificationSequence > sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("normal")) && (notificationSequence > sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("normal")) && (notificationSequence > sequenceAVG) && (delay > 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("normal")) && (notificationSequence > sequenceAVG) && (delay > 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("normal")) && (notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("normal")) && (notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("normal")) && (notificationSequence <= sequenceAVG) && (delay > 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("normal")) && (notificationSequence <= sequenceAVG) && (delay > 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 0.0;

        }


//*********************************************************Silent /////////////////////////////////////////////////////////////////////////////////


        if ((RingerMode.equals("silent")) && (notificationSequence > sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("silent")) && (notificationSequence > sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("silent")) && (notificationSequence > sequenceAVG) && (delay > 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("silent")) && (notificationSequence > sequenceAVG) && (delay > 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("silent")) && (notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("silent")) && (notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("silent")) && (notificationSequence <= sequenceAVG) && (delay > 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("silent")) && (notificationSequence <= sequenceAVG) && (delay > 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 0.0;

        }


        //*********************************************************Vibrate /////////////////////////////////////////////////////////////////////////////////


        if ((RingerMode.equals("vibrate")) && (notificationSequence > sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("vibrate")) && (notificationSequence > sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("vibrate")) && (notificationSequence > sequenceAVG) && (delay > 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("vibrate")) && (notificationSequence > sequenceAVG) && (delay > 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 0.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("vibrate")) && (notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("vibrate")) && (notificationSequence <= sequenceAVG) && (delay <= 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 1.0;
            screenStatusWeight = 0.0;

        } else if ((RingerMode.equals("vibrate")) && (notificationSequence <= sequenceAVG) && (delay > 10) && (screenstatus.equals("off"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 1.0;

        } else if ((RingerMode.equals("vibrate")) && (notificationSequence <= sequenceAVG) && (delay > 10) && (screenstatus.equals("on"))) {
            ringerModeWeight = 1.0;
            sequenceWeight = 1.0;
            timeDelayWeight = 0.0;
            screenStatusWeight = 0.0;

        } else {
            Log.d("inotify ", "error");

        }

        attentValue = (((0.113 * ringerModeWeight * RingerModeValue) + (0.1190 * ScreeStatusValue * screenStatusWeight) + (0.3539 * timeDelayWeight * TimeDelayValue) + (0.1936 * sequenceWeight * notificationSequence))/3.5998)*100;
        //attentValue = (0.113 * ringerModeWeight * RingerModeValue) + (0.1190 * ScreeStatusValue * screenStatusWeight) + (0.3539 * timeDelayWeight * TimeDelayValue) + (0.1936 * sequenceWeight * notificationSequence);


        //*******************************************************************************************************//

        return attentValue;

    }




}


