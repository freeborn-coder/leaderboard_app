package com.gads2020.leaderboardapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.gads2020.leaderboardapp.Models.TopLearnerData;
import com.gads2020.leaderboardapp.Models.TopScorerData;
import com.gads2020.leaderboardapp.Models.UserData;
import com.gads2020.leaderboardapp.Services.DownloadService;
import com.gads2020.leaderboardapp.Services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gads2020.leaderboardapp.Constants.TOP_LEARNERS_FRAGMENT;

public class LeaderBoardFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);

        int fragmentType = this.getArguments().getInt("type");

        if(this.getArguments() != null){
            getUsersList(fragmentType);
        }

    }

    private void getUsersList(int fragmentType) {
        DownloadService downloadService = ServiceBuilder.buildService(DownloadService.class);

        if(fragmentType == TOP_LEARNERS_FRAGMENT) {
            downloadService.getLearningLeaders().enqueue(new Callback<List<TopLearnerData>>() {
                @Override
                public void onResponse(Call<List<TopLearnerData>> call, Response<List<TopLearnerData>> response) {
                    if(response.isSuccessful()) {
                        Log.d(getContext().getPackageName(), "onResponse: "+response.body().toString());
                        LearnersRecyclerAdapter recyclerAdapter = new LearnersRecyclerAdapter(new ArrayList<UserData>(response.body()));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(recyclerAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<TopLearnerData>> call, Throwable t) {

                }
            });

        }else{

            downloadService.getTopScorers().enqueue(new Callback<List<TopScorerData>>() {
                @Override
                public void onResponse(Call<List<TopScorerData>> call, Response<List<TopScorerData>> response) {
                    if(response.isSuccessful()) {
                        Log.d(getContext().getPackageName(), "onResponse: "+response.body().toString());
                        LearnersRecyclerAdapter recyclerAdapter = new LearnersRecyclerAdapter(new ArrayList<UserData>(response.body()));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(recyclerAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<TopScorerData>> call, Throwable t) {

                }
            });

        }
    }
}
