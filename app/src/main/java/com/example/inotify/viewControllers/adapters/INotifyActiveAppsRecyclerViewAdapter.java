package com.example.inotify.viewControllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inotify.R;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.ViewHolderModel;

import java.util.Collections;
import java.util.List;

public class INotifyActiveAppsRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderModel> {

    List<ApplicationInfoModel> list = Collections.emptyList();
    Context context;

    public INotifyActiveAppsRecyclerViewAdapter(List<ApplicationInfoModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_inotifyactiveapps, viewGroup, false);
        ViewHolderModel holder = new ViewHolderModel(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderModel viewHolderModel, int i) {
        viewHolderModel.appid.setText(list.get(i).getPakageName());
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
