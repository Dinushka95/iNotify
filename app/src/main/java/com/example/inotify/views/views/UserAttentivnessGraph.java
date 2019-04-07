package com.example.inotify.views.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.inotify.R;
import com.example.inotify.configs.TbColNames;
import com.example.inotify.dbHelpers.AttentivnessPerAppDbHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAttentivnessGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attentivness_graph);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
       // anyChartView.setProgressBar(findViewById(R.id.progress_bar));
        AttentivnessPerAppDbHelper attentivnessPerAppDbHelper = new AttentivnessPerAppDbHelper(this);
        ArrayList<String> appNameArray = attentivnessPerAppDbHelper.displayApplication();
        ArrayList<String> totalAttentivnessPercenArray = attentivnessPerAppDbHelper.displayAttentivnessPercenatge();

        Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener() {
            @Override
            public void onClick(Event event) {
                Toast.makeText(UserAttentivnessGraph.this ,event.getData().get("App") + ";" + event.getData().get("Attentivness"),Toast.LENGTH_SHORT);
            }
        });

        List<DataEntry> data = new ArrayList<>();
        for(int i=1 ;i< appNameArray.size();i++)
        {
            String appNames = appNameArray.get(i).toString();
            double attentivnessPercenatage =Double.parseDouble(totalAttentivnessPercenArray.get(i).toString()) ;

            Log.d("inotify" , "UserAttentivnessGraph -------------------Pie chart ------------- Apps" +appNames);
            Log.d("inotify" , "UserAttentivnessGraph -------------------Pie chart ------------- attentivnessPercenage" +attentivnessPercenatage);

            data.add(new ValueDataEntry(appNames,attentivnessPercenatage));
        }



        pie.data(data);

        pie.labels().position("inside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Attentivness Percentage")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER)
                ;


        anyChartView.setChart(pie);

    }
}
