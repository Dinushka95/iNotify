package com.example.inotify.viewControllers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import com.example.inotify.views.fragments.TabAllNotificationsFragment;
import com.example.inotify.views.fragments.TabApplicationFragment;
import com.example.inotify.views.fragments.TabDashBoardFragment;
import com.example.inotify.views.fragments.TabSmartNotificationFragment;
import com.example.inotify.views.fragments.TabUserCharacteristicsFragment;


public class MainMenuPagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public MainMenuPagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                TabDashBoardFragment tabDashBoardFragment = new TabDashBoardFragment();
                return tabDashBoardFragment;
            case 1:
                TabSmartNotificationFragment tabSmartNotificationFragment = new TabSmartNotificationFragment();
                return tabSmartNotificationFragment;
            case 2:
                TabAllNotificationsFragment tabAllNotificationsFragment = new TabAllNotificationsFragment();
                return tabAllNotificationsFragment;
            case 3:
                TabApplicationFragment tabApplicationFragment = new TabApplicationFragment();
                return tabApplicationFragment;
            case 4:
                TabUserCharacteristicsFragment tabUserCharacteristicsFragment = new TabUserCharacteristicsFragment();
                return tabUserCharacteristicsFragment;
            default:
                return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }


}
