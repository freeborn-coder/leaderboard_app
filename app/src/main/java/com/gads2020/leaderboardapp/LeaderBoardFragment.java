package com.gads2020.leaderboardapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.gads2020.leaderboardapp.Adapters.LearnersRecyclerAdapter;
import com.gads2020.leaderboardapp.Models.UserData;


import static com.gads2020.leaderboardapp.Constants.TOP_LEARNERS_FRAGMENT;

public class LeaderBoardFragment extends Fragment implements DownloadCallback {

    private RecyclerView recyclerView;
    private LearnersRecyclerAdapter recyclerAdapter;
    int fragmentType;

    public LeaderBoardFragment(int fragmentType) {
        this.fragmentType = fragmentType;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        getUsersList(fragmentType);
    }

    private void getUsersList(int fragmentType) {

        if(fragmentType == TOP_LEARNERS_FRAGMENT) {
            APIUtils.getTopLearners(this);
        }else{
            APIUtils.getTopScorers(this);
        }

    }

    @Override
    public void onDownloadComplete(ArrayList<UserData> response) {
        recyclerAdapter = new LearnersRecyclerAdapter(response);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onDownloadFailed(Throwable t) {
        Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
