package com.example.inotify.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.inotify.R;
import com.example.inotify.views.views.Conscientiousness;
import com.example.inotify.views.views.Extraversion;
import com.example.inotify.views.views.Neuroticism;

public class Agreeableness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreeableness);
    }


    public void next_agree(View view) {
        Intent intent = new Intent(this, Extraversion.class);
        startActivity(intent);

    }

    public void back_agree(View view) {
        Intent intent = new Intent(this, Neuroticism.class);
        startActivity(intent);

    }
}
