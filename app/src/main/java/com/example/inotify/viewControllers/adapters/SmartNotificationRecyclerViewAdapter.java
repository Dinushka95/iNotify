package com.example.inotify.viewControllers.adapters;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inotify.R;
import com.example.inotify.models.NotificationModel;
import com.example.inotify.viewControllers.viewHolder.SmartNotificationViewHolder;

import java.util.Collections;
import java.util.List;


public class SmartNotificationRecyclerViewAdapter extends RecyclerView.Adapter<SmartNotificationViewHolder> {

    List<NotificationModel> list = Collections.emptyList();
    Context context;

    public SmartNotificationRecyclerViewAdapter(List<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.notifyDataSetChanged();
    }

    @Override
    public SmartNotificationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_smart_notification, viewGroup, false);
        SmartNotificationViewHolder smartNotificationViewHolder = new SmartNotificationViewHolder(v);
        return smartNotificationViewHolder;
    }

    @Override
    public void onBindViewHolder(SmartNotificationViewHolder smartNotificationViewHolder, int position) {
        smartNotificationViewHolder.title.setText(list.get(position).getId());
        try {
            smartNotificationViewHolder.imageView.setImageDrawable(context.getPackageManager().getApplicationIcon(list.get(position).getPackageName()));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //holder.description.setText(list.get(position).description);
        // holder.imageView.setImageResource(list.get(position).imageId);
      /*  holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("inotify","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXDDDDDDDDDDDDD");
                Log.d("inotify","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXDDDDDDDDDDDDD");

            }
        });*/

        //animate(holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
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
