package com.example.inotify.views;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.MainDbHelp;
import com.example.inotify.dbHelpers.NV_DbHelper;

public class NotificationViewabilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_viewability);

        NV_DbHelper pra = new NV_DbHelper(this);
        String[] ans2 = new String[30];

        ans2 = pra.display_prob();

        final TextView textViewToChange = (TextView) findViewById(R.id.proid);
        final TextView textViewToChange2 = (TextView) findViewById(R.id.times);
        final TextView textViewToChange3 = (TextView) findViewById(R.id.days);
        final TextView textViewToChange4 = (TextView) findViewById(R.id.prob);

        textViewToChange.setText(ans2[1]);
        textViewToChange2.setText(ans2[2]);
        textViewToChange3.setText(ans2[3]);
        textViewToChange4.setText(ans2[4]);
    }




}
