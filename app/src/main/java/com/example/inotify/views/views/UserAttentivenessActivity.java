package com.example.inotify.views.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.UserAttentivnessDbHelper;
import com.example.inotify.logger.Log;

public class UserAttentivenessActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attentiveness);


        UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(this);
        String List = userAttentivnessDbHelper.displayData();
        String[] rows = List.split("_");
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tab);

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            TableRow tableRow = new TableRow(getApplicationContext());
            final String[] cols = row.split(";");

            Handler handler = null;

            for (int j = 0; j < cols.length; j++) {
                final String col = cols[j];
                final TextView columnsView = new TextView(getApplicationContext());
                columnsView.setText(String.format("%20s", col));
                tableRow.addView(columnsView);
            }

        }





  }
}