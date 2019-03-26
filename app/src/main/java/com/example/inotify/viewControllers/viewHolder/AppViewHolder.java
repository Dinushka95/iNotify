package com.example.inotify.viewControllers.viewHolder;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.helpers.INotifyActiveAppsHelper;

public class AppViewHolder extends RecyclerView.ViewHolder{

    public TextView title;
    public TextView packageName;
    public Switch aSwitch;

    public AppViewHolder(View itemView) {

        super(itemView);


        packageName = (TextView) itemView.findViewById(R.id.list_item_packagename);

        title = (TextView) itemView.findViewById(R.id.list_item_string);
        title.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.popup_active_application);
            TextView textView = dialog.findViewById(R.id.showdetails_aap);
            textView.setText("Show Details "+title.getText());
            dialog.show();
        });

        aSwitch =  (Switch) itemView.findViewById(R.id.switch2);
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
           // Toast.makeText(itemView.getContext(), "SSSSSSSSSSSSSSSSSSS" , Toast.LENGTH_SHORT).show();
            INotifyActiveAppsHelper iNotifyActiveAppsHelper = new INotifyActiveAppsHelper(buttonView.getContext());
            if(isChecked){
                iNotifyActiveAppsHelper.changeState(packageName.getText().toString(),isChecked);
            }else{
                iNotifyActiveAppsHelper.changeState(packageName.getText().toString(),isChecked);
            }
        });
    }
}
