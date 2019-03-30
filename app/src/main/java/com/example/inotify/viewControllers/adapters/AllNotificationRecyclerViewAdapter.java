package com.example.inotify.viewControllers.adapters;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inotify.R;
import com.example.inotify.models.NotificationModel;
import com.example.inotify.viewControllers.viewHolder.AllNotificationViewHolder;

import java.util.Collections;
import java.util.List;

public class AllNotificationRecyclerViewAdapter extends RecyclerView.Adapter<AllNotificationViewHolder>  {

    List<NotificationModel> list = Collections.emptyList();
    Context context;

    public AllNotificationRecyclerViewAdapter(List<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AllNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_all_notification, viewGroup, false);
        AllNotificationViewHolder allNotificationViewHolder = new AllNotificationViewHolder(v);
        return allNotificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllNotificationViewHolder allNotificationViewHolder, int i) {
        allNotificationViewHolder.nid.setText(list.get(i).getId());
        allNotificationViewHolder.date.setText(list.get(i).getDate());
        allNotificationViewHolder.time.setText(list.get(i).getTimeRecevied());
        allNotificationViewHolder.appName.setText(list.get(i).getAppName());
        allNotificationViewHolder.title.setText(list.get(i).getTitle());
        allNotificationViewHolder.description.setText(list.get(i).getContent());
        try {
            allNotificationViewHolder.imageView.setImageDrawable(context.getPackageManager().getApplicationIcon(list.get(i).getPackageName()));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView
    public void insert(int position, NotificationModel notificationModel) {
        list.add(position, notificationModel);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing the Data object
    public void remove(NotificationModel notificationModel) {
        int position = list.indexOf(notificationModel);
        list.remove(position);
        notifyItemRemoved(position);
    }
}
