package com.example.inotify.views.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.AttentivnessPerAppDbHelper;
import com.example.inotify.dbHelpers.UserAttentivnessDbHelper;
import com.example.inotify.logger.Log;

import java.util.ArrayList;

public class UserAttentivenessActivity extends AppCompatActivity {

    LinearLayout Layout;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attentiveness);

        Layout = findViewById(R.id.liner);
        AttentivnessPerAppDbHelper attentivnessPerAppDbHelper = new AttentivnessPerAppDbHelper(this);
        ArrayList<String> ansArry =attentivnessPerAppDbHelper.displayData();
        displayTable(ansArry);
  }


  public void displayTable(ArrayList<String> ansin)
  {
      TableLayout mytb = new TableLayout(this);
      mytb.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
      int count3 =1;

      for (int count = 0; count < Integer.parseInt(ansin.get(0)) / 2; count++) {

          TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
          lp.setMargins(50, 10, 10, 10);
          TableRow row = new TableRow(this);

          row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
          row.setLayoutParams(lp);

          for (int count2 = 0; count2 < 2; count2++) {
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
      Layout.addView(mytb);

  }

}