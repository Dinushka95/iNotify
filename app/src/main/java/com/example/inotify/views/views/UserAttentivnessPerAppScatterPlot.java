package com.example.inotify.views.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Scatter;
import com.anychart.core.scatter.series.Line;
import com.anychart.core.scatter.series.Marker;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.graphics.vector.GradientKey;
import com.anychart.graphics.vector.LinearGradientStroke;
import com.anychart.graphics.vector.SolidFill;
import com.anychart.graphics.vector.text.HAlign;
import com.example.inotify.R;
import com.example.inotify.dbHelpers.UserAttentivnessDbHelper;

import java.util.ArrayList;
import java.util.List;

public class UserAttentivnessPerAppScatterPlot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attentivness_per_app_scatter_plot);


//        UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(this);
//         ArrayList<String> notificationIdArray  = userAttentivnessDbHelper.displayNotificationID();
//         ArrayList<String> attentivnessArray = userAttentivnessDbHelper.displayNotificationID();

        AnyChartView anyChartView = findViewById(R.id.any_chart_view2);
        //anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Scatter scatter = AnyChart.scatter();

        scatter.animation(true);

        scatter.title("Attentivness Per Notification");
        scatter.xScale()
                .minimum(1d)
                .maximum(40d);
//        scatter.xScale().tick
        scatter.yScale()
                .minimum(4.0d)
                .maximum(0.05d);
        scatter.yAxis(0).title("Attentivness");
        scatter.xAxis(0)
                .title("Notification Id")
                .drawFirstLabel(false)
                .drawLastLabel(false);

        scatter.interactivity()
                .hoverMode(HoverMode.BY_SPOT)
                .spotRadius(30d);

        scatter.tooltip().displayMode(TooltipDisplayMode.UNION);
        Marker marker = scatter.marker(getMarkerData());
        marker.type(MarkerType.CROSS)
                .size(1d);
        marker.hovered()
                .size(7d)
                .fill(new SolidFill("gold", 1d))
                .stroke("anychart.color.darken(gold)");
        marker.tooltip()
                .hAlign(HAlign.START)
                .format("Waiting time: ${%Value} min.\\nDuration: ${%X} min.");

        Line scatterSeriesLine = scatter.line(getLineData());

        GradientKey gradientKey[] = new GradientKey[] {
                new GradientKey("#abcabc", 0d, 1d),
                new GradientKey("#cbacba", 40d, 1d)
        };
        LinearGradientStroke linearGradientStroke = new LinearGradientStroke(0d, null, gradientKey, null, null, true, 1d, 2d);
        scatterSeriesLine.stroke(linearGradientStroke, 3d, null, (String) null, (String) null);

        anyChartView.setChart(scatter);


    }

    private List<DataEntry> getLineData() {
        List<DataEntry> data = new ArrayList<>();
        UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(this);
        ArrayList<String> notificationIdArray = userAttentivnessDbHelper.displayNotificationID();
        ArrayList<String> attentivnessArray = userAttentivnessDbHelper.displayAttentivness();

        Log.d("inotify " , "UserAttentivnessPerNotificationScatterPlot ====================== notification Id  array  " +notificationIdArray);
        Log.d("inotify " , "UserAttentivnessPerNotificationScatterPlot ====================== Attentivness  array  " +attentivnessArray);

        for(int i=1 ;i< notificationIdArray.size();i++)
        {
            String notificationID = notificationIdArray.get(i).toString();

            double Attentivness = Double.parseDouble(attentivnessArray.get(i).toString());

            Log.d("inotify " , "UserAttentivnessPerNotificationScatterPlot ====================== notification Id value  " +notificationID);
            Log.d("inotify " , "UserAttentivnessPerNotificationScatterPlot ====================== Attentivness value   " +Attentivness);

            data.add(new ValueDataEntry(notificationID, Attentivness));
        }
        return data;
    }


    private List<DataEntry> getMarkerData() {
        List<DataEntry> data = new ArrayList<>();
        UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(this);
        ArrayList<String> notificationIdArray = userAttentivnessDbHelper.displayNotificationID();
        ArrayList<String> attentivnessArray = userAttentivnessDbHelper.displayAttentivness();

        Log.d("inotify " , "UserAttentivnessPerNotificationScatterPlot ====================== notification Id  array  " +notificationIdArray);
        Log.d("inotify " , "UserAttentivnessPerNotificationScatterPlot ====================== Attentivness  array  " +attentivnessArray);

        for(int i=1 ;i< notificationIdArray.size();i++)
        {
            String notificationID = notificationIdArray.get(i).toString();

            double Attentivness = Double.parseDouble(attentivnessArray.get(i).toString());

            Log.d("inotify " , "UserAttentivnessPerNotificationScatterPlot ====================== notification Id value  " +notificationID);
            Log.d("inotify " , "UserAttentivnessPerNotificationScatterPlot ====================== Attentivness value   " +Attentivness);

            data.add(new ValueDataEntry(notificationID, Attentivness));
        }
        return data;
    }






}
