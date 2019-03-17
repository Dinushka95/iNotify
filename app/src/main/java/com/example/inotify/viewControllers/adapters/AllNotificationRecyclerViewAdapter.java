package com.example.inotify.viewControllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inotify.R;
import com.example.inotify.models.NotificationModel;
import com.example.inotify.models.ViewHolderModel;

import java.util.Collections;
import java.util.List;

public class AllNotificationRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderModel>  {

    List<NotificationModel> list = Collections.emptyList();
    Context context;

    public AllNotificationRecyclerViewAdapter(List<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_all_notification, viewGroup, false);
        ViewHolderModel viewHolderModel = new ViewHolderModel(v);
        return viewHolderModel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderModel viewHolderModel, int i) {
        viewHolderModel.title.setText(list.get(i).getId());
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
