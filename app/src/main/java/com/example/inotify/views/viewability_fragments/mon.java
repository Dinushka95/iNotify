package com.example.inotify.views.viewability_fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.inotify.R;
import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.NotificationViewabilityDbHelper;
import com.example.inotify.logger.Log;
import com.example.inotify.views.views.NotificationFinalViewabilityActivity;
import com.google.api.Context;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class mon extends Fragment {

    View view;

    LinearLayout mLayout;


    public mon() {
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.viewability_fragment_mon,container,false);

        mLayout = (LinearLayout) view.findViewById(R.id.containermon);


        /*TextView textView = (TextView) view.findViewById(R.id.textmon);
        textView.setText("Hello");*/

        //TextView textView = new TextView(getActivity());
        //textView.setText("Hello");
        NotificationViewabilityDbHelper pra = new NotificationViewabilityDbHelper(getActivity());

        ArrayList<String> ansArry = pra.display_probFinal(TbNames.PROBABILITYQUERYMON_TABLE);

        TableLayout mytb = new TableLayout(getActivity());
        mytb.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        int count3 = 1;

        for (int count = 0; count < Integer.parseInt(ansArry.get(0)) / 2; count++) {
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(50, 10, 10, 10);
            TableRow row = new TableRow(getActivity());
            //row.setLayoutParams(lp);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            row.setLayoutParams(lp);
            for (int count2 = 0; count2 < 2; count2++) {
                TextView valueTV = new TextView(getActivity());
                valueTV.setBackgroundColor(0xdddddddd);
                valueTV.setPadding(10, 10, 10, 10);
                valueTV.setGravity(Gravity.CENTER);
                if (count3 % 2 == 0) {
                    if (Double.parseDouble(ansArry.get(count3)) >= 75) {
                        valueTV.setBackgroundColor(0xff00f000);
                    } else if (Double.parseDouble(ansArry.get(count3)) >= 50) {
                        valueTV.setBackgroundColor(0xffe0f000);
                    } else if (Double.parseDouble(ansArry.get(count3)) >= 25) {
                        valueTV.setBackgroundColor(0xfff0e000);
                    } else if (Double.parseDouble(ansArry.get(count3)) < 25) {
                    valueTV.setBackgroundColor(0xfff00000);
                    }
                }
                valueTV.setText("" + ansArry.get(count3));
                count3 = count3 + 1;
                valueTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                row.addView(valueTV);
            }
            mytb.addView(row);
        }
        mLayout.addView(mytb);

        Log.d("View","Start");
        return view;

    }


}
