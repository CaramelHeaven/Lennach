package com.caramelheaven.lennach.ui.welcome.container.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.caramelheaven.lennach.ui.welcome.container.FragmentFirst;
import com.caramelheaven.lennach.ui.welcome.container.FragmentSecond;

public class WelcomePagerAdapter extends FragmentPagerAdapter {

    private static final int ITEMS = 2;

    public WelcomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return FragmentFirst.newInstance();
            case 1:
                return FragmentSecond.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return ITEMS;
    }
}
