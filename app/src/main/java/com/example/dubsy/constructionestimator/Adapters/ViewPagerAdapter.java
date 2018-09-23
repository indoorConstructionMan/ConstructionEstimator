package com.example.dubsy.constructionestimator.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dubsy.constructionestimator.Fragments.FragmentAnalytics;
import com.example.dubsy.constructionestimator.Fragments.FragmentBoard;
import com.example.dubsy.constructionestimator.Fragments.FragmentEmployee;
import com.example.dubsy.constructionestimator.Fragments.FragmentTimer;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public ViewPagerAdapter(FragmentManager manager, int tabCount) {
        super(manager);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentAnalytics();
            case 1:
                return new FragmentTimer();
            case 2:
                return new FragmentBoard();
            case 3:
                return new FragmentEmployee();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabCount;
    }

}