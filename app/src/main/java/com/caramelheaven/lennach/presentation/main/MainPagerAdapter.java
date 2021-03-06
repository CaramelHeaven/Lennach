package com.caramelheaven.lennach.presentation.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.thread.ThreadFragment;

/**
 * Created by CaramelHeaven on 22:06, 07/12/2018.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return BoardFragment.newInstance("b", 0);
            case 1:
                return ThreadFragment.newInstance("", "", 1);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
