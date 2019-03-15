package com.example.inotify.views;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.dbHelpers.UserAttentivnessDbHelper;

import java.util.ArrayList;
import java.util.List;

public class UserAttentivenessActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attentiveness);


        UserAttentivnessDbHelper userAttentivnessDbHelper = new UserAttentivnessDbHelper(this);
        String[] list  = new String[30];


        list = userAttentivnessDbHelper.View_Attentivness();

        //final TextView textViewToChange = (TextView) findViewById(R.id.nid);
        final TextView textViewToChange2 = (TextView) findViewById(R.id.application);
        final TextView textViewToChange3 = (TextView)  findViewById(R.id.attentivness);

       // textViewToChange.setText(list[1]);
        textViewToChange2.setText(list[2]);
        textViewToChange3.setText(list[3]);




    }

/*    public void ViewData(){
        SQLiteDatabase db = this.getReadableDataBase();




    }*/


}
