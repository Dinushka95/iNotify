package com.example.inotify.viewControllers.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inotify.R;

public class AllNotificationViewHolder extends RecyclerView.ViewHolder {

    public TextView nid;
    public TextView date;
    public TextView time;
    public TextView appName;
    public TextView title;
    public TextView description;
    public ImageView imageView;


    public AllNotificationViewHolder(View itemView) {
        super(itemView);
        nid = (TextView) itemView.findViewById(R.id.allnotiid);
        date = (TextView) itemView.findViewById(R.id.allnotidatetextView);
        time = (TextView) itemView.findViewById(R.id.allnotitimetextView);
        appName = (TextView) itemView.findViewById(R.id.allnotiappnametextView);
        title = (TextView) itemView.findViewById(R.id.allnotititle);
        description = (TextView) itemView.findViewById(R.id.allnotidescription);
        imageView = (ImageView) itemView.findViewById(R.id.allnotiimageView);
    }

}
