package com.example.inotify.viewControllers.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inotify.R;

public class SmartNotificationViewHolder extends RecyclerView.ViewHolder {

    public TextView nid;
    public TextView date;
    public TextView time;
    public TextView appName;
    public TextView title;
    public TextView description;
    public ImageView imageView;

    public SmartNotificationViewHolder(View itemView) {
        super(itemView);
        nid = (TextView) itemView.findViewById(R.id.smartnotiid);
        date = (TextView) itemView.findViewById(R.id.smartnotidatetextView);
        time = (TextView) itemView.findViewById(R.id.smartnotimetextView);
        appName = (TextView) itemView.findViewById(R.id.smartnotiappnametextView);
        title = (TextView) itemView.findViewById(R.id.smartnotititle);
        description = (TextView) itemView.findViewById(R.id.smartnotidescription);
        imageView = (ImageView) itemView.findViewById(R.id.smartnotiimageView);

    }

}
