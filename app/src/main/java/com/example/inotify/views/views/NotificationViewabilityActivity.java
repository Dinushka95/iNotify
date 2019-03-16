package com.example.inotify.views.views;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.MainDbHelp;
import com.example.inotify.dbHelpers.NV_DbHelper;

public class NotificationViewabilityActivity extends AppCompatActivity {


    LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_viewability);
        mLayout = findViewById(R.id.liner);

        NV_DbHelper pra = new NV_DbHelper(this);
        String[] ans2 = new String[100];

        ans2 = pra.display_prob();

        pra.probability_query();



        display_table(ans2);
    }

    public void display_table(String[] ansin) {

        TableLayout mytb = new TableLayout(this);
        mytb.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        int count3 = 1;

        for (int count = 0; count < Integer.parseInt(ansin[0]) / 6; count++) {
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
                valueTV.setText("" + ansin[count3]);
                count3 = count3 + 1;
                valueTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                row.addView(valueTV);
            }
            mytb.addView(row);
        }
        mLayout.addView(mytb);

    }

}

