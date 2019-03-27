package com.example.inotify.interfaces;

import android.view.View;

public interface RecyclerViewItemClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);

    void onChangeSw(Boolean checked);
}
