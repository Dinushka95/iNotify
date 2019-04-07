package com.example.inotify.viewControllers.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inotify.R;

public class PendingNotificationViewHolder  extends RecyclerView.ViewHolder {

    public TextView nid;
    public TextView date;
    public TextView time;
    public TextView appName;
    public TextView title;
    public TextView description;
    public ImageView imageView;



    public PendingNotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        nid = (TextView) itemView.findViewById(R.id.pennotiid);
        date = (TextView) itemView.findViewById(R.id.pennotidatetextView);
        time = (TextView) itemView.findViewById(R.id.pennotitimetextView);
        appName = (TextView) itemView.findViewById(R.id.pennotiappnametextView);
        title = (TextView) itemView.findViewById(R.id.pennotititle);
        description = (TextView) itemView.findViewById(R.id.pennotidescription);
        imageView = (ImageView) itemView.findViewById(R.id.pennotiimageView);
    }
}

