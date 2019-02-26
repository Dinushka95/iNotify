package com.example.inotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.inotify.view.inotifyActiveAppsAdapter;

import java.util.ArrayList;

public class iNotificationActiviteAppsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inotify_activite_apps);

        //generate list
        ArrayList<String> list = new ArrayList<String>();
        list.add("item1");
        list.add("item2");

        //instantiate custom adapter
        inotifyActiveAppsAdapter inotifyActiveAppsAdapter = new inotifyActiveAppsAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listv);
        lView.setAdapter(inotifyActiveAppsAdapter);
    }
}
