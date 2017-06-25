package com.example.tzapt.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tzapt.fragments.AllBookingsFragment;
import com.example.tzapt.fragments.TodayAllBookingsFragment;
import com.example.tzapt.fragments.TodayBookingsFragment;

/**
 * Created by tzapt on 6/24/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TodayBookingsFragment();
            case 1:
                return new AllBookingsFragment();
            case 2:
                return new TodayAllBookingsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
