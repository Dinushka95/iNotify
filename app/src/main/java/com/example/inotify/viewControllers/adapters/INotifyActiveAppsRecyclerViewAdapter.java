package com.example.inotify.viewControllers.adapters;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inotify.R;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.viewControllers.viewHolder.INotifyActiveAppViewHolder;

import java.util.Collections;
import java.util.List;

public class INotifyActiveAppsRecyclerViewAdapter extends RecyclerView.Adapter<INotifyActiveAppViewHolder> {

    List<ApplicationInfoModel> list = Collections.emptyList();
    Context context;

    public INotifyActiveAppsRecyclerViewAdapter(List<ApplicationInfoModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public INotifyActiveAppViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_inotifyactiveapps, viewGroup, false);
        INotifyActiveAppViewHolder holder = new INotifyActiveAppViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull INotifyActiveAppViewHolder iNotifyActiveAppViewHolder, int i) {
        iNotifyActiveAppViewHolder.title.setText(list.get(i).getAppName());
        String value =list.get(i).getInotifystate();
        boolean temvalue=false;
        if(value.equals("true")){temvalue=true;}else {temvalue=false;}

        try {
            iNotifyActiveAppViewHolder.icon.setImageDrawable(context.getPackageManager().getApplicationIcon(list.get(i).getPackageName()));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        iNotifyActiveAppViewHolder.aSwitch.setChecked(temvalue);
        iNotifyActiveAppViewHolder.packageName.setText(list.get(i).getPackageName());
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
    public void insert(int position, ApplicationInfoModel applicationInfoModel) {
        list.add(position, applicationInfoModel);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing the Data object
    public void remove(ApplicationInfoModel applicationInfoModel) {
        int position = list.indexOf(applicationInfoModel);
        list.remove(position);
        notifyItemRemoved(position);
    }
}
