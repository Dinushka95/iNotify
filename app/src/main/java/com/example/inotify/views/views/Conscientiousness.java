package com.example.inotify.views.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.inotify.R;

public class Conscientiousness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conscientiousness);
    }


    public void next_con(View view) {
        Intent intent = new Intent(this,Openness.class);
        startActivity(intent);

    }
}
