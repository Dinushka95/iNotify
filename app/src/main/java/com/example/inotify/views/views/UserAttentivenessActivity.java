package com.example.inotify.views.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.AttentivnessPerAppDbHelper;
import com.example.inotify.dbHelpers.UserAttentivnessDbHelper;

import java.util.ArrayList;

public class UserAttentivenessActivity extends AppCompatActivity {

    LinearLayout Layout;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_user_attentiveness);
       setContentView(R.layout.activity_user_attentiveness);

       UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(this);

       ArrayList<String> txp = userAttentivnessDbHelper.DisplayAppInDropDown();
       Spinner sp= (Spinner) findViewById(R.id.applistSpinner);
       ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,txp);
       adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       sp.setAdapter(adap);


       for(int i=1 ; i< txp.size() ; i++){
           String a = txp.get(i).toString();
           Log.d("inotify" , "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ===== " +a  );
       }








//       sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//           @Override
//           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////               Spinner spinner = (Spinner) findViewById(R.id.applistSpinner);
//               String  applcation = ((Spinner) sp).getSelectedItem().toString();
//               Log.d("inotify" , "aaaaaaaaaaaa=============applcation ===== " +applcation);
//
//               String App  = (String) parent.getItemAtPosition(position);
//               Toast.makeText(getApplicationContext(), App, Toast.LENGTH_SHORT);
//               Log.d("Inotify" , "xxxxxxxxxxxxxxxxxxxxx==============App==========  " +App);
//
//               String  item = sp.getItemAtPosition(position).toString();
//               Log.d("Inotify" , "xxxxxxxxxxxxxxxxxxxxx==============item==========  " +item);
//           }
//
//           @Override
//           public void onNothingSelected(AdapterView<?> parent) {
//
//           }
//       });



        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String  applcation = ((Spinner) sp).getSelectedItem().toString();
                Log.d("inotify" , "aaaaaaaaaaaa=============applcation ===== " +applcation);

                String App  = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), App, Toast.LENGTH_SHORT);
                Log.d("Inotify" , "xxxxxxxxxxxxxxxxxxxxx==============App==========  " +App);

                String  item = sp.getItemAtPosition(position).toString();
                Log.d("Inotify" , "xxxxxxxxxxxxxxxxxxxxx==============item==========  " +item);




                Intent intent = new Intent(getApplicationContext() , UserAttentivnessLineChart.class);
                intent.putExtra("applcation" ,applcation);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//       sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//           }
//       });


//       sp.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//
//           }
//       });


       ///////////////////////////////////////////////////////////////////////


        Layout = findViewById(R.id.liner);
        AttentivnessPerAppDbHelper attentivnessPerAppDbHelper = new AttentivnessPerAppDbHelper(this);
        ArrayList<String> ansArry =attentivnessPerAppDbHelper.displayData();
        displayTable(ansArry);





//Pie Chart
       Button chartButton = (Button)findViewById(R.id.chartbutton);
       chartButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent myIntent = new Intent(v.getContext(),UserAttentivnessGraph.class);
               startActivityForResult(myIntent, 0);
           }
       });


       //ScatterPlot
       Button ScatterPlotButton =(Button) findViewById(R.id.ScatterPltButton);
       ScatterPlotButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent myIntent = new Intent(v.getContext(),UserAttentivnessPerAppScatterPlot.class);
               startActivityForResult(myIntent,0);
           }
       });

       //Line Chart
        Button LineChartButton =(Button) findViewById(R.id.LineChartBitton);
        LineChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(),UserAttentivnessLineChart.class);
                startActivityForResult(myIntent,0);
            }
        });



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