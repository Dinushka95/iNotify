package com.example.inotify.viewControllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.inotify.views.NotificationHistoryALLFragment;
import com.example.inotify.views.NotificationHistorySNSFragment;


public class NotificationHistoryPagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public NotificationHistoryPagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                NotificationHistorySNSFragment notificationHistorySNSFragment = new NotificationHistorySNSFragment();
                return notificationHistorySNSFragment;
            case 1:
                NotificationHistoryALLFragment notificationHistoryALLFragment = new NotificationHistoryALLFragment();
                return notificationHistoryALLFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
