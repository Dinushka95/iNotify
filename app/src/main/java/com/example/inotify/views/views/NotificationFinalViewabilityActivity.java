package com.example.inotify.views.views;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.NotificationViewabilityDbHelper;
import com.example.inotify.viewControllers.adapters.ViewabilityViewpageAdapter;
import com.example.inotify.views.viewability_fragments.Tue;
import com.example.inotify.views.viewability_fragments.fri;
import com.example.inotify.views.viewability_fragments.mon;
import com.example.inotify.views.viewability_fragments.sat;
import com.example.inotify.views.viewability_fragments.sun;
import com.example.inotify.views.viewability_fragments.thu;
import com.example.inotify.views.viewability_fragments.wed;

import java.util.ArrayList;

public class NotificationFinalViewabilityActivity extends AppCompatActivity {

    LinearLayout Layout;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_final_viewability);
        Layout = findViewById(R.id.liner);

        tabLayout =(TabLayout) findViewById(R.id.tablayout_id);
        viewPager =(ViewPager) findViewById(R.id.viewpager_id);
        ViewabilityViewpageAdapter adapter = new ViewabilityViewpageAdapter(getSupportFragmentManager());
        //adding fragments
        adapter.AddFragments(new mon(),"Mon");
        adapter.AddFragments(new Tue(),"Tue");
        adapter.AddFragments(new wed(),"Wed");
        adapter.AddFragments(new thu(),"Thu");
        adapter.AddFragments(new fri(),"Fri");
        adapter.AddFragments(new sat(),"Sat");
        adapter.AddFragments(new sun(),"Sun");


        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        NotificationViewabilityDbHelper pra = new NotificationViewabilityDbHelper(this);
        //ArrayList<String> ansArry = pra.display_probFinal();

        //Log.d("Final", "onCreate: "+ ansArry.get(1));
        //display_table(ansArry);
    }

    public void display_table(ArrayList<String> ansin) {

        TableLayout mytb = new TableLayout(this);
        mytb.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        int count3 = 1;

        for (int count = 0; count < Integer.parseInt(ansin.get(0)) / 2; count++) {
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(50, 10, 10, 10);
            TableRow row = new TableRow(this);
            //row.setLayoutParams(lp);
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
