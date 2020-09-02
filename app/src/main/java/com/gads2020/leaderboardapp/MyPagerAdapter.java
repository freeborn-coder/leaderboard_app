package com.gads2020.leaderboardapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import static com.gads2020.leaderboardapp.Constants.TOP_LEARNERS_FRAGMENT;
import static com.gads2020.leaderboardapp.Constants.TOP_SCORERS_FRAGMENT;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        int fragmentType = 0;
        if(position == 0){
            fragmentType = TOP_LEARNERS_FRAGMENT;
        }else{
            fragmentType = TOP_SCORERS_FRAGMENT;
        }
        args.putInt("type",fragmentType);
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0? "Learning Leaders":"Skill IQ Leaders";
    }
}
