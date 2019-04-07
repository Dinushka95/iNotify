package com.example.inotify.views.views;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.inotify.R;
import com.example.inotify.dbHelpers.UserAttentivnessDbHelper;

import java.util.ArrayList;
import java.util.List;

public class UserAttentivnessLineChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attentivness_line_chart);

        UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(this);


//
        Intent intent=getIntent();
        String code = intent.getStringExtra("applcation");
        Log.d("inotify" , "inotify ------------------------------- code -----------------------------  " +code);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



                AnyChartView anyChartView = findViewById(R.id.any_chart_view3);
                Cartesian cartesian = AnyChart.line();
                cartesian.animation(true);
                cartesian.padding(10d, 20d, 5d, 20d);
                cartesian.crosshair().enabled(true);
                cartesian.crosshair().yLabel(true)
                        // TODO ystroke
                        .yStroke((Stroke) null, null, null, (String) null, (String) null);

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.title("Attentivness Per Notification");

                cartesian.yAxis(0).title("Time (hours)");

                cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);
                ArrayList<String> timeArray = userAttentivnessDbHelper.displayAttentivnessLineChartTime(code);
                ArrayList<String> attentivnessWhatsAppArray = userAttentivnessDbHelper.displayAttentivnessLineChartAttentivness(code);
                ArrayList<String> appnameArray = userAttentivnessDbHelper.displayAttentivnessLineChartApp();

                List<DataEntry> seriesData = new ArrayList<>();


                for(int i=1 ; i< timeArray.size() ; i++){
                    String timeValues = timeArray.get(i).toString();
                    Log.d("inotify" , "==============================timeArray  ===== " + i + ", " +timeValues  );

                    double  attentivnessValue = Double.parseDouble(attentivnessWhatsAppArray.get(i).toString());
                    Log.d("inotify" , "==============================attentivnessValue ===== " + i + ", " +attentivnessValue  );

                    seriesData.add(new ValueDataEntry(timeValues,attentivnessValue));

                }

                Set set = Set.instantiate();
                set.data(seriesData);
                Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
//                Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
//                Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");


                Line series1 = cartesian.line(series1Mapping);
                series1.hovered().markers().enabled(true);
                series1.hovered().markers().type(MarkerType.CIRCLE).size(4d);
                series1.tooltip().position("right").anchor(Anchor.LEFT_CENTER).offsetX(5d).offsetY(5d);

                cartesian.legend().enabled(true);
                cartesian.legend().fontSize(13d);
                cartesian.legend().padding(0d, 0d, 10d, 0d);

                anyChartView.setChart(cartesian);
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////











        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        public void LineChart(String Application){
//
//
//
//
//        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////







    }







    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }
}
