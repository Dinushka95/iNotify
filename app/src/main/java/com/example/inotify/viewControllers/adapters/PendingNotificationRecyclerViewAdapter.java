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
import com.example.inotify.viewControllers.viewHolder.PendingNotificationViewHolder;

import java.util.Collections;
import java.util.List;

public class PendingNotificationRecyclerViewAdapter extends RecyclerView.Adapter<PendingNotificationViewHolder>{

    List<NotificationModel> list = Collections.emptyList();
    Context context;



    public PendingNotificationRecyclerViewAdapter(List<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pendingl_notification, viewGroup, false);
        PendingNotificationViewHolder pendingNotificationViewHolder = new PendingNotificationViewHolder(v);
        return pendingNotificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PendingNotificationViewHolder pendingNotificationViewHolder, int i) {
        pendingNotificationViewHolder.title.setText(list.get(i).getId());
        try {
            pendingNotificationViewHolder.imageView.setImageDrawable(context.getPackageManager().getApplicationIcon(list.get(i).getPackageName()));
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
