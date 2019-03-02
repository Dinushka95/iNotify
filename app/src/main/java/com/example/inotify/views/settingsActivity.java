package com.example.inotify.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.inotify.R;

import static com.example.inotify.dbHelpers.MainSqlliteOpenHelp.DIN_SNS_TABLE;

public class settingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void resetDb(View view) {
        this.deleteDatabase(DIN_SNS_TABLE);
    }
}
