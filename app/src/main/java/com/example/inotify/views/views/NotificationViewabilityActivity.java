package com.example.inotify.views.views;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.TbColNames;
import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.NotificationViewability_DbHelper;

import java.util.ArrayList;

public class NotificationViewabilityActivity extends AppCompatActivity {


    LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_viewability);
        mLayout = findViewById(R.id.liner);

        NotificationViewability_DbHelper pra = new NotificationViewability_DbHelper(this);
        //String[] ans2 = new String[100];

        //ans2 = pra.display_prob();
        boolean x;

        for(int j = 0;  j < 7; j++ ) {
            String[] Days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            String[] table ={
                    TbNames.PROBABILITYQUERYMON_TABLE,
                    TbNames.PROBABILITYQUERYTUE_TABLE,
                    TbNames.PROBABILITYQUERYWED_TABLE,
                    TbNames.PROBABILITYQUERYTHU_TABLE,
                    TbNames.PROBABILITYQUERYFRI_TABLE,
                    TbNames.PROBABILITYQUERYSAT_TABLE,
                    TbNames.PROBABILITYQUERYSUN_TABLE
            };
            ArrayList<String> timeMon = pra.selectTimeSlot(Days[j]);
            if(timeMon.size()> 1) {
                for (int i = 1; i <= Integer.parseInt(timeMon.get(0)); i++) {
                    pra.probability_query(timeMon.get(i), Days[j]);
                }

                pra.genarateProbability(table[j], Days[j]);
            }
            pra.close();
        }

        ArrayList<String> ansArry = pra.display_prob();
        display_table(ansArry);
    }



    public void display_table(ArrayList<String> ansin) {

        TableLayout mytb = new TableLayout(this);
        mytb.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        int count3 = 1;

        for (int count = 0; count < Integer.parseInt(ansin.get(0)) / 6; count++) {
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(50, 10, 10, 10);
            TableRow row = new TableRow(this);
            //row.setLayoutParams(lp);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            row.setLayoutParams(lp);
            for (int count2 = 0; count2 < 6; count2++) {
                TextView valueTV = new TextView(this);
                valueTV.setBackgroundColor(0xdddddddd);
                valueTV.setPadding(10, 10, 10, 10);
                valueTV.setGravity(Gravity.CENTER);
                valueTV.setText("" + ansin.get(count3));
                count3 = count3 + 1;
                valueTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                row.addView(valueTV);
            }
            mytb.addView(row);
        }
        mLayout.addView(mytb);

    }

}

