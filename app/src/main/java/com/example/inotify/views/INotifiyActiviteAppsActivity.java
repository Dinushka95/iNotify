package com.example.inotify.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.inotify.R;
import com.example.inotify.viewControllers.INotifyActiveAppsAdapter;
import com.example.inotify.viewControllers.INotifyActiveAppsLogic;

import java.util.ArrayList;

public class INotifiyActiviteAppsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inotify_activite_apps);

        //generate list
        INotifyActiveAppsLogic activity_inotify_activite_apps =new INotifyActiveAppsLogic(this);

        ArrayList<String> list = new ArrayList<String>();
        list =activity_inotify_activite_apps.getApplicationList();
        //instantiate custom adapter
        INotifyActiveAppsAdapter inotifyActiveAppsAdapter = new INotifyActiveAppsAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listv);
        lView.setAdapter(inotifyActiveAppsAdapter);
    }
}
