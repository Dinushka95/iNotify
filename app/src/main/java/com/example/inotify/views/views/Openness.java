package com.example.inotify.views.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.inotify.R;

public class Openness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openness);
    }

    public void next_open(View view) {
        Intent intent = new Intent(this,Neuroticism.class);
        startActivity(intent);

    }

    public void back_open(View view) {
        Intent intent = new Intent(this,Conscientiousness.class);
        startActivity(intent);

    }
}
