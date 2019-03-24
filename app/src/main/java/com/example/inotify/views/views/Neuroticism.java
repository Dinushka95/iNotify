package com.example.inotify.views.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.inotify.R;
import com.example.inotify.views.Agreeableness;

public class Neuroticism extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuroticism);
    }

    public void neuro_next(View view) {
        Intent intent = new Intent(this, Agreeableness.class);
        startActivity(intent);

    }

    public void neuro_back(View view) {
        Intent intent = new Intent(this, Openness.class);
        startActivity(intent);

    }
}
