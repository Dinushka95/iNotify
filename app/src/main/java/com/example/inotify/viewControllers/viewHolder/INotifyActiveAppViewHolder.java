package com.example.inotify.viewControllers.viewHolder;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.inotify.R;
import com.example.inotify.helpers.INotifyActiveAppsHelper;

public class INotifyActiveAppViewHolder extends RecyclerView.ViewHolder{

    public ImageView icon;
    public TextView title;
    public TextView packageName;
    public Switch aSwitch;

    public INotifyActiveAppViewHolder(View itemView) {

        super(itemView);

        icon = (ImageView) itemView.findViewById(R.id.inotifyappsicon);

        packageName = (TextView) itemView.findViewById(R.id.inotifyappspackagename);

        title = (TextView) itemView.findViewById(R.id.inotifyappstitle);
        title.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.popup_active_application);
            TextView textView = dialog.findViewById(R.id.showdetails_aap);
            textView.setText("Show Details "+title.getText());
            dialog.show();
        });

        aSwitch =  (Switch) itemView.findViewById(R.id.inotifyappsswitch);
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            INotifyActiveAppsHelper iNotifyActiveAppsHelper = new INotifyActiveAppsHelper(buttonView.getContext());
            if(isChecked){
                iNotifyActiveAppsHelper.changeState(packageName.getText().toString(),isChecked);
            }else{
                iNotifyActiveAppsHelper.changeState(packageName.getText().toString(),isChecked);
            }
        });
    }
}
