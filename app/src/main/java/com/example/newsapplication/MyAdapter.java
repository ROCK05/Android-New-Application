package com.example.newsapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapplication.fragments.businessFragment;
import com.example.newsapplication.fragments.entertainmentFragment;
import com.example.newsapplication.fragments.healthFragment;
import com.example.newsapplication.fragments.homeFragment;
import com.example.newsapplication.fragments.scienceFragment;
import com.example.newsapplication.fragments.sportsFragment;
import com.example.newsapplication.fragments.technologyFragment;
import com.example.newsapplication.fragments.worldFragment;

public class MyAdapter extends FragmentPagerAdapter {

    int tabcount;

    public MyAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new homeFragment();
            case 1:
                return new healthFragment();
            case 2:
                return new entertainmentFragment();
            case 3:
                return new scienceFragment();
            case 4:
                return new businessFragment();
            case 5:
                return new sportsFragment();
            case 6:
                return new technologyFragment();
            case 7:
                return new worldFragment();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
