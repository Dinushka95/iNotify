package com.example.inotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.inotify.settings.SettingsSNActive_view;
import com.example.inotify.view.inotifyActiveAppsAdapter;

import java.util.ArrayList;

public class iNotificationActiviteAppsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inotify_activite_apps);

        //generate list
        SettingsSNActive_view activity_inotify_activite_apps =new SettingsSNActive_view(this);

        ArrayList<String> list = new ArrayList<String>();
    list =activity_inotify_activite_apps.getApplicationList();
        //instantiate custom adapter
        inotifyActiveAppsAdapter inotifyActiveAppsAdapter = new inotifyActiveAppsAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listv);
        lView.setAdapter(inotifyActiveAppsAdapter);
    }
}
