package com.example.inotify.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.inotify.R;
import com.example.inotify.viewControllers.inotifyActiveAppsAdapter;
import com.example.inotify.viewControllers.inotifyActiveAppsLogic;

import java.util.ArrayList;

public class iNotifiyActiviteAppsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inotify_activite_apps);

        //generate list
        inotifyActiveAppsLogic activity_inotify_activite_apps =new inotifyActiveAppsLogic(this);

        ArrayList<String> list = new ArrayList<String>();
        list =activity_inotify_activite_apps.getApplicationList();
        //instantiate custom adapter
        inotifyActiveAppsAdapter inotifyActiveAppsAdapter = new inotifyActiveAppsAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listv);
        lView.setAdapter(inotifyActiveAppsAdapter);
    }
}
