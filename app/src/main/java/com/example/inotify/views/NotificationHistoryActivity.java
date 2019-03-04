package com.example.inotify.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.inotify.R;
import com.example.inotify.viewControllers.INotifyActiveAppsAdapter;
import com.example.inotify.viewControllers.INotifyActiveAppsLogic;
import com.example.inotify.viewControllers.NotificationHistoryAdapter;
import com.example.inotify.viewControllers.NotificationHistoryLogic;

import java.util.ArrayList;

public class NotificationHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_history);

        //generate list
        NotificationHistoryLogic notificationHistoryLogic = new NotificationHistoryLogic(this);

        ArrayList<String> list = new ArrayList<String>();
        list =notificationHistoryLogic.getNotificationList();

        //instantiate custom adapter
        NotificationHistoryAdapter notificationHistoryAdapter = new NotificationHistoryAdapter(list,this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listview);
        lView.setAdapter(notificationHistoryAdapter);
    }

}
