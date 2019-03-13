package com.example.inotify.viewControllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.inotify.views.TabAllNotificationsFragment;
import com.example.inotify.views.TabApplicationFragment;
import com.example.inotify.views.TabDashBoardFragment;
import com.example.inotify.views.TabSmartNotificationFragment;
import com.example.inotify.views.TabUserCharacteristicsFragment;


public class MainMenuPagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public MainMenuPagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        Log.d("inotify","DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD"+String.valueOf(position));
        switch(position)
        {

            case 0:
                TabAllNotificationsFragment tabAllNotificationsFragment = new TabAllNotificationsFragment();
                return tabAllNotificationsFragment;
            case 1:
                TabApplicationFragment tabApplicationFragment= new TabApplicationFragment();
                return tabApplicationFragment;
            case 2:
                TabDashBoardFragment tabDashBoardFragment = new TabDashBoardFragment();
                return tabDashBoardFragment;
            case 3:
               TabSmartNotificationFragment tabSmartNotificationFragment = new TabSmartNotificationFragment();
                return tabSmartNotificationFragment;
            case 4:
                TabUserCharacteristicsFragment tabUserCharacteristicsFragment = new TabUserCharacteristicsFragment();
                return tabUserCharacteristicsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }



}
