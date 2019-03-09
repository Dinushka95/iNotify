package com.example.inotify.viewControllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.inotify.R;

import java.util.ArrayList;

public class INotifyActiveAppsAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public INotifyActiveAppsAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater != null ? inflater.inflate(R.layout.inotifyactiveapps, null) : null;
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) (view != null ? view.findViewById(R.id.list_item_string) : null);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Switch switchBtn = (Switch) view.findViewById(R.id.switch2);

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("inotify", "onCheckedChanged: "+ position);
                Log.d("inotify", "app name of change"+list.get(position));

            }
        });

        return view;
    }
}
