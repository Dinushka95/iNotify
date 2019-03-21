package com.example.inotify.interfaces;

import com.example.inotify.models.TopAppModel;

import java.util.List;

public interface MyCallback {
    void onCallback(List<TopAppModel> topAppModel);
}
