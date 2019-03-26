package com.example.inotify.viewControllers.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewabilityViewpageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> ViwabilityFragmentList = new ArrayList<>();
    private final List<String> ViwabilityFragmentListTitles = new ArrayList<>();

    public ViewabilityViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ViwabilityFragmentList.get(position);
    }

    @Override
    public int getCount() {

        return ViwabilityFragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ViwabilityFragmentListTitles.get(position);
    }

    public void AddFragments (Fragment fragment, String title){
        ViwabilityFragmentList.add(fragment);
        ViwabilityFragmentListTitles.add(title);
    }
}
