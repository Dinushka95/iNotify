package com.example.inotify.views.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.inotify.R;

import java.util.ArrayList;
import java.util.List;

public class AverageBarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_bar_chart);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        // anyChartView.setProgressBar(findViewById(R.id.progress_bar));
        Openness openness = new Openness();
        long openness_charac = openness.displayOpennessAVG();
        Log.d("inotify","Openness-chartAVG  " + openness_charac);

        Conscientiousness conscientiousness = new Conscientiousness();
        long  conscientiousness_charac = conscientiousness.displayConscientiousnessAVG();
        Log.d("inotify","conscientiousness_characAVG " + conscientiousness_charac);

        Extraversion extraversion = new Extraversion();
        long extraversion_charac = extraversion.displayExtraversionAVG();
        Log.d("inotify","extraversion_characAVG " + extraversion_charac);


        Neuroticism neuroticism  = new Neuroticism();
        long neuroticism_charac = neuroticism.neuroticismDisplayAVG();
        Log.d("inotify","neuroticism_characAVG " + neuroticism_charac);


        Agreeablenesss agreeablenesss = new Agreeablenesss();
        long agreeablenesss_charc = agreeablenesss.DisplayAgreeablenessAVG();
        Log.d("inotify","agreeablenesss_charcAVG " + agreeablenesss_charc);



        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Openness", openness_charac));
        data.add(new ValueDataEntry("Conscientiousness", conscientiousness_charac));
        data.add(new ValueDataEntry("Extraversion", extraversion_charac));
        data.add(new ValueDataEntry("Neuroticism", neuroticism_charac));
        data.add(new ValueDataEntry("Agreeablenesss", agreeablenesss_charc));

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("User Characteristics");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Character");
        cartesian.yAxis(0).title("Value");

        anyChartView.setChart(cartesian);
    }
}