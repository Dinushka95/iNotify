package com.example.inotify.models;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.inotify.R;


//The adapters View Holder
public class ViewHolderModel extends RecyclerView.ViewHolder {

    public CardView cv;
    public TextView title;
    public TextView appid;
    public TextView description;
    public ImageView imageView;
    public Switch aSwitch;

    public ViewHolderModel(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        appid = (TextView) itemView.findViewById(R.id.list_item_string);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //aSwitch =  (Switch) itemView.findViewById(R.id.switch1);
    }

}
