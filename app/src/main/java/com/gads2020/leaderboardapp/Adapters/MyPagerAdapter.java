package com.gads2020.leaderboardapp.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gads2020.leaderboardapp.LeaderBoardFragment;

import java.util.ArrayList;

import static com.gads2020.leaderboardapp.Constants.TOP_LEARNERS_FRAGMENT;
import static com.gads2020.leaderboardapp.Constants.TOP_SCORERS_FRAGMENT;

public class MyPagerAdapter extends FragmentPagerAdapter {

    ArrayList<LeaderBoardFragment> fragments;
    ArrayList<String> fragmentTitles;

    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        // Create and add fragments to fragment list
        fragments = new ArrayList<>();
        fragments.add(new LeaderBoardFragment(TOP_LEARNERS_FRAGMENT));
        fragments.add(new LeaderBoardFragment(TOP_SCORERS_FRAGMENT));

        fragmentTitles = new ArrayList<>();
        fragmentTitles.add("LEARNING LEADERS");
        fragmentTitles.add("SKILL IQ LEADERS");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
